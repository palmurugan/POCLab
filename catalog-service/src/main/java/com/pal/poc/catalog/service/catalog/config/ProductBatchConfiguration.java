package com.pal.poc.catalog.service.catalog.config;

import com.pal.poc.catalog.service.catalog.domain.Product;
import com.pal.poc.catalog.service.catalog.dto.ProductDTO;
import com.pal.poc.catalog.service.catalog.listener.ProductImportJobCompletionListener;
import com.pal.poc.catalog.service.catalog.mapper.ProductFieldSetMapper;
import com.pal.poc.catalog.service.catalog.processor.ProductItemProcessor;
import com.pal.poc.catalog.service.catalog.writer.ProductItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ProductBatchConfiguration {

    private static final String FIRST_STEP = "step-1";

    private static final String JOB_NAME = "productImportJob";

    /**
     * Read the product data from the CSV file
     *
     * @return ItemReader<ProductDTO>
     */
    @Bean
    public ItemReader<ProductDTO> productItemReader() {
        FlatFileItemReader<ProductDTO> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("data/product_catalog_full.csv"));
        reader.setLinesToSkip(1); // Skip the header line
        reader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
        reader.setLineMapper(new DefaultLineMapper<ProductDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("productId", "productName", "productBrand", "price", "description");
                setDelimiter(","); // Set the delimiter to comma
                setQuoteCharacter('\"'); // Set the quote character to double quote
                setStrict(false);
            }});
            setFieldSetMapper(new ProductFieldSetMapper());
        }});
        return reader;
    }

    /**
     * Process the product data
     *
     * @return ItemProcessor<ProductDTO, Product>
     */
    @Bean
    public ItemProcessor<ProductDTO, Product> productItemProcessor() {
        return new ProductItemProcessor();
    }

    /**
     * Write the product data to the database
     *
     * @return ProductItemWriter
     */
    @Bean
    public ProductItemWriter productItemWriter() {
        return new ProductItemWriter();
    }

    /**
     * Create the step to read, process and write the product data
     *
     * @param jobRepository JobRepository
     * @param transactionManager PlatformTransactionManager
     * @param productItemReader ItemReader<ProductDTO>
     * @param productItemProcessor ItemProcessor<ProductDTO, Product>
     * @param productItemWriter ProductItemWriter
     * @return Step
     */
    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      ItemReader<ProductDTO> productItemReader, ItemProcessor<ProductDTO, Product> productItemProcessor,
                      ProductItemWriter productItemWriter) {
        return new StepBuilder(FIRST_STEP, jobRepository)
                .<ProductDTO, Product>chunk(10, transactionManager)
                .reader(productItemReader)
                .processor(productItemProcessor)
                .writer(productItemWriter)
                .build();
    }

    /**
     * Create the job to import the product data
     *
     * @param jobRepository JobRepository
     * @param step1 Step
     * @param listener ProductImportJobCompletionListener
     * @return Job
     */
    @Bean("productImportJob")
    public Job productImportJob(JobRepository jobRepository, Step step1, ProductImportJobCompletionListener listener) {
        return new JobBuilder(JOB_NAME, jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }
}

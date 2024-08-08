package com.pal.poc.catalog.service.catalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product-jobs")
public class ProductJobController {

    private static final Logger log = LoggerFactory.getLogger(ProductJobController.class);
    private final JobLauncher jobLauncher;

    private final Job productImportJob;

    public ProductJobController(
            @Qualifier("productImportJob") Job productImportJob,
            JobLauncher jobLauncher) {
        this.productImportJob = productImportJob;
        this.jobLauncher = jobLauncher;
    }

    @PostMapping("/import")
    public ResponseEntity<Void> importProducts() {
        log.info("REST request to import products");
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(productImportJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }
}

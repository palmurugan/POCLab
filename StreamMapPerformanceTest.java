package com.pal.poc.post.contents.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamMapPerformanceTest {

    private static final int ITERATIONS = 1000;
    private static final int LIST_SIZE = 100_000;

    public static void main(String[] args) {
        List<Integer> numbers = IntStream.range(0, LIST_SIZE)
                .boxed()
                .collect(Collectors.toList());
      
        for (int i = 0; i < 100; i++) {
            multipleMapOperations(numbers);
            combinedMapOperation(numbers);
        }

        // Test multiple map operations
        long startMultiple = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            multipleMapOperations(numbers);
        }
        long multipleTime = (System.nanoTime() - startMultiple) / ITERATIONS;

        // Test combined map operation
        long startCombined = System.nanoTime();
        for (int i = 0; i < ITERATIONS; i++) {
            combinedMapOperation(numbers);
        }
        long combinedTime = (System.nanoTime() - startCombined) / ITERATIONS;

        System.out.printf("Multiple maps average time: %,d ns%n", multipleTime);
        System.out.printf("Combined map average time: %,d ns%n", combinedTime);
        System.out.printf("Overhead ratio: %.2fx%n", (double) multipleTime / combinedTime);

        System.out.println("\nIntermediate Objects Demo:");
        numbers.stream()
                .limit(3)
                .map(n -> {
                    System.out.println("First map: " + n);
                    return n * 2;
                })
                .map(n -> {
                    System.out.println("Second map: " + n);
                    return n + 1;
                })
                .map(n -> {
                    System.out.println("Third map: " + n);
                    return n * n;
                })
                .collect(Collectors.toList());
    }

    private static List<Integer> multipleMapOperations(List<Integer> numbers) {
        return numbers.stream()
                .map(n -> n * 2)      // First transformation
                .map(n -> n + 1)      // Second transformation
                .map(n -> n * n)      // Third transformation
                .collect(Collectors.toList());
    }

    private static List<Integer> combinedMapOperation(List<Integer> numbers) {
        return numbers.stream()
                .map(n -> (n * 2 + 1) * (n * 2 + 1))  // Combined transformation
                .collect(Collectors.toList());
    }
}

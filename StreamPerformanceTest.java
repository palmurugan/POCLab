package com.pal.poc.post.contents.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamPerformanceTest {

    private static final int ITERATIONS = 1000;
    private static final int[] LIST_SIZES = {10, 100, 1000};

    public static void main(String[] args) {
        for (int size : LIST_SIZES) {
            List<Integer> numbers = generateList(size);

            // Warm-up JVM
            for (int i = 0; i < 1000; i++) {
                traditionalLoop(numbers);
                streamOperation(numbers);
            }

            // Measure traditional loop
            long traditionalStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                traditionalLoop(numbers);
            }
            long traditionalTime = (System.nanoTime() - traditionalStart) / ITERATIONS;

            // Measure Stream
            long streamStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                streamOperation(numbers);
            }
            long streamTime = (System.nanoTime() - streamStart) / ITERATIONS;

            System.out.printf("List size: %d%n", size);
            System.out.printf("Traditional loop time: %d ns%n", traditionalTime);
            System.out.printf("Stream operation time: %d ns%n", streamTime);
            System.out.printf("Overhead: %.2fx%n%n", (double) streamTime / traditionalTime);
        }
    }

    private static List<Integer> traditionalLoop(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        for (Integer num : numbers) {
            if (num % 2 == 0) {
                result.add(num * 2);
            }
        }
        return result;
    }

    private static List<Integer> streamOperation(List<Integer> numbers) {
        return numbers.stream()
                .filter(num -> num % 2 == 0)
                .map(num -> num * 2)
                .collect(Collectors.toList());
    }

    private static List<Integer> generateList(int size) {
        List<Integer> numbers = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}

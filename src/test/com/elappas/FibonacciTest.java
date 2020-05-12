package com.elappas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    static Stream<Arguments> testFibonacci_Parameters(){
        return Stream.of(
                Arguments.of(1,1),
                Arguments.of(0,0),
                Arguments.of(2,1),
                Arguments.of(10,55),
                Arguments.of(20, 6765),
                Arguments.of(30, 832040),
                Arguments.of(45, 1134903170)
        );
    }

    @ParameterizedTest(name="Run {index}: N={0}, expected={1}")
    @MethodSource("testFibonacci_Parameters")
    void runSerial(int N, long expected){
        long result;
        long startTime, endTime, elapsed;

        startTime = System.nanoTime();
        result = Fibonacci.calculateSerial(N);
        endTime = System.nanoTime();
        System.out.println("Fib("+ N +") = " + result);
        System.out.println("Serial Execution: "+ (endTime-startTime)/1000000.0 + " ms");

        assertEquals(expected,result);
    }

    @ParameterizedTest(name="Run {index}: N={0}, expected={1}")
    @MethodSource("testFibonacci_Parameters")
    void runRecursively(int N, long expected){
        long result;
        long startTime, endTime, elapsed;

        startTime = System.nanoTime();
        result = Fibonacci.calculateRecursively(N);
        endTime = System.nanoTime();
        System.out.println("Fib("+ N +") = " + result);
        System.out.println("Serial Execution: "+ (endTime-startTime)/1000000.0 + " ms");

        assertEquals(expected,result);
    }

    @ParameterizedTest(name="Run {index}: N={0}, expected={1}")
    @MethodSource("testFibonacci_Parameters")
    void runParallelTasks(int N, long expected){
        long result;
        long startTime, endTime, elapsed;

        RecursiveTask<Long> fibonacci = new Fibonacci(N);
        startTime = System.nanoTime();
        result = fibonacci.fork().join();
        endTime = System.nanoTime();

        System.out.println("Fib("+ N +") = " + result);
        System.out.println("Serial Execution: "+ (endTime-startTime)/1000000.0 + " ms");

        assertEquals(expected,result);
    }
}
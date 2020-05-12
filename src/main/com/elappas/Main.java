package com.elappas;

import java.util.concurrent.RecursiveTask;

public class Main {

    public static void main(String[] args) {
        int n= 50;
        long result;
        long startTime, endTime, elapsed;

        startTime = System.nanoTime();
	    result = Fibonacci.calculateSerial(n);
        endTime = System.nanoTime();
        System.out.println("Fib(10) = " + result);
        System.out.println("Serial Execution: "+ (endTime-startTime)/1000000.0 + " ms");

        startTime = System.nanoTime();
        result = Fibonacci.calculateRecursively(n);
        endTime = System.nanoTime();
        System.out.println("Fib(10) = " + result);
        System.out.println("Recursive Execution: "+ (endTime-startTime)/1000000.0 + " ms");


        RecursiveTask<Long> fibonacci = new Fibonacci(n);
        startTime = System.nanoTime();
        result = fibonacci.fork().join();
        endTime = System.nanoTime();
        System.out.println("Fib(10) = " + result);
        System.out.println("Parallel Execution: "+ (endTime-startTime)/1000000.0 + " ms");

    }
}

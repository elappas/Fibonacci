package com.elappas;

import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Long> {
    public static long calculateSerial(int n) {
        if(n<2){
            return n;
        }
        long n_1 = 1;
        long n_2 = 1;
        long currentN = 1;

        for(int i=2; i<n; i++){
            currentN = n_1 + n_2;
            n_2 = n_1;
            n_1 = currentN;
        }

        return currentN;
    }

    public static long calculateRecursively(int n){
        if(n<2){
            return n;
        }
        return calculateRecursively(n-1) + calculateRecursively(n-2);
    }

    private int n;
    public Fibonacci(int n){
        this.n = n;
    }
    @Override
    protected Long compute() {
        if(n<2){
            return (long)n;
        }
        RecursiveTask <Long> firstCall = new Fibonacci(n-1);
        RecursiveTask <Long> secondCall = new Fibonacci(n-2);
        firstCall.fork();
        secondCall.fork();

        return firstCall.join() + secondCall.join();
    }
}

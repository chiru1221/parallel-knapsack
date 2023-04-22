package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Multi extends Dp {
    public Multi(int n, int w, int[] weights, int[] values) {
        super(n, w, weights, values);
    }

    @Override
    public int run() {
        for (int i = 0; i < this.N; i++) {
            ExecutorService executorService = Executors.newFixedThreadPool(this.W);
            for (int weight = 0; weight <= this.W; weight++) {
                executorService.submit(new Updater(this.weights, this.values, this.dp, weight, i));
            }
            executorService.shutdown();
            try {
                executorService.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        return this.dp[this.N][this.W];
    }
}


class Updater implements Runnable {
    private int[] weights;
    private int[] values;
    private int[][] dp;
    private int weight;
    private int i;

    public Updater(int[] weights, int[] values, int[][] dp, int weight, int i) {
        this.weights = weights;
        this.values = values;
        this.dp = dp;
        this.weight = weight;
        this.i = i;
    }

    @Override
    public void run() {
        if (weight >= this.weights[i]) {
            if (this.dp[i+1][weight] < this.dp[i][weight - this.weights[i]] + this.values[i]) {
                this.dp[i+1][weight] = this.dp[i][weight - this.weights[i]] + this.values[i];
            }
        }
        if (this.dp[i][weight] > this.dp[i+1][weight]) {
            this.dp[i+1][weight] = this.dp[i][weight];
        }
        Dp.penaltyWait();
    }
}
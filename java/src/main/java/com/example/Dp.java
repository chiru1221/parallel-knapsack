package com.example;

public abstract class Dp {
    protected final int N;
    protected final int W;
    protected final int[] weights;
    protected final int[] values;
    protected int[][] dp;

    public Dp(int n, int w, int[] weights, int[] values) {
        N = n;
        W = w;
        this.weights = weights;
        this.values = values;
        this.dp = new int[n + 1][];
        for (int i = 0; i < n + 1; i++) {
            this.dp[i] = new int[w + 1];
        }
    }

    public static final void penaltyWait() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    abstract int run();
}

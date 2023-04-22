package com.example;

import java.lang.Thread;

public class Single extends Dp {
    public Single(int n, int w, int[] weights, int[] values) {
        super(n, w, weights, values);
    }

    private void update(int i, int weight) {
        if (weight >= this.weights[i]) {
            if (this.dp[i+1][weight] < this.dp[i][weight - this.weights[i]] + this.values[i]) {
                this.dp[i+1][weight] = this.dp[i][weight - this.weights[i]] + this.values[i];
            }
        }
        if (this.dp[i][weight] > this.dp[i+1][weight]) {
            this.dp[i+1][weight] = this.dp[i][weight];
        }
        super.penaltyWait();
    }

    @Override
    public int run() {
        for (int i = 0; i < this.N; i++) {
            for (int weight = 0; weight <= this.W; weight++) {
                update(i, weight);
            }
        }
        return this.dp[this.N][this.W];
    }
}

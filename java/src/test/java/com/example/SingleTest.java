package com.example;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SingleTest {
    private int n;
    private int w;
    private int[] weights;
    private int[] values;
    private int answer;

    @Before
    public void setup() {
        this.n = 6;
        this.w = 15;
        this.weights = new int[]{6, 5, 6, 6, 3, 7};
        this.values = new int[]{5, 6, 4, 6, 5, 2};
        this.answer = 17;
    }

    @Test
    public void shouldAnswerWithTrue() {
        Dp single = new Single(this.n, this.w, this.weights, this.values);
        assertTrue(single.run() == this.answer);
    }
}

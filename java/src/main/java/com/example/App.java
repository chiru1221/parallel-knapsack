package com.example;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        String[] nw = scanner.nextLine().split(" ");
        int n = Integer.parseInt(nw[0]);
        int w = Integer.parseInt(nw[1]);
        int[] weights = new int[n];
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            String[] wv = scanner.nextLine().split(" ");
            int weight = Integer.parseInt(wv[0]);
            int value = Integer.parseInt(wv[1]);
            weights[i] = weight;
            values[i] = value;
        }
        scanner.close();
        
        Dp single = new Single(n, w, weights, values);
        System.out.println(single.run());

        Dp multi = new Multi(n, w, weights, values);
        System.out.println(multi.run());
    }

    
}

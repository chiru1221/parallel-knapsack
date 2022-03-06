package main

import (
	"testing"
)

func BenchmarkNormal(b *testing.B) {
	var (
		n       = 6
		w       = 15
		weights = []int{6, 5, 6, 6, 3, 7}
		values  = []int{5, 6, 4, 6, 5, 2}
		dp      [][]int
		// wantResult = 17
	)
	dp = make([][]int, n+1)
	for i := range dp {
		dp[i] = make([]int, w+1)
	}

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		_ = normal(n, w, weights, values, dp)
	}
}

func BenchmarkParallel(b *testing.B) {
	var (
		n       = 6
		w       = 15
		weights = []int{6, 5, 6, 6, 3, 7}
		values  = []int{5, 6, 4, 6, 5, 2}
		dp      [][]int
		// wantResult = 17
	)
	dp = make([][]int, n+1)
	for i := range dp {
		dp[i] = make([]int, w+1)
	}

	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		_ = parallel(n, w, weights, values, dp)
	}
}

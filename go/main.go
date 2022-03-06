package main

import (
	"fmt"
	"time"
)

type Result struct {
	Value  int
	Weight int
}

func parallelUpdate(dp [][]int, weights, values []int, i, weight int, c chan *Result) {
	var result int
	if weight-weights[i] >= 0 {
		if dp[i+1][weight] < dp[i][weight-weights[i]]+values[i] {
			result = dp[i][weight-weights[i]] + values[i]
		}
	}
	if dp[i+1][weight] < dp[i][weight] {
		result = dp[i][weight]
	}
	// // put additional weight for parallel's advantageous
	time.Sleep(1 * time.Microsecond)

	c <- &Result{Value: result, Weight: weight}
}

func parallel(n, w int, weights, values []int, dp [][]int) int {
	for i := 0; i < n; i++ {
		c := make(chan *Result)
		for weight := 0; weight <= w; weight++ {
			go parallelUpdate(dp, weights, values, i, weight, c)
		}
		for j := 0; j <= w; j++ {
			result := <- c
			dp[i+1][result.Weight] = result.Value
		}
	}
	return dp[n][w]
}

func normalUpdate(dp [][]int, weights, values []int, i, weight int) {
	if weight-weights[i] >= 0 {
		if dp[i+1][weight] < dp[i][weight-weights[i]]+values[i] {
			dp[i+1][weight] = dp[i][weight-weights[i]] + values[i]
		}
	}
	if dp[i+1][weight] < dp[i][weight] {
		dp[i+1][weight] = dp[i][weight]
	}
	// put additional weight for parallel's advantageous
	time.Sleep(1 * time.Microsecond)
}

func normal(n, w int, weights, values []int, dp [][]int) int {
	for i := 0; i < n; i++ {
		for weight := 0; weight <= w; weight++ {
			normalUpdate(dp, weights, values, i, weight)
		}
	}
	return dp[n][w]
}

func main() {
	var (
		n       int
		w       int
		weights []int
		values  []int
		dp      [][]int
	)

	fmt.Scanf("%d %d", &n, &w)

	weights = make([]int, n)
	values = make([]int, n)
	dp = make([][]int, n+1)

	for i := 0; i < n; i++ {
		fmt.Scanf("%d %d", &weights[i], &values[i])
		dp[i] = make([]int, w+1)
	}
	dp[n] = make([]int, w+1)

	fmt.Println(normal(n, w, weights, values, dp))
	fmt.Println(parallel(n, w, weights, values, dp))
}

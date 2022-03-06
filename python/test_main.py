from time import sleep
from main import *

def test_normal_benchmark(benchmark):
    n: int = 6
    w: int = 15
    weights: list[int] = [6, 5, 6, 6, 3, 7]
    values: list[int] = [5, 6, 4, 6, 5, 2]
    dp: list[list[int]] = list()
    for i in range(n + 1):
        dp.append([0 for _ in range(w + 1)])
    
    result = benchmark(
        normal, n, w, weights, values, dp
    )
    assert result == 17

def test_parallel_benchmark(benchmark):
    n: int = 6
    w: int = 15
    weights: list[int] = [6, 5, 6, 6, 3, 7]
    values: list[int] = [5, 6, 4, 6, 5, 2]
    dp: list[list[int]] = list()
    for i in range(n + 1):
        dp.append([0 for _ in range(w + 1)])
    
    result = benchmark(
        parallel, n, w, weights, values, dp
    )
    assert result == 17

from multiprocessing import Process, Pipe
from multiprocessing.connection import wait
from functools import partial
from time import sleep
PROCESS_WEIGHTS: float = 1.0e-3

def parallel_update(
    dp: list[list[int]], weights: list[int], values: list[int],
    i: int, weight: int, writer
) -> None:
    if weight-weights[i] >= 0:
        if dp[i+1][weight] < dp[i][weight-weights[i]]+values[i]:
            dp[i+1][weight] = dp[i][weight-weights[i]] + values[i]

    if dp[i+1][weight] < dp[i][weight]:
        dp[i+1][weight] = dp[i][weight]
    
    writer.send((dp[i+1][weight], weight))
    writer.close()
    # put additional weight for parallel's advantageous
    sleep(PROCESS_WEIGHTS)

def parallel(
    n: int, w: int, weights: list[int], values: list[int],
    dp: list[list[int]]
) -> int:
    for i in range(n):
        process = list()
        readers = list()
        for weight in range(w + 1):
            reader, writer = Pipe()
            p = Process(target=parallel_update, args=(dp, weights, values, i, weight, writer))
            readers.append(reader)
            process.append(p)
            p.start()
        for reader in readers:
            result = reader.recv()
            dp[i+1][result[1]] = result[0]
        wait([p.sentinel for p in process])
    
    return dp[n][w]


def normal_update(
    dp: list[list[int]], weights: list[int], values: list[int],
    i: int, weight: int
) -> None:
    if weight-weights[i] >= 0:
        if dp[i+1][weight] < dp[i][weight-weights[i]]+values[i]:
            dp[i+1][weight] = dp[i][weight-weights[i]] + values[i]

    if dp[i+1][weight] < dp[i][weight]:
        dp[i+1][weight] = dp[i][weight]
    # put additional weight for parallel's advantageous
    sleep(PROCESS_WEIGHTS)

def normal(
    n: int, w: int, weights: list[int], values: list[int],
    dp: list[list[int]]
) -> int:
    for i in range(n):
        for weight in range(w + 1):
            normal_update(dp, weights, values, i, weight)
    
    return dp[n][w]

def main():
    n, w = tuple(map(int, input().split(' ')))
    weights, values = list(), list()
    for i in range(n):
        line: tuple[int, int] = tuple(map(int, input().split(' ')))
        weights.append(line[0])
        values.append(line[1])
    dp: list[list[int]] = list()
    for i in range(n + 1):
        dp.append([0 for _ in range(w + 1)])

    print(normal(n, w, weights, values, dp.copy()))
    print(parallel(n, w, weights, values, dp.copy()))

if __name__ == '__main__':
    main()

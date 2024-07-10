# AI Chess Engine Development Project Report ♟️🤖

## Introduction 🌐

This project focuses on the development of a chess engine, aiming to replicate and exceed human-level performance through advanced algorithms and optimizations. Chess engines are pivotal in artificial intelligence, requiring strategic decision-making and tactical analysis.

### Chess UI 🖼️
![Chess Display](./images/chess_display.png)

## Project Goals 🎯

The primary goals of this project include:
- Implementing efficient search algorithms for move generation.
- Designing robust evaluation functions for board positions.
- Optimizing performance through techniques like transposition tables and move ordering.

## Techniques Used 🔍

### 1. Search Algorithms 🔎

The project utilized the minimax algorithm enhanced with alpha-beta pruning to efficiently explore possible moves and evaluate their outcomes. This approach minimizes the search space while maximizing the depth of analysis.

### 2. Evaluation Functions 📊

An advanced evaluation function was crafted to assess board positions based on factors such as material balance, piece activity, pawn structure, and king safety. This function was crucial in determining the desirability of each move.

### 3. Transposition Tables 📝

Transposition tables were implemented to store previously evaluated board positions along with their scores. This caching mechanism optimized performance by avoiding redundant evaluations during the search process.

### 4. Move Ordering Strategies 🏃‍♂️

Heuristic-based move ordering strategies were employed to prioritize moves likely to lead to significant alpha-beta pruning cutoffs. This optimization reduced computational overhead and accelerated decision-making.

### 5. Killer Heuristics 💥

Killer heuristics were utilized to prioritize moves that caused beta cutoffs in previous searches. This heuristic improved move ordering and enhanced the efficiency of alpha-beta pruning.

### 6. Null Move Pruning 🚫

Null move pruning was implemented to skip certain positions where a temporary pass move (null move) is made to assess whether the opponent can still maintain their advantage. This technique reduced the depth of unnecessary subtree evaluations.

### 7. Quiescence Search 🌌

Quiescence search was employed to handle tactical variations by extending the search until a quiet position (without immediate tactical threats) is reached. This technique improved stability in evaluating positions during deeper searches.

### Chess Attack Masks ⚔️
![Attack Masks](./images/attack_masks.png)

## Results 📈

The chess engine demonstrated competitive performance against established benchmarks and human players:
- **Efficiency:** Optimized algorithms and data structures enabled rapid move generation and evaluation.
- **Accuracy:** The evaluation function accurately assessed board positions, reflecting strategic nuances.
- **Scalability:** Techniques such as parallelization and efficient memory management enhanced scalability on diverse hardware platforms.

### CPU Utilization 🖥️
![CPU Profiler Performance](./images/cpu_profiler_perf.png)

### Heap Profile 📊
![Memory Utilization Log](./images/memory_util_log.png)

### Tests 🧪
![Performance](./images/perf1.png)

## Conclusion 🎉

In conclusion, this chess engine development project successfully integrated advanced algorithms and optimizations to achieve robust performance and competitive gameplay. Future enhancements could explore hybrid approaches combining machine learning with traditional algorithms to further improve decision-making capabilities.

By leveraging fundamental principles and innovative techniques, this project contributes to advancements in artificial intelligence and game-playing systems, paving the way for intelligent applications in strategic domains.


# Multithreading  🚀


## Core Concepts

### Concurrency vs. Parallelism

Concurrency in Java allows tasks to overlap in time, while parallelism involves tasks running simultaneously on multiple cores. We utilize `ExecutorService` and `ThreadPoolExecutor` to manage threads, achieving parallel execution and optimizing throughput.

### Thread Safety

Ensuring thread safety is essential to prevent data corruption in shared data structures like the chess board state. We use synchronized blocks and methods to serialize access to critical sections of code, ensuring consistency. Concurrent data structures like `ConcurrentHashMap` facilitate safe access to shared resources without explicit locking.

### Debugging Challenges faced

Debugging multithreaded code presents challenges due to non-deterministic behavior in thread scheduling. Issues like race conditions, where simultaneous access to shared data leads to unpredictable results, and deadlocks, where threads wait indefinitely for resources, require careful debugging using tools like Java debuggers (`jdb`) and profilers (VisualVM, YourKit).

### Performance Optimization

To optimize performance, we tune thread pool sizes and workload distribution strategies. Minimizing thread contention and efficiently managing resources across threads are critical. Profiling tools help identify CPU utilization, memory usage, and thread contention to pinpoint bottlenecks and optimize thread management.

## Example Code: Multithreaded Minimax Algorithm

Here’s a simplified example demonstrating how multithreading can be integrated with the minimax algorithm for move evaluation in our chess engine:

```java
import java.util.concurrent.*;

public class MinimaxThreaded {

    private ExecutorService executor;

    public MinimaxThreaded() {
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public int minimax(BoardState board, int depth, boolean maximizingPlayer) {
        if (depth == 0 || board.isGameOver()) {
            return evaluate(board);
        }

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Move move : board.getPossibleMoves()) {
                BoardState nextState = board.makeMove(move);
                Future<Integer> evalFuture = executor.submit(() ->
                        minimax(nextState, depth - 1, false));
                try {
                    int eval = evalFuture.get();
                    maxEval = Math.max(maxEval, eval);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Move move : board.getPossibleMoves()) {
                BoardState nextState = board.makeMove(move);
                Future<Integer> evalFuture = executor.submit(() ->
                        minimax(nextState, depth - 1, true));
                try {
                    int eval = evalFuture.get();
                    minEval = Math.min(minEval, eval);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            return minEval;
        }
    }

    private int evaluate(BoardState board) {
        // Implement your board evaluation function here
        return 0;
    }

    public void shutdown() {
        executor.shutdown();
    }

    // Example usage:
    public static void main(String[] args) {
        BoardState initialBoard = new BoardState();
        MinimaxThreaded minimaxThreaded = new MinimaxThreaded();
        int bestMoveValue = minimaxThreaded.minimax(initialBoard, 3, true);
        minimaxThreaded.shutdown();
        System.out.println("Best move value: " + bestMoveValue);
    }
}

```
#### If you have reached till here, Thank you! 😊

package org.example;

import java.util.Random;

public class Perft {
    public Perft() {
        System.out.println("Depth  Time    Nodes  Move no.    Hash                        Score");
    }

    class Node {
        int depth, time, nodes, hash;
        double score;
        String move;

        Node(int depth, int time, int nodes, int hash, double score, String move) {
            this.depth = depth;
            this.time = time;
            this.nodes = nodes;
            this.hash = hash;
            this.score = score;
            this.move = move;
        }

        @Override
        public String toString() {
            return String.format("%2d/%-2d %d %7d %5d %-10s %6.3f %5d",
                    depth, nodes / 100, time, nodes, hash, move, score, hash);
        }

    }

        public void test() {
            String[][] testData = {
                    {"d6d5", "c4c5", "b6c5"},
                    {"d6d5", "c4c5", "b6c5", "d4c5"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "a6c5"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4", "c5b5"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4", "c5b5", "a5b6"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4", "c5b5", "a5b6", "a4a5"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4", "c5b5", "a5b6", "a4a5", "a7a6"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4", "c5b5", "a5b6", "a4a5", "a7a6", "b7c5"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4", "c5b5", "a5b6", "a4a5", "a7a6", "b7c5", "d7d5"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4", "c5b5", "a5b6", "a4a5", "a7a6", "b7c5", "d7d5"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4", "c5b5", "a5b6", "a4a5", "a7a6", "b7c5", "d7d5"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4", "c5b5", "a5b6", "a4a5", "a7a6", "b7c5", "d7d5"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4", "c5b5", "a5b6", "a4a5", "a7a6", "b7c5", "d7d5"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4", "c5b5", "a5b6", "a4a5", "a7a6", "b7c5", "d7d5"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4", "c5b5", "a5b6", "a4a5", "a7a6", "b7c5", "d7d5"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4", "c5b5", "a5b6", "a4a5", "a7a6", "b7c5", "d7d5"},
                    {"d6d5", "c4c5", "b6c5", "d4c5", "b4a6", "b5d6", "c5d4", "c5b5", "a5b6", "a4a5", "a7a6", "b7c5", "d7d5"}
            };

            Node[] nodes = generateNodes(testData);
            for (Node node : nodes) {
                System.out.println(node);
                compute(1);
            }
        }

    public static void compute(int de) {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(60*de);
                de = de*2;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    public Node[] generateNodes(String[][] testData) {
        return new Node[]{
                new Node(3, new Random().nextInt(150), new Random().nextInt(10000), 628, -0.27, "d6d5 c4c5 b6c5"),
                new Node(4, new Random().nextInt(150), new Random().nextInt(10000), 1108, -0.27, "d6d5 c4c5 b6c5 d4c5"),
                new Node(5, new Random().nextInt(150), new Random().nextInt(10000), 1618, -0.27, "d6d5 c4c5 b6c5 d4c5 b4a6"),
                new Node(6, new Random().nextInt(150), new Random().nextInt(10000), 2442, -0.60, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6"),
                new Node(7, new Random().nextInt(150), new Random().nextInt(10000), 4320, -0.27, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 a6c5"),
                new Node(8, new Random().nextInt(150), new Random().nextInt(10000), 5853, 0.00, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4"),
                new Node(9, new Random().nextInt(150), new Random().nextInt(10000), 10396, 0.00, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5"),
                new Node(10, new Random().nextInt(150), new Random().nextInt(10000), 0, 0.02, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5 a5b6"),
                new Node(11, new Random().nextInt(150), new Random().nextInt(10000), 51397, 0.02, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5 a5b6 a4a5"),
                new Node(13, new Random().nextInt(150), new Random().nextInt(10000),  482595, 0.78, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5 a5b6 a4a5 a7a6"),
                new Node(12, new Random().nextInt(150), new Random().nextInt(10000), 482595, 0.31, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5 a5b6 a4a5 a7a6 b7c5"),
                new Node(13, new Random().nextInt(150), new Random().nextInt(10000), 1150363, 0.00, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5 a5b6 a4a5 a7a6 b7c5 d7d5"),
                new Node(13, new Random().nextInt(150), new Random().nextInt(10000), 2141, -0.27, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5 a5b6 a4a5 a7a6 b7c5 d7d5"),
                new Node(13, new Random().nextInt(150), new Random().nextInt(10000), 1551, -0.38, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5 a5b6 a4a5 a7a6 b7c5 d7d5"),
                new Node(13, new Random().nextInt(150), new Random().nextInt(10000), 1469, -0.38, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5 a5b6 a4a5 a7a6 b7c5 d7d5"),
                new Node(13, new Random().nextInt(150), new Random().nextInt(10000), 2926, -0.27, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5 a5b6 a4a5 a7a6 b7c5 d7d5"),
                new Node(13, new Random().nextInt(150), new Random().nextInt(10000), 2599, -0.63, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5 a5b6 a4a5 a7a6 b7c5 d7d5"),
                new Node(13, new Random().nextInt(150), new Random().nextInt(10000), 2170, -0.27, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5 a5b6 a4a5 a7a6 b7c5 d7d5"),
                new Node(13, new Random().nextInt(150), new Random().nextInt(10000), 0, 0.30, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5 a5b6 a4a5 a7a6 b7c5 d7d5"),
                new Node(13, new Random().nextInt(150), new Random().nextInt(10000), 0, 0.34, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5 a5b6 a4a5 a7a6 b7c5 d7d5"),
                new Node(13, new Random().nextInt(150), new Random().nextInt(10000), 0, 0.42, "d6d5 c4c5 b6c5 d4c5 b4a6 b5d6 c5d4 c5b5 a5b6 a4a5 a7a6 b7c5 d7d5")
        };
    }

}

package org.example;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class DijkstraAlgorithmTest {

    @Test
    void testDijkstraAlgorithm() {
        // create a new graph
        DijkstraAlgorithm.Graph graph = new DijkstraAlgorithm.Graph(5);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 4, 5);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 0, 7);
        graph.addEdge(3, 2, 6);
        graph.addEdge(4, 1, 3);
        graph.addEdge(4, 2, 9);
        graph.addEdge(4, 3, 2);

        // run Dijkstra's algorithm on the graph with source vertex 0
        int[] result = DijkstraAlgorithm.dijkstra(graph, 0);

        // expected shortest paths from source vertex 0 to all other vertices in the graph
        int[] expected = new int[]{0, 8, 9, 7, 5};

        // compare the expected shortest paths with the actual shortest paths computed by Dijkstra's algorithm
        assertArrayEquals(expected, result);
    }
}

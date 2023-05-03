package org.example;
import java.util.*;

public class DijkstraAlgorithm {

    public static int[] dijkstra(Graph graph, int source) {
        int[] distances = new int[graph.size()];
        boolean[] visited = new boolean[graph.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(graph.size(), Comparator.comparingInt(node -> node.distance));

        pq.offer(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            visited[curr.vertex] = true;

            for (Edge edge : graph.adjacencyList.get(curr.vertex)) {
                if (!visited[edge.destination]) {
                    int newDistance = distances[curr.vertex] + edge.weight;
                    if (newDistance < distances[edge.destination]) {
                        distances[edge.destination] = newDistance;
                        pq.offer(new Node(edge.destination, newDistance));
                    }
                }
            }
        }
        return distances;
    }

    static class Edge {
        int source;
        int destination;
        int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Node {
        int vertex;
        int distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    static class Graph {
        int size;
        List<List<Edge>> adjacencyList;

        Graph(int size) {
            this.size = size;
            this.adjacencyList = new ArrayList<>(size);
            for (int i = 0; i < size; ++i) {
                this.adjacencyList.add(new ArrayList<>());
            }
        }

        void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            this.adjacencyList.get(source).add(edge);
        }

        int size() {
            return this.size;
        }
    }
}

package com.civilization.Model;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class GFG {
    private int dist[];
    private Set<Coordination> settled;
    private PriorityQueue<Node> pq;

    private int V;
    List<List<Node>> adj;

    public GFG(int V) {
        this.V = V;
        this.dist = new int[V];
        this.settled = new HashSet<Coordination>();
        this.pq = new PriorityQueue<Node>(V, new Node());
    }

    public void djikstra(List<List<Node>> adj, int src) {
        this.adj = adj;

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        pq.add(new Node(src, 0));


    }
}

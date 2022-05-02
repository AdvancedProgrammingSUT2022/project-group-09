package com.civilization.Model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Node{
    private Integer price;
    private Coordination coordination;
    private HashMap<Node, Integer> adjNodes;
    private Node parentNode;

    public Node(Integer price, Coordination coordination, HashMap<Node, Integer> adjNodes, Node parentNode) {
        this.price = price;
        this.coordination = coordination;
        this.adjNodes = adjNodes;
        this.parentNode = parentNode;
    }

    public HashMap<Node, Integer> getAdjNodes() {
        return this.adjNodes;
    }

    public Node getParentNode() {
        return this.parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Integer getPrice() {
        return price;
    }

    public Coordination getCoordination() {
        return this.coordination;
    }

    public void setCoordination(Coordination coordination) {
        this.coordination = coordination;
    }
}

package com.civilization.Model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Node{
    private Integer price;
    private Coordination coordination;
    private List<Node> path;
    private HashMap<Node, Integer> adjNodes;
    private Node parentNode;

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

    public List<Node> getPath() {
        return this.path;
    }

    public void setCoordination(Coordination coordination) {
        this.coordination = coordination;
    }
    
    public void setPath(List<Node> path) {
        this.path = path;
    }
}

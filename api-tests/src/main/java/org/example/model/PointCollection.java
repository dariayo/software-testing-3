package org.example.model;

import java.util.LinkedList;
import java.util.List;

public class PointCollection {
    private List<Point> collection = new LinkedList<>();
    public void add(Point point){
        collection.add(point);
    }
    public List<Point> getCollection(){
        return collection;
    }
    public Point getLast(){ return collection.get(collection.size()-1); }

}
package com.pathfinder.pathfinder.Model;

public class dataNode {

    private int row;
    private int col;
    private boolean isStart;
    private boolean isFinish;
    private int distance;
    private boolean sortestPathNode;

    public boolean isSortestPathNode() {
        return this.sortestPathNode;
    }

    public void setSortestPathNode(boolean sortestPathNode) {
        this.sortestPathNode = sortestPathNode;
    }

    private boolean isVisited;
    private boolean isWall;
    private dataNode prevNode;
    
    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isIsStart() {
        return this.isStart;
    }

    public void setIsStart(boolean isStart) {
        this.isStart = isStart;
    }

    public boolean isIsFinish() {
        return this.isFinish;
    }

    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isIsVisited() {
        return this.isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public boolean isIsWall() {
        return this.isWall;
    }

    public void setIsWall(boolean isWall) {
        this.isWall = isWall;
    }

    public dataNode getPrevNode(){
        return this.prevNode;
    }

    public void setPrevNode(dataNode prevNode){
        this.prevNode = prevNode;
    }

    public dataNode() {
    }

    public dataNode(int row, int col, boolean isStart, boolean isFinish, int distance, boolean isVisited, boolean isWall) {
        this.row = row;
        this.col = col;
        this.isStart = isStart;
        this.isFinish = isFinish;
        this.distance = Integer.MAX_VALUE;
        this.isVisited = isVisited;
        this.isWall = isWall;
        this.prevNode = null;
    }
}

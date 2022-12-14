package com.pathfinder.pathfinder.Model;

public class Data {
    
    public String grid;
    private int START_NODE_ROW;
    private int START_NODE_COL;
    private int FINISH_NODE_ROW;
    private int FINISH_NODE_COL;
    public int NUM_ROWS;
    public int NUM_COLS;
    
    public Data(){

    }
    
    public Data(String grid, int START_NODE_ROW, int START_NODE_COL, int FINISH_NODE_ROW, int FINISH_NODE_COL, int NUM_ROWS, int NUM_COLS) {
        this.grid = grid;
        this.START_NODE_ROW = START_NODE_ROW;
        this.START_NODE_COL = START_NODE_COL;
        this.FINISH_NODE_ROW = FINISH_NODE_ROW;
        this.FINISH_NODE_COL = FINISH_NODE_COL;
        this.NUM_ROWS = NUM_ROWS;
        this.NUM_COLS = NUM_COLS;
    }

    public String getGrid() {
        return this.grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public int getSTART_NODE_ROW() {
        return this.START_NODE_ROW;
    }

    public void setSTART_NODE_ROW(int START_NODE_ROW) {
        this.START_NODE_ROW = START_NODE_ROW;
    }

    public int getSTART_NODE_COL() {
        return this.START_NODE_COL;
    }

    public void setSTART_NODE_COL(int START_NODE_COL) {
        this.START_NODE_COL = START_NODE_COL;
    }

    public int getFINISH_NODE_ROW() {
        return this.FINISH_NODE_ROW;
    }

    public void setFINISH_NODE_ROW(int FINISH_NODE_ROW) {
        this.FINISH_NODE_ROW = FINISH_NODE_ROW;
    }

    public int getFINISH_NODE_COL() {
        return this.FINISH_NODE_COL;
    }

    public void setFINISH_NODE_COL(int FINISH_NODE_COL) {
        this.FINISH_NODE_COL = FINISH_NODE_COL;
    }

    public int getNUM_ROWS() {
        return this.NUM_ROWS;
    }

    public void setNUM_ROWS(int NUM_ROWS) {
        this.NUM_ROWS = NUM_ROWS;
    }

    public int getNUM_COLS() {
        return this.NUM_COLS;
    }

    public void setNUM_COLS(int NUM_COLS) {
        this.NUM_COLS = NUM_COLS;
    }

}

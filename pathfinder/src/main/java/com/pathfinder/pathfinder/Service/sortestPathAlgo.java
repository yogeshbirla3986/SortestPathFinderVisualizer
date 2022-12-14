package com.pathfinder.pathfinder.Service;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Service;

import com.pathfinder.pathfinder.Model.Data;
import com.pathfinder.pathfinder.Model.dataNode;

@Service
public class sortestPathAlgo {
    
    public void findSortestPath(dataNode[][] grid, Data data){

        findPath(grid,data);
        
        int row = data.getSTART_NODE_ROW();
        int col = data.getSTART_NODE_COL();

        grid[row][col].setDistance(0);

        int destRow = data.getFINISH_NODE_ROW();
        int destCol = data.getFINISH_NODE_COL();

        int hopes = grid[destRow][destCol].getDistance();
        markPath(destRow,destCol,hopes,row,col,grid,data);
    }

    public void findPath(dataNode[][] grid,Data data){
        
        Queue<dataNode> Q = new LinkedList<>();

        int START_NODE_ROW = data.getSTART_NODE_ROW();
        int START_NODE_COL = data.getSTART_NODE_COL();

        boolean[][] vis = new boolean[data.NUM_ROWS][data.NUM_COLS];
        Q.add(grid[START_NODE_ROW][START_NODE_COL]);
        grid[START_NODE_ROW][START_NODE_COL].setDistance(0);
        int levelS = 1;
        
        while(Q.size() > 0){

            int newlevelS = 0;

            while(levelS-- > 0){

                dataNode item = Q.remove();
                
                if(item.isIsFinish()){

                    System.out.println("findPath reaced to finish !!!");
                    Q.clear();
                    return;
                }

                int row = item.getRow();
                int col = item.getCol();

                if(vis[row][col] == false){
                    
                    vis[row][col] = true;
                    
                    for(int i = -1; i <= 1; i++){

                        if(row+i < 0 || row+i >= data.getNUM_ROWS())
                            continue;

                        for(int j = -1; j <= 1; j++){

                            if( Math.abs(i+j) != 1 || col+j < 0 || col+j >= data.getNUM_COLS() )
                                continue;

                            if(!grid[row+i][col+j].isIsVisited() || grid[row+i][col+j].isIsWall() || vis[row+i][col+j])
                                continue;

                            System.out.println("findPath "+row+i+" "+col+j);
                            grid[row+i][col+j].setDistance(1+item.getDistance());
                            Q.add(grid[row+i][col+j]);
                            newlevelS += 1;
                        }
                    }
                }
            }

            levelS = newlevelS;
        }

    }

    public void markPath(int r,int c,int hopes,int row,int col,dataNode[][] grid,Data data){

        System.out.println("mark"+r+" "+c+" "+hopes);

        if(r == row && c == col){
            return;
        }
        
        if(r-1 >= 0 && grid[r-1][c].isIsVisited()
                && grid[r-1][c].getDistance() == hopes-1){

            grid[r-1][c].setSortestPathNode(true);
            markPath(r-1, c,hopes-1, row, col, grid,data);
        }

        else if(r+1 < data.NUM_ROWS && grid[r+1][c].isIsVisited()
                && grid[r+1][c].getDistance() == hopes-1){

            grid[r+1][c].setSortestPathNode(true);
            markPath(r+1, c,hopes-1, row, col, grid,data);
        }

        else if(c-1 >= 0 && grid[r][c-1].isIsVisited()
                && grid[r][c-1].getDistance() == hopes-1){

            grid[r][c-1].setSortestPathNode(true);
            markPath(r, c-1,hopes-1, row, col, grid,data);
        }

        else if(c+1 < data.NUM_COLS && grid[r][c+1].isIsVisited()
                && grid[r][c+1].getDistance() == hopes-1){

            grid[r][c+1].setSortestPathNode(true);
            markPath(r, c+1,hopes-1, row, col, grid,data);
        }

    }
}

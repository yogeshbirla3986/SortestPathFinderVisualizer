package com.pathfinder.pathfinder.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathfinder.pathfinder.Model.Data;
import com.pathfinder.pathfinder.Model.dataNode;


class pair{

    dataNode node;
    double dist;

    public pair(dataNode node,double dist){

        this.node = node;
        this.dist = dist;
    }
}


@Service
public class searchAlgo {

    @Autowired
    public sortestPathAlgo path;



    public List<dataNode> Dijkstra(dataNode[][] grid, Data data){

        List<dataNode> res = new ArrayList<>();
        PriorityQueue<pair> Q = new PriorityQueue<>((a,b)->(a.dist-b.dist >= 0 ? 1 : -1));

        int START_NODE_ROW = data.getSTART_NODE_ROW();
        int START_NODE_COL = data.getSTART_NODE_COL();
        boolean isFound = false;
        grid[START_NODE_ROW][START_NODE_COL].setDistance(0);
        Q.add(new pair(grid[START_NODE_ROW][START_NODE_COL],0));
        int levelS = 1;

        while(Q.size() > 0){

            int newlevelS = 0;

            while(levelS-- > 0){

                pair item = Q.remove();
                
                if(item.node.isIsFinish()){

                    item.node.setIsVisited(true);
                    isFound = true;
                    Q.clear();
                    break;
                }

                int row = item.node.getRow();
                int col = item.node.getCol();

                if(item.node.isIsVisited() == false){
                    
                    res.add(item.node);
                    item.node.setIsVisited(true);
                    
                    for(int i = -1; i <= 1; i++){

                        if(row+i < 0 || row+i >= data.getNUM_ROWS())
                            continue;

                        for(int j = -1; j <= 1; j++){

                            if( Math.abs(i+j) != 1 || col+j < 0 || col+j >= data.getNUM_COLS() )
                                continue;

                            if(grid[row+i][col+j].isIsVisited() || grid[row+i][col+j].isIsWall())
                                continue;

                            grid[row+i][col+j].setDistance(1+item.node.getDistance());
                            Q.add(new pair(grid[row+i][col+j],grid[row+i][col+j].getDistance()));
                            newlevelS += 1;
                        }
                    }
                }
            }

            levelS = newlevelS;
        }

        if(isFound)
            path.findSortestPath(grid,data);

        System.out.println("Dijkstra completed !!!");
        return res;
    }






    private double GreedyBFSheuristic(int row,int col,Data data){

        int dR = data.getFINISH_NODE_ROW()-row;
        int dC = data.getFINISH_NODE_COL()-col;

        return Math.sqrt(dR*dR+dC*dC);
    }

    public List<dataNode> greedybfs(dataNode[][] grid, Data data){

        List<dataNode> res = new ArrayList<>();
        PriorityQueue<pair> Q = new PriorityQueue<>((a,b)->(a.dist-b.dist >= 0 ? 1 : -1));

        int START_NODE_ROW = data.getSTART_NODE_ROW();
        int START_NODE_COL = data.getSTART_NODE_COL();
        boolean isFound = false;
        Q.add(new pair(grid[START_NODE_ROW][START_NODE_COL],GreedyBFSheuristic(START_NODE_ROW,START_NODE_COL,data)));
        grid[START_NODE_ROW][START_NODE_COL].setDistance(0);
        int levelS = 1;

        while(Q.size() > 0){

            int newlevelS = 0;

            while(levelS-- > 0){

                pair item = Q.remove();
                
                if(item.node.isIsFinish()){

                    item.node.setIsVisited(true);
                    isFound = true;
                    Q.clear();
                    break;
                }

                int row = item.node.getRow();
                int col = item.node.getCol();

                if(item.node.isIsVisited() == false){
                    
                    res.add(item.node);
                    item.node.setIsVisited(true);
                    
                    for(int i = -1; i <= 1; i++){

                        if(row+i < 0 || row+i >= data.getNUM_ROWS())
                            continue;

                        for(int j = -1; j <= 1; j++){

                            if( Math.abs(i+j) != 1 || col+j < 0 || col+j >= data.getNUM_COLS() )
                                continue;

                            if(grid[row+i][col+j].isIsVisited() || grid[row+i][col+j].isIsWall())
                                continue;

                            grid[row+i][col+j].setDistance(1+item.node.getDistance());
                            Q.add(new pair(grid[row+i][col+j],GreedyBFSheuristic(row+i, col+j, data)));
                            newlevelS += 1;
                        }
                    }
                }
            }

            levelS = newlevelS;
        }

        if(isFound)
            path.findSortestPath(grid,data);

        System.out.println("greedy bfs completed !!!");
        return res;
    }



    private double Astarheuristic(int row,int col,Data data,dataNode[][] grid){

        int dR = data.getFINISH_NODE_ROW()-row;
        int dC = data.getFINISH_NODE_COL()-col;

        double h = Math.sqrt(dR*dR+dC*dC);
        double g = grid[row][col].getDistance();

        return g+h;
    }

    public List<dataNode> Astar(dataNode[][] grid, Data data){

        List<dataNode> res = new ArrayList<>();
        PriorityQueue<pair> Q = new PriorityQueue<>((a,b)->(a.dist-b.dist >= 0 ? 1 : -1));

        int START_NODE_ROW = data.getSTART_NODE_ROW();
        int START_NODE_COL = data.getSTART_NODE_COL();
        boolean isFound = false;
        Q.add(new pair(grid[START_NODE_ROW][START_NODE_COL],Astarheuristic(START_NODE_ROW,START_NODE_COL,data,grid)));
        grid[START_NODE_ROW][START_NODE_COL].setDistance(0);
        int levelS = 1;

        while(Q.size() > 0){

            int newlevelS = 0;

            while(levelS-- > 0){

                pair item = Q.remove();
                
                if(item.node.isIsFinish()){

                    item.node.setIsVisited(true);
                    isFound = true;
                    Q.clear();
                    break;
                }

                int row = item.node.getRow();
                int col = item.node.getCol();

                if(item.node.isIsVisited() == false){
                    
                    res.add(item.node);
                    item.node.setIsVisited(true);
                    
                    for(int i = -1; i <= 1; i++){

                        if(row+i < 0 || row+i >= data.getNUM_ROWS())
                            continue;

                        for(int j = -1; j <= 1; j++){

                            if( Math.abs(i+j) != 1 || col+j < 0 || col+j >= data.getNUM_COLS() )
                                continue;

                            if(grid[row+i][col+j].isIsVisited() || grid[row+i][col+j].isIsWall())
                                continue;

                            grid[row+i][col+j].setDistance(1+item.node.getDistance());
                            Q.add(new pair(grid[row+i][col+j],Astarheuristic(row+i, col+j, data,grid)));
                            newlevelS += 1;
                        }
                    }
                }
            }

            levelS = newlevelS;
        }

        if(isFound)
            path.findSortestPath(grid,data);

        System.out.println("A star completed !!!");
        return res;
    }

    public List<dataNode> bfs(dataNode[][] grid, Data data){

        List<dataNode> res = new ArrayList<>();
        Queue<dataNode> Q = new LinkedList<>();

        int START_NODE_ROW = data.getSTART_NODE_ROW();
        int START_NODE_COL = data.getSTART_NODE_COL();
        boolean isFound = false;
        Q.add(grid[START_NODE_ROW][START_NODE_COL]);
        grid[START_NODE_ROW][START_NODE_COL].setDistance(0);
        int levelS = 1;

        while(Q.size() > 0){

            int newlevelS = 0;

            while(levelS-- > 0){

                dataNode item = Q.remove();
                
                if(item.isIsFinish()){

                    item.setIsVisited(true);
                    isFound = true;
                    Q.clear();
                    break;
                }

                int row = item.getRow();
                int col = item.getCol();

                if(item.isIsVisited() == false){
                    
                    res.add(item);
                    item.setIsVisited(true);
                    
                    for(int i = -1; i <= 1; i++){

                        if(row+i < 0 || row+i >= data.getNUM_ROWS())
                            continue;

                        for(int j = -1; j <= 1; j++){

                            if( Math.abs(i+j) != 1 || col+j < 0 || col+j >= data.getNUM_COLS() )
                                continue;

                            if(grid[row+i][col+j].isIsVisited() || grid[row+i][col+j].isIsWall())
                                continue;

                            grid[row+i][col+j].setDistance(1+item.getDistance());
                            Q.add(grid[row+i][col+j]);
                            newlevelS += 1;
                        }
                    }
                }
            }

            levelS = newlevelS;
        }

        if(isFound)
            path.findSortestPath(grid,data);

        System.out.println("bfs completed !!!");
        return res;
    }


    public List<dataNode> bidirectional(dataNode[][] grid, Data data){

        List<dataNode> res = new ArrayList<>();
        Queue<dataNode> Qfront = new LinkedList<>();
        Queue<dataNode> Qback = new LinkedList<>();

        int START_NODE_ROW = data.getSTART_NODE_ROW();
        int START_NODE_COL = data.getSTART_NODE_COL();
        Qfront.add(grid[START_NODE_ROW][START_NODE_COL]);

        boolean isFound = false;
        
        int FINISH_NODE_ROW = data.getFINISH_NODE_ROW();
        int FINISH_NODE_COL = data.getFINISH_NODE_COL();
        Qback.add(grid[FINISH_NODE_ROW][FINISH_NODE_COL]);

        int levelSFront = 1;
        int levelSBack = 1;

        boolean[][] vis = new boolean[data.NUM_ROWS][data.NUM_COLS];

        while(Qfront.size() > 0 && Qback.size() > 0 && !isFound){

            int newlevelSFront = 0;

            while(levelSFront-- > 0){

                dataNode item = Qfront.remove();

                int row = item.getRow();
                int col = item.getCol();

                if(item.isIsVisited() == false){
                    
                    res.add(item);
                    item.setIsVisited(true);
                    vis[row][col] = true;

                    for(int i = -1; i <= 1; i++){

                        if(row+i < 0 || row+i >= data.getNUM_ROWS())
                            continue;

                        for(int j = -1; j <= 1; j++){

                            if( Math.abs(i+j) != 1 || col+j < 0 || col+j >= data.getNUM_COLS() )
                                continue;

                            if(grid[row+i][col+j].isIsVisited() || grid[row+i][col+j].isIsWall())
                                continue;

                            Qfront.add(grid[row+i][col+j]);
                            newlevelSFront += 1;
                        }
                    }
                }
            }

            levelSFront = newlevelSFront;


            // now go for back one

            int newlevelSBack = 0;

            while(levelSBack-- > 0 && !isFound){

                dataNode item = Qback.remove();

                int row = item.getRow();
                int col = item.getCol();
                System.out.println(row+" "+col+" "+vis[row][col]);
                
                if(vis[row][col]){

                    isFound = true;
                    break;
                }

                if(item.isIsVisited() == false){
                    
                    res.add(item);
                    item.setIsVisited(true);
                    
                    for(int i = -1; !isFound && i <= 1; i++){

                        if(row+i < 0 || row+i >= data.getNUM_ROWS())
                            continue;

                        for(int j = -1; !isFound && j <= 1; j++){

                            if( Math.abs(i+j) != 1 || col+j < 0 || col+j >= data.getNUM_COLS() )
                                continue;

                            if(vis[row+i][col+j]){
                                isFound = true;
                                break;
                            }

                            if(grid[row+i][col+j].isIsVisited() || grid[row+i][col+j].isIsWall())
                                continue;

                            Qback.add(grid[row+i][col+j]);
                            newlevelSBack += 1;
                        }
                    }
                }
            }

            levelSBack = newlevelSBack;

        }

        if(isFound)
            path.findSortestPath(grid,data);

        System.out.println("bidirectional completed !!! "+isFound);
        return res;
    }


    public List<dataNode> dfs(dataNode[][] grid, Data data){

        List<dataNode> res = new ArrayList<>();
        boolean isfound = fillResDFS(0,data.getSTART_NODE_ROW(),data.getSTART_NODE_COL(),res,grid,data);

        if(isfound)
            path.findSortestPath(grid, data);

        System.out.println("dfs completed !!!");
        return res;
    }

    public boolean fillResDFS(int dist,int row,int col,List<dataNode> res,dataNode[][] grid,Data data){

        grid[row][col].setIsVisited(true);
        grid[row][col].setDistance(dist);
        res.add(grid[row][col]);

        if(row == data.getFINISH_NODE_ROW() && col == data.getFINISH_NODE_COL()){
            return true;
        }

        for(int i = -1; i <=1 ; i++){
            for(int j = -1; j <= 1; j++){

                if(row+i < 0 || col+j < 0 || row+i >= data.getNUM_ROWS() || col+j >= data.getNUM_COLS())
                    continue;
                else if(Math.abs(i+j) != 1 || i+j == 0 || grid[row+i][col+j].isIsWall())
                    continue;
                else if (grid[row+i][col+j].isIsVisited()){
                    continue;
                } 
                else{
                    boolean val = fillResDFS(1+dist,row+i,col+j,res,grid,data);
                    if(val)
                        return val;
                }
            }
        }

        return false;
    }




}

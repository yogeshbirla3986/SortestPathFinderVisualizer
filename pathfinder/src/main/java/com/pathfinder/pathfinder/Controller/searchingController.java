package com.pathfinder.pathfinder.Controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pathfinder.pathfinder.Model.Data;
import com.pathfinder.pathfinder.Model.dataNode;
import com.pathfinder.pathfinder.Service.searchAlgo;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/pathfinder",  produces="application/json")
public class searchingController {

    @Autowired
    public searchAlgo search;

    @PostMapping("/search")  
    public List<dataNode> search(@RequestParam(name = "algorithm") String algorithm,@ModelAttribute Data data) {
        
        int NUM_ROWS = data.getNUM_ROWS();
        int NUM_COLS = data.getNUM_COLS();
        dataNode[][] grid = new dataNode[NUM_ROWS][NUM_COLS];

        JSONArray json2DArr = new JSONArray(data.grid);

        for (int i = 0; i < json2DArr.length(); i++)
        {
            JSONArray jsonArr = (JSONArray)json2DArr.get(i);
            grid[i] = new dataNode[NUM_COLS];

            for(int j = 0; j < jsonArr.length(); j++){

                JSONObject obj = jsonArr.getJSONObject(j);
                grid[i][j] = new dataNode();

                try{
                    grid[i][j].setRow((int)obj.get("row"));
                    grid[i][j].setCol((int)obj.get("col"));
                    grid[i][j].setIsStart((boolean)obj.get("isStart"));
                    grid[i][j].setIsFinish((boolean)obj.get("isFinish"));
                    grid[i][j].setDistance((int)obj.get("distance"));
                    grid[i][j].setIsVisited((boolean)obj.get("isVisited"));
                    grid[i][j].setIsWall((boolean)obj.get("isWall"));
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }

        if(algorithm.equals("bfs")){
            return search.bfs(grid, data);
        }
        else if(algorithm.equals("dfs")){
            return search.dfs(grid, data);
        }
        else if(algorithm.equals("Bidirectional")){
            return search.bidirectional(grid, data);
        }
        else if(algorithm.equals("Greedy bfs")){
            return search.greedybfs(grid, data);
        }
        else if(algorithm.equals("A star")){
            return search.Astar(grid, data);
        }
        else if(algorithm.equals("Dijkstra")){
            return search.Dijkstra(grid, data);
        }
        else{
            System.out.println("No such algorithm %s found !!! "+algorithm);
        }

        return null;
    }
}
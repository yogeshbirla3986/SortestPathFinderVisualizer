
import { useState } from "react";
import axios from 'axios';
import "./Header.css";
import startIcon from "../assets/start.png";

export default function Header(props) {

  const {
    state, 
    setState,
    speed,
    setSpeed,
    isSE,
    setIsSE,
    sepair,
    setSEpair,
    NUM_ROWS,
    NUM_COLS } = props;

  const [algorithm,setAlgorithm] = useState("dfs");
  const [speedToss,setSpeedToss] = useState(false);
  const [algoToss,setAlgoToss] = useState(false);

  async function clearBoard(){

      const newGrid = state.grid;

      for(var i = 0; i < NUM_ROWS; i++){

        for(var j = 0; j < NUM_COLS; j++){

            const newNode = {
                ...newGrid[i][j],
                isStart: false,
                isFinish: false,
                sortestPathNode: false,
                distance: 10000000,
                isVisited: false,
                isWall: false,
            }
            
            newGrid[newGrid[i][j].row][newGrid[i][j].col] = newNode;
        }
      }

      setState({grid: newGrid , mouseIsPressed: state.mouseIsPressed})
  }

  async function clearWalls(){

    const newGrid = state.grid;

    for(var i = 0; i < NUM_ROWS; i++){

      for(var j = 0; j < NUM_COLS; j++){

          const newNode = {
              ...newGrid[i][j],
              isWall: false,
          }

          newGrid[newGrid[i][j].row][newGrid[i][j].col] = newNode;
      }
    }

    setState({grid: newGrid , mouseIsPressed: state.mouseIsPressed})
  }


  async function clearPath(){

    const newGrid = state.grid;

    for(var i = 0; i < NUM_ROWS; i++){

      for(var j = 0; j < NUM_COLS; j++){

          const newNode = {
              ...newGrid[i][j],
              distance: 10000000,
              isVisited: false,
              sortestPathNode: false
          }

          newGrid[newGrid[i][j].row][newGrid[i][j].col] = newNode;
      }
    }

    setState({grid: newGrid , mouseIsPressed: state.mouseIsPressed})
  }

  async function startVisualizing() {

    if(sepair.START_NODE_ROW < 0 || sepair.START_NODE_COL < 0 || sepair.START_NODE_ROW >= NUM_ROWS || sepair.START_NODE_COL >= NUM_COLS){
      alert("choose correct start node !!!");
      return;
    }

    if(sepair.FINISH_NODE_ROW < 0 || sepair.FINISH_NODE_COL < 0 || sepair.FINISH_NODE_ROW >= NUM_ROWS || sepair.FINISH_NODE_COL >= NUM_COLS){
      alert("choose correct finish node !!!")
      return;
    }

    const data = new FormData();
    data.append("grid", JSON.stringify(state.grid))
    data.append("START_NODE_ROW", sepair.START_NODE_ROW)
    data.append("START_NODE_COL", sepair.START_NODE_COL)
    data.append("FINISH_NODE_ROW", sepair.FINISH_NODE_ROW)
    data.append("FINISH_NODE_COL", sepair.FINISH_NODE_COL)
    data.append("NUM_ROWS", NUM_ROWS)
    data.append("NUM_COLS", NUM_COLS)

    await axios({
      method: "POST",
      url: "http://localhost:8080/pathfinder/search",
      params: { algorithm: `${algorithm}` },
      data,
    })
    .then(res => {

        const newGrid = state.grid.slice();
        var data = res.data;
        const sortestPath = []
        var i = 0;

        function printGrid(){

            setTimeout(() => {

            for(var j = 0; j < 1+10*parseInt(Math.pow(2,speed)) && i < data.length; j++ & i++){
                
              if(data[i].sortestPathNode == true){

                const newNode = {
                    ...data[i],
                    distance: 0
                }
                sortestPath.push(newNode)
              }

              newGrid[data[i].row][data[i].col] = data[i];
              setState({grid: newGrid, mouseIsPressed: state.mouseIsPressed})
            }

              if(i < data.length){
                 printGrid();
              }
              else if(sortestPath.length > 0){
                i = 0;
                
                printSortestPath();
              }

            }, 1);

        }

        function printSortestPath(){

          setTimeout(() => {
          
         
          newGrid[sortestPath[i].row][sortestPath[i].col].distance = 0;
          setState({grid: newGrid, mouseIsPressed: state.mouseIsPressed})
          i += 1;

          if(i < sortestPath.length){
            printSortestPath();
          }

          }, 10+parseInt(Math.pow(4,speed)));
      }

        printGrid();
    })
    .catch(err => {
      console.log("error in request", err);
    });
    
  }

  function selectStart(){

    setIsSE({
      ...isSE,
      isStart: true
    })

  }

  function selectEnd(){

    setIsSE({
      ...isSE,
      isFinish: true
    })
  }


  return (
    <header className="Header">
        <nav className="Nav1">
          <button class="btn noHover">Pathfinding Visualizer</button>
          <button class="dropdown" onClick={()=>{setAlgoToss(!algoToss)}}>
            Algorithms
            <div class="dropdown-content" style={{display : algoToss ? "block" : "none"}} >
                <label onClick={()=>{setAlgorithm("A star")}}>A star</label>
                <label onClick={()=>{setAlgorithm("Greedy bfs")}}>Greedy bfs</label>
                <label onClick={()=>{setAlgorithm("Dijkstra")}}>Dikstra</label>
                <label onClick={()=>{setAlgorithm("Bidirectional")}}>Bidirectional</label>
                <label onClick={()=>{setAlgorithm("dfs")}}>dfs</label>
                <label onClick={()=>{setAlgorithm("bfs")}}>bfs</label>
            </div>
          </button>
          <button onClick={()=>{startVisualizing()}}>Mazes & Patterns</button>
          <button onClick={()=>{startVisualizing()}}>Visualize {algorithm}!</button>
          <button onClick={()=>{clearBoard()}}>Clear Board</button>
          <button onClick={()=>{clearWalls()}}>Clear Walls</button>
          <button onClick={()=>{clearPath()}}>Clear Path</button>

          <button class="dropdown" onClick={()=>{setSpeedToss(!speedToss)}}>
            Speed {speed === 0.5 ? "slow" : speed === 1 ? "normal" : speed === 2 ? "fast" : "super-fast"}
            <div class="dropdown-content" style={{display : speedToss ? "block" : "none"}} >
                <label onClick={()=>{setSpeed(0.5)}}>slow</label>
                <label onClick={()=>{setSpeed(1)}}>normal</label>
                <label onClick={()=>{setSpeed(2)}}>fast</label>
                <label onClick={()=>{setSpeed(3)}}>super-fast</label>
            </div>
          </button>

        </nav>

        <nav className="Nav2">
          <button onClick={()=>{selectStart()}}><div><img className="icon" src={startIcon} /><label>Start Node</label></div></button>
          <button onClick={()=>{selectEnd()}}>Target Node</button>
          <button>Bomb Node</button>
          <button>Universal Node</button>
          <button>Visited Node</button>
          <button>Shortest-Path Node</button>
          <button>Wall Node</button>
        </nav>

    </header>
  );
}

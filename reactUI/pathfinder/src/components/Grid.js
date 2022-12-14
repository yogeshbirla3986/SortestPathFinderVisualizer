
import './Grid.css'
import Node from './Node';

function Grid(props) {

  const {
          state , setState ,
          isSE , setIsSE ,
          sepair , setSEpair ,
          NUM_ROWS , NUM_COLS
        } = props;

  function getNewGridWithWallToggled(grid, row, col){

    const newGrid = grid.slice();
    const node = newGrid[row][col];
    const newNode = {
      ...node,
      isWall: !node.isWall,
    };
    newGrid[row][col] = newNode;
    return newGrid;
  };

  function handleMouseDown(row, col) {

    if(isSE.isStart){
      state.grid[row][col].isStart = true;
      if(sepair.START_NODE_ROW >= 0 && sepair.START_NODE_COL >= 0 && sepair.START_NODE_ROW < NUM_ROWS && sepair.START_NODE_COL < NUM_COLS){
        state.grid[sepair.START_NODE_ROW][sepair.START_NODE_COL].isStart = false;
      }
      setSEpair({
        ...sepair,
        START_NODE_ROW: row,
        START_NODE_COL: col,
      });
      setIsSE({
        ...isSE,
        isStart: false
      });
      return;
    }
    if(isSE.isFinish){
      state.grid[row][col].isFinish = true;
      if(sepair.FINISH_NODE_ROW >= 0 && sepair.FINISH_NODE_COL >= 0 && sepair.FINISH_NODE_ROW < NUM_ROWS && sepair.FINISH_NODE_COL < NUM_COLS){
        state.grid[sepair.FINISH_NODE_ROW][sepair.FINISH_NODE_COL].isFinish = false;
      }
      setSEpair({
        ...sepair,
        FINISH_NODE_ROW: row,
        FINISH_NODE_COL: col,
      });
      setIsSE({
        ...isSE,
        isFinish: false
      });
      return;
    }
    const newGrid = getNewGridWithWallToggled(state.grid, row, col);
    setState({grid: newGrid, mouseIsPressed: true});
  }

  function handleMouseEnter(row, col) {
    if (!state.mouseIsPressed) return;
    const newGrid = getNewGridWithWallToggled(state.grid, row, col);
    setState({grid: newGrid , mouseIsPressed: state.mouseIsPressed});
  }

  function handleMouseUp() {
    setState({grid: state.grid , mouseIsPressed: false});
  }


  return (
    <div className="grid">
      { 
          state.grid.map((row,rowIndx) => {
            return(
              <div key={rowIndx} className="gridRow" >{
                  row.map((item,itemIndx) => {

                      const {row, col, isFinish, distance, isStart, isWall, isVisited} = item;
                      return(
                        <Node
                          key={itemIndx}
                          row={row}
                          col={col}
                          isFinish={isFinish}
                          isStart={isStart}
                          isWall={isWall}
                          isVisited={isVisited}
                          distance={distance}
                          mouseIsPressed={state.mouseIsPressed}
                          handleMouseDown={handleMouseDown}
                          handleMouseEnter={handleMouseEnter}
                          handleMouseUp={handleMouseUp}>
                        </Node>
                      );
                  })
                }
              </div>)
          })
      }
    </div>
  );
  
}


export default Grid;

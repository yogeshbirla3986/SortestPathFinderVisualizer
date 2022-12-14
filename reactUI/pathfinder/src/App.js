
import './App.css'
import Header from './components/Header';
import Grid from './components/Grid';
import { useState , useEffect } from 'react';
import useWindowDimensions from './components/useWindowDimentions';


var NUM_ROWS = 0;
var NUM_COLS = 0;

function App() {

  const [state,setState] = useState({
    grid: [],
    mouseIsPressed: false,
  });

  const [speed, setSpeed] = useState(1);

  const [isSE, setIsSE] = useState({
    isStart: false,
    isFinish: false,
  });

  const [sepair, setSEpair] = useState({
    START_NODE_ROW: -1,
    START_NODE_COL: -1,
    FINISH_NODE_ROW: -1,
    FINISH_NODE_COL: -1,
  });

  const { height, width } = useWindowDimensions();
  NUM_ROWS = parseInt((height*0.85)/18);
  NUM_COLS = parseInt((width*0.98)/18);

  function createNode(row, col){
    return {
      row,
      col,
      isStart: false,
      isFinish: false,
      sortestPathNode: false,
      distance: 10000000,
      isVisited: false,
      isWall: false,
  }
}

  useEffect(() => {

    const grid = [];

    for(let row = 0; row < NUM_ROWS; row++){

      const currentRow = [];
      for(let col = 0; col < NUM_COLS; col++){

        const currentNode = createNode(row,col);
        currentRow.push(currentNode);
      }
      
      grid.push(currentRow);
    }

    setState({grid: grid, mouseIsPressed: state.mouseIsPressed});
  },[])


  return (
    <div className='App'>
      <Header 
          state={state} 
          setState={setState}
          speed={speed}
          setSpeed={setSpeed}
          isSE={isSE}
          setIsSE={setIsSE}
          sepair={sepair}
          setSEpair={setSEpair}
          NUM_ROWS={NUM_ROWS}
          NUM_COLS={NUM_COLS}
          />
      <Grid state={state} setState={setState} 
            isSE={isSE} setIsSE={setIsSE}
            sepair={sepair} setSEpair={setSEpair}
            NUM_ROWS={NUM_ROWS}
            NUM_COLS={NUM_COLS} />
    </div>
  );
}


export default App;

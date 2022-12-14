import './Node.css'
import startIcon from "../assets/start.png";
import finishIcon from "../assets/finish.png";

function Node(props){

    const {
        row,
        col,
        isFinish,
        isStart,
        isWall,
        isVisited,
        distance,
        handleMouseDown,
        handleMouseEnter,
        handleMouseUp,
    } = props;

    const extraClassName = isFinish
        ? 'node-finish'
        : isStart
        ? 'node-start'
        : isWall
        ? 'node-wall'
        : distance === 0
        ? 'node-shortest-path'
        : isVisited
        ? 'node-visited'
        : '';
  
    return (
        <div
          id={`node-${row}-${col}`}
          className={`node ${extraClassName}`}
          onMouseDown={() => handleMouseDown(row, col)}
          onMouseEnter={() => handleMouseEnter(row, col)}
          onMouseUp={() => handleMouseUp()}>
        
            {
                isStart ? <img src={startIcon} alt="startLogo" style={{display:'inline-block'}}/> : 
                isFinish ? <img src={finishIcon} /> :
                <></>
            }

        </div>
      );
}

export default Node;
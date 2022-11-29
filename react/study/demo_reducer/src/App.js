import './App.css';
import { Messenger } from './Messenger'
import { MessengerUseState } from './MessengerUseState';
import { MessengerWithMyReducer } from './MyReducer';

function App() {
  return (
    <div className="App">
      <p>useState </p>
      <MessengerUseState />
      <p>useReducer </p>
      <Messenger /><hr />
      <p>手动实现 reducer </p>
      <MessengerWithMyReducer /><hr />
    </div>
  );
}

export default App;

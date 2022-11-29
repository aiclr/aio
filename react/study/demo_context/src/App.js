import './App.css';
import { UseContext, UseContext2 } from './UseContext';
import {UsePropsForImg} from './UsePropsForImg'
import {UseContextForImg} from './UseContextForImg'

function App() {
  return (
    <>
      <UsePropsForImg/>
      <UseContextForImg/>
      <UseContext /><hr/>
      <UseContext2/><hr/>
    </>
  );
}

export default App;

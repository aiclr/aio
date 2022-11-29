import './App.css';
import { StatusBar, SaveButton, StatusBar2, SaveButton2, StatusBar3, SaveButton3 } from './StatusBar'
import { Form, FormPlus } from './Form'
import { Chat } from './ChatRoom'
import { Animation } from './welcome'
import { Challenges } from './Challenges'
import { Canvas } from './Canvas'


function App() {
  return (
    <>
      <Canvas />
      <Challenges />
      <p>插拔网线：监听是否断网 启动前断网有bug</p>
      <StatusBar /><hr />
      <p>插拔网线：断网无法点击 启动前断网有bug</p>
      <SaveButton /><hr />
      <p>插拔网线：监听是否断网 启动前断网有bug</p>
      <StatusBar2 /><hr />
      <p>插拔网线：断网无法点击 启动前断网有bug</p>
      <SaveButton2 /><hr />
      <p>插拔网线：监听是否断网 修复启动前断网有bug</p>
      <StatusBar3 /><hr />
      <p>插拔网线：断网无法点击 修复启动前断网有bug</p>
      <SaveButton3 /><hr />

      <p>提取form表单相同的输入逻辑和状态</p>
      <Form /><hr />
      <FormPlus /><hr />
      <p>提取 useEffect 到自定义hooks</p>
      <Chat /><hr />
      <p>淡入效果实现方式大全</p>
      <Animation />
    </>
  );
}

export default App;
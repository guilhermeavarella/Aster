import { useState } from 'react'
import logo from '/public/favicon.svg'
import LiquidGlass from 'liquid-glass-react'


function App() {
  return (
    <>
      <div>
        <a href="https://vite.dev" target="_blank">
          <img src={logo} className="logo" alt="Vite logo" />
        </a>
      </div>
      <h1>This is H1. Lorem Ipsum</h1>
      <h2>This is H2. Lorem Ipsum</h2>
      <h3>This is H3. Lorem Ipsum</h3>
      <h4>This is H4. Lorem Ipsum</h4>
      <p>This is a paragraph. Lorem Ipsum</p>
      <div className="card">
        <p>
          Edit <code>src/App.tsx</code> and save to test HMR
        </p>  
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  )
}

export default App

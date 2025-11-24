import { Outlet } from 'react-router-dom'

function App() {

  return (
    <div className="w-full h-full min-h-screen min-w-screen flex flex-row items-start justify-center">
      <Outlet />
    </div>
  )
}

export default App

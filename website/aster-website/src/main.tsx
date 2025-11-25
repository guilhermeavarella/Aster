import { StrictMode } from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import Home from './pages/Home.tsx'
import Produtos from './pages/Produtos.tsx'

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      { index: true, element: <Home /> },
      { path: 'home', element: <Home /> },
      { path: 'produtos' , element: <Produtos /> },/*
      { path: 'sobre', element: <SobreNos /> },
      { path: 'contato', element: <Contato /> }*/
    ]
  }
])

createRoot(document.getElementById('root')!).render(
    <StrictMode>
      <RouterProvider router={router} />
    </StrictMode>
)

import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import './index.css'
import App from './App.tsx'
import Login from './pages/Login.tsx'

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      { index: true, element: <Login /> },

      { path: 'login', element: <Login /> },/*
      { path: 'home', element: <Home /> },
      { path: 'docs', element: <Documents /> },

      { path: 'painel/d/:user', element: <Painel_1 /> },
      { path: 'painel/i/:user', element: <Painel_2 /> },

      { path: 'operacoes/exibir/:entId', element: <Exibir /> },
      { path: 'operacoes/alterar/:entId', element: <Alterar /> },
      { path: 'operacoes/criar/:entId?', element: <Criar /> }

      { path: 'suporte/devolutivas', element: <Devolutivas /> },
      { path: 'suporte/responder', element: <Responder /> },*/
    ]
  }
])

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)

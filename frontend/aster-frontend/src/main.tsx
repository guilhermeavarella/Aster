import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import './index.css'
import App from './App.tsx'
import Login from './pages/Login.tsx'
import Exibir from './pages/Exibir.tsx'
import Home from './pages/Home.tsx'
import Documents from './pages/Documentacao.tsx'
import Devolutivas from './pages/Devolutivas.tsx'
import { painelRoutes } from './pages/Painel.tsx'

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      { index: true, element: <Login /> },

      { path: 'login', element: <Login /> },
      { path: 'home', element: <Home /> },
      { path: 'docs', element: <Documents /> },

      { path: 'operacoes/exibir/:entidade', element: <Exibir /> }, /*
      { path: 'operacoes/alterar/:entidade/:regId', element: <Alterar /> },
      { path: 'operacoes/criar/:entidade/:regId?', element: <Criar /> }*/

      { path: 'suporte/devolutivas', element: <Devolutivas /> },/*
      { path: 'suporte/responder', element: <Responder /> },*/

      ...painelRoutes,
    ]
  }
])

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)

import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import './index.css'
import App from './App.tsx'
import Login from './pages/Login.tsx'
import Exibir from './pages/Exibir.tsx'
import { createTheme } from '@mui/material'
import { ThemeProvider } from '@mui/material'

// Teste das telas de formulário
import ProdutoForm from './components/forms/ProdutoForm.tsx'
import VersaoForm from './components/forms/VersaoForm.tsx'

// Tema global dos componentes MUI
  const theme = createTheme({
    typography: {
      fontFamily: 'Segoe UI'
    }
  })

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      { index: true, element: <Login /> },

      { path: 'login', element: <Login /> },
      { path: 'operacoes/criar/produto', element: <ProdutoForm />},
      { path: 'operacoes/criar/versao', element: <VersaoForm />},
      /*
      { path: 'home', element: <Home /> },
      { path: 'docs', element: <Documents /> },

      { path: 'painel/d/:user', element: <Painel_1 /> },
      { path: 'painel/i/:user', element: <Painel_2 /> },*/

      { path: 'operacoes/exibir/:entidade', element: <Exibir /> }, /*
      { path: 'operacoes/alterar/:entidade/:regId', element: <Alterar /> },
      { path: 'operacoes/criar/:entidade/:regId?', element: <Criar /> }

      { path: 'suporte/devolutivas', element: <Devolutivas /> },
      { path: 'suporte/responder', element: <Responder /> },*/
    ]
  }
])

createRoot(document.getElementById('root')!).render(
  <ThemeProvider theme={theme}>
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
  </ThemeProvider>
)

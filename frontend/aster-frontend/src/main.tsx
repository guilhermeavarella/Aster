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
import PacoteForm from './components/forms/PacoteForm.tsx'
import LicencaForm from './components/forms/LicencaForm.tsx'
import ClienteIndividualForm from './components/forms/ClienteIndividualForm.tsx'
import ClienteOrganizacaoForm from './components/forms/ClienteOrganizacaoForm.tsx'
import DevolutivaFeedbackForm from './components/forms/DevolutivaFeedbackForm.tsx'
import DevolutivaTicketForm from './components/forms/DevolutivaTicketForm.tsx'
import UsuarioForm from './components/forms/UsuarioForm.tsx'

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
      { path: 'operacoes/criar/pacote', element: <PacoteForm />},
      { path: 'operacoes/criar/licenca', element: <LicencaForm />},
      { path: 'operacoes/criar/cliente-individual', element: <ClienteIndividualForm />},
      { path: 'operacoes/criar/cliente-organizacao', element: <ClienteOrganizacaoForm />},
      { path: 'operacoes/criar/devolutiva-feedback', element: <DevolutivaFeedbackForm />},
      { path: 'operacoes/criar/devolutiva-ticket', element: <DevolutivaTicketForm />},
      { path: 'operacoes/criar/usuario', element: <UsuarioForm />},
      /*
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
  <ThemeProvider theme={theme}>
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
  </ThemeProvider>
)

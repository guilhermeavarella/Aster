import { StrictMode } from 'react'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import './index.css'
import App from './App.tsx'
import About from './pages/About.tsx'
import Contato from './pages/Contato.tsx'
import { createTheme, ThemeProvider } from '@mui/material'

// Tema global dos componentes MUI
const theme = createTheme({
  typography: {
    fontFamily: 'Segoe UI'
  }
})
import Home from './pages/Home.tsx'
import Produtos from './pages/Produtos.tsx'

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      { index: true, element: <Home /> },
      { path: 'home', element: <Home /> },
      { path: 'produtos' , element: <Produtos /> },
      { path: 'sobre', element: <SobreNos /> },
      { path: 'contato', element: <Contato /> }
    ]
  }
])

createRoot(document.getElementById('root')!).render(
  <ThemeProvider theme={theme}>
    <StrictMode>
      <RouterProvider router={router} />
    </StrictMode>
  </ThemeProvider>
)

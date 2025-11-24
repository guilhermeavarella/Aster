import { StrictMode } from 'react'
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

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    children: [
      // { index: true, element: <Landing />},
      // { path: 'landing', element: <Landing /> },
      // { path: 'galeria', element: <Galeria /> },
      { path: 'sobre', element: <About /> },
      { path: 'contato', element: <Contato />},
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

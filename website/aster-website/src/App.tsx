import { useState } from "react"
import { Outlet, useNavigate } from "react-router-dom"
import NavTop from "./components/NavTop"
import NavTopWhite from './components/NavTopWhite'

function App() {
  const navigate = useNavigate()

  const [ navColor, setNavColor ] = useState('transparent')

  return (
    <div>
      {navColor === "white" ? (<NavTopWhite />) : (<NavTop />)}
      <main className="w-full h-full min-h-screen min-w-screen flex flex-col items-center justify-start">
        <Outlet context={{setNavColor}}/>
        <section className="w-full bg-[var(--background-dark)] flex flex-col items-center justify-center py-9 px-80 gap-40">
          <div className="w-full flex flex-row items-start justify-between text-sm text-[var(--content-inverse)] py-16" >
            <div className="w-full flex flex-row items-start justify-center gap-20">
              <img src="/logos/all-white.svg" alt="Aster Logo" className="h-16 hover:cursor-pointer"/>
              <div className="flex flex-col items-start justify-center mt-6 gap-4">
                <p className="font-semibold hover:cursor-pointer" onClick={() => window.location.href = '/'}>Início</p>
                <p className="font-semibold hover:cursor-pointer" onClick={() => navigate('produtos')}>Produtos e Pacotes</p>
                <p className="font-semibold hover:cursor-pointer" onClick={() => navigate('sobre')}>Sobre a Aster</p>
                <p className="font-semibold hover:cursor-pointer" onClick={() => navigate('contato')}>Contato</p>
              </div>
              <div className="flex flex-col items-start justify-center mt-6 gap-4">
                <p className="font-semibold hover:cursor-pointer" onClick={() => window.open('https://localhost:3000/')}>Dashboard</p>
                <p className="font-semibold hover:cursor-pointer" onClick={() => window.open('https://github.com/guilhermeavarella/Aster')}>Mais informações</p>
              </div>
            </div>

            <div className="w-full flex flex-col items-center justify-center gap-4">
              <p>Clique aqui:</p>
              <img src="/assets/icons/app.svg" alt="Aster app icon" className="w-12 h-12 hover:cursor-pointer" onClick={() => window.open('https://github.com/guilhermeavarella/Aster')} />
            </div>
          </div>
          <p className="text-sm text-[var(--content-inverse)]">Para informações sobre os integrantes da equipe de desenvolvimento, acesse a aba “Contato” :)</p>
        </section>
      </main>
    </div>
  )
}

export default App

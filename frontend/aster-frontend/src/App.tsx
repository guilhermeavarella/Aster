import { useState } from 'react';
import NavItem from './components/NavItem';
import Glass from './components/Glass';
import { Outlet, Link, useLocation, useNavigate, createContext } from 'react-router-dom'

function App() {  
  const location = useLocation();

  const isLoginPage = location.pathname === "/" || location.pathname === "/login";
  const [currentUser, setCurrentUser] = useState();

  return (
    isLoginPage ? ( 
      <main className="w-full h-full min-h-screen min-w-screen flex flex-row items-center justify-center">
        <Outlet context={{ currentUser, setCurrentUser }} />
      </main>
     ) : (
      <div className="w-full h-full min-h-screen min-w-screen flex flex-row items-start justify-start bg-gradient-to-tr from-[var(--brand-blue)] via-[var(--brand-lavender)] to-[var(--brand-pink)]">  
        <div className="w-full h-full max-w-92 flex flex-row items-start justify-center p-6">
          <Glass>
            <div className="min-h-[calc(100vh-6rem)] min-w-68 max-h-268 w-full flex flex-col items-center justify-start gap-9">
              
              <img src="/src/assets/logos/dashboard-black.svg" alt="Aster Logo" className="h-12" />
              <div className="w-full flex flex-col items-center justify-start gap-3">
                <NavItem label="Início" onClick={() => {}} />
                <NavItem label="Documentação" onClick={() => {}} />
              </div>

              <div className="w-full flex flex-col items-center justify-start gap-6" >
                <section className="w-full flex flex-col items-center justify-start gap-6 bg-[var(--content-primary)]/5 rounded-3xl p-3">
                  <div className="w-full flex flex-col items-center justify-start gap-3"> {/* SERÁ DINÂMICO, VARIA DE ACROD COM A VISÃO */}
                    <div className="w-full flex flex-row gap-1 items-center">
                      <img src="/src/assets/icons/sidemenu/painel.svg" alt="Panel Icon" className="h-6" />
                      <p> Painel </p>
                    </div>
                    <NavItem label="Varia de acordo com a visão" onClick={() => {}} />
                    <NavItem label="Varia de acordo com a visão" onClick={() => {}} />
                  </div>
                </section>

                <section className="w-full flex flex-col items-center justify-start gap-6 bg-[var(--content-primary)]/5 rounded-3xl p-3">
                  <div className="w-full flex flex-col items-center justify-start gap-3"> {/* SERÁ DINÂMICO, VARIA DE ACROD COM A VISÃO */}
                    <div className="w-full flex flex-row gap-1 items-center">
                      <img src="/src/assets/icons/sidemenu/operacoes.svg" alt="Operations Icon" className="h-6" />
                      <p> Operações </p>
                    </div>
                    <NavItem label="Licenças" onClick={() => {}} />
                    <NavItem label="Produtos" onClick={() => {}} />
                    <NavItem label="Versões" onClick={() => {}} />
                    <NavItem label="Clientes" onClick={() => {}} />
                    <NavItem label="Pacotes" onClick={() => {}} />
                  </div>
                </section>

                <section className="w-full flex flex-col items-center justify-start gap-6 bg-[var(--content-primary)]/5 rounded-3xl p-3">
                  <div className="w-full flex flex-col items-center justify-start gap-3"> {/* SERÁ DINÂMICO, VARIA DE ACROD COM A VISÃO */}
                    <div className="w-full flex flex-row gap-1 items-center">
                      <img src="/src/assets/icons/sidemenu/suporte.svg" alt="Support Icon" className="h-6" />
                      <p> Suporte </p>
                    </div>
                    <NavItem label="Devolutivas" onClick={() => {}} />
                    <NavItem label="Responder ticket" onClick={() => {}} />
                  </div>
                </section>
              </div>
            </div>
          </Glass>
        </div>

        <main className="w-full h-full pt-6 pb-6 pl-3 pr-9">
          <Outlet context={{ currentUser, setCurrentUser }} />
        </main>
      </div>
    )
  )
}

export default App

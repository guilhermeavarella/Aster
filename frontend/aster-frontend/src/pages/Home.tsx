import { useState, useEffect } from "react"
import { useOutletContext } from "react-router-dom";
import ProfileMenu from "../components/ProfileMenu";
import Glass from "../components/Glass";


export default function Home() {
    const currentUser = localStorage.getItem('currentUser');
    const [user, setUser] = useState("Fulano");
    const [visao, setVisao] = useState("Nenhuma");
    
    async function fetchUserData(userId: string) {
        userId = userId.slice(1, -1);
        let userData;

        await fetch('/mocks/profiles.json')
            .then(response => response.json())
            .then(data => userData = (data))

        const user = userData.map((profile: any) => {
            if (profile.user === userId) {
                setUser(profile.nome);
                setVisao(profile.setor);
            }
            return;
        })
    }

    useEffect(() => {
        if (currentUser) {
            fetchUserData(currentUser);
        }

    }, []);
    
    return (
        <section className="w-full h-full flex flex-col items-center justify-start gap-6">
            <Glass padding="p-12" className="min-w-full">
                <div className="w-full flex flex-col gap-6">
                    <section className="w-full flex flex-row items-center justify-between">
                        <div className="flex flex-row gap-1.5">
                            <img src="/src/assets/logos/icon.svg" alt="Icone Aster" className="h-16" />
                            <div className="flex flex-col mt-2 gap-1">
                                <h2>Bem vindo(a) de volta, {user}</h2>
                                <p className="text-[var(--content-secondary)]">Visão: {visao}</p>
                            </div>
                        </div>
                        <ProfileMenu />
                    </section>
                    <section className="w-full grid grid-flow-dense grid-cols-4 grid-rows-5 gap-6">
                        <div className="col-span-1 row-span-2 bg-[var(--background-fixed-white)] rounded-3xl">
                            <div className="w-full h-full flex flex-col items-center justify-center p-6">
                                Teste
                            </div>
                        </div>
                        <div className="col-span-1 row-span-1 bg-[var(--background-fixed-white)] rounded-3xl">
                            <div className="w-full h-full flex flex-col items-center justify-center p-6">
                                Teste
                            </div>
                        </div>
                        <div className="col-span-1 row-span-1 bg-[var(--background-fixed-white)] rounded-3xl">
                            <div className="w-full h-full flex flex-col items-center justify-center p-6">
                                Teste
                            </div>
                        </div>
                        <div className="col-span-1 row-span-2 bg-[var(--background-fixed-white)] rounded-3xl">
                            <div className="w-full h-full flex flex-col items-center justify-center p-6">
                                Teste
                            </div>
                        </div>

                        <div className="col-span-2 row-span-2 bg-[var(--background-fixed-white)] rounded-3xl">
                            <div className="w-full h-full flex flex-col items-center justify-center p-6">
                                Teste
                            </div>
                        </div>

                        <div className="col-span-1 row-span-1 bg-[var(--background-fixed-white)] rounded-3xl">
                            <div className="w-full h-full flex flex-col items-center justify-center p-6">
                                Teste
                            </div>
                        </div>
                        <div className="col-span-1 row-span-3 bg-[var(--background-fixed-white)] rounded-3xl">
                            <div className="w-full h-full flex flex-col items-center justify-center p-6">
                                Teste
                            </div>
                        </div>

                        <div className="col-span-3 row-span-2 bg-[var(--background-fixed-white)] rounded-3xl">
                            <div className="w-full h-full flex flex-col items-center justify-center p-6">
                                Teste
                            </div>
                        </div>
                    </section>
                </div>
            </Glass>
        </section>
    )
}
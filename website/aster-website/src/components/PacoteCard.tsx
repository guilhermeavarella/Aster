import Button from '../components/Button'

export default function PacoteCard () { {/* TORNAR DINAMICO */}
    return (
        <div className="w-80 h-116 bg-[var(--background-fixed-white)] rounded-3xl shadow-sm flex flex-col items-center justify-center p-6 gap-3">
            <p className="font-semibold text-2xl text-center">Design</p>
            <div className="w-full h-full flex flex-col items-center justify-between">
                <div className="w-full flex flex-col items-center justify-start gap-2">
                    <div className="w-full flex flex-row items-center justify-start gap-2">
                        <img src="/assets/icons/app.svg" alt="Icone" className="w-12 h-12"/>
                        <p className="font-semibold text-lg">Aikonic</p>
                    </div>
                    <div className="w-full flex flex-row items-center justify-start gap-2">
                        <img src="/assets/icons/app.svg" alt="Icone" className="w-12 h-12"/>
                        <p className="font-semibold text-lg">Aikonic</p>
                    </div>
                    <div className="w-full flex flex-row items-center justify-start gap-2">
                        <img src="/assets/icons/app.svg" alt="Icone" className="w-12 h-12"/>
                        <p className="font-semibold text-lg">Aikonic</p>
                    </div>
                </div>
                
                <div className="w-full flex flex-col gap-1 mb-6">
                    <p className="w-full text-sm text-[var(--content-secondary)]">Todos os recursos de modelagem 3D por:</p>
                    <div className="w-full flex flex-row items-start justify-start gap-3">
                        <div className="flex flex-col items-start justify-star">
                            <p className="font-semibold text-lg mb-[-0.25rem]">R$ 99,90/mês</p>
                            <p>Individual</p>
                        </div>
                        <p className="text-sm text-[var(--content-tertiary)] mt-[0.4rem]">ou R$ 999,00/ano</p>
                    </div>
                    <div className="w-full flex flex-row items-start justify-start gap-3">
                        <div className="flex flex-col items-start justify-star">
                            <p className="font-semibold text-lg mb-[-0.25rem]">R$ 99,90/mês</p>
                            <p>Organizacional</p>
                        </div>
                        <p className="text-sm text-[var(--content-tertiary)] mt-[0.4rem]">ou R$ 999,00/ano</p>
                    </div>
                </div>
            </div>

            <div className="w-full flex flex-row justify-end">
                <Button variant="black" label="Compre agora" px={6} />
            </div>
        </div>
    )
}
import Button from './Button'

export default function PacoteCard () { {/* TORNAR DINAMICO */}
    return (
         <div className="w-80 h-116 bg-[var(--background-fixed-white)] rounded-3xl shadow-sm flex flex-col items-center justify-center p-6 gap-3">
            <div className="w-full h-full flex flex-col items-start justify-start gap-2">
                <img src="/assets/icons/app.svg" alt="Icone" className="w-12 h-12"/>
                <p className="font-semibold text-lg">Aikonic</p>
                <p className="w-full text-sm text-[var(--content-secondary)]">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                <p className="text-sm underline hover:cursor-pointer">Saiba mais</p>
            </div>

            <div className="w-full flex flex-row justify-between">
                <Button variant="black" label="Compre agora" px={6} />
                <div className="flex items-start justify-start mx-3">
                    <Button variant="clear-black" label="Solicitar demo" />
                </div>
            </div>
        </div>
    )
}
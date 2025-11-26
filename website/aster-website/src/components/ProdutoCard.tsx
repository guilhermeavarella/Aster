import { useNavigate } from 'react-router-dom';
import Button from './Button'

export default function ProdutoCard({
    id,
    name,
    descricao,
}: {
    id: string;
    name: string;
    descricao: string;
}) {
    const navigate = useNavigate();
    return (
         <div className="w-80 h-116 bg-[var(--background-fixed-white)] rounded-3xl shadow-sm flex flex-col items-center justify-center p-6 gap-3">
            <div className="w-full h-full flex flex-col items-start justify-start gap-2">
                <img src={`/assets/products/${id}.svg`} alt="Icone" className="w-12 h-12"/>
                <p className="font-semibold text-lg">{name}</p>
                <p className="w-full text-sm text-[var(--content-secondary)]">{descricao}</p>
                <p className="text-sm underline hover:cursor-pointer">Saiba mais</p>
            </div>

            <div className="w-full flex flex-row justify-between">
                <Button variant="black" label="Compre agora" px={6} onClick={() => {navigate('/produtos')}} />
                <div className="flex items-start justify-start mx-3">
                    <Button variant="clear-black" label="Solicitar demo" onClick={() => {navigate('/produtos')}} />
                </div>
            </div>
        </div>
    )
}
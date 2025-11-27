import { useNavigate } from 'react-router-dom';
import Button from '../components/Button'

export default function PacoteCard({
    name,
    individual,
    produtos,
    organizacional
}: {
    name: string;
    individual: number;
    produtos: {};
    organizacional: number;
}) {
    const navigate = useNavigate();
    return (
        <div className="w-80 h-116 bg-[var(--background-fixed-white)] rounded-3xl shadow-sm flex flex-col items-center justify-center p-6 gap-3">
            <p className="font-semibold text-2xl text-center">{name}</p>
            <div className="w-full h-full flex flex-col items-center justify-between">
                <div className="w-full flex flex-col items-center justify-start gap-2">
                    {produtos.slice(0, 3).map((produto: any, index: number) => {
                        const isThird = index === 2 && produtos.length > 3;
                        return (
                            <div key={produto.produtoId} className="w-full flex flex-row items-center justify-start gap-3">
                                <img src={`/assets/products/${produto.produtoId}.svg`} alt="Icone" className="w-12 h-12" />
                                <p className="font-semibold text-lg">
                                    {produto.nomeProduto}{isThird ? " ..." : ""}
                                </p>
                            </div>
                        );
                    })}
                </div>
                
                <div className="w-full flex flex-col gap-1 mb-6">
                    <p className="w-full text-sm text-[var(--content-secondary)]">Todos os recursos de {name} por:</p>
                    <div className="w-full flex flex-row items-start justify-start gap-2">
                        <div className="flex flex-col items-start justify-star">
                            <p className="font-semibold text-lg mb-[-0.25rem]">R$ {Intl.NumberFormat("pt-BR", { minimumFractionDigits: 2 }).format(individual)}/mês</p>
                            <p>Individual</p>
                        </div>
                        <p className="text-sm text-[var(--content-tertiary)] mt-[0.4rem]">ou R$ {Intl.NumberFormat("pt-BR", { minimumFractionDigits: 2 }).format(individual * 10)}/ano</p>
                    </div>
                    <div className="w-full flex flex-row items-start justify-start gap-2">
                        <div className="flex flex-col items-start justify-star">
                            <p className="font-semibold text-lg mb-[-0.25rem]">R$ {Intl.NumberFormat("pt-BR", { minimumFractionDigits: 2 }).format(organizacional)}/mês</p>
                            <p>Organizacional</p>
                        </div>
                        <p className="text-sm text-[var(--content-tertiary)] mt-[0.4rem]">ou R$ {Intl.NumberFormat("pt-BR", { minimumFractionDigits: 2 }).format(organizacional * 10)}/ano</p>
                    </div>
                </div>
            </div>

            <div className="w-full flex flex-row justify-end">
                <Button variant="black" label="Compre agora" px={6} onClick={() => {navigate('/produtos')}} />
            </div>
        </div>
    )
}
import { useState, useEffect } from "react";
import Button from "../components/Button"
import PacoteCard from "../components/PacoteCard";
import ProdutoCard from "../components/ProdutoCard";
import api from "../services/api"

export default function Produtos () {
    const [ produtos, setProdutos ] =  useState();
    const [ pacotes, setPacotes ] =  useState();
    const [ currentCategoria, setCurrentCategoria ] = useState("Todos");

    const fetchProdutos = async () => {
        try {
            const response = await api.get('/galeria/produtos');
            setProdutos(response.data);
        } catch (error) {
            console.error('Erro ao buscar produtos:', error);
        }
    }

    const fetchPacotes = async () => {
        try {
            const response = await api.get('/galeria/pacotes');
            setPacotes(response.data);
        } catch (error) {
            console.error('Erro ao buscar pacotes:', error);
        }
    }

    const handleCategoriaChange = (categoria: string) => {
        setCurrentCategoria(categoria);
    }

    useEffect(() => {
        fetchProdutos();
        fetchPacotes();
    }, []);

    if (!produtos || !pacotes) {
        return <div>Carregando...</div>;
    }

    return (
        <section className="w-full bg-gradient-to-tr from-[var(--brand-blue)] via-[var(--brand-lavender)] to-[var(--brand-pink)] flex flex-col items-center justify-start">
            <div className="w-full h-16 flex items-center justify-center text-center"> {/* header area */} </div>

            <div className="w-full flex flex-col items-center justify-center py-32 gap-4">
                <h2>Explore todos os produtos Aster. Crie tudo que puder imaginar.</h2>
                <p className="max-w-180 text-center">A Aster reúne em um único ecossistema tudo o que você precisa para criar: edição avançada de imagens, design gráfico inteligente, animação fluida, efeitos visuais e produção multimídia completa. <br/> Cada software foi projetado para oferecer performance, simplicidade e liberdade criativa.</p>
            </div>

            <div className="w-full bg-[var(--background-light)] flex flex-row items-start justify-center p-32 gap-32">
                <div className="flex flex-col items-start justify-start gap-6">
                    <p>Filtre por categoria</p>
                    <div className="flex flex-col items-start justify-start gap-1">
                        {currentCategoria === "Todos" ? (
                        <button className="w-64 h-10.5 bg-[var(--brand-lavender)]/50 rounded-md px-4 hover:cursor-pointer" key="todos" onClick={() => handleCategoriaChange("Todos")}>
                            <p className="font-semibold text-left justify-center">
                                Todos
                            </p>
                        </button>
                        ) : (
                        <button className="w-64 h-10.5 rounded-md px-4 hover:cursor-pointer" key="todos" onClick={() => handleCategoriaChange("Todos")}>
                            <p className="font-semibold text-left justify-center">
                                Todos
                            </p>
                        </button>
                        )}
                        {pacotes.map((pacote, index) => ( 
                            (index >= 8) ? null :
                                (!pacote.nome.includes(currentCategoria)) ? (
                                    <button className="w-64 h-10.5 rounded-md px-4 hover:cursor-pointer" key={pacote.nome} onClick={() => handleCategoriaChange(pacote.nome)}>
                                        <p className="font-semibold text-left justify-center">
                                            {pacote.nome}
                                        </p>
                                    </button>
                                ) : (
                                    <button className="w-64 h-10.5 bg-[var(--brand-lavender)]/50 rounded-md px-4 hover:cursor-pointer" key={pacote.nome} onClick={() => handleCategoriaChange(pacote.nome)}>
                                        <p className="font-semibold text-left justify-center">
                                            {pacote.nome}
                                        </p>
                                    </button>
                        )))}
                    </div>
                </div>

                <div className="grid grid-cols-4 gap-6">
                    {produtos.map((produto) => (
                        (!produto.categorias.includes(currentCategoria)) && currentCategoria !== "Todos" ? null :
                        <ProdutoCard 
                            key={produto.nome}
                            id={produto.id}
                            name={produto.nome}
                            descricao={produto.descricao}
                        />
                    ))}
                </div>
            </div>

            <div className="w-full bg-gradient-to-br from-[#B273D5] via-[#9779CD] to-[#807AD0] flex flex-col items-center justify-center px-32 py-24 gap-8">
                <h4 className="text-center text-[var(--content-inverse)]">Está em dúvida? Solicite uma demo. <br/> Experimente gratuitamente e descubra por que milhares de criadores estão migrando para a próxima geração de softwares de criação.</h4>
            </div>

            <div className="w-full bg-[var(--background-light)] flex flex-col items-center justify-start p-32 gap-12">
                <div className="w-full flex flex-col items-center justify-center text-center gap-3">
                    <h4 className="text-3xl">Nossos pacotes</h4>
                    <p>Adquira um de nossos pacotes e economize! Junte-se a milhares de criadores que já estão transformando suas ideias em realidade com a Aster.</p>
                </div>
                <div className="flex flex-col gap-9">
                    <div className="grid grid-cols-4 gap-9">
                        {pacotes.map((pacote, index) => ( 
                            pacote.nome.includes("Whole Bundle") || index >= 8 ? null :
                            <PacoteCard 
                                key={pacote.nome}
                                name={pacote.nome}
                                produtos={pacote.produtos}
                                individual={pacote.precoIndividual}
                                organizacional={pacote.precoOrganizacional}
                            />
                        ))}
                    </div>
                        {console.log(pacotes)}
                    <div className="w-347 h-130 bg-[var(--background-fixed-white)] rounded-3xl shadow-sm flex flex-col items-center justify-center p-6 gap-3">
                        <p className="font-semibold text-2xl text-center">Whole Bundle</p>
                        <div className="w-full h-full flex flex-col items-center justify-between">
                            <div className="w-full flex flex-row items-start justify-between gap-4">
                                <div className="w-full flex flex-col items-center justify-start gap-2">
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P001.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[0].nome}</p>
                                    </div>
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P002.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[1].nome}</p>
                                    </div>
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P003.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[2].nome}</p>
                                    </div>
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P005.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[3].nome}</p>
                                    </div>
                                </div>
                                
                                <div className="w-full flex flex-col items-center justify-start gap-2">
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P006.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[4].nome}</p>
                                    </div>
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P007.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[5].nome}</p>
                                    </div>
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P008.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[6].nome}</p>
                                    </div>
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P009.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[7].nome}</p>
                                    </div>
                                </div>

                                <div className="w-full flex flex-col items-center justify-start gap-2">
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P010.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[8].nome}</p>
                                    </div>
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P011.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[9].nome}</p>
                                    </div>
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P012.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[10].nome}</p>
                                    </div>
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P013.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[11].nome}</p>
                                    </div>
                                </div>

                                <div className="w-full flex flex-col items-center justify-start gap-2">
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P014.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[12].nome}</p>
                                    </div>
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P015.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[13].nome}</p>
                                    </div>
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P016.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[14].nome}</p>
                                    </div>
                                    <div className="w-full flex flex-row items-center justify-start gap-2">
                                        <img src="/assets/products/P017.svg" alt="Icone" className="w-12 h-12"/>
                                        <p className="font-semibold text-lg">{produtos[15].nome}</p>
                                    </div>
                                </div>
                            </div>
                            
                            <div className="w-full flex flex-col gap-1 mb-6">
                                <p className="w-full text-sm text-[var(--content-secondary)]">Adquira a coleção completa da Aster por:</p>
                                <div className="w-full flex flex-row items-start justify-start gap-3">
                                    <div className="flex flex-col items-start justify-star">
                                        <p className="font-semibold text-lg mb-[-0.25rem]">R$ {Intl.NumberFormat("pt-BR", { minimumFractionDigits: 2 }).format(pacotes[8].precoIndividual)}/mês</p>
                                        <p>Individual</p>
                                    </div>
                                    <p className="text-sm text-[var(--content-tertiary)] mt-[0.4rem]">ou R$ {Intl.NumberFormat("pt-BR", { minimumFractionDigits: 2 }).format(pacotes[8].precoIndividual * 10)}/ano</p>
                                </div>
                                <div className="w-full flex flex-row items-start justify-start gap-3">
                                    <div className="flex flex-col items-start justify-star">
                                        <p className="font-semibold text-lg mb-[-0.25rem]">R$ {Intl.NumberFormat("pt-BR", { minimumFractionDigits: 2 }).format(pacotes[8].precoOrganizacional)}/mês</p>
                                        <p>Organizacional</p>
                                    </div>
                                    <p className="text-sm text-[var(--content-tertiary)] mt-[0.4rem]">ou R$ {Intl.NumberFormat("pt-BR", { minimumFractionDigits: 2 }).format(pacotes[8].precoOrganizacional * 10)}/ano</p>
                                </div>
                            </div>
                        </div>
            
                        <div className="w-full flex flex-row justify-end">
                            <Button variant="black" label="Compre agora" px={6} />
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}
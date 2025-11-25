import { useNavigate } from "react-router-dom";
import Button from "../components/Button";
import PacoteCard from "../components/PacoteCard";

export default function Home() {
    const navigate = useNavigate();
    
    // Mock temporario para testes
    const mockProdutos = [
        {
            "id": "P001",
            "nome": "Nova",
            "status": "Comercializável",
            "descricaoBreve": "Software Nova",
            "descricao": "Software Nova completo",
            "icone": "nova.png",
            "categorias": [
            "Design"
            ]
        },
        {
            "id": "P002",
            "nome": "Nova2",
            "status": "Comercializável",
            "descricaoBreve": "Software Nova",
            "descricao": "Software Nova completo",
            "icone": "nova.png",
            "categorias": [
            "Design"
            ]
        },
        {
            "id": "P003",
            "nome": "Nova3",
            "status": "Comercializável",
            "descricaoBreve": "Software Nova",
            "descricao": "Software Nova completo",
            "icone": "nova.png",
            "categorias": [
            "Design"
            ]
        },
        {
            "id": "P004",
            "nome": "Nova4",
            "status": "Comercializável",
            "descricaoBreve": "Software Nova",
            "descricao": "Software Nova completo",
            "icone": "nova.png",
            "categorias": [
            "Design"
            ]
        }
    ];

    return (
        <section className="w-full flex flex-col items-center justify-start">
            <section className="w-full bg-gradient-to-br from-[#B273D5] via-[#9779CD] to-[#807AD0] flex flex-col items-center justify-start px-36">
                <div className="w-full h-16 flex items-center justify-center text-center"> {/* header area */} </div>
                <div className="w-full flex flex-row items-start justify-between py-64">
                    <p className="h-full max-w-128 text-2xl text-[var(--content-inverse)] text-start flex flex-col items-start justify-start">
                        A gama completa de recursos para designers, artistas, videomakers, criadores independentes e profissionais que querem transformar ideias em experiências visuais extraordinárias. Um ecossistema poderoso, intuitivo e inovador - criado para quem vive de criatividade.
                    </p>
                    <p className="h-full font-semibold text-6xl text-[var(--content-inverse)] text-end flex flex-col items-end justify-end mt-32 mb-8">
                        Levando suas ideias <br/> aos astros. <br/> Tudo que você precisa
                    </p>
                </div>
            </section>

            <section className="w-full h-220 bg-[var(--background-light)] flex flex-col items-center justify-center">
                <div className="absolute mt-[-24rem] bg-[var(--background-fixed-white)] shadow-lg flex flex-col items-center justify-center rounded-3xl py-9 px-18 gap-9">
                    <p className="text-2xl">Explore nossos produtos mais populares. Crie tudo que puder imaginar.</p>
                    <div className="flex flex-col items-center justify-center gap-6">

                        <div className="flex flex-row items-center justify-center gap-6">
                            <div className="w-76 h-76 shadow-md rounded-xl flex flex-col items-start justify-between bg-gradient-to-r from-[#FF9A8B] to-[#FF99AC] p-8">
                                <div className="w-full flex flex-col items-start justify-start gap-4">
                                    <div className="w-full flex flex-row items-start justify-start gap-4">
                                        <img src={`/assets/products/${mockProdutos[0].id}.svg`} alt="img" className="w-12 h-12"/>
                                        <p className="font-semibold text-xl text-[var(--content-inverse)]">{mockProdutos[0].nome}</p>
                                    </div>
                                    <p className="w-full max-w-105 text-md text-[var(--content-inverse)]">{mockProdutos[0].descricaoBreve}</p>
                                </div>

                                <div className="w-full flex flex-row items-start justify-start gap-4">
                                    <Button variant="black" label="Saiba mais" onClick={() => {navigate('/produtos')}} />
                                    <Button variant="white" label="Solicitar demo" onClick={() => {navigate('/produtos')}} />
                                </div>
                            </div>
                    
                            <div className="w-182 h-76 shadow-md rounded-xl flex flex-col items-start justify-between bg-gradient-to-r from-[#FF9A8B] to-[#FF99AC] p-8">
                                <div className="w-full flex flex-col items-start justify-start gap-4">
                                    <div className="w-full flex flex-row items-start justify-start gap-4">
                                        <img src={`/assets/products/${mockProdutos[1].id}.svg`} alt="img" className="w-12 h-12"/>
                                        <p className="font-semibold text-xl text-[var(--content-inverse)]">{mockProdutos[1].nome}</p>
                                    </div>
                                    <p className="w-full max-w-105 text-md text-[var(--content-inverse)]">{mockProdutos[1].descricaoBreve}</p>
                                </div>

                                <div className="w-full flex flex-row items-start justify-start gap-4">
                                    <Button variant="black" label="Saiba mais" onClick={() => {navigate('/produtos')}} />
                                    <Button variant="white" label="Solicitar demo" onClick={() => {navigate('/produtos')}} />
                                </div>
                            </div>
                        </div>
                        
                        <div className="flex flex-row items-center justify-center gap-6">
                            <div className="w-182 h-76 shadow-md rounded-xl flex flex-col items-start justify-between bg-gradient-to-r from-[#FF9A8B] to-[#FF99AC] p-8">
                                <div className="w-full flex flex-col items-start justify-start gap-4">
                                    <div className="w-full flex flex-row items-start justify-start gap-4">
                                        <img src={`/assets/products/${mockProdutos[2].id}.svg`} alt="img" className="w-12 h-12"/>
                                        <p className="font-semibold text-xl text-[var(--content-inverse)]">{mockProdutos[2].nome}</p>
                                    </div>
                                    <p className="w-full max-w-105 text-md text-[var(--content-inverse)]">{mockProdutos[2].descricaoBreve}</p>
                                </div>

                                <div className="w-full flex flex-row items-start justify-start gap-4">
                                    <Button variant="black" label="Saiba mais" onClick={() => {navigate('/produtos')}} />
                                    <Button variant="white" label="Solicitar demo" onClick={() => {navigate('/produtos')}} />
                                </div>
                            </div>

                            <div className="w-76 h-76 shadow-md rounded-xl flex flex-col items-start justify-between bg-gradient-to-r from-[#FF9A8B] to-[#FF99AC] p-8">
                                <div className="w-full flex flex-col items-start justify-start gap-4">
                                    <div className="w-full flex flex-row items-start justify-start gap-4">
                                        <img src={`/assets/products/${mockProdutos[3].id}.svg`} alt="img" className="w-12 h-12"/>
                                        <p className="font-semibold text-xl text-[var(--content-inverse)]">{mockProdutos[3].nome}</p>
                                    </div>
                                    <p className="w-full max-w-105 text-md text-[var(--content-inverse)]">{mockProdutos[3].descricaoBreve}</p>
                                </div>

                                <div className="w-full flex flex-row items-start justify-start gap-4">
                                    <Button variant="black" label="Saiba mais" onClick={() => {navigate('/produtos')}} />
                                    <Button variant="white" label="Solicitar demo" onClick={() => {navigate('/produtos')}} />
                                </div>
                            </div>
                        </div>
                    </div>

                    <Button variant="black" label="Ver todos os produtos Aster" px={6} onClick={() => {navigate('/produtos')}} />
                </div>
            </section>

            <section className="w-full bg-gradient-to-b from-[#66578E] to-[#50677E] flex flex-col items-center justify-center px-32 py-24 gap-8">
                <h4 className="text-center text-[var(--content-inverse)]">Artista, criador independente ou organização <br/> Com a Aster, suas criações alcançam as estrelas!</h4>
                <div className="w-full max-w-320 flex flex-col items-center justify-center text-center text-[var(--content-inverse)] gap-3">
                    <p>Seja você iniciante ou profissional, oferecemos fluxos de trabalho rápidos e inteligentes que elevam seu processo criativo. Templates dinâmicos, ferramentas assistidas por IA, automações inteligentes e uma interface pensada para quem quer criar - não lutar com o software.</p>
                    <p className="mx-24">A Aster nasce com engenharia de ponta, processamento otimizado e recursos que acompanham você em qualquer projeto — do simples ao cinematográfico. Renderização avançada, camadas ilimitadas, efeitos em tempo real e integração total entre ferramentas.</p>
                </div>
            </section>

            <section className="w-full bg-[var(--background-light)] flex flex-col items-center justify-center py-16 gap-16">
                <div className="w-full flex flex-col items-center justify-center text-center gap-3">
                    <h4 className="text-3xl">Pronto para levar sua criatividade às estrelas?</h4>
                    <p>Adquira um de nossos pacotes e economize! Junte-se a milhares de criadores que já estão transformando suas ideias em realidade com a Aster.</p>
                </div>

                {/* FAZER DINAMICO */}
                <div className="w-full flex flex-row items-center justify-center gap-9">
                    <PacoteCard />
                    <PacoteCard />
                    <PacoteCard />
                    <PacoteCard />
                </div>
               
                
                <Button variant="black" label="Ver todos os pacotes Aster" px={6} onClick={() => {navigate('/produtos')}} />
            </section>
        </section>
    )
}
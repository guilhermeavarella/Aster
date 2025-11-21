import { useEffect, useState } from "react";
import ProfileMenu from "../../components/ProfileMenu";
import Glass from "../../components/Glass";
import { BarChart } from '@mui/x-charts/BarChart';
import { LineChart } from '@mui/x-charts/LineChart';

export default function Desempenho() {
    const [vendasProdutoData, setVendasProdutoData] = useState();
    const [vendasPacoteData, setVendasPacoteData] = useState();
    const [tempoProdutoData, setTempoProdutoData] = useState();
    const [tempoPacoteData, setTempoPacoteData] = useState();

    const [produtoSelecionado, setProdutoSelecionado] = useState("Nova");
    const [pacoteSelecionado, setPacoteSelecionado] = useState("Design");

    const [colorScheme1, setColorScheme1] = useState();
    const [colorScheme2, setColorScheme2] = useState();
    const [colorScheme3, setColorScheme3] = useState();

    const fetchData = async (request: string) => {
        try {
            const response = await api.get(`/painel/d/estrategia/${request}`);
            return response.data;
        } catch (error) {
            console.error(`Error fetching ${request}:`, error);
        }
    };

    // FETCH MOCKS
    const fetchJson = async (path: string) => {
        const response = await fetch(path);
        if (!response.ok) throw new Error(`Erro ao buscar: ${path}`);
        return response.json();
    };

    function recortarArray(dados: Record<string, any[]>, chave: string) {
        return dados[chave] ?? [];
    }

    function prepararSerie(serie: any[]) {
        return serie.map((item) => ({
            ...item,
            data: new Date(item.data).getTime(),
        }));
    }

    useEffect(() => {
        async function carregarDados() {
            const [
                vendasProduto,
                vendasPacote,
                tempoProduto,
                tempoPacote,
                palette1,
               palette2,
                palette3,
            ] = await Promise.all([
                // MOCKS
                fetchJson(`/mocks/metricas-painel/vendasProduto.json`),
                fetchJson(`/mocks/metricas-painel/vendasPacote.json`),
                fetchJson(`/mocks/metricas-painel/tempoProduto.json`),
                fetchJson(`/mocks/metricas-painel/tempoPacote.json`),
                
                fetchJson(`/src/assets/files/color-palettes/chartPalette3.json`),
                fetchJson(`/src/assets/files/color-palettes/chartPalette1.json`),
                fetchJson(`/src/assets/files/color-palettes/chartPalette2.json`),
            ]);

            setVendasProdutoData(vendasProduto);
            setVendasPacoteData(vendasPacote);
            setTempoProdutoData(tempoProduto);
            setTempoPacoteData(tempoPacote);

            setColorScheme1(palette1);
            setColorScheme2(palette2);
            setColorScheme3(palette3);
        }

        carregarDados();
    }, []);

    if (
        !vendasProdutoData ||
        !vendasPacoteData ||
        !tempoProdutoData ||
        !tempoPacoteData ||
        !colorScheme1 ||
        !colorScheme2 ||
        !colorScheme3
    ) {
        return <div>Carregando...</div>;
    }

    const dateFormatter = (value: number) => {
        return new Date(value).toLocaleDateString("pt-BR", {
            month: "long",
            year: "numeric",
        });
    };

    return (
        <section className="w-full h-full flex flex-col items-center justify-start gap-6">
            <section className="w-full flex flex-row items-center justify-end">
                <ProfileMenu />
            </section>

            <section className="w-full flex flex-col 2xl:flex-row items-start justify-center gap-6"> 
                <section className="w-full flex flex-col items-start justify-center gap-6">
                    <Glass className="min-w-full rounded-t-3xl rounded-b-3xl">
                        <div className="ml-3 mb-2">
                            <h5 className="text-xl font-semibold">Vendas totais dos Produtos</h5>
                            <p className="text-sm">Coloque o mouse sobre as barras para mais detalhes</p>
                        </div>
                        <BarChart
                            dataset={vendasProdutoData}
                            xAxis={[{ 
                                scaleType: 'band', 
                                dataKey: 'produto', 
                                colorMap: {
                                    type: 'ordinal',
                                    values: vendasProdutoData.findIndex((d: any) => d.produto === 'Aikonic') !== -1 ? vendasProdutoData.map((d: any) => d.produto) : [],
                                    colors: colorScheme1
                                }
                            }]}
                            series={[{ dataKey: 'vendas', label: 'Vendas' }]}
                            height={320}
                            hideLegend={true}
                        />
                    </Glass>

                    <Glass className="min-w-full rounded-t-3xl rounded-b-3xl" >
                        <div className="ml-3 mb-2">
                            <h5 className="text-xl font-semibold">Vendas totais dos Pacotes</h5>
                            <p className="text-sm">Coloque o mouse sobre as barras para mais detalhes</p>
                        </div>
                        <BarChart
                            dataset={vendasPacoteData}
                            xAxis={[{ scaleType: 'linear', }]}
                            yAxis={ [{ 
                                scaleType: 'band', 
                                dataKey: 'pacote', 
                                colorMap: {
                                    type: 'ordinal',
                                    values: vendasPacoteData.findIndex((d: any) => d.pacote === 'Aikonic') !== -1 ? vendasPacoteData.map((d: any) => d.pacote) : [],
                                    colors: colorScheme1
                                },
                                width: 96
                            }]}
                            series={[{ dataKey: 'vendas', label: 'Vendas' }]}
                            hideLegend={true}
                            layout="horizontal"
                            height={vendasPacoteData.length * 30}
                            colors= {colorScheme1}
                            className="ml-[-3rem]"
                        />
                    </Glass>
                </section>

                <section className="w-full flex flex-col items-start justify-center gap-6">
                    <Glass className="min-w-full rounded-t-3xl rounded-b-3xl">
                        <div className="ml-3 mb-2">
                            <h5 className="text-xl font-semibold">Desempenho do Produto</h5>
                            <p className="text-sm">Selecione o produto que deseja visualizar</p>
                        </div>
                        <LineChart
                            dataset={prepararSerie(recortarArray(tempoProdutoData, produtoSelecionado))}
                            xAxis={[{
                                dataKey: 'data',
                                scaleType: 'time',
                                valueFormatter: dateFormatter,
                            }]}
                            series={[{
                                dataKey: 'vendas',
                                showMark: false,
                                color: colorScheme2[vendasProdutoData.findIndex((d: any) => d.produto === produtoSelecionado) % colorScheme2.length]
                            }]}
                            height={240}
                            grid={{ vertical: true, horizontal: true }}
                        />

                        <div className="flex gap-2 mt-4 pb-3 flex-wrap justify-center">
                            {Object.keys(tempoProdutoData).map((produto) => (
                                <button
                                    key={produto}
                                    onClick={() => setProdutoSelecionado(produto)}
                                    className={`
                                        px-4 py-1 rounded-lg
                                        ${produtoSelecionado === produto 
                                            ? "bg-[var(--content-secondary)] font-semibold text-[var(--content-inverse)]" 
                                            : "bg-[var(--background-fixed-white)] text-[var(--content-primary)] hover:bg-gray-100"
                                        }
                                    `}
                                >
                                    {produto}
                                </button>
                            ))}
                        </div>
                    </Glass>

                    <Glass className="min-w-full rounded-t-3xl rounded-b-3xl">
                        <div className="ml-3 mb-2">
                            <h5 className="text-xl font-semibold">Desempenho do Pacote</h5>
                            <p className="text-sm">Selecione o pacote que deseja visualizar</p>
                        </div>
                        <LineChart
                            dataset={prepararSerie(recortarArray(tempoPacoteData, pacoteSelecionado))}
                            xAxis={[{
                                dataKey: 'data',
                                scaleType: 'time',
                                valueFormatter: dateFormatter,
                            }]}
                            series={[{
                                dataKey: 'vendas',
                                showMark: false,
                                color: colorScheme2[vendasPacoteData.findIndex((d: any) => d.pacote === pacoteSelecionado) % colorScheme2.length],
                            }]}
                            height={240}
                            grid={{ vertical: true, horizontal: true }}
                        />

                        <div className="flex gap-2 mt-4 pb-3 flex-wrap justify-center">
                            {Object.keys(tempoPacoteData).map((pacote) => (
                                <button
                                    key={pacote}
                                    onClick={() => setPacoteSelecionado(pacote)}
                                    className={`
                                        px-4 py-1 rounded-lg
                                        ${pacoteSelecionado === pacote 
                                            ? "bg-[var(--content-secondary)] font-semibold text-[var(--content-inverse)]" 
                                            : "bg-[var(--background-fixed-white)] text-[var(--content-primary)] hover:bg-gray-100"
                                        }
                                    `}
                                >
                                    {pacote}
                                </button>
                            ))}
                        </div>
                    </Glass>

                    <Glass className="min-w-full rounded-t-3xl rounded-b-3xl">
                        Alguma coisinha aqui
                    </Glass>
                </section>
            </section>
            


        </section>
    );
}
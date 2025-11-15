import type { ProdutoFormSchemaType } from "../components/forms/ProdutoForm";
import api from "../services/api";

export async function CriarProduto(data: ProdutoFormSchemaType) {
    try {
        await api.post('/operacoes/produto', data)
    } catch(error) {
        console.log('Erro na rquisição')
        throw error
    }
}

export async function EditarProduto(data: ProdutoFormSchemaType) {
    try {
        await api.patch(`/operacoes/produto/${data.id}`, data)
    } catch(error) {
        console.log('Erro na rquisição')
        throw error
    }
}

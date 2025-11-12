// ----- To-do -----
// 1. Validação com zod
// 2. Estrutura do form com MUI
// 3. HandleSubmit com o hook de CREATE/EDIT
// 4. Caso a entidade contenha chaves estrangeiras com poucos registro, fazem select
// 5. Utilizar o RHF para controlar o form
// 6. Colocar o router  
// ----- * -----

import { useForm } from 'react-hook-form'
import type { SubmitHandler } from 'react-hook-form'
import * as z from 'zod'
import { zodResolver } from '@hookform/resolvers/zod'
import type { Produto } from '../types/produto.ts'
import { Stack, Card, CardHeader, Typography, MenuItem, Box } from '@mui/material'
import StyledInputText from './mui/InputText.tsx'
import StyledInputTextArea from './mui/InputTextArea.tsx'
import StyledInputSelect from './mui/InputSelect.tsx'
import Button from './Button.tsx'
import { useNavigate } from 'react-router'

// Schema para validação da entidade
const ProdutoFormSchema = z.object({
    id: z.string().min(1, 'Campo obrigatório'),
    nome: z.string().min(1, 'Campo obrigatório'),
    categoria: z.string().min(1, 'Campo obrigatório'),
    versao_atual: z.string().min(1, 'Campo obrigatório'),
    descricao_breve: z.string().min(1, 'Campo obrigatório'),
    descricao: z.string().min(1, 'Campo obrigatório'),
})

type ProdutoFormSchemaType = z.infer<typeof ProdutoFormSchema>

type produtoProps = {
    produto?: Produto
}

// Get das categorias para quando o hook ficar pronto
// const { categorias } = useGetCategorias()

// Get das versões para quando o hook ficar pronto
// const { versoes } = useGetVersoes()

// Props do produto em caso de edit
export default function ProdutoForm({ produto }: produtoProps) {
    // Router
    const navigate = useNavigate()

    // Valores padrão do formulário
    const defaultValues: ProdutoFormSchemaType = {
        id: '',
        nome: '',
        categoria: '',
        versao_atual: '',
        descricao_breve: '',
        descricao: ''
    }

    // useForm
    const methods = useForm<ProdutoFormSchemaType>({
        mode: 'all',
        resolver: zodResolver(ProdutoFormSchema),
        defaultValues,
        values: produto && {
            id: produto.id,
            nome: produto.nome,
            categoria: produto.categoria,
            versao_atual: produto.versao_atual,
            descricao_breve: produto.descricao_breve,
            descricao: produto.descricao
        }
    })

    const { handleSubmit, register, reset } = methods

    // Handler criar/editar
    const handleCreateEdit: SubmitHandler<ProdutoFormSchemaType> = (data) => {
        try {
            if (produto) {
                // Hook de edit
                console.log('Edit - Payload enviado')
            } else {
                console.log('Create - Payload enviaod')
                // Hook de criar
            }
            reset()
            navigate("")
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <form onSubmit={handleSubmit(handleCreateEdit)}>
            <Card sx={{ m: 3, p: 3 }}>
                <CardHeader title='Criar - Produto' sx={{ fontWeight: 'bold', px: 0, py: 1 }}>
                </CardHeader>
                <Typography sx={{ pb: 2 }}>
                    Para criar um registro preencha as informações abaixo:
                </Typography>
                <Stack spacing={4}>
                    <Stack sx={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                        <StyledInputText
                            label='ID'
                            placeholder='ID'
                            slotProps={{ inputLabel: { shrink: true } }}
                            {...register('id')}
                            sx={{ width: '48%' }}
                        >
                        </StyledInputText>
                        <StyledInputText
                            label='Nome'
                            placeholder='Nome'
                            slotProps={{ inputLabel: { shrink: true } }}
                            {...register('nome')}
                            sx={{ width: '48%' }}
                        >
                        </StyledInputText>
                    </Stack>
                    <Stack sx={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                        <StyledInputSelect
                            label='Versão atual'
                            {...register('versao_atual')}
                            sx={{ width: '48%' }}
                        >
                            <MenuItem value=''>
                                Selecione uma da opções abaixo
                            </MenuItem>
                            <MenuItem value='a'>
                                a
                            </MenuItem>
                            {/* {versoes.map((versao) => {
                                <MenuItem key={versao.id} value={versao.name}>
                                    {versao.name}
                                </MenuItem>
                            })} */}
                        </StyledInputSelect>
                        <StyledInputSelect
                            label='Categoria'
                            {...register('categoria')}
                            sx={{ width: '48%' }}
                        >
                            <MenuItem value=''>
                                Selecione uma da opções abaixo
                            </MenuItem>
                            <MenuItem value='b'>
                                b
                            </MenuItem>
                            {/* {categorias.map((categoria) => {
                                <MenuItem key={categoria.id} value={categoria.name}>
                                    {categoria.name}
                                </MenuItem>
                            })} */}
                        </StyledInputSelect>
                    </Stack>
                    <StyledInputText
                        label='Descrição breve'
                        placeholder='Descrição breve'
                        slotProps={{ inputLabel: { shrink: true } }}
                        {...register('descricao_breve')}
                        sx={{ width: '100%' }}
                    >
                    </StyledInputText>
                    <StyledInputTextArea
                        label='Descrição'
                        placeholder='Descrição'
                        slotProps={{ inputLabel: { shrink: true } }}
                        {...register('descricao')}
                        sx={{ width: '100%' }}
                    >
                    </StyledInputTextArea>
                    <Box sx={{display: 'flex', flexDirection: 'row', justifyContent: 'flex-end'}}>
                        <Button
                            variant='black'
                            label={produto ? 'Editar ' : 'Criar '}
                            onClick={() => console.log('Payload enviado com sucesso')}
                        >
                        </Button>
                    </Box>
                </Stack>
            </Card>
        </form >
    )

}
// ----- To-do -----
// 1. Validação com zod ok
// 2. Estrutura do form com MUI ok
// 3. HandleSubmit com o hook de CREATE/EDIT ok
// 4. Caso a entidade contenha chaves estrangeiras com poucos registro, fazem select ok
// 5. Utilizar o RHF para controlar o form ok
// 6. Colocar o router ok
// ----- * -----

import { useForm } from 'react-hook-form'
import type { SubmitHandler } from 'react-hook-form'
import { Controller } from 'react-hook-form'
import * as z from 'zod'
import { zodResolver } from '@hookform/resolvers/zod'
import type { Produto } from '../../types/produto.ts'
import { Stack, Card, CardHeader, Typography, MenuItem, Box } from '@mui/material'
import StyledInputText from '../mui/InputText.tsx'
import StyledInputTextArea from '../mui/InputTextArea.tsx'
import StyledInputSelect from '../mui/InputSelect.tsx'
import StyledInputMultiSelect from '../mui/InputMultiSelect.tsx'
import Button from '../Button.tsx'
import { useNavigate } from 'react-router'
import { useEffect, useState } from 'react'
import { type SelectChangeEvent } from '@mui/material'
import Glass from '../Glass.tsx'
import ProfileMenu from '../ProfileMenu.tsx'
import { CriarProduto, EditarProduto } from '../../actions/Produto.ts'
import SubmitDialog from '../mui/SubmitDialog.tsx'

// Schema para validação da entidade
const ProdutoFormSchema = z.object({
    id: z.string().min(1, 'Campo obrigatório'),
    nome: z.string().min(1, 'Campo obrigatório'),
    status: z.string().min(1, 'Campo obrigatório'),
    descricaoBreve: z.string().min(1, 'Campo obrigatório'),
    descricaoCompleta: z.string().min(1, 'Campo obrigatório'),
    icone: z.string().min(1, 'Campo obrigatório'),
    categorias: z.array(z.string().min(1, 'Campo obrigatório'))
})

export type ProdutoFormSchemaType = z.infer<typeof ProdutoFormSchema>

type produtoProps = {
    produto?: Produto
}

// Props do produto em caso de edit
export default function ProdutoForm({ produto }: produtoProps) {
    // Router
    const navigate = useNavigate()

    // Valores padrão do formulário
    const defaultValues: ProdutoFormSchemaType = {
        id: '',
        nome: '',
        status: '',
        descricaoBreve: '',
        descricaoCompleta: '',
        icone: '',
        categorias: []
    }

    // useForm
    const methods = useForm<ProdutoFormSchemaType>({
        mode: 'all',
        resolver: zodResolver(ProdutoFormSchema),
        defaultValues,
        values: produto && {
            id: produto.id,
            nome: produto.nome,
            status: produto.status,
            descricaoBreve: produto.descricaoBreve,
            descricaoCompleta: produto.descricaoCompleta,
            icone: produto.icone,
            categorias: produto.categorias,
        }
    })

    // Methods do useForm
    const { handleSubmit, reset, control } = methods

    // Array de status
    const status: string[] = [
        'Em desenvolvimento',
        'Comercializável',
        'Descontinuado'
    ]

    // ----- multiSelect -----
    // Array de categorias
    const categorias: string[] = [
        'Design',
        'Social Media',
        'Fotos',
        'Vídeos',
        'Animação',
        'Ilustração',
        'Documentos', '3D'
    ]

    // Debug
    useEffect(() => {
        console.log(methods.getValues())
    }, [methods.watch()])

    // Handler criar/editar
    const handleCreateEdit: SubmitHandler<ProdutoFormSchemaType> = (async (data) => {
        try {
            if (produto) {
                // Hook de edit
                await EditarProduto(data)
                console.log('Edit - Payload enviado: ' + JSON.stringify(data))
            } else {
                await CriarProduto(data)
                console.log('Create - Payload enviaod: ' + JSON.stringify(data))
                // Hook de criar
            }
            reset()
            // navigate("")
        } catch (error) {
            console.log(error)
        }
    })

    return (
        <form onSubmit={handleSubmit(handleCreateEdit)}>
            <Stack sx={{ display: 'flex', flexDirection: 'column' }} spacing={4}>
                <section className="w-full flex flex-row items-center justify-center gap-6">
                    <Glass padding="p-3">
                        <div className="w-full min-h-10 min-w-[calc(100vw-33.25rem)] flex flex-row justify-center items-center">
                            <p className="font-semibold text-[var(--content-inverse)]">Criar registro</p>
                        </div>
                    </Glass>
                    <ProfileMenu />
                </section>
                <Card sx={{ p: 3, borderRadius: '30px', boxShadow: '10px 10px 4px 0 rgba(0, 0, 0, 0.25)' }}>
                    <CardHeader title='Criar - Produto' sx={{ fontWeight: 'bold', px: 0, pt: 1 }} titleTypographyProps={{
                        sx: { fontWeight: 'bold', fontSize: '40px', color: 'black' }
                    }}>
                    </CardHeader>
                    <Typography sx={{ pb: 5 }}>
                        Para criar um registro preencha as informações abaixo:
                    </Typography>
                    <Stack spacing={4}>
                        <Stack sx={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                            <Controller
                                name="id"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputText
                                        label="ID"
                                        placeholder="ID"
                                        value={field.value}
                                        onChange={field.onChange}
                                        onBlur={field.onBlur}
                                        inputRef={field.ref}
                                        slotProps={{ inputLabel: { shrink: true } }}
                                        sx={{ width: '48%' }}
                                    />
                                )}
                            />

                            <Controller
                                name="nome"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputText
                                        label="Nome"
                                        placeholder="Nome"
                                        value={field.value}
                                        onChange={field.onChange}
                                        onBlur={field.onBlur}
                                        inputRef={field.ref}
                                        slotProps={{ inputLabel: { shrink: true } }}
                                        sx={{ width: '48%' }}
                                    />
                                )}
                            />
                        </Stack>

                        <Stack sx={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                            <Controller
                                name="status"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputSelect
                                        label="Status"
                                        value={field.value}
                                        onChange={field.onChange}
                                        onBlur={field.onBlur}
                                        inputRef={field.ref}
                                        sx={{ width: '48%' }}
                                    >
                                        <MenuItem value="">Selecione uma da opções abaixo</MenuItem>
                                        {status.map((status, index) => (
                                            <MenuItem key={index} value={status}>
                                                {status}
                                            </MenuItem>
                                        ))}
                                    </StyledInputSelect>
                                )}
                            />

                            <Controller
                                name="categorias"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputMultiSelect
                                        label="Categorias"
                                        value={field.value || []}
                                        onChange={(e) => field.onChange(e.target.value)}
                                        sx={{ width: '48%' }}
                                    >
                                        <MenuItem value="">Selecione uma ou mais das opções abaixo</MenuItem>
                                        {categorias.map((categoria, index) => (
                                            <MenuItem key={index} value={categoria}>
                                                {categoria}
                                            </MenuItem>
                                        ))}
                                    </StyledInputMultiSelect>
                                )}
                            />
                        </Stack>

                        <Controller
                            name="descricaoBreve"
                            control={control}
                            render={({ field }) => (
                                <StyledInputText
                                    label="Descrição breve"
                                    placeholder="Descrição breve"
                                    value={field.value}
                                    onChange={field.onChange}
                                    onBlur={field.onBlur}
                                    inputRef={field.ref}
                                    slotProps={{ inputLabel: { shrink: true } }}
                                    sx={{ width: '100%' }}
                                />
                            )}
                        />

                        <Controller
                            name="icone"
                            control={control}
                            render={({ field }) => (
                                <StyledInputText
                                    label="Ícone"
                                    placeholder="Ícone"
                                    value={field.value}
                                    onChange={field.onChange}
                                    onBlur={field.onBlur}
                                    inputRef={field.ref}
                                    slotProps={{ inputLabel: { shrink: true } }}
                                    sx={{ width: '100%' }}
                                />
                            )}
                        />

                        <Controller
                            name="descricaoCompleta"
                            control={control}
                            render={({ field }) => (
                                <StyledInputTextArea
                                    label="Descrição completa"
                                    placeholder="Descrição completa"
                                    value={field.value}
                                    onChange={field.onChange}
                                    onBlur={field.onBlur}
                                    inputRef={field.ref}
                                    slotProps={{ inputLabel: { shrink: true } }}
                                    sx={{ width: '100%' }}
                                />
                            )}
                        />
                        <Box sx={{ display: 'flex', flexDirection: 'row', justifyContent: 'flex-end' }}>
                            <SubmitDialog label={produto ? 'Editar ' : 'Criar '} handleSubmit={handleSubmit(handleCreateEdit)} />
                        </Box>
                    </Stack>
                </Card>
            </Stack>
        </form >
    )

}
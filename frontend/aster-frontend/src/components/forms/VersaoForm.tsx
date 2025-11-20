// ----- To-do -----
// 1. Validação com zod ok
// 2. Estrutura do form com MUI
// 3. HandleSubmit com o hook de CREATE/EDIT
// 4. Caso a entidade contenha chaves estrangeiras com poucos registro, fazem select
// 5. Utilizar o RHF para controlar o form
// 6. Colocar o router  
// ----- * -----

import type { Versao } from '../../types/versao'
import type { SubmitHandler } from 'react-hook-form'
import { Controller } from 'react-hook-form'
import * as z from 'zod'
import { zodResolver } from '@hookform/resolvers/zod'
import { useNavigate } from 'react-router-dom'
import { useForm } from 'react-hook-form'
import { EditarVersao, CriarVersao } from '../../actions/Versao'
import { Stack, Card, CardHeader, Typography, MenuItem, Box } from '@mui/material'
import { ListProduto } from '../../actions/Produto.ts'
import StyledInputText from '../mui/InputText.tsx'
import StyledInputTextArea from '../mui/InputTextArea.tsx'
import StyledInputSelect from '../mui/InputSelect.tsx'
import StyledInputMultiSelect from '../mui/InputMultiSelect.tsx'
import Button from '../Button.tsx'
import { useEffect, useState } from 'react'
import { type SelectChangeEvent } from '@mui/material'
import Glass from '../Glass.tsx'
import ProfileMenu from '../ProfileMenu.tsx'
import type { ProdutoFormSchemaType } from './ProdutoForm.tsx'

const VersaoFormSchema = z.object({
    numeroVersao: z.string().length(7, 'Campo obrigatório'),
    produtoId: z.string().length(4, 'Campo obrigatório'),
    dataLancamento: z.string().min(1, 'Campo obrigatório'),
    arquivoInstalador: z.string().min(1, 'Campo obrigatório').max(50, 'Limite máximo de caracteres'),
    patchNotes: z.string().min(1, 'Campo obrigatório').max(300, 'Limite máximo de caracteres')
})

export type VersaoFormSchemaType = z.infer<typeof VersaoFormSchema>

type versaoProps = {
    versao?: Versao
}

export default function VersaoForm({ versao }: versaoProps) {
    // Router
    const navigate = useNavigate()

    // Valores padrão do formulário
    const defaultValues: VersaoFormSchemaType = {
        numeroVersao: '',
        produtoId: '',
        dataLancamento: '',
        arquivoInstalador: '',
        patchNotes: '',
    }

    // useForm
    const methods = useForm<VersaoFormSchemaType>({
        mode: 'all',
        resolver: zodResolver(VersaoFormSchema),
        defaultValues,
        values: versao && {
            numeroVersao: versao.numeroVersao,
            produtoId: versao.produtoId,
            dataLancamento: versao.dataLancamento,
            arquivoInstalador: versao.arquivoInstalador,
            patchNotes: versao.patchNotes
        }
    })

    // Methods do useForm
    const { handleSubmit, reset, control } = methods

    // Handler criar/editar
    const handleCreateEdit: SubmitHandler<VersaoFormSchemaType> = (async (data) => {
        console.log(data)
        try {
            if (versao) {
                // Hook de edit
                await EditarVersao(data)
                console.log('Edit - Payload enviado: ' + JSON.stringify(data))
            } else {
                await CriarVersao(data)
                console.log('Create - Payload enviaod: ' + JSON.stringify(data))
                // Hook de criar
            }
            reset()
            // navigate("")
        } catch (error) {
            console.log(error)
        }
    })

    // Get de produtos
    const [produtos, setProdutos] = useState<ProdutoFormSchemaType[]>([]);
    useEffect(() => {
        async function fetchData() {
            const produtos = await ListProduto(0);
            setProdutos(produtos);
        }
        fetchData();
    }, []);


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
                    <CardHeader title='Criar - Versão' sx={{ fontWeight: 'bold', px: 0, pt: 1 }} titleTypographyProps={{
                        sx: { fontWeight: 'bold', fontSize: '40px', color: 'black' }
                    }}>
                    </CardHeader>
                    <Typography sx={{ pb: 5 }}>
                        Para criar um registro preencha as informações abaixo:
                    </Typography>
                    <Stack spacing={4}>
                        <Stack sx={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                            <Controller
                                name="numeroVersao"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputText
                                        label="Número da versão"
                                        placeholder="Número da versão"
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
                                name="produtoId"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputSelect
                                        label="Produto"
                                        value={field.value}
                                        onChange={field.onChange}
                                        onBlur={field.onBlur}
                                        inputRef={field.ref}
                                        sx={{ width: '48%' }}
                                    >
                                        <MenuItem value="">Selecione uma da opções abaixo</MenuItem>
                                        {produtos.map((produto) => (
                                            <MenuItem value={produto.id}>{produto.nome}</MenuItem>
                                        ))}
                                    </StyledInputSelect>
                                )}
                            />
                        </Stack>
                        <Stack sx={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                            <Controller
                                name="dataLancamento"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputText
                                        label="Data de Lançamento"
                                        placeholder="Data de lançamento"
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
                                name="arquivoInstalador"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputText
                                        label="Arquivo de instalação"
                                        placeholder="Arquivo de instalação"
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
                        <Controller
                            name="patchNotes"
                            control={control}
                            render={({ field }) => (
                                <StyledInputTextArea
                                    label="Notas da versão"
                                    placeholder="Notas da versão"
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
                        <Button
                            variant='black'
                            label={versao ? 'Editar ' : 'Criar '}
                            onClick={handleSubmit(handleCreateEdit)}
                        >
                        </Button>
                    </Box>
                    </Stack>
                </Card>
            </Stack>
        </form>
    )
}
import { useForm } from 'react-hook-form'
import type { SubmitHandler } from 'react-hook-form'
import { Controller } from 'react-hook-form'
import * as z from 'zod'
import { zodResolver } from '@hookform/resolvers/zod'
import type { ClienteOrganizacao } from '../../types/cliente-organizacao.ts'
import { Stack, Card, CardHeader, Typography, MenuItem, Box } from '@mui/material'
import StyledInputText from '../mui/InputText.tsx'
import StyledInputSelect from '../mui/InputSelect.tsx'
import Button from '../Button.tsx'
import { useNavigate } from 'react-router'
import Glass from '../Glass.tsx'
import ProfileMenu from '../ProfileMenu.tsx'
import { CriarClienteOrganizacao, EditarClienteOrganizacao } from '../../actions/cliente/ClienteOrganizacao.ts'
import countries from 'i18n-iso-countries'
import pt from "i18n-iso-countries/langs/pt.json";
import SubmitDialog from '../mui/SubmitDialog.tsx'

// Schema para validação da entidade
const ClienteOrganizacaoFormSchema = z.object({
    documento: z.string().min(1, 'Campo obrigatório').max(30, 'Limite máximo de caracteres'),
    nome: z.string().min(1, 'Campo obrigatório').max(40, 'Limite máximo de caracteres'),
    email: z.string().min(1, 'Campo obrigatório').max(30, 'Limite máximo de caracteres').includes('@'),
    regiao: z.string().min(1, 'Campo obrigatório').max(30, 'Limite máximo de caracteres'),
    continente: z.string().min(1, 'Campo obrigatório').max(20, 'Limite máximo de caracteres'),
    telefone: z.string().min(1, 'Campo obrigatório').max(15, 'Limite máximo de caracteres'),
    porte: z.string().min(1, 'Campo obrigatório').max(20, 'Limite máximo de caracteres'),
    setorAtuacao: z.string().min(1, 'Campo obrigatório').max(50, 'Limite máximo de caracteres'),
})

export type ClienteOrganizacaoFormSchemaType = z.infer<typeof ClienteOrganizacaoFormSchema>

type clienteOrganizacaoProps = {
    clienteOrganizacao?: ClienteOrganizacao
}

export default function ClienteOrganizacaoForm({ clienteOrganizacao }: clienteOrganizacaoProps) {
    // Router
    const navigate = useNavigate()

    // Valores padrão do formulário
    const defaultValues: ClienteOrganizacaoFormSchemaType = {
        documento: '',
        nome: '',
        email: '',
        regiao: '',
        continente: '',
        telefone: '',
        porte: '',
        setorAtuacao: ''
    }

    // useForm
    const methods = useForm<ClienteOrganizacaoFormSchemaType>({
        mode: 'all',
        resolver: zodResolver(ClienteOrganizacaoFormSchema),
        defaultValues,
        values: clienteOrganizacao && {
            documento: clienteOrganizacao.documento,
            nome: clienteOrganizacao.nome,
            email: clienteOrganizacao.email,
            regiao: clienteOrganizacao.regiao,
            continente: clienteOrganizacao.continente,
            telefone: clienteOrganizacao.telefone,
            porte: clienteOrganizacao.porte,
            setorAtuacao: clienteOrganizacao.setorAtuacao
        }
    })

    const { handleSubmit, reset, control } = methods

    // Lista dos países 
    countries.registerLocale(pt)
    const codigoPais = countries.getNames("pt")
    const paises = Object.values(codigoPais)

    // Lista continentes
    const continentes = [
        'América do Sul',
        'América do Norte',
        'Europa',
        'Ásia',
        'África',
        'Oceania'
    ]

    // Lista portes
    const portes = [
        'Pequena',
        'Média',
        'Grande',
        'Imensa',
    ]

    // Handler criar/editar
    const handleCreateEdit: SubmitHandler<ClienteOrganizacaoFormSchemaType> = (async (data) => {
        try {
            if (clienteOrganizacao) {
                // Hook de edit
                await EditarClienteOrganizacao(data)
                console.log('Edit - Payload enviado: ' + JSON.stringify(data))
            } else {
                await CriarClienteOrganizacao(data)
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
                    <CardHeader title='Criar - Cliente organização' sx={{ fontWeight: 'bold', px: 0, pt: 1 }} titleTypographyProps={{
                        sx: { fontWeight: 'bold', fontSize: '40px', color: 'black' }
                    }}>
                    </CardHeader>
                    <Typography sx={{ pb: 5 }}>
                        Para criar um registro preencha as informações abaixo:
                    </Typography>
                    <Stack spacing={4}>
                        <Stack sx={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
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
                            <Controller
                                name="documento"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputText
                                        label="Documento"
                                        placeholder="Documento"
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
                                name="regiao"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputSelect
                                        label="Região"
                                        value={field.value}
                                        onChange={field.onChange}
                                        onBlur={field.onBlur}
                                        inputRef={field.ref}
                                        sx={{ width: '48%' }}
                                    >
                                        <MenuItem value="">Selecione uma da opções abaixo</MenuItem>
                                        {paises.map((pais) => (
                                            <MenuItem value={pais}>{pais}</MenuItem>
                                        ))}
                                    </StyledInputSelect>
                                )}
                            />
                            <Controller
                                name="continente"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputSelect
                                        label="Continente"
                                        value={field.value}
                                        onChange={field.onChange}
                                        onBlur={field.onBlur}
                                        inputRef={field.ref}
                                        sx={{ width: '48%' }}
                                    >
                                        <MenuItem value="">Selecione uma da opções abaixo</MenuItem>
                                        {continentes.map((continente) => (
                                            <MenuItem value={continente}>{continente}</MenuItem>
                                        ))}
                                    </StyledInputSelect>
                                )}
                            />
                        </Stack>
                        <Stack sx={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                            <Controller
                                name="email"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputText
                                        label="Email"
                                        placeholder="Email"
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
                                name="telefone"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputText
                                        label="Telefone"
                                        placeholder="Telefone"
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
                                name="setorAtuacao"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputText
                                        label="Setor de Atuação"
                                        placeholder="Setor de Atuação"
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
                                name="porte"
                                control={control}
                                render={({ field }) => (
                                    <StyledInputSelect
                                        label="Porte"
                                        value={field.value}
                                        onChange={field.onChange}
                                        onBlur={field.onBlur}
                                        inputRef={field.ref}
                                        sx={{ width: '48%' }}
                                    >
                                        <MenuItem value="">Selecione uma da opções abaixo</MenuItem>
                                        {portes.map((porte) => (
                                            <MenuItem value={porte}>{porte}</MenuItem>
                                        ))}
                                    </StyledInputSelect>
                                )}
                            />
                        </Stack>
                        <Box sx={{ display: 'flex', flexDirection: 'row', justifyContent: 'flex-end' }}>
                            <SubmitDialog label={clienteOrganizacao ? 'Editar ' : 'Criar '} handleSubmit={handleSubmit(handleCreateEdit)} />
                        </Box>
                    </Stack>
                </Card>
            </Stack>
        </form>
    )
}
import { Box, IconButton, Stack, Typography } from "@mui/material";
import LikedinIcon from '../assets/icons/linkedin_icon.svg'
import { useOutletContext } from "react-router-dom";
import { useEffect } from "react";

type ProfileType = {
    foto: string,
    linkedin: string,
    atividades: string[],
    vulgo: string,
    nome: string
}

const profiles: ProfileType[] = [
    {
        foto: 'profile_danilo.png',
        linkedin: 'www.linkedin.com/in/danilo-nishimoto',
        atividades: ['Desenvolvimento Front End', 'Ideação da identidade visual'],
        vulgo: '"Danillo"',
        nome: 'Danilo Yuzo Nishimoto'
    },
    { 
        foto: '',
        linkedin: '',
        atividades: ['Organização e Gestão do projeto', "Desenvolvimento do Front End e Back End", "Criação do minimundo e dos protótipos"],
        vulgo: '"Purplish"',
        nome: 'Guilherme Aika Alves Varella',
    },
    {
        foto: 'profile_leticia.png',
        linkedin: 'www.linkedin.com/in/letícia-honda',
        atividades: ['Organização do projeto', 'Desenvolvimento Back End', "Criação do Banco de Dados"],
        vulgo: '"Cinnamoroll_lover_<3"',
        nome: 'Letícia Namie Ono Honda'
    },
    {
        foto: '',
        linkedin: '',
        atividades: ['Desenvolvimento Back End','População do Banco de Dados'],
        vulgo: '"Luisão"',
        nome: 'Luís Guilherme Hitoshi Kanazawa',
    },
    { 
        foto: '',
        linkedin: '',
        atividades: ['Contribuição na ideação do minimundo'],
        vulgo: '"Sofia"',
        nome: 'Sofia Ferreira Leopoldo'
    }
]

type OutletContextType = {
  setNavColor: (color: string) => void;
};

function Profile({ profile }: { profile: ProfileType }) {
  
    return (
        <Stack direction={"row"} sx={{ display: 'flex', flexDirection: 'row', width: '558px', maxWidth: '600px', height: '130px' }} spacing={2}>
            {profile.foto ? (<Box sx={{
                minWidth: '130px',
                width: '130px',
                height: '130px',
                backgroundImage: `url(${new URL(`../assets/profiles/${profile.foto}`, import.meta.url).href})`,
                backgroundSize: 'cover',
                borderRadius: '6px',
            }}>
            </Box>) : (<Box sx={{
                minWidth: '130px',
                width: '130px',
                height: '130px',
                backgroundColor: '#D9D9D9',
                borderRadius: '6px'
            }}>
            </Box>)}
            <Box sx={{display: 'flex', flexDirection: 'row', justifyContent: 'space-between', width: '100%'}}>
                 <Box sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>
                <Box sx={{height: 'auto'}}>
                    {profile.atividades.map((atividade) => (
                        <Typography sx={{
                            color: 'white',
                            fontFamily: 'Segoe UI',
                            fontWeight: '400',
                            fontSize: '16px',
                            lineHeight: '16px',
                            mb: '5px'
                        }}>{atividade}</Typography>
                    ))}
                </Box>
                <Box>
                    <Typography sx={{
                        color: 'white',
                        fontFamily: 'Segoe UI',
                        fontWeight: '300',
                        fontSize: '14px',
                        lineHeight: 'normal'
                    }}>{profile.vulgo}</Typography>
                    <Typography sx={{
                        color: 'white',
                        fontFamily: 'Segoe UI',
                        fontWeight: '600',
                        fontSize: '24px',
                        lineHeight: 'normal'
                    }}>{profile.nome}</Typography>
                </Box>
            </Box>
            <Box sx={{ display: 'flex', flexDirection: 'row', justifyContent: 'flex-end', alignItems: 'flex-end'}}>
                <IconButton href={`https://${profile.linkedin}`}>
                    <img src={LikedinIcon} />
                </IconButton>
            </Box>
            </Box>
        </Stack>
    )
}

export default function Contato() {

    const { setNavColor } = useOutletContext<OutletContextType>();
    
    useEffect(() => {
    setNavColor('transparent');
  }, [setNavColor]);

    return (
        <Box sx={{ width: '100%', height: '100%' }}>
            <Box sx={{ height: '436px', backgroundImage: 'linear-gradient(257deg, #CB9AD2 0%, #BBB3D9 50%, #ABC2E2 100%)', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                <Stack sx={{ display: "flex", justifyContent: 'center', alignItems: 'center' }} spacing={3}>
                    <Typography sx={{
                        color: '#272727',
                        textAlign: 'center',
                        fontFamily: "Segoe UI",
                        fontSize: '24px',
                        fontStyle: 'normal',
                        fontWeight: '600',
                        lineHeight: 'normal',
                    }}>
                        Sobre o projeto
                    </Typography>
                    <Box sx={{ width: '776px', textAlign: 'center' }}>
                        <Typography sx={{
                            fontFamily: 'Segoe UI',
                            fontWeight: '400',
                            fontSize: '16px',

                        }}>
                            As aplicações da Aster foram desenvolvidas para a obtenção de créditos na disciplina de Banco de Dados I, ministrada pela Prof. Dra. Patrícia Rufino Oliveira durante o segundo semestre do ano de 2025, e para serem utilizadas como parte do portfolio dos integrantes.
                        </Typography>
                    </Box>
                </Stack>
            </Box>
            <Box sx={{ height: '973px', backgroundColor: '#473F4B' }}>
                <Stack sx={{ height: '100%', width: '100%', display: "flex", flexDirection: 'column', alignItems: 'center', pt: '66px', px: '250px' }} spacing={6}>
                    <Typography sx={{
                        color: 'white',
                        fontFamily: 'Segoe UI',
                        fontWeight: '600',
                        fontSize: '30px',
                    }}>Equipe</Typography>
                    <Stack sx={{display: "flex", flexDirection: "row", maxWidth: '1284', flexWrap: 'wrap', margin: '0', justifyContent: 'center'}} >
                        {profiles.map((profile) => (
                            <Box sx={{my: '40px', mx: '60px'}}>
                                <Profile profile={profile} />
                            </Box>
                        ))}
                    </Stack>
                    <Stack></Stack>
                    <Box></Box>
                </Stack>
            </Box>
        </Box >
    )
}
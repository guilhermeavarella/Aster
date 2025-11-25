import { Box, Container, Stack, Typography } from '@mui/material'
import AsterHero from '../assets/backgrounds/aster-hero-about.png'
import AsterIsco from '../assets/icons/aster_asterisco.png'
import PlainButton from '../components/mui/PlainButton'
import { useNavigate, useOutletContext } from 'react-router'
import { useEffect } from 'react'

type OutletContextType = {
  setNavColor: (color: string) => void;
};

export default function About() {
    const navigate = useNavigate()

    const { setNavColor } = useOutletContext<OutletContextType>();
    
    useEffect(() => {
    setNavColor('white');
  }, [setNavColor]);

    return (
        <Box>
            <Stack>
                <Box sx={{
                    backgroundImage: `url(${AsterHero})`,
                    backgroundSize: 'cover',
                    backgroundRepeat: 'no-repeat',
                    height: '485px',
                    width: '100%'
                }}>
                </Box>
            </Stack>
            <Stack sx={{ display: 'flex', flexDirection: 'column', justifyContent: 'center', alignItems: 'center', padding: '100px', backgroundColor: '#F3F0F9' }} spacing={4}>
                <Box>
                    <Typography sx={{ fontFamily: 'Segoe UI', fontSize: '30px', fontWeight: '600' }}>
                        Decolando suas ideias aos astros.
                    </Typography>
                </Box>
                <Box>
                    <Typography sx={{ fontFamily: 'Segoe UI', fontSize: '24px', fontWeight: 400, textAlign: 'center', maxWidth: '1100px' }}>
                        A gama completa de recursos para designers, artistas, videomakers, criadores independentes e profissionais que querem transformar ideias em experiências visuais extraordinárias. Um ecossistema poderoso, intuitivo e inovador — criado para quem vive de criatividade.
                    </Typography>
                </Box>
            </Stack>
            <Box sx={{ width: '100%', height: '752px', background: '#473F4B' }}>
                <Stack sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', padding: '44px' }} spacing={8}>
                    <Box sx={{
                        height: '96px',
                        width: '96px',
                        backgroundImage: `url(${AsterIsco})`,
                        backgroundSize: 'cover'
                    }} />
                    <Stack sx={{ px: '250px', textAlign: 'justify'}} spacing={2}>
                        <Typography sx={{ fontSize: '18px', fontFamily: 'Segoe UI', color: 'white', fontWeight: '600'}}>
                            A Aster é uma empresa global especializada no desenvolvimento e distribuição de softwares de criação profissional. Nosso ecossistema foi projetado para atender criadores de todos os níveis e áreas, oferecendo ferramentas poderosas para diversas aplicações e mídias — da edição de vídeo e imagem ao design gráfico, motion design, ilustração, storyboarding, modelagem 3D e texture paint, entre outras modalidades criativas. Cada produto é construído com foco em desempenho, fluidez e liberdade artística.
                        </Typography>
                        <Typography sx={{ fontSize: '18px', fontFamily: 'Segoe UI', color: 'white', fontWeight: '600' }}>
                            Com uma linha completa de softwares e diferentes pacotes de aquisição, a Aster oferece soluções flexíveis tanto para criadores independentes quanto para estúdios, equipes e grandes empresas. Nossos planos foram pensados para permitir que cada profissional escolha exatamente o conjunto de ferramentas que potencializa sua forma única de criar.
                        </Typography>
                        <Typography sx={{ fontSize: '18px', fontFamily: 'Segoe UI', color: 'white', fontWeight: '600' }}>
                            Hoje, a Aster já impulsiona a criatividade de milhares de clientes ao redor do mundo. De artistas individuais a grandes equipes de produção, nossa missão é simples: transformar poder tecnológico em possibilidades infinitas, ajudando ideias a decolarem — e chegarem ainda mais longe.
                        </Typography>
                    </Stack>
                    <PlainButton onClick={() => { navigate('/contato') }}>
                        Entre em contato
                    </PlainButton>
                </Stack>
            </Box>
        </Box>
    )
}
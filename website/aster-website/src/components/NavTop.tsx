import { Box, Link, Stack } from "@mui/material";
import PlainButton from "./mui/PlainButton";
import AsterLogo from '../assets/icons/aster_logo_contrast.svg'
import { useNavigate } from "react-router-dom";

export default function NavTop() {
    const navigate = useNavigate()

    return (
        <Stack sx={{display: 'flex', flexDirection: 'row', justifyContent: 'space-between',alignItems: 'center', padding: '8px 128px', width: '100%', backgroundColor: 'transparent', position: 'absolute', zIndex: 10}}>
            <Box sx={{
                width: '120px',
                height: '50px',
                backgroundImage: `url(${AsterLogo})`,
                backgroundSize: 'contain',
                backgroundRepeat: 'no-repeat'
            }}/>
            <Stack direction={'row'} sx={{display: 'flex', flexDirection: 'row', alignItems: 'center'}} spacing={4}>
                <Link underline="none" sx={{color: 'white', fontWeight: 600}} component='button' onClick={() => {navigate('/produtos')}}>Produtos</Link>
                <Link underline="none" sx={{color: 'white', fontWeight: 600}} component='button' onClick={() => {navigate('/sobre')}}>Sobre nós</Link>
                <Link underline="none" sx={{color: 'white', fontWeight: 600}} component='button' onClick={() => {navigate('/contato')}}>Contato</Link>
                <PlainButton>
                    Comece a criar
                </PlainButton>
            </Stack>    
        </Stack>
    )
}
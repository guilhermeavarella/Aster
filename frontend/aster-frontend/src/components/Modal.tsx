// ----- utilização -----
// no elemento pai:
// const [ openModal, setOpenModal] = useState(false)
// ... 
// <button onClick={() => setOpenModal(true)}>Modal</button>
//<Modal isOpen={openModal} onClose={() => setOpenModal(false)} register={register} tipo={"Produto"} createRegister={criarRegistro} updateRegister={alterarRegistro} deleteRegister={deletarRegistro}/> 
// ----- parâmetro -----
// isOpen - state do modal
// onClose - setState(false)
// register - registro
// type - nome da entidade
// createRegister - função que encaminha para a página de create
// updateRegister - função que encaminha para a página de edit
// deleteRegister - função que contém o hook que deleta o registro
// ----- To-do -----
// adicionar funcionalidade de exclusão, quando o hook for implementado

import { createPortal } from "react-dom";
import { Card, Stack, Box, Typography } from "@mui/material";
import type { Produto } from "../types/produto";
import Button from "./Button"

type ModalProps = {
    type: string
    register: Produto
    isOpen: boolean
    onClose: () => void
    createRegister: () => void
    updateRegister: () => void
    deleteRegister: () => void
}


export function Modal({ register, type, isOpen, onClose, updateRegister, createRegister, deleteRegister}: ModalProps) {
    if (!isOpen)
        return null;

    return createPortal(
        <Card sx={{ position: "fixed", backgroundColor: "rgba(0, 0, 0, 0.5)", display: "flex", alignItems: "center", justifyContent: "center", top: 0, left: 0, bottom: 0, right: 0 }}
            onClick={onClose}
        >
            <Stack sx={{ width: '62.5vw', height: '80vh', display: 'flex', alignItems: 'center', justifyContent: 'center' }}
            >
                <Box sx={{ paddingBottom: '32px', fontSize: "36px", color: 'white' }}>
                    <h1 className="font-Segoe UI">{type}</h1>
                </Box>
                <Stack sx={{ width: '100%', height: '90%', backgroundColor: 'white', borderRadius: '48px', padding: '36px', gap: '24px' }}
                    onClick={(e) => e.stopPropagation()}
                >
                    {Object.entries(register).map((item, index) => (
                        <div>
                            <Typography key={index} sx={{ fontWeight: 'bold', textTransform: 'capitalize' }}>{item}</Typography>
                            <Typography>{item}</Typography>
                        </div>
                    ))}
                </Stack>
                <Stack sx={{ display: "flex", flexDirection: 'row', justifyContent: 'space-between', marginTop: '16px', width: '100%' }}>
                    <Button
                        variant="white"
                        label="Criar novo registro"
                        onClick={createRegister}
                    ></Button>
                    <Stack sx={{ display: "flex", flexDirection: 'row', gap: '16px'}}>
                        <Button
                            variant="white"
                            label="Alterar"
                            onClick={updateRegister}
                        ></Button>
                        <Button
                            variant="black"
                            label="Excluir"
                            onClick={deleteRegister}
                        ></Button>
                    </Stack>
                </Stack>
            </Stack>
        </Card>,
        document.body
    )
}
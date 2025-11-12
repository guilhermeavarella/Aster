import TextField from "@mui/material/TextField";
import type { TextFieldProps } from "@mui/material/TextField";
import { styled } from "@mui/material/styles"

const InputText = styled(TextField)<TextFieldProps>(() => ({
    maxHeight: '50px',
    ".MuiOutlinedInput-root": {
        maxHeight: '50px',
    },
    "& .MuiInputBase-input": {
        fontSize: "16px",
    },
    '& .MuiFormLabel-root': {
        fontWeight: 'bold',
        color: 'black',
        fontSize: "18px",
        backgroundColor: '#fff',
        padding: '0 6px 0 0px',
    },
    '& .MuiInputBase-input::placeholder': {
        fontStyle: 'italic',
    }
}))

export default function StyledInputText(props: TextFieldProps) {
    return <InputText {...props} />
}
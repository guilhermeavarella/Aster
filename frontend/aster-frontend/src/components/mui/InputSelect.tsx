import { Select, FormControl, InputLabel, MenuItem } from "@mui/material";
import type { SelectProps } from "@mui/material/Select";
import { styled } from "@mui/material/styles";

const StyledFormControl = styled(FormControl)(() => ({
   width: 'fit-content',
    ".MuiOutlinedInput-root": {
        minHeight: '50px',
        width: '100%'
    },
    ".MuiInputLabel-root": {
        fontWeight: "bold",
        fontSize: "18px",
        color: "black",
        backgroundColor: "#fff",
        padding: "0 4px",
    },
    "& .MuiInputLabel-shrink": {
        transform: "translate(14px, -8px) scale(0.85)",
    },
    "& .MuiSelect-select": {
        fontSize: "16px",
        padding: "12px 14px",
    },
    "& .MuiOutlinedInput-notchedOutline": {
        borderColor: "#ccc",
    },
    "&:hover .MuiOutlinedInput-notchedOutline": {
        borderColor: "#888",
    },
}));

export default function StyledInputSelect(props: SelectProps) {
    return (
        <StyledFormControl variant="outlined" sx={{ width: "100%", ...props.sx }}>
            <InputLabel shrink>Categoria</InputLabel>
            <Select {...props}>
            </Select>
        </StyledFormControl>
    );
}

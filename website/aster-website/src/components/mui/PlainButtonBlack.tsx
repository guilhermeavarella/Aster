import { Button } from "@mui/material";
import type { ButtonProps } from "@mui/material/Button";
import { createTheme, styled } from "@mui/material/styles";

declare module '@mui/material/Button' {
    interface ButtonPropsVariantOverrides {
        black: true;
        white: true;
    }
}

const StyledButton = styled(Button)(() => ({
    width: '200px',
    height: '40px',
    borderRadius: '200px',
    backgroundColor: 'var(--content-primary)',
    boxShadow: 'none',
}));

export default function PlainButton(props: ButtonProps) {
    return (
        <StyledButton variant="contained" {...props} sx={{ color: 'var(--content-inverse)', fontWeight: 600, textTransform: 'initial', fontSize: '16px', fontFamily: 'Segoe UI', px: '15px' }}>
        </StyledButton>
    );
}

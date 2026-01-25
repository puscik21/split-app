import {Button} from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import {styled} from "@mui/material/styles";

interface AddButtonProps {
  text: string;
}

const AddButton = ({text}: AddButtonProps) => (
  <StyledButton
    variant="contained"
    size="large"
    startIcon={<AddIcon/>}
  >
    {text}
  </StyledButton>
)

export default AddButton;

const StyledButton = styled(Button)`
    color: ${({theme}) => theme.palette.primary.contrastText};
    border-radius: ${({theme}) => theme.shape.borderRadius};
    text-transform: uppercase;
    font-weight: 600;
`

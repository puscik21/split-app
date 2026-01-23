import { Box, Container, Stack, Typography, Button } from "@mui/material";
import { styled } from "@mui/material/styles";
import AddIcon from "@mui/icons-material/Add";
import SplitGroupList from "../features/split-groups/components/SplitGroupList.tsx";

// TODO: Maybe move header to separate file?
const GroupsPage = () => {
  return (
    <Container maxWidth="lg">
      <Box sx={{ py: 6 }}>
        <Stack
          direction={{ xs: 'column', sm: 'row' }}
          justifyContent="space-between"
          alignItems={{ xs: 'flex-start', sm: 'center' }}
          spacing={2}
          sx={{ mb: 5 }}
        >
          <Box>
            <HeaderText variant="h4">Twoje Grupy</HeaderText>
            <Typography variant="body1" color="text.secondary">
              Zarządzaj wspólnymi wydatkami ze znajomymi
            </Typography>
          </Box>

          <Button
            variant="contained"
            size="large"
            startIcon={<AddIcon />}
            sx={{ borderRadius: 2, textTransform: 'none', fontWeight: 600 }}
          >
            Nowa grupa
          </Button>
        </Stack>

        {/* Główna zawartość - nasza lista */}
        <SplitGroupList />
      </Box>
    </Container>
  );
};

export default GroupsPage;

const HeaderText = styled(Typography)`
    font-weight: 600;
    color: ${({theme}) => theme.palette.text.primary};
    margin-bottom: ${({theme}) => theme.spacing(4)};
`;

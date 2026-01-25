import {Box, Button, Container} from "@mui/material";
import SplitGroupList from "../features/split-groups/components/SplitGroupList.tsx";
import PageHeader from "../components/common/PageHeader.tsx";
import AddIcon from "@mui/icons-material/Add";

const GroupsPage = () => {
  return (
    <Container maxWidth="xl">
      <Box sx={{py: 6}}>
        <PageHeader
          title="Twoje Grupy"
          subtitle="Zarządzaj wspólnymi wydatkami ze znajomymi"
          action={
            <Button
              variant="contained"
              size="large"
              startIcon={<AddIcon/>}
              sx={{borderRadius: 2, textTransform: "none", fontWeight: 600}}
            >
              Nowa grupa
            </Button>
          }
        />

        <SplitGroupList/>
      </Box>
    </Container>
  );
};

export default GroupsPage;

import {Box, Container} from "@mui/material";
import SplitGroupList from "../features/split-groups/components/SplitGroupList.tsx";
import PageHeader from "../components/common/PageHeader.tsx";
import AddButton from "../components/common/AddButton.tsx";

const GroupsPage = () => {
  return (
    <Container maxWidth="xl">
      <Box sx={{py: 6}}>
        <PageHeader
          title="Twoje Grupy"
          subtitle="Zarządzaj wspólnymi wydatkami ze znajomymi"
          action={<AddButton text="Nowa grupa"/>}
        />
        <SplitGroupList/>
      </Box>
    </Container>
  );
};

export default GroupsPage;

import {Box, Grid} from "@mui/material";
import type {SplitGroupDTO} from "../../../types/splitGroup.ts";
import {SplitGroupCard} from "./SplitGroupCard.tsx";

// TODO: Get real data via API
const mockGroups: SplitGroupDTO[] = [
  {
    id: 101,
    title: "Wyjazd do Zakopanego",
    description: "Wydatki na domek i oscypki",
    creationTimestamp: "2025-09-29T21:27:40",
    userLogins: ["user1", "user2", "user3"]
  },
  {
    id: 102,
    title: "Zakupy spożywcze",
    description: "Domowe zakupy - wspólne",
    creationTimestamp: "2025-10-05T12:00:00",
    userLogins: ["user1", "partnerka"]
  }
];

const SplitGroupList = () => {
  return (
    <Box>
      <Grid container spacing={3}>
        {mockGroups.map((group) => (
          <Grid key={group.id} size={{xs: 12, sm: 6, md: 3}}>
            <SplitGroupCard group={group}/>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
};

export default SplitGroupList;

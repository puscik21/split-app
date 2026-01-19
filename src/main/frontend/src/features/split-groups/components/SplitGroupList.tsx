import {Box, Card, CardContent, Chip, Grid, Stack, Typography} from "@mui/material";
import {styled} from "@mui/material/styles";
import PeopleIcon from "@mui/icons-material/People";
import {formatDate} from "../../../utils/dateFormatter.ts";
import type {SplitGroupDTO} from "../../../types/splitGroup.ts";

// Mock danych (później zastąpisz to przez axios.get / fetch)
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

// TODO probably move to something like SplitGroupCard
// TODO: Move HeaderText to SplitGroupPage
const SplitGroupList = () => {
  return (
    <Box sx={{p: 4}}>
      <HeaderText variant="h4">Twoje Grupy</HeaderText>

      <Grid container spacing={3}>
        {mockGroups.map((group) => (
          <Grid size={{xs: 12, sm: 6, md: 3}}>
            <GroupCard elevation={0}>
              <CardContent sx={{p: 3}}>
                <Stack spacing={2}>
                  <Box display="flex" justifyContent="space-between" alignItems="center">
                    <Typography variant="h6" sx={{fontWeight: 700}}>
                      {group.title}
                    </Typography>
                    <UserCountChip
                      icon={<PeopleIcon/>}
                      label={group.userLogins.length}
                    />
                  </Box>

                  <Typography variant="body2" color="text.secondary" sx={{  minHeight: '40px' }}>
                    {group.description}
                  </Typography>

                  <CreatedAt>
                    <Typography variant="caption" color="text.disabled">
                      Utworzono: {formatDate(group.creationTimestamp)}
                    </Typography>
                  </CreatedAt>
                </Stack>
              </CardContent>
            </GroupCard>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
};

export default SplitGroupList;

const HeaderText = styled(Typography)`
    font-weight: 600;
    color: ${({theme}) => theme.palette.text.primary};
    margin-bottom: ${({theme}) => theme.spacing(4)};
`;

const GroupCard = styled(Card)`
    height: 100%;
    transition: transform 0.2s ease-in-out, box-shadow 0.2s;
    cursor: pointer;
    border-radius: ${({theme}) => theme.shape.borderRadius}px;
    background-color: ${({theme}) => theme.palette.background.paper};
    border: 1px solid ${({theme}) => theme.palette.divider};

    &:hover {
        transform: translateY(-4px);
        box-shadow: ${({theme}) => theme.shadows[12]};
    }
`;

const UserCountChip = styled(Chip)`
    background-color: ${({theme}) => theme.palette.divider};
    color: ${({theme}) => theme.palette.text.secondary};
    font-weight: 500;
`;

const CreatedAt = styled(Box)`
    padding-top: ${({theme}) => theme.spacing(2)};
    border-top: 1px solid;
    border-color: ${({theme}) => theme.palette.divider};
`;

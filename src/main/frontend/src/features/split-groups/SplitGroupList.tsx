import {
  Box,
  Typography,
  Card,
  CardContent,
  Grid,
  Chip,
  Stack
} from '@mui/material';
import { styled } from '@mui/material/styles';
import PeopleIcon from '@mui/icons-material/People';
import type {SplitGroupDTO} from "../../types/splitGroup.ts";

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

const GroupList = () => {
  return (
    <Box sx={{ flexGrow: 1, p: 3 }}>
      <HeaderText variant="h4" sx={{ mb: 4 }}>
        Twoje Grupy
      </HeaderText>

      <Grid container spacing={3}>
        {mockGroups.map((group) => (
          <Grid item xs={12} sm={6} md={4} key={group.id}>
            <GroupCard elevation={0}>
              <CardContent>
                <Stack spacing={2}>
                  <Box display="flex" justifyContent="space-between" alignItems="start">
                    <Typography variant="h6" component="div" sx={{ fontWeight: 'bold' }}>
                      {group.title}
                    </Typography>
                    <UserCountChip
                      icon={<PeopleIcon sx={{ fontSize: '1rem' }} />}
                      label={group.userLogins.length}
                      size="small"
                    />
                  </Box>

                  <Typography variant="body2" color="text.secondary" sx={{ minHeight: '40px' }}>
                    {group.description}
                  </Typography>

                  <Typography variant="caption" color="text.disabled">
                    Utworzono: {new Date(group.creationTimestamp).toLocaleDateString()}
                  </Typography>
                </Stack>
              </CardContent>
            </GroupCard>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
};

export default GroupList;

// const GroupCard = styled(Card)(({ theme }) => ({
//   '&:hover': {
//     transform: 'translateY(-4px)',
//     boxShadow: `0 8px 24px ${theme.palette.mode === 'dark' ? 'rgba(0,0,0,0.5)' : 'rgba(0,0,0,0.1)'}`,
//   },
// }));

const GroupCard = styled(Card)`
    height: 100%;
    transition: transform 0.2s ease-in-out, box-shadow 0.2s;
    cursor: pointer;
    border-radius: ${({ theme }) => theme.shape.borderRadius}px;
    background-color: ${({ theme }) => theme.palette.background.paper};
    border: 1px solid ${({ theme }) => theme.palette.divider};

    &:hover {
        transform: translateY(-4px);
        box-shadow: ${({ theme }) => theme.shadows[8]};
    }
`;

// const HeaderText = styled(Typography)`
//     font-weight: 600;
//     color: #1a2027;
// `;

const HeaderText = styled(Typography)`
    font-weight: 600;
    color: ${({ theme }) => theme.palette.text.primary};
    margin-bottom: ${({ theme }) => theme.spacing(4)};
`;

const UserCountChip = styled(Chip)`
    background-color: ${({ theme }) => theme.palette.action.hover};
    font-weight: 500;
`;


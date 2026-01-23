import {Box, Card, CardContent, Chip, Stack, Typography} from "@mui/material";
import {styled} from "@mui/material/styles";
import PeopleIcon from "@mui/icons-material/People";
import {formatDate} from "../../../utils/dateFormatter.ts";
import type {SplitGroupDTO} from "../../../types/splitGroup.ts";

interface SplitGroupCardProps {
  group: SplitGroupDTO;
}

export const SplitGroupCard = ({group}: SplitGroupCardProps) => {
  return (
    <StyledCard elevation={0}>
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

          <Typography variant="body2" color="text.secondary" sx={{minHeight: "40px"}}>
            {group.description}
          </Typography>

          <CreatedAt>
            <Typography variant="caption" color="text.disabled">
              Utworzono: {formatDate(group.creationTimestamp)}
            </Typography>
          </CreatedAt>
        </Stack>
      </CardContent>
    </StyledCard>
  );
};

const StyledCard = styled(Card)`
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

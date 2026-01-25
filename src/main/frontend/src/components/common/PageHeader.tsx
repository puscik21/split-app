import {styled} from "@mui/material/styles";
import {Box, Stack, Typography} from "@mui/material";
import type {ReactNode} from "react";

interface PageHeaderProps {
  title: string;
  subtitle?: string;
  action?: ReactNode;
}

const PageHeader = ({title, subtitle, action}: PageHeaderProps) => {
  return (
    <Stack
      direction={{xs: "column", sm: "row"}}
      justifyContent="space-between"
      alignItems={{xs: "flex-start", sm: "center"}}
      spacing={2}
      sx={{mb: 5}}
    >
      <Box>
        <HeaderText variant="h4">{title}</HeaderText>
        {subtitle && (
          <Typography variant="body1" color="text.secondary">
            {subtitle}
          </Typography>
        )}
      </Box>
      {action && (
        <Box>
          {action}
        </Box>
      )}
    </Stack>
  )
}

export default PageHeader;

const HeaderText = styled(Typography)`
    font-weight: 600;
    color: ${({theme}) => theme.palette.text.primary};
    margin-bottom: ${({theme}) => theme.spacing(4)};
`;

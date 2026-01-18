import {createTheme} from "@mui/material";

export const darkTheme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#90caf9', // Jasny błękit
      light: '#e3f2fd',
      dark: '#42a5f5',
      contrastText: '#000',
    },
    secondary: {
      main: '#f48fb1', // Róż
    },
    background: {
      default: '#0a1929',
      paper: '#132f4c',
    },
    divider: 'rgba(255, 255, 255, 0.12)',
    text: {
      primary: 'rgba(255, 255, 255, 0.95)',
      secondary: 'rgba(255, 255, 255, 0.7)',
    },
    action: {
      hover: 'rgba(255, 255, 255, 0.08)',
    },
    customShadow: 'rgba(0, 0, 0, 0.7)',
  },
  shape: {
    borderRadius: 16,
  },
  spacing: 8,
  typography: {
    fontFamily: '"Inter", "Roboto", "Helvetica", "Arial", sans-serif',
    h4: {
      fontWeight: 700,
      letterSpacing: '-0.02em',
    },
    h6: {
      lineHeight: 1.2,
    }
  },
  components: {
    MuiCard: {
      styleOverrides: {
        root: {
          backgroundImage: 'none', // Turn off the default gradient MUI in dark mode
        },
      },
    },
  },
});

// TODO: add lightTheme

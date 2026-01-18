import {createTheme} from "@mui/material";

// export const darkTheme = createTheme({
//   palette: {
//     mode: "dark",
//     primary: {
//       main: "#90caf9",
//     },
//     secondary: {
//       main: "#f48fb1",
//     },
//   },
//   spacing: "8px",
//   shape: {
//     borderRadius: 16,
//   },
// });

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
      default: '#0a1929', // Bardzo głęboki granat/czerń na tło strony
      paper: '#132f4c',   // Nieco jaśniejszy kolor dla kart (Card)
    },
    divider: 'rgba(255, 255, 255, 0.12)', // Kolor linii rozdzielających
    text: {
      primary: 'rgba(255, 255, 255, 0.95)',
      secondary: 'rgba(255, 255, 255, 0.7)',
    },
    action: {
      hover: 'rgba(255, 255, 255, 0.08)', // Wykorzystywane w Twoim UserCountChip
    }
  },
  shape: {
    borderRadius: 16, // To już masz - super, nadaje nowoczesny, "miękki" wygląd
  },
  // Warto dodać domyślny spacing, choć domyślnie jest to 8px
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
    // Możesz tu zdefiniować domyślne style dla wszystkich komponentów MUI
    MuiCard: {
      styleOverrides: {
        root: {
          backgroundImage: 'none', // Wyłącza domyślny gradient MUI w dark mode
        },
      },
    },
  },
});

// TODO: add lightTheme

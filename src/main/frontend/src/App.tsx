import {CssBaseline, ThemeProvider} from '@mui/material';
import {darkTheme} from "./theme/theme.ts";

function App() {
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline/>
      {/*<GameContextProvider>*/}
      {/*  <GameContent/>*/}
      {/*</GameContextProvider>*/}
      <h1>Hello</h1>
    </ThemeProvider>
  );
}

export default App;

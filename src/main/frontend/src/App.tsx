import {CssBaseline, ThemeProvider} from '@mui/material';
import {darkTheme, lightTheme} from "./theme/theme.ts";
import {useState} from "react";
import SplitGroupList from "./features/split-groups/components/SplitGroupList.tsx";

function App() {
  const [isDarkMode, setIsDarkMode] = useState(false);

  return (
    <ThemeProvider theme={isDarkMode ? darkTheme : lightTheme}>
      <CssBaseline/>
      {/*<GameContextProvider>*/}
      {/*  <GameContent/>*/}
      {/*</GameContextProvider>*/}
      <SplitGroupList/>
    </ThemeProvider>
  );
}

export default App;

import {CssBaseline, ThemeProvider} from '@mui/material';
import {darkTheme, lightTheme} from "./theme/theme.ts";
import GroupList from "./features/split-groups/SplitGroupList.tsx";
import {useState} from "react";

function App() {
  const [isDarkMode, setIsDarkMode] = useState(false);

  return (
    <ThemeProvider theme={isDarkMode ? darkTheme : lightTheme}>
      <CssBaseline/>
      {/*<GameContextProvider>*/}
      {/*  <GameContent/>*/}
      {/*</GameContextProvider>*/}
      <GroupList/>
    </ThemeProvider>
  );
}

export default App;

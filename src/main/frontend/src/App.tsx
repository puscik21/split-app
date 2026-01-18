import {CssBaseline, ThemeProvider} from '@mui/material';
import {darkTheme} from "./theme/theme.ts";
import GroupList from "./features/split-groups/SplitGroupList.tsx";

function App() {
  return (
    <GroupList/>
  );

  // return (
  //   <ThemeProvider theme={darkTheme}>
  //     <CssBaseline/>
  //     {/*<GameContextProvider>*/}
  //     {/*  <GameContent/>*/}
  //     {/*</GameContextProvider>*/}
  //     <GroupList/>
  //   </ThemeProvider>
  // );
}

export default App;

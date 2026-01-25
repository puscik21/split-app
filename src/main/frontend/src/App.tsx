import {CssBaseline, ThemeProvider} from "@mui/material";
import {darkTheme, lightTheme} from "./theme/theme.ts";
import {useState} from "react";
import SplitGroupListPage from "./pages/SplitGroupListPage.tsx";

function App() {
  const [isDarkMode, setIsDarkMode] = useState(false);

  return (
    <ThemeProvider theme={isDarkMode ? darkTheme : lightTheme}>
      <CssBaseline/>
      <SplitGroupListPage/>
    </ThemeProvider>
  );
}

export default App;

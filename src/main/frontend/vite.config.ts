import {defineConfig} from "vite"
import react from "@vitejs/plugin-react"

export default defineConfig({
  plugins: [react()],
  build: {
    // Build files will go to static resources of Spring app
    outDir: "../resources/static",
    // Clean directory before every build
    emptyOutDir: true,
  },
  server: {
    proxy: {
      // Proxy requests to Spring during development
      '/api': 'http://localhost:8080'
    }
  }
})

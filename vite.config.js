import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    open: 'http://localhost:3000/',
    proxy: {
      '/api': 'http://localhost:8080',
      '/fdi': 'http://localhost:8080',
    },
    port: 3000,
  },
})

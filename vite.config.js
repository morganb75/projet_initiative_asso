import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  define: {
    global: 'globalThis',
  },
  server: {
    port: 3000,
    open: 'http://localhost:3000/',
    proxy: {
      '/api': 'http://localhost:8080',
      '/fdi': 'http://localhost:8080',
      // Proxy WebSocket vers /ws
      '/ws': {
        target: 'ws://localhost:8080',
        ws: true,             // 👈 Indique que c'est une route WebSocket
        changeOrigin: true
      },
    },
  },
})


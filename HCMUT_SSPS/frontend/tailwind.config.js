/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        primary: {
          100: '#cce5ff',
          200: '#99cbff',
          300: '#66b0ff',
          400: '#3396ff',
          500: '#007aff', 
          600: '#006ae6',
          700: '#0056bf',
          800: '#004299',
          900: '#002866',
        }
        
      },
    },
  },
  plugins: [],
}


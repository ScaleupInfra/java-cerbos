/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        // Define custom colors by inspecting the screenshot and adding them here
        cerbosYellow: '#ffb901',
        cerbosDarkYellow: '#CE9400',
        cerbosDark: '#0F172A',
        // ...other colors
      },
      fontFamily: {
        // Define custom fonts if needed
        sans: ['Inter', 'sans-serif'],
        // ...other font families
      },
    },
  },
  plugins: [],
}


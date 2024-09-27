/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        PINK: "#FB7091",
        PURPLE: "#8761A5",
        TEXTCOLOR:"#413F3F",
      },
    },
  },
  plugins: [],
};

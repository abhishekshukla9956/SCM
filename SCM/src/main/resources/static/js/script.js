let currentTheme = getTheme();
changeTheme(currentTheme);

function changeTheme(currentTheme) {
  document.querySelector("html").classList.add(currentTheme);

  const changeTheme = document
    .querySelector("#theme_change_button")
    .addEventListener("click", () => {
      if (currentTheme === "light") {
        document.querySelector("html").classList.remove("light");
        document.querySelector("html").classList.add("dark");
        currentTheme = "dark";
      } else {
        document.querySelector("html").classList.remove("dark");
        document.querySelector("html").classList.add("light");
        currentTheme = "light";
      }
      settheme(currentTheme);
    });
}

function settheme(theme) {
  localStorage.setItem("theme", currentTheme);
}

function getTheme() {
  let theme = localStorage.getItem("theme");
  return theme ? theme : "light";
}

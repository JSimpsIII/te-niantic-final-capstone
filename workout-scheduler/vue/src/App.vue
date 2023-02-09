<template>
  <div id="app">
    
    <router-view />
  
  </div>
</template>

<style>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100;400;700&display=swap');

/*Reset*/
* {
  margin: 0;
  box-sizing: border-box;
}

img {
  display: block;
  max-width: 100%;
}

/*Set color palette*/
:root {
  --blue: #295882;
  --red: #822958;
  --green: #298253;
  --smoke: #2D2D2D;
  --black: #0f0606;
  --white: #FDFFFC;
}

body {
  max-width: 600px;
  margin: 0 auto;
  font-family: 'Roboto', sans-serif;
  background-color: var(--black);
  color: var(--white);
}

.navigation-button {
  color:darkgoldenrod;
}
</style>

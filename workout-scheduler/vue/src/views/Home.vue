<template>
  <div class="home">
    <div id="profile-header">
      <img id="profile-img" src="https://www.writeups.org/wp-content/uploads/Machop-Pokemon.jpg" />
      <div id="username">{{ username }}</div>
      <router-link id="settings" :to="{name: 'profile'}">
        <img src="../assets/settings.png">
      </router-link>
    </div>

    <div id="motivation">Welcome back, {{ username }}! Just 5 more workouts until you level up! </div>

    <div class="btn-container">
      <router-link id="gym" class="home-options-btn" to="/">
        <div>
          <img src="../assets/star.png">
          Gym
        </div>
      </router-link>
      
      <router-link id="metrics" class="home-options-btn" to="/">
        <div>
          <img src="../assets/chart.png">
          Metrics
        </div>
      </router-link>

      <router-link id="goals" class="home-options-btn" :to="{name: 'goals'}">
        <div>
          <img src="../assets/goal.png">
          Goals
        </div>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      // get name from login
      username: "Machop"
    }
  }
};
</script>

<style scoped>

* {
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}

a {
  text-decoration: none;
}

.home {
  width: 600px;
  margin: 0 auto;
}

#profile-header {
  display: flex;
  align-items: center;
  width: 90%;
  margin: 0 auto;
  margin-bottom: 20px;
}

#username {
  margin-left: 10px;
}

#settings {
  margin-left: auto;
}


#profile-img {
  width: 50px;
  height: 50px;
  object-fit: cover;
  border-radius: 100%;
  object-position: top;
}

#motivation {
  font-size: 25px;
  text-align: center;
  width: 90%;
  margin: 0 auto;
  margin-bottom: 20px;
}

#gym div {
  background-color: #3a86ff;
}

#metrics div {
  
  background-color: #ff006e; 
}

#goals div {
  background-color: #8338ec;
}

#goals div,
#metrics div,
#gym div {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 5px;
  color: #FDFFFC;
  width: 90%;
  margin: 0 auto;
  font-size: 20px;
  padding: 40px 0px;
  text-align: center;
  border-radius: 10px;
  margin-bottom: 25px;
}

</style>

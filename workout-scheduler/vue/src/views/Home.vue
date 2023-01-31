<template>
  <div class="home">
    <p>You must be logged-in to see this</p>
    <img id="profile-img" src="https://www.writeups.org/wp-content/uploads/Machop-Pokemon.jpg" />
    <h1>Welcome, {{ username }}</h1>
    <router-link :to="{name: 'profile'}">
      <div id="edit-profile">edit</div>
    </router-link>

    <div class="container">
      <div id="gym" class="home-options-btn">
        <h2>Gym</h2>
      </div>
      <div id="metrics" class="home-options-btn">
        <h2>Metrics</h2>
      </div>
      <div id="goals" class="home-options-btn">
        <h2>Goals</h2>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      // get name from login
      username: "test"
    }
  }
};
</script>

<style>

* {
  font-family:Verdana, Geneva, Tahoma, sans-serif;
}

body {
  width: 600px;
  margin: 0 auto;
  border: red 1px solid;
  background-color: #0f0606;
  color: #FDFFFC;
}

#profile-img{
  width: 50px;
  height: 50px;
  object-fit: cover;
  border-radius: 100%;
  object-position: top;
}

#gym {
  background-color: #3a86ff;
}

#metrics {
  background-color: #ff006e; 
}

#goals {
  background-color: #8338ec;
}

.home-options-btn {
  font-size: 20px;
}

</style>

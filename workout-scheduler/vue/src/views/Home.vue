<template>
  <div class="home">
    <p>You must be logged-in to see this</p>
    <img src="" />
    <h1>Welcome, User</h1>
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
};
</script>

<style>

body {
  background-color: #011627;
  color: #FDFFFC;
}
</style>

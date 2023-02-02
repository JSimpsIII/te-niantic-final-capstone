<template>
  <div class="home">
    <div id="profile-header">
      <img class="profile-img" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo30ulQk-69OJ5GGdowFt21Lsau4GfWzfbBSmsfE4hGrVxBbnVNOr12yOYULoq2Gb7XEU&usqp=CAU" />
      <div id="username">{{ username }}</div>
      <router-link id="settings" :to="{name: 'profile'}">
        <img src="../assets/settings.png" alt="settings-icon">
      </router-link>
      
      <router-link id="logout" :to="{name: 'logout'}">
        <img src="../assets/logout.png" alt="logout-icon">
      </router-link>
    </div>

    <div id="motivation">Welcome back, {{ username }}! Just 1 more workout until you hit your weekly goal! </div>

    <div class="btn-container">
      <router-link id="gym" class="home-options-btn" to="gym">
        <div>
          <img src="../assets/star.png">
          Gym
        </div>
      </router-link>
      
      <router-link id="metrics" class="home-options-btn" :to="{name: 'metrics'}">
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
import profileService from '../services/ProfileService';

export default {
  name: "home",
  data() {
    return {
      // get name from login
      username: "Steve Rogers"
    }
  },
  created() {
      profileService.getProfile(this.$store.state.user.username)
                    .then(res => {
                      const { customerId } = res.data.customer;
                      this.$store.commit("SET_CUSTOMER_ID", customerId);
                    })
    }
};
</script>

<style scoped>

a {
  text-decoration: none;
}

.home {
  margin-top: 20px;
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

#logout {
  margin-left: 5px;
}

.profile-img {
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
  background-color: var(--blue);
}

#metrics div {
  
  background-color: var(--red); 
}

#goals div {
  background-color: var(--green);
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

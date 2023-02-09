<template>
  <div class="home">
    <div id="profile-header">
      <img
        class="profile-img"
        :src="this.$store.state.profile.photo"
      />

      <div id="username">{{ this.$store.state.user.username}}</div>

      <router-link id="settings" :to="{ name: 'profile' }">
        <img src="../assets/settings.png" alt="settings-icon" />
      </router-link>

      <router-link id="logout" :to="{ name: 'logout' }">
        <img src="../assets/logout.png" alt="logout-icon" />
      </router-link>
    </div>

    <div id="motivation">
      <div id='welcome-message'>Welcome back, <span class='users-name-welcome'>{{ this.$store.state.profile.name }}</span>!</div>
      <div class='spacer'><hr></div>
      <quote-changer />
    </div>

    <div class="btn-container">
      <router-link id="gym" class="home-options-btn" to="gym">
        <div>
          <img src="../assets/star.png" />
          Gym
        </div>
      </router-link>

      <router-link
        id="metrics"
        class="home-options-btn"
        :to="{ name: 'metrics' }"
      >
        <div>
          <img src="../assets/chart.png" />
          Metrics
        </div>
      </router-link>

      <router-link id="goals" class="home-options-btn" :to="{ name: 'goals' }">
        <div>
          <img src="../assets/goal.png" />
          Goals
        </div>
      </router-link>
    </div>
    
    <!-- <router-link id="Schedule" class="home-options-btn" :to="{ name: 'schedule' }">
      <div>
        <img src="../assets/goal.png" />
         Schedule
      </div>
    </router-link> -->

    <footer>
      <nav-bar />
    </footer>
  </div>
</template>

<script>
import profileService from "../services/ProfileService";
import NavBar from "../components/NavBar.vue";
import metricService from '../services/MetricService';
import exerciseService from '../services/ExerciseService';
import goalService from '../services/GoalService'
import QuoteChanger from '../components/QuoteChanger.vue';
import gymService from '../services/GymService';

export default {
  name: "home",
  components: {
    NavBar,
    QuoteChanger
  },
  data() {
    return {
      profile: {
        customerId: '',
        name: '',
        photo: ''
    }
    };
  },
  methods: {
      loadExerciseList() {
        exerciseService
          .getAllExercises()
          .then(response => {
            this.$store.commit('LOAD_EXERCISE_LIST', response.data);
          })
      },
      loadMetricsList(){
        let userId = this.$store.state.profile.customerId;
        metricService
          .getAllMetrics(userId)
          .then(response => {
            this.$store.commit('LOAD_METRICS_LIST', response.data);
          })
      },
      loadGoalList() {
        let userId = this.$store.state.profile.customerId;
        goalService
          .getAllGoals(userId)
          .then(response => {
            this.$store.commit('LOAD_GOAL_LIST', response.data);
          })
      },
      loadGymLogs() {
        let userId = this.$store.state.profile.customerId;
        gymService
          .getAllVisits(userId)
          .then(response => {
            this.$store.commit('LOAD_GYM_LOGS', response.data);
        })
      }
  },
  computed: {

  },
  created() {
    profileService.getProfile(this.$store.state.user.username).then(res => {
      const { customerId, name, photo } = res.data;
      this.profile.customerId = customerId;
      this.profile.name = name;
      this.profile.photo = photo;
      this.$store.commit("SET_PROFILE", this.profile);
      this.loadExerciseList();
      this.loadMetricsList();
      this.loadGoalList();
      this.loadGymLogs();
    });
  }
};
</script>

<style scoped>
a {
  text-decoration: none;
}

.home {
  padding-top: 20px;
  background-color: rgb(29, 34, 39);
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
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
  margin-left: 10px;
}

.profile-img {
  width: 60px;
  height: 60px;
  object-fit: cover;
  border-radius: 100%;
  object-position: top;
  background: var(--smoke);
  text-indent: 100%;
  white-space: nowrap;
  overflow: hidden;
}

#motivation {
  font-size: 25px;
  text-align: center;
  width: 90%;
  margin: 0 auto;
  margin-bottom: 30px;
}
.users-name-welcome {
  font-size: 1.15em;
  color: sandybrown;
}

.spacer {
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: center;
}
hr {
  width: 250px;
  border: 1px solid rgb(55, 66, 77);
  margin-top: 15px;
  margin-bottom: 15px;
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
  color: #fdfffc;
  width: 90%;
  margin: 0 auto;
  font-size: 20px;
  padding: 40px 0px;
  text-align: center;
  border-radius: 10px;
  margin-bottom: 30px;
}

.btn-container {
  margin-bottom: 100px;
  margin-top: 10px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

footer {
  position: fixed;
  bottom: 0;
  width: 600px;
  height: 50px;
  background: var(--smoke);
  padding-top: 15px;
}
</style>

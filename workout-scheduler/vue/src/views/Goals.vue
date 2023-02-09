<template>
  <div id="goals-page-container">
    <main>
      <img
        id="goals-img"
        src="../assets/gym-for-everyone.png"
        alt="gym-for-everyone-img"
      />

      <div class="goals-banner">
        <div class="goals-title-add-container">
          <div class="goals-title">Goals</div>

          <div class="add-btn-container">
            <button class="add-btn" @click="toggleAddGoal">
              {{ isAddingGoal ? "Cancel" : "+ Add" }}
            </button>
          </div>
        </div>
      </div>

      <div class="goals-container">
        <add-goal v-if="isAddingGoal" />

        <list-goals />

      </div>
    </main>

    <footer>
      <nav-bar />
    </footer>
  </div>
</template>

<script>
import AddGoal from "../components/AddGoal.vue";
import ListGoals from '../components/ListGoals.vue';
import NavBar from "../components/NavBar.vue";
import goalService from "../services/GoalService";

export default {
  name: "goals",
  components: {
    AddGoal,
    ListGoals,
    NavBar,
  },
  data() {
    return {
      isAddingGoal: false,
    };
  },
  created() {
    goalService
      .getAllGoals(this.$store.state.profile.customerId)
      .then((res) => (this.$store.state.goalList = res.data));
    this.loadGoalList();
  },
  methods: {
    toggleAddGoal() {
      this.isAddingGoal = !this.isAddingGoal;
    },
    loadGoalList() {
        let userId = this.$store.state.profile.customerId;
        goalService
          .getAllGoals(userId)
          .then(response => {
            this.$store.commit('LOAD_GOAL_LIST', response.data);
          })
      }
    
  }
};
</script>

<style scoped>

#goals-page-container {
  background-color: rgb(29, 34, 39);
  height: 100vh;
}

#goals-container {
  width: 90%;
  margin: 0 auto;
}

#goals-img {
  background-color: var(--green);
}

.goals-banner {
  background-color: var(--green);
  font-size: 25px;
  margin-bottom: 10px;
}

.goals-title-add-container {
  background-color: #143625;
  display: flex;
  align-items: center;
  justify-content: space-around;
  width: 100%;
  padding-top: 10px;
  padding-bottom: 10px;
  margin: 0 auto;
}

div.goal:first-child {
  background: linear-gradient(90deg, var(--green) 33%, var(--smoke) 0%);
}

.add-btn {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  margin: 0 auto;
  background-color: #143625;
  padding: 10px 0 5px 0;
  border-radius: 10px;
  font-size: 18px;
  border: none;
  color: inherit;
}

.add-btn:hover {
  cursor: pointer;
  color: sandybrown;
}

main {
  padding-bottom: 50px;
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


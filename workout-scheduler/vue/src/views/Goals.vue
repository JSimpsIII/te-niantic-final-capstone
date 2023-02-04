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

        <div
          class="goal"
          v-for="(goal, i) in this.$store.state.goalList"
          :key="i"
        >
          <div class="goal-name">
            {{ goal.name }}
            {{ goal.reps != 0 || goal.weight != 0 || goal.time != 0 || goal.distance != 0 || goal.days != 0 || goal.misc != null ? ":" : "" }}
            {{ goal.reps != 0 ? goal.reps : "" }}
            {{ goal.weight != 0 ? goal.weight : "" }}
            {{ goal.time != 0 ? goal.time : "" }}
            {{ goal.distance != 0 ? goal.distance : "" }}
            {{ goal.days != 0 ? goal.days : "" }}
            {{ goal.misc != null ? goal.misc : "" }}
          </div>
        </div>
      </div>
    </main>

    <footer>
      <nav-bar />
    </footer>
  </div>
</template>

<script>
import AddGoal from "../components/AddGoal.vue";
import NavBar from "../components/NavBar.vue";
import goalService from "../services/GoalService";

export default {
  name: "goals",
  components: {
    AddGoal,
    NavBar,
  },
  data() {
    return {
      goals: [],
      isAddingGoal: false,
    };
  },
  created() {
    goalService
      .getAllGoals(this.$store.state.customerId)
      .then(res => this.$store.state.goalList = res.data);
  },
  methods: {
    toggleAddGoal() {
      this.isAddingGoal = !this.isAddingGoal;
    },
  },
};
</script>

<style scoped>
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
  margin-bottom: 20px;
}

.goals-title-add-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 70%;
  margin: 0 auto;
}

.goal {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: var(--smoke);
  margin-bottom: 10px;
  width: 70%;
  margin: 0 auto;
  height: 60px;
  margin-bottom: 10px;
  border-radius: 10px;
}

div.goal:first-child {
  background: linear-gradient(90deg, var(--green) 33%, var(--smoke) 0%);
}

.add-btn {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  margin: 0 auto;
  background-color: var(--green);
  padding: 10px;
  border-radius: 10px;
  font-size: 18px;
  border: none;
  padding-right: 0;
  padding-left: 0;
  color: inherit;
}

.add-btn:hover {
  cursor: pointer;
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


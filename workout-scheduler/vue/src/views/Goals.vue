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
      goals: [],
      isAddingGoal: false,
    };
  },
  created() {
    goalService
      .getAllGoals(this.$store.state.profile.customerId)
      .then((res) => (this.$store.state.goalList = res.data));
  },
  methods: {
    toggleAddGoal() {
      this.isAddingGoal = !this.isAddingGoal;
    },
    deleteGoal(e) {
      const goalId = e.target.parentElement.dataset.goalId;
      goalService
        .deleteGoal(this.$store.state.profile.customerId, goalId)
        .then((res) => {
          if (res.status == 200) {
            goalService
              .getAllGoals(this.$store.state.profile.customerId)
              .then((res) => (this.$store.state.goalList = res.data));
          }
        })
        .catch((error) => {
          console.error(error);
        });
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
  margin-bottom: 10px;
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
  justify-content: space-between;
  background-color: var(--smoke);
  margin-bottom: 10px;
  width: 70%;
  margin: 0 auto;
  height: 60px;
  margin-bottom: 10px;
  border-radius: 10px;
  padding: 0 25px;
}

.delete-icon {
  cursor: pointer;
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

main {
  padding-bottom: 50px;
}
</style>


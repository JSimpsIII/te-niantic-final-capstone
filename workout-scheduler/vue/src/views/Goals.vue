<template>
  <div id="goals-page-container">
    <main>
      <img id="goals-img" src="../assets/gym-for-everyone.png" alt="gym-for-everyone-img">

      <div class="goals-banner">
        <div class="goals-title-add-container">
          <div class="goals-title">Goals</div>
          <div class="add-btn-container">
              <button class="add-btn" @click="toggleAddGoal">
                {{ isAddingGoal ? "Cancel" : "+ Add"}}
              </button>
          </div>
        </div>
      </div>

      <div class="goals-container">
        <add-goal v-if="isAddingGoal" />

        <div class="goal" v-for="goal in goals" :key="goal.id">
          <div class="goal-name">{{ goal.goalName }}</div>
        </div>
      </div>
    </main>

    <footer>
      <nav-bar />
    </footer>

  </div>
</template>

<script>
import AddGoal from '../components/AddGoal.vue'
import NavBar from '../components/NavBar.vue'

export default {
    name: "goals",
    components: {
      AddGoal, 
      NavBar
    },
    data() {
      return {
        goals: [
          {
            goalId: "id",
            goalName: "Go to the gym 3 days a week",
            goalRep: null,
            goalWeight: null,
            goalTime: null,
            goalDays: 3,
            goalMisc: null 
          },
          {
            goalId: "id2",
            goalName: "Feel less tired",
            goalRep: null,
            goalWeight: null,
            goalTime: null,
            goalDays: null,
            goalMisc: null 
          },
          {
            goalId: "id3",
            goalName: "Improve stamina",
            goalRep: null,
            goalWeight: null,
            goalTime: null,
            goalDays: null,
            goalMisc: null 
          }
        ],
        isAddingGoal: false
      }
    },
    methods: {
      toggleAddGoal() {
        this.isAddingGoal = !this.isAddingGoal;
      }
    }
}
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


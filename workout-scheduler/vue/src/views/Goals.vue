<template>
  <div id="goals-page-container">
    <img src="../assets/gym-for-everyone.png" alt="gym-for-everyone-img">
    <div class="goals-title">Goals</div>

    <div class="goals-container">
      <div class="goal" v-for="goal in goals" :key="goal.id">
        <div class="goal-name">{{ goal.goalName }}</div>
      </div>

      <div class="add-goals">
        <button class="add-btn">+ Add</button>
      </div>
    </div>

  </div>
</template>

<script>
export default {
    name: "goals",
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
        ]
      }
    }
}
</script>

<style>

#goals-container {
  width: 90%;
  margin: 0 auto;
}

.goals-title {
  background-color: var(--green);
  font-size: 25px;
  text-align: center;
  margin-bottom: 20px;
  padding: 10px;
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
  justify-content: center;
  width: 100px;
  margin: 0 auto;
  background-color: var(--green);
  color: inherit;
  padding: 10px;
  border-radius: 10px;
  font-size: 18px;
}

.add-btn:hover {
  cursor: pointer;
}

</style>


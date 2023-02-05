<template>
  <div class="add-goal">
    <div class="add-new-goal">
      <img
        src="../assets/add.png"
        alt="add-icon"
        class="add-new-goal-btn"
        @click="saveGoal"
      />
      <div>
        Days in gym every week:
        <input type="number" min="1" max="7" v-model="newGoal.days" />
      </div>
    </div>

    <div class="add-new-goal">
      <img
        src="../assets/add.png"
        alt="add-icon"
        class="add-new-goal-btn"
        @click="saveGoal"
      />
      <div>
        Minutes in gym every day:
        <input type="number" min="1" max="480" v-model="newGoal.time" />
      </div>
    </div>

    <div class="add-new-goal">
      <img
        src="../assets/add.png"
        alt="add-icon"
        class="add-new-goal-btn"
        @click="saveGoal"
      />
      <div>
        Bench press (lbs):
        <input type="number" min="1" max="480" v-model="newGoal.weight" />
      </div>
    </div>

    <div class="add-new-goal">
      <img
        src="../assets/add.png"
        alt="add-icon"
        class="add-new-goal-btn"
        @click="saveCustomGoal"
      />
      <input type="text" placeholder="Custom goal (e.g. feel less stressed)" />
    </div>
  </div>
</template>

<script>
import goalService from "../services/GoalService";

export default {
  name: "add-goal",
  data() {
    return {
      newGoal: {
        name: "",
        customerId: this.$store.state.profile.customerId,
        exerciseId: 1,
        date: null,
        reps: 0.0,
        weight: 0.0,
        time: 0.0,
        distance: 0.0,
        days: 0,
        misc: null,
        completed: false,
      },
    };
  },
  methods: {
    saveGoal(e) {
      const newGoalInnerText = e.target.nextElementSibling.innerText;
      const newGoalName = newGoalInnerText.substring(
        0,
        newGoalInnerText.indexOf(":")
      );
      this.newGoal.name = newGoalName;

      this.$store.state.goalList.push(this.newGoal);

      goalService
        .addNewGoal(this.$store.state.profile.customerId, this.newGoal)
        .then((response) => {
          if (response.status == 200) {
            this.newGoal = {
              name: "",
              customerId: this.$store.state.profile.customerId,
              exerciseId: 1,
              date: null,
              reps: 0.0,
              weight: 0.0,
              time: 0.0,
              distance: 0.0,
              days: 0,
              misc: null,
              completed: false,
            };
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    saveCustomGoal(e) {
      const newGoalName = e.target.nextElementSibling.value;
      this.newGoal.name = newGoalName;

      this.$store.state.goalList.push(this.newGoal);

      goalService
        .addNewGoal(this.$store.state.profile.customerId, this.newGoal)
        .then((response) => {
          if (response.status == 200) {
            this.newGoal = {
              name: "",
              customerId: this.$store.state.profile.customerId,
              exerciseId: 1,
              date: null,
              reps: 0.0,
              weight: 0.0,
              time: 0.0,
              distance: 0.0,
              days: 0,
              misc: null,
              completed: false,
            };
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
.add-goals {
  width: 70%;
  margin: 0 auto;
}

.add-new-goal {
  display: flex;
  align-items: center;
  height: 60px;
  background-color: var(--green);
  border-radius: 10px;
  margin-bottom: 10px;
  padding-left: 10px;
}

.add-new-goal > div > input {
  color: inherit;
  background-color: transparent;
  border: 1px var(--white) solid;
  border-radius: 5px;
  height: 30px;
  width: 40px;
}

.add-new-goal > div > input::placeholder {
  color: inherit;
}

.add-new-goal > input {
  width: 75%;
  height: 30px;
  color: inherit;
  background-color: transparent;
  border: none;
}

.add-new-goal > input::placeholder {
  color: var(--white);
  font-style: italic;
}

.add-new-goal-btn {
  cursor: pointer;
}

input:focus {
  outline-color: white;
}
</style>
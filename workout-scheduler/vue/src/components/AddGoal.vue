<template>
  <div class="add-goals">
    <div class="add-new-goal">
      <img
        src="../assets/add.png"
        alt="add-icon"
        class="add-new-goal-btn"
        @click="saveGoal"
      />
      <div>
          Days in gym every week: 
          <input type="number" min="1" max="7" v-model="newGoal.days"/>
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
        <input type="number" min="1" max="480" v-model="newGoal.time"/>
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
          <input type="number" min="1" max="480" v-model="newGoal.weight"/>
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
import goalService from '../services/GoalService';

export default {
  name: "add-goals",
  data() {
    return {
      newGoal: {
        name: "Test",
        date: "",
        reps: 0.0,
        weight: 0.0,
        time: 0.0,
        distance: 0.0,
        days: 0,
        misc: null,
        completed: false,
      }
    }
  },
  methods: {
    saveGoal() {
        goalService
          .addNewGoal(this.$store.state.customerId, this.newGoal)
          .then(response => {
            if (response.status == 201) {
              console.log('goal ' + this.newGoal.name + ' added');
            }
          }).catch(error => {
            console.error(error);
          });
    },
    saveCustomGoal: (e => {
        console.log(e.target.nextElementSibling.value)
    })
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
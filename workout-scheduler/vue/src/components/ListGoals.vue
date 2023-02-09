<template>
  <div id="list-goals">
    <div
      class="goal"
      v-for="goal in filteredGoalsNotCompleted"
      :key="goal.id"
      :data-goal-id="goal.id"
    >
      <div class="goal-details">
        {{ goal.name }}
        {{
          goal.reps != 0 ||
          goal.weight != 0 ||
          goal.time != 0 ||
          goal.sets != 0 ||
          goal.days != 0 ||
          goal.misc != null
            ? ":"
            : ""
        }}
        {{ goal.reps != 0 ? goal.reps : "" }}
        {{ goal.weight != 0 ? goal.weight : "" }}
        {{ goal.time != 0 ? goal.time : "" }}
        {{ goal.sets != 0 ? goal.sets : "" }}
        {{ goal.days != 0 ? goal.days : "" }}
        {{ goal.misc != null ? goal.misc : "" }}
      </div>

      <div class="goals-icons">
        <img
          src="../assets/check.png"
          alt="check-icon"
          class="check-icon"
          @click="updateGoal"
        />

        <img
          src="../assets/trash.png"
          alt="delete-icon"
          class="delete-icon"
          @click="deleteGoal"
        />
      </div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import VueConfetti from "vue-confetti";
import goalService from "../services/GoalService";

Vue.use(VueConfetti);

export default {
  name: "list-goals",
  data() {
    return {
      updatedGoal: {
        name: "",
        id: 0,
        customerId: this.$store.state.profile.customerId,
        exerciseId: 1,
        date: null,
        reps: 0.0,
        weight: 0.0,
        time: 0.0,
        sets: 0.0,
        days: 0,
        misc: null,
        completed: false,
      },
    };
  },
  computed: {
    filteredGoalsNotCompleted() {
      return this.$store.state.goalList.filter((g) => g.completed == false);
    },
  },
  methods: {
    updateGoal(e) {
      const goalId = e.target.parentElement.parentElement.dataset.goalId;
      const userId = this.$store.state.profile.customerId;

      this.$store.state.goalList.forEach((g) => {
        if (g.id == goalId) {
          this.updatedGoal = g;
        }
      });

      this.updatedGoal.completed = true;
      delete this.updatedGoal.id;

      goalService
        .updateGoal(userId, goalId, this.updatedGoal)
        .then((res) => {
          if (res.status == 200) {
            this.doConfetti();
          }
        })
        .catch((error) => console.error(error));
    },
    deleteGoal(e) {
      const goalId = e.target.parentElement.parentElement.dataset.goalId;

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
    doConfetti() {
      this.$confetti.start({

        particles: [
          {
            type: "circle",
          },
          {
            type: "heart",
          },
        ],
        defaultColors: [
          "DodgerBlue",
          "OliveDrab",
          "Gold",
          "pink",
          "SlateBlue",
          "lightblue",
          "Violet",
          "PaleGreen",
          "SteelBlue",
          "SandyBrown",
          "Chocolate",
          "Crimson",
        ],
        particlesPerFrame: 4,
        windSpeedMax: 1,
        defaultSize: 5,
      });

      setTimeout(() => {
        this.$confetti.stop();
      }, 3000);
    },
  },
};
</script>

<style scoped>
.goal {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: var(--smoke);
  width: 70%;
  margin: 0 auto 10px auto;
  height: 60px;
  border-radius: 10px;
  padding: 0 25px;
}

.check-icon,
.delete-icon {
  cursor: pointer;
}


.goals-icons {
  display: none;
}

.goal:hover .goals-icons {
  display: flex;
  gap: 10px;
  animation: fade-in 1s ease;
}

.goal:hover {
  background: linear-gradient(90deg, #143625 75%, var(--green) 0%);
  
}


@keyframes fade-in {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

@keyframes fade-out {
  100% {
    opacity: 0;
  }
  0% {
    opacity: 1;
  }
}



</style>
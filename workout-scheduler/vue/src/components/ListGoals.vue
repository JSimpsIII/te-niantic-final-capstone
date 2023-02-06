<template>
    <div id='list-goals'>
        <div
          class="goal"
          v-for="goal in this.$store.state.goalList"
          :key="goal.id"
          :data-goal-id="goal.id"
        >
          <div class="goal-details">
            {{ goal.name }}
            {{
              goal.reps != 0 ||
              goal.weight != 0 ||
              goal.time != 0 ||
              goal.distance != 0 ||
              goal.days != 0 ||
              goal.misc != null
                ? ":"
                : ""
            }}
            {{ goal.reps != 0 ? goal.reps : "" }}
            {{ goal.weight != 0 ? goal.weight : "" }}
            {{ goal.time != 0 ? goal.time : "" }}
            {{ goal.distance != 0 ? goal.distance : "" }}
            {{ goal.days != 0 ? goal.days : "" }}
            {{ goal.misc != null ? goal.misc : "" }}
          </div>
          <img
            src="../assets/trash.png"
            alt="delete-icon"
            class="delete-icon"
            @click="deleteGoal"
          />
        </div>
    </div>
</template>

<script>
import goalService from "../services/GoalService";

export default {
    name: 'list-goals',
    methods: {
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
    }
    }

}
</script>

<style scoped>
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
</style>
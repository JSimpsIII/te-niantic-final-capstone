<template>
    <div id='list-goals'>
        <div
          class="goal"
          v-for="goal in this.$store.state.goalList"
          :key="goal.id"
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
export default {
    name: 'list-goals'

}
</script>

<style>

</style>
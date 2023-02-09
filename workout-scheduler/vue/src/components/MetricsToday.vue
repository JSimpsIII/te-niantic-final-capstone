<template>
  <div>
      <h3>Minutes per Exercise</h3>
    <bar-chart></bar-chart>
    <hr>
    <h3>Minutes by Target Muscle Group</h3>
    <target-bar-chart></target-bar-chart>
  </div>
</template>

<script>
import BarChart from './charts/WorkoutsTodayBarChart.vue'
import TargetBarChart from './charts/TargetTodayBarChart.vue';

export default {
  name: "metrics-today",
  components: {
    BarChart,
    TargetBarChart
  }
}
</script>

<style scoped>
h3 {
  text-align: center;
  margin-bottom: 10px;
}

hr {
  width: 70%;
  margin: 60px auto 50px auto;
}
</style>
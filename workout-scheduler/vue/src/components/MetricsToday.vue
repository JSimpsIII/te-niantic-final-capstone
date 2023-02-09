<template>
  <div>
      <h3>Minutes per Exercise</h3>
    <bar-chart></bar-chart>
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
</style>
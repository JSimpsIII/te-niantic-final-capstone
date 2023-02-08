<template>
  <div>
      <h3>Minutes per Exercise</h3>
    <bar-chart></bar-chart>
  </div>
</template>

<script>
import BarChart from './charts/WorkoutsTodayBarChart.vue'

export default {
  name: "metrics-today",
  components: {
    BarChart
  }
}
</script>

<style scoped>
h3 {
  text-align: center;
  margin-bottom: 10px;
}
</style>
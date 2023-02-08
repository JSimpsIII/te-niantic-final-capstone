<template>
  <div id="equipment-donut-chart">
      <h3>Workouts by Equipment</h3>
        <donut-chart></donut-chart>
  </div>
</template>

<script>
import DonutChart from './charts/MachinesPercentageDonutChart.vue';

export default {
  name: "metrics-machine",
  components: {
    DonutChart
  }
}
</script>

<style scoped>

h3 {
  text-align: center;
  margin-bottom: 10px;
}

</style>
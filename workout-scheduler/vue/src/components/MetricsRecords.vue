<template>
  <div>
      <h3>Weights</h3>
    <radar-chart></radar-chart>
  </div>
</template>

<script>
import RadarChart from './charts/PersonalRecordsRadarChart.vue';

export default {
  name: "metrics-records",
  components: {
    RadarChart
  }
}
</script>

<style scoped>
h3 {
  text-align: center;
  margin-bottom: 10px;
}
</style>
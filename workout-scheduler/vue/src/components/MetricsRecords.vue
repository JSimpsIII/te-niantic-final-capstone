<template>
  <div>
      <h3>Radar Chart</h3>
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
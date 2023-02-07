<template>
  <div>
      <h3>Exercise Time by Day</h3>
        <donut-chart></donut-chart>
  </div>
</template>

<script>
import DonutChart from './charts/MachinesPercentageDonutChart.vue';

export default {
  name: "metrics-minutes",
  components: {
    DonutChart
  }
}
</script>
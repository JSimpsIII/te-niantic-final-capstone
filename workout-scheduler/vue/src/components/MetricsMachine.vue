<template>
  <div>
      <h3>Exercise Percentages by Equipment</h3>
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
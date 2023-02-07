<template>
  <div>
      <h3>Exercise Time by Last 7 Days</h3>
    <bar-chart></bar-chart>
  </div>
</template>

<script>
import BarChart from './charts/MinutesPerDayBarChart.vue';

export default {
  name: "metrics-minutes",
  components: {
    BarChart
  }
}
</script>
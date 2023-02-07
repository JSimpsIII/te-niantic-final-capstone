<template>
  <div>
    <bar-chart></bar-chart>
    <pie-chart></pie-chart>
  </div>
</template>

<script>
import PieChart from './charts/PieChart';
import BarChart from './charts/MinutesPerDayBarChart.vue';

export default {
  name: "metrics-minutes",
  components: {
    PieChart,
    BarChart
  }
}
</script>
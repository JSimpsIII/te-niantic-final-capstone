<template>
  <div>
    <h3>Pie Chart Example in Vue</h3>
    <pie-chart></pie-chart>
  </div>
</template>

<script>
import PieChart from './charts/PieChart'
export default {
  name: "metrics-minutes",
  components: {
    PieChart
  }
}
</script>
<template>
  <div id='time-by-body-target'>
      <h3>Minutes per Target Muscle Group</h3>
      <time-by-body-target-donut-chart></time-by-body-target-donut-chart>
  </div>
</template>

<script>
import TimeByBodyTargetDonutChart from '../components/charts/TimeByBodyTargetDonutChart.vue'

export default {
    name: 'time-by-body-target',
    components: { TimeByBodyTargetDonutChart },
}
</script>

<style scoped>
h3 {
  text-align: center;
  margin-bottom: 10px;
}
</style>
<template>
  <div id='time-by-body-part'>
      <h3>Minutes per Body Part</h3>
      <time-by-body-part-donut-chart></time-by-body-part-donut-chart>
  </div>
</template>

<script>
import TimeByBodyPartDonutChart from './charts/TimeByBodyPartDonutChart.vue'
export default {
    name: 'time-by-body-part',
    components: { TimeByBodyPartDonutChart }
}
</script>

<style scoped>
h3 {
  text-align: center;
  margin-bottom: 10px;
}
</style>
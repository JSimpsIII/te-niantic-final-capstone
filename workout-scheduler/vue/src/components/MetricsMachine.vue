<template>
  <div id="equipment-donut-chart">
      <h3>Total Workouts per Equipment</h3>
        <donut-chart></donut-chart>
        <hr>
        <h3 id="minutes-header">Total Minutes per Equipment</h3>
        <equipment-minutes></equipment-minutes>
  </div>
</template>

<script>
import DonutChart from './charts/MachinesPercentageDonutChart.vue';
import EquipmentMinutes from './charts/EquipmentTimeDonutChart.vue';

export default {
  name: "metrics-machine",
  components: {
    DonutChart,
    EquipmentMinutes
  }
}
</script>

<style scoped>

h3 {
  text-align: center;
  margin-bottom: 10px;
}

#minutes-header {
  margin-top: 10px;
}

hr {
  width: 70%;
  margin: 60px auto 50px auto;
}


</style>
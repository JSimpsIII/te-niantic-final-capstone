<template>
  <div id='gym-visits-year'>
      <h3>Year</h3>
      <gym-check-ins-chart />
  </div>
</template>

<script>
import GymCheckInsChart from '../components/charts/GymCheckInsChart.vue'

export default {
    name: 'gym-visits-year',
    components: {
        GymCheckInsChart
    }
}
</script>

<style>

</style>
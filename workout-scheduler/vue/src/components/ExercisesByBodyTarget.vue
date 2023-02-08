<template>
    <div id='exercises-by-body-target'>
        <h3>Exercises by Target Muscle Group</h3>
        <exercises-by-body-target-donut-chart></exercises-by-body-target-donut-chart>
    </div>
</template>

<script>
import ExercisesByBodyTargetDonutChart from './charts/ExercisesByBodyTargetDonutChart.vue'
export default {
    name: 'exercises-by-body-target',
    components: { ExercisesByBodyTargetDonutChart }
}
</script>

<style scoped>
h3 {
  text-align: center;
  margin-bottom: 10px;
}
</style>
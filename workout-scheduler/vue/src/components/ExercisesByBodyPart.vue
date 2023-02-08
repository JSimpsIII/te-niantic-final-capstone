<template>
    <div id='exercises-by-body-part'>
        <h3>Exercises by Body Part</h3>
        <exercises-by-body-part-donut-chart></exercises-by-body-part-donut-chart>
    </div>
</template>

<script>
import ExercisesByBodyPartDonutChart from './charts/ExercisesByBodyPartDonutChart.vue'

export default{
    name: 'exerciese-by-body-part',
    components: {
        ExercisesByBodyPartDonutChart
    }
}
</script>

<style scoped>
h3 {
  text-align: center;
  margin-bottom: 10px;
}
</style>
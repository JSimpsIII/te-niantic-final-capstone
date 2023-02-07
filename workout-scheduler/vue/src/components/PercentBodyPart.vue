<template>
    <div id='percent-body-part'>
        <h3>Exercise Time by Body Part</h3>
        <percent-body-part-pie-chart></percent-body-part-pie-chart>
    </div>
</template>

<script>
import PercentBodyPartPieChart from '../components/charts/PercentBodyPartPieChart.vue'

export default{
    name: 'percent-body-part',
    components: {
        PercentBodyPartPieChart
    }
}
</script>

<style scoped>
</style>
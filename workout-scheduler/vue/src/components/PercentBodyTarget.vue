<template>
    <div id='percent-body-target'>
        <h3>Exercise Time by Body Target</h3>
        <percent-body-target-pie-chart></percent-body-target-pie-chart>
    </div>
</template>

<script>
import PercentBodyTargetPieChart from '../components/charts/PercentBodyTargetPieChart.vue'
export default {
    name: 'percent-body-target',
    components: { PercentBodyTargetPieChart }
}
</script>

<style>

</style>
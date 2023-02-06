<template>
    <div id='percent-body-part'>

        <h2>Strength: {{ this.percentStrength() }}%</h2>
        
            <h3>Arms: {{ this.percentUpperLimbs() }}%</h3>
                <p>Shoulders: {{ this.percentShoulders() }}%</p>
                <p>Upper Arms: {{ this.percentUpperArms() }}%</p>
                <p>Lower Arms: {{ this.percentLowerArms() }}%</p>
        
            <h3>Legs: {{ this.percentLowerLimbs() }}%</h3>
                <p>Upper Legs: {{ this.percentUpperLegs() }}%</p>
                <p>Lower Legs: {{ this.percentLowerLegs() }}%</p>
        
            <h3>Core: {{ this.percentCore() }}%</h3>
                <p>Neck: {{ this.percentNeck() }}%</p>
                <p>Chest: {{ this.percentChest() }}%</p>
                <p>Back: {{ this.percentBack() }}%</p>
                <p>Waist: {{ this.percentWaist() }}%</p>

        <h2>Cardio: {{ this.percentCardio() }}%</h2>

    </div>
</template>

<script>
import metricsStatsService from '../services/MetricsStatsService.js'

export default {
    name: 'percent-body-part',
    computed: {
        percentBack() {
            return metricsStatsService.getPercentBack();
        },
        percentCardio() {
            return metricsStatsService.getPercentCardio();
        },
        percentChest() {
            return metricsStatsService.getPercentChest();
        },
        percentUpperArms() {
            return metricsStatsService.getPercentUpperArms();
        },
        percentLowerArms() {
            return metricsStatsService.getPercentLowerArms();
        },
        percentUpperLegs() {
            return metricsStatsService.getPercentUpperLegs();
        },
        percentLowerLegs() {
            return metricsStatsService.getPercentLowerLegs();
        },
        percentNeck() {
            return metricsStatsService.getPercentNeck();
        },
        percentShoulders() {
            return metricsStatsService.getPercentShoulders();
        },
        percentWaist() {
            return metricsStatsService.getPercentWaist();
        },
        percentUpperLimbs() {
            return metricsStatsService.getPercentUpperLimbs();
        },
        percentLowerLimbs() {
            return metricsStatsService.getPercentLowerLimbs();
        },
        percentCore() {
            return metricsStatsService.getPercentCore();
        },
        percentStrength() {
            return metricsStatsService.getPercentStrength();
        }
    }

}
</script>

<style scoped>
</style>
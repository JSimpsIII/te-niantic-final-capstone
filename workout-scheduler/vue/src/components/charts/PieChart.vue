<template>
    <div id='pie-chart'>
        <!-- <Pie 
            :chart-data='chartData' 
            :chart-options='chartOptions'
            :chart-id='chartId'
            :dataset-id-key='datasetIdKey'
            :plugins='plugins'
            :css-classes='cssClasses'
            :styles='styles'
            :width='width'
            :height='height'
        /> -->
    </div>
</template>

<script>
// import { Pie } from 'vue-chartjs/legacy'
// import {
//     Chart as ChartJs, 
//     Title, 
//     Tooltip, 
//     Legend, 
//     ArcElement, 
//     CategoryScale 
// } from 'chart.js'

// ChartJs.register(
//     Title, 
//     Tooltip, 
//     Legend, 
//     ArcElement, 
//     CategoryScale
// )

export default {
    name: 'pie-chart',
    // components: {
    //     Pie
    // },
    props: {
        chartId: {
            type: String,
            default: 'pie-chart'
        },
        datasetIdKey: {
            type: String,
            default: 'label'
        },
        width: {
            type: Number,
            default: 300
        },
        height: {
            type: Number,
            default: 300
        },
        cssClasses: {
            type: String,
            default: ''
        },
        styles: {
            type: Object,
            default: () => {}
        },
        plugins: {
            type: Array,
            default: () => []
        }
    },
    data() {
        return {
            chartData: {
                labels: [],
                datasets: []
            },
            chartOptions: {
                responsive: true,
                maintainAspectRation: false
            }
        }
    }
}
</script>

<style>

</style>
<template>
    <div id='bar-chart'>
        <Bar 
            :chart-data='chartData' 
            :chart-options='chartOptions' 
            :chart-id='chartId'
            :dataset-id-key='datasetIdKey'
            :plugins='plugins'
            :css-classes='cssClasses'
            :styles='styles'
            :width='width'
            :height='height'
        />
    </div>
</template>

<script>
import { Bar } from 'vue-chartjs/legacy'
import { 
    Chart as ChartJS, 
    Title, 
    Tooltip, 
    Legend, 
    BarElement, 
    CategoryScale, 
    LinearScale 
} from 'chart.js'

ChartJS.register(
    Title, 
    Tooltip, 
    Legend, 
    BarElement, 
    CategoryScale, 
    LinearScale
)

export default {
    name: 'bar-chart',
    components: { Bar },
    props: {
        chartId: {
        type: String,
        default: 'bar-chart'
        },
        datasetIdKey: {
        type: String,
        default: 'label'
        },
        width: {
        type: Number,
        default: 400
        },
        height: {
        type: Number,
        default: 400
        },
        cssClasses: {
        default: '',
        type: String
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
                maintainAspectRatio: false
            }
        }
    }
}
</script>

<style>

</style>
<template>
    <div id='body-part-focus-chart'>
        <Pie 
            :chart-options="chartOptions"
            :chart-data="chartData"
            :chart-id="chartId"
            :dataset-id-key="datasetIdKey"
            :plugins="plugins"
            :css-classes="cssClasses"
            :styles="styles"
            :width="width"
            :height="height" 
        />
    </div>
</template>

<script>
import ChartsService from '../../../services/ChartsService.js'
import { Pie } from 'vue-chartjs/legacy'
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
    name: 'bodypart-focus-chart',
    components: { Pie },
    data() {
        return {
            chartId: 'bodypart-focus-chart',
            datasetIdKey: 'body part',
            height: 300,
            width: 300,
            chartData: {
                labels: [
                    'arms (upper)',
                    'arms (lower)', 
                    'back', 
                    'cardio', 
                    'chest', 
                    'legs (upper)',
                    'legs (lower)', 
                    'neck', 
                    'shoulders',
                    'waist'
                ],
                dataSets: [
                    {
                        backgroundColor: [
                            '#CC99FF', // 'back'
                            '#FF9999', // 'cardio'
                            '#FF9933', // 'chest'
                            '#CCFFCC', // 'lower arms'
                            '#CCFFFF', // 'lower legs' 
                            '#9900FF', // 'neck'
                            '#66CCCC', // 'shoulders' 
                            '#3366FF', // 'upper arms' 
                            '#339999', // 'upper legs' 
                            '#FF33CC'  // 'waist'
                        ],
                        data: this.percentagesArray
                    }
                ]
            }
        }
    },
    computed: {
        percentagesArray() {
            return ChartsService.getPercentageArrayForBodyPart();
        }
    }

}
</script>

<style>

</style>
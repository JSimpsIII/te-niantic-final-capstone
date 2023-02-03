<template>
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
</template>

<script>
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
            let minback = 0
            let mincardio = 0
            let minchest = 0
            let minlowerarms = 0
            let minlowerlegs = 0
            let minneck = 0
            let minshoulders = 0
            let minupperarms = 0
            let minupperlegs = 0
            let minwaist = 0
            let mintotal = 0
            this.$store.state.metricsList.forEach(metric => {
                let eId = metric.exerciseId;
                this.$store.state.exerciseList.forEach(exercise => {
                    if (exercise.id == eId) {
                        switch (exercise.bodyPart) {
                            case 'back':
                                minback += metric.time;
                                mintotal += metric.time;
                                break;
                            case 'cardio':
                                mincardio += metric.time;
                                mintotal += metric.time;
                                break;
                            case 'chest':
                                minchest += metric.time;
                                mintotal += metric.time;
                                break;
                            case 'lower arms':
                                minlowerarms += metric.time;
                                mintotal += metric.time;
                                break;
                            case 'lower legs':
                                minlowerlegs += metric.time;
                                mintotal += metric.time;
                                break;
                            case 'neck':
                                minneck += metric.time;
                                mintotal += metric.time;
                                break;
                            case 'shoulders':
                                minshoulders += metric.time;
                                mintotal += metric.time;
                                break;
                            case 'upper arms':
                                minupperarms += metric.time;
                                mintotal += metric.time;
                                break;
                            case 'upper legs':
                                minupperlegs += metric.time;
                                mintotal += metric.time;
                                break;
                            case 'waist':
                                minwaist += metric.time;
                                mintotal += metric.time;
                                break;
                        }
                    }
                })
            })
            let array = [minback/mintotal, mincardio/mintotal, minchest/mintotal, minlowerarms/mintotal, minlowerlegs/mintotal, minneck/mintotal, minshoulders/mintotal, minupperarms/mintotal, minupperlegs/mintotal, minwaist/mintotal];
            return array;
        }
    }

}
</script>

<style>

</style>
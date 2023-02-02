<template>
  <Pie :data='chartData' />
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
            chartData: {
                labels: [
                    'back', 
                    'cardio', 
                    'chest', 
                    'lower arms', 
                    'lower legs', 
                    'neck', 
                    'shoulders', 
                    'upper arms', 
                    'upper legs', 
                    'waist'
                ],
                dataSets: [

                ]
            }
        }
    }

}
</script>

<style>

</style>
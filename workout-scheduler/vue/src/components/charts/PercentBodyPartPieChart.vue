<script>
  import { Pie } from 'vue-chartjs'
  import metricsStatsService from '../../services/MetricsStatsService'

  export default {
    extends: Pie,
    data () {
      return {
        chartData: {
          labels: ['back', 'cardio', 'chest', 'lower arms', 'lower legs', 'neck', 'shoulders', 'upper arms', 'upper legs', 'waist'],
          datasets: [{
              borderWidth: 1,
              borderColor: [
              'rgba(204, 102, 255, 1)',
              'rgba(255, 102, 153, 1)',
              'rgba(255, 102, 51, 1)',
              'rgba(204, 51, 102, 1)',
              'rgba(204, 0, 153, 1)',
              'rgba(102, 0, 255, 1)',
              'rgba(102, 0, 153, 1)',
              'rgba(102, 153, 0, 1)',
              'rgba(153, 204, 153, 1)',
              'rgba(51, 255, 204, 1)'            
              ],
              backgroundColor: [
              'rgba(204, 102, 255, 0.2)',
              'rgba(255, 102, 153, 0.2)',
              'rgba(255, 102, 51, 0.2)',
              'rgba(204, 51, 102, 0.2)',
              'rgba(204, 0, 153, 0.2)',
              'rgba(102, 0, 255, 0.2)',
              'rgba(102, 0, 153, 0.2)',
              'rgba(102, 153, 0, 0.2)',
              'rgba(153, 204, 153, 0.2)',
              'rgba(51, 255, 204, 0.2)'                       
              ],
              data: metricsStatsService.getPercentageArrayForBodyPart
            }]
        },
        options: {
          legend: {
            display: true
          },
          responsive: true,
          maintainAspectRatio: false
        }
      }
    },
    mounted () {
      this.renderChart(this.chartData, this.options)
    }
  }
</script>
<script>
  import { Pie } from 'vue-chartjs'
  import metricsStatsService from '../../services/MetricsStatsService'

  export default {
    extends: Pie,
    computed: {
      dataArray() {
        return metricsStatsService.getPercentageArrayForBodyTarget()
      }
    },
    data () {
      return {
        chartData: {
          labels: [
              'abductors', 'abs', 'adductors', 'biceps', 'calves', 'cardiovascular', 'delts', 'forearms', 'glutes', 'hamstrings', 'levator scapulae', 'pectorals', 'quads', 'serratus anterior', 'spine', 'traps', 'triceps', 'upper back'
          ],
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
              'rgba(51, 255, 204, 1)',
              'rgba(244, 67, 54, 1)',
              'rgba(233, 30, 99, 1)',
              'rgba(225, 190, 231, 1)',
              'rgba(101, 31, 255, 1)',
              'rgba(63, 81, 181, 1)',
              'rgba(66, 165, 245, 1)',
              'rgba(0, 188, 212, 1)',
              'rgba(0, 137, 123, 1)'
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
              'rgba(51, 255, 204, 0.2)',
              'rgba(244, 67, 54, 0.2)',
              'rgba(233, 30, 99, 0.2)',
              'rgba(225, 190, 231, 0.2)',
              'rgba(101, 31, 255, 0.2)',
              'rgba(63, 81, 181, 0.2)',
              'rgba(66, 165, 245, 0.2)',
              'rgba(0, 188, 212, 0.2)',
              'rgba(0, 137, 123, 0.2)'                       
              ],
              data: [1, 50]
            }]
        },
        options: {
          legend: {
            display: true
          },
          responsive: true,
          maintainAspectRatio: false
        },
        exerciseObj: {}
      }
    },
    mounted () {
      this.renderChart(this.chartData, this.options)
    },
    created() {
      this.chartData.datasets[0].data = metricsStatsService.getPercentageArrayForBodyTarget();
    }
  }
</script>
<script>
  import { Radar } from 'vue-chartjs'
  export default {
    extends: Radar,
    data () {
      return {
        chartData: {
          labels: [
             "workout1",
             "workout2",
             "workout3",
             "workout4",
             "workout5"
          ],
          datasets: [
            {
              label: 'Radar Chart',
              borderWidth: 1,
              backgroundColor: 'rgba(54, 162, 235, 0.7)',
             
              data: [
                120,
                110,
                50,
                20,
                200
              ]
            }
          ]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          scale: {
              gridLines: {
                  color: 'lightblue'
              }
          }
        }
      }
    },
    mounted () {
      this.renderChart(this.chartData, this.options)
    }
  }
</script>
<script>
  import { Radar } from 'vue-chartjs'
  export default {
    extends: Radar,
    data () {
      return {
        chartData: {
          labels: [
          ],
          datasets: [
            {
              label: 'Personal Record',
              borderWidth: 1,
              backgroundColor: 'rgba(204, 174, 6, 0.6)',
             
              data: [
                120,
                50
              ]
            }
          ]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          scale: {
              gridLines: {
                  color: 'lightyellow'
              }
          },
          legend: {
            display: false
          }
        }
      }
    },
    mounted () {
      this.renderChart(this.chartData, this.options)
    },
    created() {
      this.chartData.labels = this.getEquipmentNameForLabels();

    },
    methods: {
      getEquipmentNameForLabels() {
      const uniqueExerciseMachines = this.getUniqueExerciseIds();

      const exerciseMachines = [];
      this.$store.state.exerciseList.forEach((machine) => {
        if (uniqueExerciseMachines.includes(machine.id)) {
          exerciseMachines.push(machine.equipment);
        }
      });
      return [...new Set(exerciseMachines)];
    },
    getUniqueExerciseIds() {
      const userExerciseMachines = this.$store.state.metricsList.map(
        (m) => m.exerciseId
      );

      return [...new Set(userExerciseMachines)];
    }
    }
  }
</script>

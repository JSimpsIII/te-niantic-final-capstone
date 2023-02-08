<script>
  import { Doughnut } from 'vue-chartjs'

  export default {
    extends: Doughnut,
    data () {
      return {
        chartData: {
          labels: [],
          datasets: [{
              borderWidth: 1,
              borderColor: [
              'rgba(0, 176, 255, 1)',
              "rgba(255, 61, 0, 1)",
              'rgba(178, 255, 89, 1)',
              "rgba(255, 183, 77, 1)",
              'rgba(0, 191, 165, 1)',
              'rgba(255, 255, 141, 1)',
              'rgba(255, 64, 129, 1)',
              'rgba(0, 229, 255, 1)',
              'rgba(124, 77, 255, 1)',
              'rgba(156, 39, 176, 1)',
              'rgba(244, 67, 54, 1)',
              "rgba(191, 54, 12, 1)",
              'rgba(0, 200, 83, 1)',
              "rgba(255, 109, 0, 1)",
              'rgba(94, 53, 177, 1)',
              'rgba(21, 101, 192, 1)',
              'rgba(251, 192, 45, 1)', 
              'rgba(40, 53, 147, 1)',
              'rgba(197, 17, 98, 1)'
              ],
              backgroundColor: [
              'rgba(0, 176, 255, 0.55)',
              "rgba(255, 61, 0, 0.55)",
              'rgba(178, 255, 89, 0.55)',
              "rgba(255, 183, 77, 0.55)",
              'rgba(0, 191, 165, 0.55)',
              'rgba(255, 235, 59, 0.55)',
              'rgba(255, 64, 129, 0.55)',
              'rgba(0, 229, 255, 0.55)',
              'rgba(124, 77, 255, 0.55)',
              'rgba(156, 39, 176, 0.55)',
              'rgba(244, 67, 54, 0.55)',
              "rgba(191, 54, 12, 0.55)",
              'rgba(0, 200, 83, 0.55)',
              "rgba(255, 109, 0, 0.55)",
              'rgba(94, 53, 177, 0.55)',
              'rgba(21, 101, 192, 0.55)',
              'rgba(251, 192, 45, 0.55)', 
              'rgba(40, 53, 147, 0.55)',
              'rgba(197, 17, 98, 0.55)'                   
              ],
              data: [1, 50]
            }]
        },
        options: {
          legend: {
            display: true,
            labels: {
              fontColor: "#FDFFFC"
            }
          },
          responsive: true,
          maintainAspectRatio: false
        },
        exerciseObj: {}
      }
    },
      mounted() {
    this.renderChart(this.chartData, this.options);
  },
  created() {
    // labels for donut chart
    this.chartData.labels = this.getMachineBodyTargetNameForLabels();

    // setup keys for machineObj
    this.machineObj = this.convertArrayToObject(this.getMachineBodyTargetNameForLabels());

    // get ids for each exercise - for machineObj
    this.$store.state.exerciseList.forEach(e => {
        if (e.target in this.machineObj) {
            this.machineObj[e.target].ids.push(e.id);
        }
    });

    // get totals for each exercise - for machineObj
    this.$store.state.metricsList.forEach(m => {
        for (const prop in this.machineObj) {
            if (this.machineObj[prop].ids.includes(m.exerciseId)) {
                this.machineObj[prop].total += 1;
            }
        }
    })

    this.chartData.datasets[0].data = this.getMachineBodyTargetNameForLabels()
                                          .map(m => this.machineObj[m].total)
    

  },
  methods: {
    getMachineBodyTargetNameForLabels() {
      const uniqueExerciseMachines = this.getUniqueExerciseIds();

      const exerciseMachines = [];
      this.$store.state.exerciseList.forEach((machine) => {
        if (uniqueExerciseMachines.includes(machine.id)) {
          exerciseMachines.push(machine.target);
        }
      });
      return [...new Set(exerciseMachines)];
    },
    convertArrayToObject(array) {
      return array.reduce(
        (obj, x) => ({ ...obj, [x]: { total: 0, ids: []} }),
        {}
      );
    },
    getUniqueExerciseIds() {
      const userExerciseMachines = this.$store.state.metricsList.map(
        (m) => m.exerciseId
      );

      return [...new Set(userExerciseMachines)];
    },
  },
};
</script>
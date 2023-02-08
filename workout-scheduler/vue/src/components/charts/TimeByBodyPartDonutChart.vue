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
        metricsDictionary: {},
        exerciseDictionary: {}
      }
    },
  mounted() {
      this.renderChart(this.chartData, this.options);
  },
  created() {
    // labels for chart
    this.chartData.labels = this.getBPLabelArray();

    // map lables as key for metrics dictionary and set value to 0
    this.chartData.labels.forEach(name => {
        this.metricsDictionary[name] = 0;
    })
    
    // map { exercise id : body part name } to dictionary
    this.$store.state.exerciseList.forEach(exercise => {
        this.exerciseDictionary[exercise.id] = exercise.bodyPart;
    });
    
    // iterate through metrics and total minutes per body part
    this.$store.state.metricsList.forEach(metric => {
        let bodyPart = this.exerciseDictionary[metric.exerciseId];
        let minutes = this.metricsDictionary[bodyPart];
        let addMinutes = metric.time;
        this.metricsDictionary[bodyPart] = (minutes + addMinutes);
    });

    // map metrics dictionary to chart data
    this.chartData.datasets[0].data = this.getBPLabelArray().map(name => this.metricsDictionary[name])
  },
  methods: {
    getBPLabelArray() {
      const uniqueExerciseBodyParts = this.getUniqueExerciseIds();
      const exerciseArray = [];
      this.$store.state.exerciseList.forEach((item) => {
        if (uniqueExerciseBodyParts.includes(item.id)) {
          exerciseArray.push(item.bodyPart);
        }
      });
      return [...new Set(exerciseArray)];
    },
    getUniqueExerciseIds() {
      const userExerciseBodyParts = this.$store.state.metricsList.map(m => m.exerciseId
      );
      return [...new Set(userExerciseBodyParts)];
    }
  }
};
</script>
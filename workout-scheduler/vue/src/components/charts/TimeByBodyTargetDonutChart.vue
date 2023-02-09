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
              backgroundColor: [
              "rgba(75, 192, 192, 0.2)",
              "rgba(153, 102, 255, 0.2)",
              "rgba(255, 159, 64, 0.2)",
              "rgba(255, 99, 132, 0.2)",
              "rgba(54, 162, 235, 0.2)",
              "rgba(255, 206, 86, 0.2)",
              "rgba(75, 192, 192, 0.2)",
              "rgba(153, 102, 255, 0.2)",
              "rgba(255, 159, 64, 0.2)",
              "rgba(255, 99, 132, 0.2)",
              "rgba(54, 162, 235, 0.2)",
              "rgba(255, 206, 86, 0.2)",
            ],
            borderColor: [
              "rgba(75, 192, 192, 1)",
              "rgba(153, 102, 255, 1)",
              "rgba(255, 159, 64, 1)",
              "rgba(255,99,132,1)",
              "rgba(54, 162, 235, 1)",
              "rgba(255, 206, 86, 1)",
              "rgba(75, 192, 192, 1)",
              "rgba(153, 102, 255, 1)",
              "rgba(255, 159, 64, 1)",
              "rgba(255,99,132,1)",
              "rgba(54, 162, 235, 1)",
              "rgba(255, 206, 86, 1)",
              "rgba(75, 192, 192, 1)",
              "rgba(153, 102, 255, 1)",
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
    this.chartData.labels = this.getTargetLabelArray();

    // map lables as key for metrics dictionary and set value to 0
    this.chartData.labels.forEach(name => {
        this.metricsDictionary[name] = 0;
    })
    
    // map { exercise id : body part name } to dictionary
    this.$store.state.exerciseList.forEach(exercise => {
        this.exerciseDictionary[exercise.id] = exercise.target;
    });
    
    // iterate through metrics and total minutes per body part
    this.$store.state.metricsList.forEach(metric => {
        let target = this.exerciseDictionary[metric.exerciseId];
        let minutes = this.metricsDictionary[target];
        let addMinutes = metric.time;
        this.metricsDictionary[target] = (minutes + addMinutes);
    });

    // map metrics dictionary to chart data
    this.chartData.datasets[0].data = this.getTargetLabelArray().map(name => this.metricsDictionary[name])
  },
  methods: {
    getTargetLabelArray() {
      const uniqueExerciseTargets = this.getUniqueExerciseIds();
      const exerciseArray = [];
      this.$store.state.exerciseList.forEach((item) => {
        if (uniqueExerciseTargets.includes(item.id)) {
          exerciseArray.push(item.target);
        }
      });
      return [...new Set(exerciseArray)];
    },
    getUniqueExerciseIds() {
      const userExerciseTargets = this.$store.state.metricsList.map(m => m.exerciseId
      );
      return [...new Set(userExerciseTargets)];
    }
  }
};
</script>
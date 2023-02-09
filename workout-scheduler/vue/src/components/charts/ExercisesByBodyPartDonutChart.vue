<script>
import { Doughnut } from "vue-chartjs";

export default {
  extends: Doughnut,
  data() {
    return {
      chartData: {
        labels: [],
        datasets: [
          {
            borderWidth: 1,
            backgroundColor: [
              "rgba(54, 162, 235, 0.2)",
              "rgba(255, 99, 132, 0.2)",
              "rgba(255, 206, 86, 0.2)",
              "rgba(75, 192, 192, 0.2)",
              "rgba(153, 102, 255, 0.2)",
              "rgba(255, 159, 64, 0.2)",
              "rgba(255, 99, 132, 0.2)",
              "rgba(54, 162, 235, 0.2)",
              "rgba(255, 206, 86, 0.2)",
              "rgba(75, 192, 192, 0.2)",
              "rgba(153, 102, 255, 0.2)",
              "rgba(255, 159, 64, 0.2)",
            ],
            borderColor: [
              "rgba(54, 162, 235, 1)",
              "rgba(255,99,132,1)",
              "rgba(255, 206, 86, 1)",
              "rgba(75, 192, 192, 1)",
              "rgba(153, 102, 255, 1)",
              "rgba(255, 159, 64, 1)",
              "rgba(255,99,132,1)",
              "rgba(54, 162, 235, 1)",
              "rgba(255, 206, 86, 1)",
              "rgba(75, 192, 192, 1)",
              "rgba(153, 102, 255, 1)",
              "rgba(255, 159, 64, 1)",
            ],
            data: [1, 50],
          },
        ],
      },
      options: {
        legend: {
          display: true,
          labels: {
            fontColor: "#FDFFFC",
          },
        },
        responsive: true,
        maintainAspectRatio: false,
      },
      bodyPartObj: {},
    };
  },
  mounted() {
    this.renderChart(this.chartData, this.options);
  },
  created() {
    // labels for donut chart
    this.chartData.labels = this.getBodyPartNameForLabels();

    // setup keys for bpObj
    this.bodyPartObj = this.convertArrayToObject(
      this.getBodyPartNameForLabels()
    );

    // get ids for each exercise - for bpObj
    this.$store.state.exerciseList.forEach((e) => {
      if (e.bodyPart in this.bodyPartObj) {
        this.bodyPartObj[e.bodyPart].ids.push(e.id);
      }
    });

    // get totals for each exercise - for machineObj
    this.$store.state.metricsList.forEach((m) => {
      for (const prop in this.bodyPartObj) {
        if (this.bodyPartObj[prop].ids.includes(m.exerciseId)) {
          this.bodyPartObj[prop].total += 1;
        }
      }
    });

    this.chartData.datasets[0].data = this.getBodyPartNameForLabels().map(
      (m) => this.bodyPartObj[m].total
    );
  },
  methods: {
    getBodyPartNameForLabels() {
      const uniqueExerciseBodyParts = this.getUniqueExerciseIds();

      const exerciseArray = [];
      this.$store.state.exerciseList.forEach((item) => {
        if (uniqueExerciseBodyParts.includes(item.id)) {
          exerciseArray.push(item.bodyPart);
        }
      });
      return [...new Set(exerciseArray)];
    },
    convertArrayToObject(array) {
      return array.reduce(
        (obj, x) => ({ ...obj, [x]: { total: 0, ids: [] } }),
        {}
      );
    },
    getUniqueExerciseIds() {
      const userExerciseBodyParts = this.$store.state.metricsList.map(
        (m) => m.exerciseId
      );
      return [...new Set(userExerciseBodyParts)];
    },
  },
};
</script>
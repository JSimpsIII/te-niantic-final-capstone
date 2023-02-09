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
      exerciseObj: {},
    };
  },
  mounted() {
    this.renderChart(this.chartData, this.options);
  },
  created() {
    // labels for donut chart
    this.chartData.labels = this.getMachineBodyTargetNameForLabels();

    // setup keys for machineObj
    this.machineObj = this.convertArrayToObject(
      this.getMachineBodyTargetNameForLabels()
    );

    // get ids for each exercise - for machineObj
    this.$store.state.exerciseList.forEach((e) => {
      if (e.target in this.machineObj) {
        this.machineObj[e.target].ids.push(e.id);
      }
    });

    // get totals for each exercise - for machineObj
    this.$store.state.metricsList.forEach((m) => {
      for (const prop in this.machineObj) {
        if (this.machineObj[prop].ids.includes(m.exerciseId)) {
          this.machineObj[prop].total += 1;
        }
      }
    });

    this.chartData.datasets[0].data =
      this.getMachineBodyTargetNameForLabels().map(
        (m) => this.machineObj[m].total
      );
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
        (obj, x) => ({ ...obj, [x]: { total: 0, ids: [] } }),
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
<script>
import { Bar } from "vue-chartjs";

export default {
  extends: Bar,
  data() {
    return {
      chartData: {
        labels: [],
        datasets: [
          {
            label: "minutes",
            borderWidth: 1,
            backgroundColor: [
              "rgba(255, 99, 132, 0.2)",
              "rgba(54, 162, 235, 0.2)",
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
              "rgba(255, 159, 64, 1)",
            ],
            pointBorderColor: "#2554FF",
            data: [],
          },
        ],
      },
      options: {
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true,
                fontColor: "#FDFFFC",
              },
              gridLines: {
                display: true,
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                display: false,
              },
              ticks: {
                fontColor: "#FDFFFC",
              },
            },
          ],
        },
        legend: {
          display: false,
        },
        responsive: true,
        maintainAspectRatio: false,
      },
      workoutsObj: {},
      today: "",
    };
  },
  mounted() {
    this.renderChart(this.chartData, this.options);
  },
  created() {
    // labels for bar chart
    this.chartData.labels = this.getWorkoutNameForLabels();

    // setup keys for workoutsObj
    this.workoutsObj = this.convertArrayToObject(
      this.getWorkoutNameForLabels()
    );

    // get ids for each bodyPart - for workoutsObj
    this.$store.state.exerciseList.forEach((e) => {
      if (e.target in this.workoutsObj) {
        this.workoutsObj[e.target].ids.push(e.id);
      }
    });

    // get totalMins for each bodyPart - for workoutsObj
    this.$store.state.metricsList.forEach((m) => {
      for (const prop in this.workoutsObj) {
        if (this.workoutsObj[prop].ids.includes(m.exerciseId) && m.date == this.today) {
          this.workoutsObj[prop].totalMins += m.time;
        }
      }
    });

    // set chart data to array of totalMins
    this.chartData.datasets[0].data = this.getWorkoutNameForLabels().map(
      (m) => this.workoutsObj[m].totalMins
    );
  },
  methods: {
    getWorkoutNameForLabels() {
      const uniqueExerciseMachines = this.getUniqueExerciseIdsForToday();

      const targets = [];
      this.$store.state.exerciseList.forEach((machine) => {
        if (uniqueExerciseMachines.includes(machine.id)) {
          targets.push(machine.target);
        }
      });
      return [...new Set(targets)];
    },
    convertArrayToObject(array) {
      return array.reduce(
        (obj, x) => ({ ...obj, [x]: { totalMins: 0, ids: [] } }),
        {}
      );
    },
    getUniqueExerciseIdsForToday() {
      const d = new Date();
      const year = d.toLocaleString("default", { year: "numeric" });
      const month = d.toLocaleString("default", { month: "2-digit" });
      const day = d.toLocaleString("default", { day: "2-digit" });
      const today = `${year}-${month}-${day}`;
      this.today = today;
      const userExerciseMachines = this.$store.state.metricsList
        .filter((m) => m.date == today)
        .map((m) => m.exerciseId);

      return [...new Set(userExerciseMachines)];
    },
  },
};
</script>
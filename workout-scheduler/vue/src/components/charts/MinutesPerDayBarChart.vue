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
            label: "Minutes",
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
                fontColor: "#FDFFFC"
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
                fontColor: "#FDFFFC"
              }
            },
          ],
        },
        legend: {
          display: false,
        },
        responsive: true,
        maintainAspectRatio: false,
      },
      metricsObj: {},
    };
  },
  mounted() {
    this.renderChart(this.chartData, this.options);
  },
  created() {
    this.chartData.labels = this.getLastSevenDays();

    // convert 7 days array to obj with dates as keys
    this.metricsObj = this.convertArrayToObject(this.getLastSevenDays());

    // iterate through metrics and total up
    this.$store.state.metricsList.forEach((metric) => {
      if (metric.date in this.metricsObj) {
        this.metricsObj[metric.date] += metric.time;
      }
    });

    // set data of barchart
    this.chartData.datasets[0].data = Object.values(this.metricsObj);
  },
  methods: {
    getLastSevenDays() {
      const dates = [...Array(7)].map((_, i) => {
        const d = new Date();
        d.setDate(d.getDate() - i);
        const year = d.toLocaleString("default", { year: "numeric" });
        const month = d.toLocaleString("default", { month: "2-digit" });
        const day = d.toLocaleString("default", { day: "2-digit" });
        return `${year}-${month}-${day}`;
      });
      return dates.reverse();
    },
    convertArrayToObject(array) {
      return array.reduce((obj, x) => ({ ...obj, [x]: 0 }), {});
    },
  },
};

</script>
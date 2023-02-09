<script>
import { Bar } from "vue-chartjs";

export default {
  name: 'gym-check-ins-chart',
  extends: Bar,
  data() {
    return {
      chartData: {
        labels: [],
        datasets: [
          {
            label: "Gym Visits",
            borderWidth: 1,
            backgroundColor: [
              "rgba(255, 99, 132, 0.4)",
              "rgba(54, 162, 235, 0.4)",
              "rgba(255, 206, 86, 0.4)",
              "rgba(75, 192, 192, 0.4)",
              "rgba(153, 102, 255, 0.4)",
              "rgba(255, 159, 64, 0.4)",
              "rgba(255, 99, 132, 0.4)",
              "rgba(54, 162, 235, 0.4)",
              "rgba(255, 206, 86, 0.4)",
              "rgba(75, 192, 192, 0.4)",
              "rgba(153, 102, 255, 0.4)",
              "rgba(255, 159, 64, 0.4)",
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
                beginAtZero: true,
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
      monthObj: {},
      visitObj: {},
      finalValues: {}
    };
  },
  mounted() {
    this.renderChart(this.chartData, this.options);
  },
  created() {
    this.chartData.labels = this.getLabelArray();
    this.monthObj = {
     'JAN':'01', 'FEB':'02', 'MAR':'03', 
     'APR':'04', 'MAY':'05', 'JUN':'06',
     'JUL':'07', 'AUG':'08', 'SEP':'09', 
     'OCT':'10', 'NOV':'11', 'DEC':'12' 
    };
    this.visitObj = {
      '01': 0, '02': 0, '03': 0, '04': 0, '05': 0, '06': 0,
      '07': 0, '08': 0, '09': 0, '10': 0, '11': 0, '12': 0
    };

    this.$store.state.metricsList.forEach(metric => {
      console.log(metric.date);
      let date = metric.date.toString();
      let year = date.substring(0, 4);
      let now = new Date().toISOString();
      let thisyear = now.substring(0, 4);
      if (year == thisyear) {
        let month = date.substring(5, 7);
        let old = this.visitObj[month];
        this.visitObj[month] = old+1;
      }
    });

    this.finalValues = this.getFinalValues();

    this.chartData.datasets[0].data = this.getLabelArray.map(name => this.visitObj[this.monthObj[name]]);
  },
  methods: {
    getLabelArray() {
      const array = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG','SEP', 'OCT', 'NOV', 'DEC'];
      return [...new Set(array)];
    },
    getFinalValues() {
      for (let [key, value] of Object.entries(this.visitObj)) {
        let mo = key;
        let name = this.monthObj[mo];
        console.log(name);
        this.finalValues[name, value];
      }
    }
  }
};

</script>
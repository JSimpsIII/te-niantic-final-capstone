<script>
import { Radar } from "vue-chartjs";
export default {
  extends: Radar,
  data() {
    return {
      chartData: {
        labels: [],
        datasets: [
          {
            label: "Record",
            borderWidth: 1,
            backgroundColor: 'rgba(255,99,132,0.5)',
            borderColor: 'rgba(255,99,132,1)',
            pointBackgroundColor: 'rgba(255,99,132,1)',
            pointBorderColor: '#fff',
            pointHoverBackgroundColor: '#fff',
            pointHoverBorderColor: 'rgba(255,99,132,1)',
            data: [],
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scale: {
          gridLines: {
            color: "lightyellow",
          },
          pointLabels: {
            fontColor: "#FDFFFC",
            fontSize: 12,
          },
          ticks: {
            beginAtZero: true,
          }
        },
        legend: {
          display: false,
        },
        tooltips: {
          callbacks: {
            title: (tooltipItem, data) => data.labels[tooltipItem[0].index]
          }
        }
      },
      recordsObj: {},
    };
  },
  mounted() {
    this.renderChart(this.chartData, this.options);
  },
  created() {
    // labels for radar chart
    // this.chartData.labels = this.getEquipmentNameForLabels();

    // setup keys for recordsObj
    this.recordsObj = this.convertArrayToObject(
      this.getEquipmentNameForLabels()
    );

    // get ids for each exercise - for recordsObj
    this.$store.state.exerciseList.forEach((e) => {
      if (e.equipment in this.recordsObj) {
        this.recordsObj[e.equipment].ids.push(e.id);
      }
    });

    // get max records for each exercise
    this.$store.state.metricsList.forEach(m => {
        for (const prop in this.recordsObj) {
            if (this.recordsObj[prop].ids.includes(m.exerciseId)) {
              if (this.recordsObj[prop].record < m.weight) {
                this.recordsObj[prop].record = m.weight;
              }
                
            }
        }
    })

    // label for radar chart (only equipment with records > 0)
    for (const prop in this.recordsObj) {
      if (this.recordsObj[prop].record > 0 && !this.chartData.labels.includes(prop)) {
        this.chartData.labels.push(prop);
      }
    }

    // set chart data to pull only records matching chartData.labels
    this.chartData.datasets[0].data = this.chartData.labels
                                          .map(m => this.recordsObj[m].record);

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
    },
    convertArrayToObject(array) {
      return array.reduce(
        (obj, x) => ({ ...obj, [x]: { record: 0, ids: [] } }),
        {}
      );
    },
  },
};
</script>

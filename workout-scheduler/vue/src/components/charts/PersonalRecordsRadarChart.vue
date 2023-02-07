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
            label: "Personal Record",
            borderWidth: 1,
            backgroundColor: "rgba(204, 174, 6, 0.6)",

            data: [120, 50, 10, 200],
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
        },
        legend: {
          display: false,
        },
      },
      recordsObj: {},
    };
  },
  mounted() {
    this.renderChart(this.chartData, this.options);
  },
  created() {
    // labels for radar chart
    this.chartData.labels = this.getEquipmentNameForLabels();

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

    // set chart data to array of records
    this.chartData.datasets[0].data = this.getEquipmentNameForLabels()
                                          .map(m => this.recordsObj[m].record)
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

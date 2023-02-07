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
              'rgba(204, 102, 255, 1)',
              'rgba(255, 102, 153, 1)',
              'rgba(255, 102, 51, 1)',
              'rgba(204, 51, 102, 1)',
              'rgba(204, 0, 153, 1)',
              'rgba(102, 0, 255, 1)',
              'rgba(102, 0, 153, 1)',
              'rgba(102, 153, 0, 1)',
              'rgba(153, 204, 153, 1)',
              'rgba(51, 255, 204, 1)'            
              ],
              backgroundColor: [
              'rgba(204, 102, 255, 0.2)',
              'rgba(255, 102, 153, 0.2)',
              'rgba(255, 102, 51, 0.2)',
              'rgba(204, 51, 102, 0.2)',
              'rgba(204, 0, 153, 0.2)',
              'rgba(102, 0, 255, 0.2)',
              'rgba(102, 0, 153, 0.2)',
              'rgba(102, 153, 0, 0.2)',
              'rgba(153, 204, 153, 0.2)',
              'rgba(51, 255, 204, 0.2)'                       
              ],
              data: [1, 50]
            }]
        },
        options: {
          legend: {
            display: true
          },
          responsive: true,
          maintainAspectRatio: false
        },
      bodyPartObj: {},
    };
  },
  mounted() {
    this.renderChart(this.chartData, this.options);
  },
  created() {
    // labels for donut chart
    this.chartData.labels = this.getBodyPartEquipmentNameForLabels();

    // setup keys for bodyPartObj
    this.bodyPartObj = this.convertArrayToObject(this.getBodyPartEquipmentNameForLabels());

    // get ids for each exercise - for bodyPartObj
    this.$store.state.exerciseList.forEach(e => {
        if (e.bodyPart in this.bodyPartObj) {
            this.bodyPartObj[e.bodyPart].ids.push(e.id);
        }
    });

    // get totals for each exercise - for bodyPartObj
    this.$store.state.metricsList.forEach(m => {
        for (const prop in this.bodyPartObj) {
            if (this.bodyPartObj[prop].ids.includes(m.exerciseId)) {
                this.bodyPartObj[prop].total += 1;
            }
        }
    })

    this.chartData.datasets[0].data = this.getbodyPartEquipmentNameForLabels()
                                          .map(m => this.bodyPartObj[m].total)
    

  },
  methods: {
    getbodyPartEquipmentNameForLabels() {
      const uniqueExerciseBodyParts = this.getUniqueExerciseIds();

      const exerciseBodyParts = [];
      this.$store.state.exerciseList.forEach((exercise) => {
        if (uniqueExerciseBodyParts.includes(exercise.id)) {
          exerciseBodyParts.push(exercise.bodyPart);
        }
      });
      return [...new Set(exerciseBodyParts)];
    },
    convertArrayToObject(array) {
      return array.reduce(
        (obj, x) => ({ ...obj, [x]: { total: 0, ids: []} }),
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
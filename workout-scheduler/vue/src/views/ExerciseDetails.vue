<template>
    <section id='exercise-details'>
        <h1>{{exercise.name}}</h1>
            
        <div class='flexbox'>
            <p class='target'>{{exercise.target}}</p>
            <p> | </p>
            <p class='bodypart'>{{exercise.bodyPart}}</p>
            <p> | </p>
            <p class='eqipment'>{{exercise.equipment}}</p>
        </div>

        <div class='gif'>
            <img :src='exercise.gifUrl'>
        </div>

        <exercise-entry />

        <footer>
          <nav-bar />
        </footer>

    </section>
</template>

<script>
import ExerciseEntry from '../components/ExerciseEntry.vue';
import exerciseService from '../services/ExerciseService';
import NavBar from '../components/NavBar.vue'

export default {
  components: { NavBar, ExerciseEntry },
    name: 'exercise-details',
    methods: {
      getExercise() {
        exerciseService
          .getExerciseById(this.$route.params.id)
          .then(response => {
            this.$store.commit('SET_CURRENT_EXERCISE', response.data);
          })
          .catch(error => {
            if (error.response && error.response.status === 404) {
              alert("Exercise not available.");
              this.$router.push({ name: 'Gym' });
            }
          })
      }
    },
    created() {
      this.getExercise();
    },
    computed: {
      exercise() {
        let exercises = this.$store.state.exerciseList.find(exercise => 
            exercise.id == this.$route.params.id)
            return exercises;
        }
    }
}
</script>

<style scoped>

h1 {
  margin-top: 30px;
  margin-bottom: 20px;
  color: sandybrown;
}

#exercise-details {
  display: flex;
  flex-direction: column;
  width: 100%;
  text-align: center;
  justify-content: center;
  align-items: center;
  background-color: rgb(29, 34, 39);
}

.flexbox {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  width: 360px;
  justify-content: space-evenly;
  color: #85C1E9;
}

p {
  padding: 10px;
}

.gif {
  margin-bottom: 20px;
}

footer {
  position: fixed;
  bottom: 0;
  width: 600px;
  height: 50px;
  background: var(--smoke);
  padding-top: 15px;
}

</style>
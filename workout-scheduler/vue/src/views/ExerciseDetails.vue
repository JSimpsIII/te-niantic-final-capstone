<template>
  <section id='exercise-details'>
    <h1>{{exercise.name}}</h1>
    <div class='flexbox'>
      <p class='target'>{{exercise.target}}</p>
      <p class='bodypart'>{{exercise.bodyPart}}</p>
      <p class='eqipment'>{{exercise.equipment}}</p>
    </div>
    <div class='gif'>
      <img :src='exercise.gifUrl'>
    </div>
  </section>
</template>

<script>
import exerciseService from '../services/ExerciseService';

export default {
    name: 'exercise-details',
    methods: {
      getExercise() {
        exerciseService
          .getExercise(this.$route.params.id)
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

<style>

</style>
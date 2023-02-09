<template>
    <div id="gym-page-container">
        
        <img id="gym-img" src="../assets/gym-2.jpg" alt="gym-picture">

        <div class="gym-banner">

        </div>

       <exercise-table />

        <footer>
            <nav-bar />
        </footer>
    </div>
</template>

<script>
import ExerciseTable from '../components/ExerciseTable.vue';
import NavBar from '../components/NavBar.vue'
import exerciseService from '../services/ExerciseService'

export default {
    name: "gym",
    components: {
      NavBar,
      ExerciseTable
    },
    data() {
      return {}
    },
    methods: {
      loadExerciseList() {
        exerciseService
          .getAllExercises()
          .then(response => {
            this.$store.commit('LOAD_EXERCISE_LIST', response.data);
          })
      }
    },
    created() {
      this.loadExerciseList();
    }
}
</script>

<style scoped>
#gym-img {
  width: 100%;
  height: 270px;
  object-fit: cover;
  object-position: bottom;

}
.gym-banner {
  background-color: var(--blue);
  font-size: 25px;
}

.gym-title-add-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-top: 30px;
  padding-bottom: 30px;
  background-color: rgba(29, 61, 89, 0.2);
}

.gym-instructions-container {
  display: flex;
  justify-content: center;
  padding: 10px;
  background-color: sandybrown;
}

#gym-instructions {
  font-size: 20px;
  font-weight: bold;
  color: var(--smoke);
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
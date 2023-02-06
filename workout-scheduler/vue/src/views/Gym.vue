<template>
    <div id="gym-page-container">
        
        <img id="gym-img" src="../assets/gym-2.jpg" alt="gym-picture">

        <div class="gym-banner">

            <div class="gym-title-add-container">

                <div class="gym-title">Virtual Gym</div>

            </div>
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

export default {
    name: "gym",
    components: {
      NavBar,
      ExerciseTable
    },
    data() {
      
    }
}
</script>

<style>
#gym-img {
  width: 100%;
  height: 270px;

}
.gym-banner {
  background-color: var(--blue);
  font-size: 25px;
}

.gym-title-add-container {
  display: flex;
  justify-content: center;
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
<template>
    <div id="gym-page-container">
        
        <img id="gym-img" src="../assets/gym-2.jpg" alt="gym-picture">

        <div class="gym-banner">

            <div class="gym-title-add-container">
                <add-gym-visit />
            </div>

        </div>

       <exercise-table />

        <footer>
            <nav-bar />
        </footer>
    </div>
</template>

<script>
import AddGymVisit from '../components/AddGymVisit.vue';
import ExerciseTable from '../components/ExerciseTable.vue';
import NavBar from '../components/NavBar.vue'

export default {
    name: "gym",
    components: {
      NavBar,
      ExerciseTable,
        AddGymVisit
    },
    data() {
      return {}
    }
}
</script>

<style scoped>
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
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-top: 30px;
  padding-bottom: 30px;
  background-color: rgb(29, 61, 89);
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
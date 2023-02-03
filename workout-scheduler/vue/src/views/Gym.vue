<template>
  <div id="gym-page-container">
    <img id="gym-img" src="../assets/gym-2.jpg" alt="gym-picture">
    <div class="gym-banner">
      <div class="gym-title-add-container">
         <div class="gym-title">Gym</div>
         <!-- <div class="exercise-search-container">
           <input type="text" id="exerciseNameFilter" v-model="filter.exerciseName"/>
          </div> -->
      </div>
    </div>
    <table id="exercise-table" v-if="selectingExercise">
      <thead>
        <tr>
          <th>&nbsp;</th>
          <th>Exercise</th>
          <th>Exercise Target</th>
          <th>Bodypart Used</th>
          <th>Equipment Used</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>&nbsp;</td>
          <td>
            <input type="text" id="exerciseNameFilter" v-model="filter.exerciseName"/>
          </td>
          <td>
            <select id="exerciseTargetFilter" v-model="filter.exerciseTarget">
              <option value>Show All</option>
              <option value="abs">Abs</option>
              <option value="quads">Quads</option>
              <option value="lats">Lats</option>
            </select>
          </td>
          <td>
            <select id="bodypartFilter" v-model="filter.bodypartName">
              <option value>Show All</option>
              <option value="waist">Waist</option>
              <option value="upper legs">Upper Legs</option>
              <option value="back">Back</option>
            </select>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr
          v-for="exercise in allExercises"
          v-bind:key="exercise.id"
        >
          <td>
            &nbsp;
          </td>
          <td>{{ exercise.name }}</td>
          <td>{{ exercise.target }}</td>
          <td>{{ exercise.bodyPart }}</td>
          <td>{{ exercise.equipment }}</td>
        </tr>
      </tbody>
    </table>

    <footer>
      <nav-bar />
    </footer>
  </div>
</template>

<script>
import NavBar from '../components/NavBar.vue'
import exerciseService from '../services/ExerciseService';

export default {
    name: "gym",
    components: {
      NavBar
    },
    data() {
      return {
        filter: {
          exerciseName: "",
          bodypartName: "",
          equipmentName: "",
          exerciseTarget: ""
        },
        exercises: [
          {
            exerciseId: 1,
            exerciseName: "3/4 sit-up",
            bodypartName: "waist",
            equipmentName: "body weight",
            exerciseTarget: "abs",
            gifURL: "http://d205bpvrqc9yn1.cloudfront.net/0001.gif"
          },
          {
            exerciseId: 2,
            exerciseName: "45° side bend",
            bodypartName: "waist",
            equipmentName: "body weight",
            exerciseTarget: "abs",
            gifURL: "http://d205bpvrqc9yn1.cloudfront.net/0002.gif"
          },
          {
            exerciseId: 3,
            exerciseName: "air bike",
            bodypartName: "waist",
            equipmentName: "body weight",
            exerciseTarget: "abs",
            gifURL: "http://d205bpvrqc9yn1.cloudfront.net/0003.gif"
          },
          {
            exerciseId: 4,
            exerciseName: "all fours squad stretch",
            bodypartName: "upper legs",
            equipmentName: "body weight",
            exerciseTarget: "quads",
            gifURL: "http://d205bpvrqc9yn1.cloudfront.net/1512.gif"
          },  {
            exerciseId: 5,
            exerciseName: "alternate lateral pulldown",
            bodypartName: "back",
            equipmentName: "cable",
            exerciseTarget: "lats",
            gifURL: "http://d205bpvrqc9yn1.cloudfront.net/0007.gif"
          },
        ],
        selectingExercise: true,
        selectedWorkoutIDs: []
      }
    },
    created() {
      this.loadExercises();
    },
    computed: {
      filteredList() {
        let filteredexercises = this.exercises;
        if (this.filter.exerciseName != "") {
          filteredexercises = filteredexercises.filter((exercise) =>
          exercise.exerciseName
            .toLowerCase()
            .includes(this.filter.exerciseName.toLowerCase())
        );
      }
      if (this.filter.exerciseTarget != "") {
        filteredexercises = filteredexercises.filter((exercise) =>
          exercise.exerciseTarget === this.filter.exerciseTarget
        );
      }
      if (this.filter.bodypartName != "") {
        filteredexercises = filteredexercises.filter((exercise) =>
          exercise.bodypartName === this.filter.bodypartName
        );
      }
    return filteredexercises;
    },
    allExercises() {
      return this.$store.state.exerciseList;
    }
    },
    methods: {
      loadExercises() {
        exerciseService
        .getAllExercises()
        .then(response => {
          this.$store.commit("LOAD_EXERCISE_LIST", response.data);
          this.isLoading = false;
          })
      }
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
  margin-bottom: 20px;
}

.gym-title-add-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 70%;
  margin: 0 auto;
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
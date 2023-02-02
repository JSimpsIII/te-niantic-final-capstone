<template>
  <div id="gym-page-container">
    <img id="gym-img" src="../assets/gym-2.jpg" alt="gym-picture">
    <div class="gym-banner">
      <div class="gym-title-add-container">
         <div class="gym-title">Gym</div>
         <!-- <div class="excercise-search-container">
           <input type="text" id="excerciseNameFilter" v-model="filter.excerciseName"/>
          </div> -->
      </div>
    </div>
    <table id="excercise-table" v-if="!selectingExercise">
      <thead>
        <tr>
          <th>&nbsp;</th>
          <th>Excercise</th>
          <th>Excercise Target</th>
          <th>Bodypart Used</th>
          <th>Equipment Used</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>&nbsp;</td>
          <td>
            <input type="text" id="excerciseNameFilter" v-model="filter.excerciseName"/>
          </td>
          <td>
            <select id="excerciseTargetFilter" v-model="filter.excerciseTarget">
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
          v-for="excercise in filteredList"
          v-bind:key="excercise.id"
        >
          <td>
            &nbsp;
          </td>
          <td>{{ excercise.excerciseName }}</td>
          <td>{{ excercise.excerciseTarget }}</td>
          <td>{{ excercise.bodypartName }}</td>
          <td>{{ excercise.equipmentName }}</td>
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
export default {
    name: "gym",
    components: {
      NavBar
    },
    data() {
      return {
        filter: {
          excerciseName: "",
          bodypartName: "",
          equipmentName: "",
          excerciseTarget: ""
        },
        excercises: [
          {
            excerciseId: 1,
            excerciseName: "3/4 sit-up",
            bodypartName: "waist",
            equipmentName: "body weight",
            excerciseTarget: "abs",
            gifURL: "http://d205bpvrqc9yn1.cloudfront.net/0001.gif"
          },
          {
            excerciseId: 2,
            excerciseName: "45° side bend",
            bodypartName: "waist",
            equipmentName: "body weight",
            excerciseTarget: "abs",
            gifURL: "http://d205bpvrqc9yn1.cloudfront.net/0002.gif"
          },
          {
            excerciseId: 3,
            excerciseName: "air bike",
            bodypartName: "waist",
            equipmentName: "body weight",
            excerciseTarget: "abs",
            gifURL: "http://d205bpvrqc9yn1.cloudfront.net/0003.gif"
          },
          {
            excerciseId: 4,
            excerciseName: "all fours squad stretch",
            bodypartName: "upper legs",
            equipmentName: "body weight",
            excerciseTarget: "quads",
            gifURL: "http://d205bpvrqc9yn1.cloudfront.net/1512.gif"
          },  {
            excerciseId: 5,
            excerciseName: "alternate lateral pulldown",
            bodypartName: "back",
            equipmentName: "cable",
            excerciseTarget: "lats",
            gifURL: "http://d205bpvrqc9yn1.cloudfront.net/0007.gif"
          },
        ],
        isAddingGoal: false,
        selectedWorkoutIDs: []
      }
    },
    computed: {
      filteredList() {
        let filteredExcercises = this.excercises;
        if (this.filter.excerciseName != "") {
          filteredExcercises = filteredExcercises.filter((excercise) =>
          excercise.excerciseName
            .toLowerCase()
            .includes(this.filter.excerciseName.toLowerCase())
        );
      }
      if (this.filter.excerciseTarget != "") {
        filteredExcercises = filteredExcercises.filter((excercise) =>
          excercise.excerciseTarget === this.filter.excerciseTarget
        );
      }
      if (this.filter.bodypartName != "") {
        filteredExcercises = filteredExcercises.filter((excercise) =>
          excercise.bodypartName === this.filter.bodypartName
        );
      }
    return filteredExcercises;
    }
    },
    methods: {}
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
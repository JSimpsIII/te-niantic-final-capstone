<template>
  <div id="exercise-table">
    <div class="black-background">
      <div>
        <p class="calendar-prompt" v-if="checkEmpty && !showForm">
          No upcoming workouts
        </p>
        <p class="calendar-prompt" v-if="!checkEmpty">Upcoming Workouts</p>
        <div v-for="item in scheduledExercises" v-bind:key="item.name">
          <p class="calendar-prompt" v-if="!cancelingExercise">
            {{ item.name }}, on {{ item.date }} at {{ item.time }}
          </p>
          <button
            class="calendar-prompt cancel-button"
            v-if="cancelingExercise"
            @click="deleteSelected(item)"
          >
            {{ item.name }}, on {{ item.date }} at {{ item.time }}
          </button>
        </div>
      </div>

      <div id="workout" class="gym-button" v-if="calendarActive && !showForm">
        <button id="workout-button" @click="openCalendar()">
          {{ buttonText }}
        </button>
      </div>

      <div id="workout-page-buttons">
        <div
          id="schedule"
          class="gym-button"
          v-if="!calendarActive && !cancelingExercise"
        >
          <button id="schedule-button" @click="openCalendar()">
            {{ buttonText }}
          </button>
        </div>

        <div
          id="cancel-schedule"
          class="gym-button"
          v-if="!calendarActive && !checkEmpty"
        >
          <button
            id="cancel-exercise-button"
            v-if="!cancelingExercise"
            @click="cancelExercise()"
          >
            Cancel Scheduled Exercise
          </button>
          <button
            id="cancel-exercise-button"
            v-if="cancelingExercise"
            @click="stopCanceling()"
          >
            Stop Canceling
          </button>
        </div>
      </div>

      <div class="gym-instructions-container">
        <div id="gym-instructions" v-if="!calendarActive">
          Select an exercise to begin
        </div>
        <div id="alternate-instructions" v-if="calendarActive && !showForm">
          Select an exercise to schedule
        </div>
        <form
          id="frmDateAndTime"
          v-show="showForm"
          v-on:submit.prevent="saveExercise"
        >
          <p id="selectDateTime">
            Select a date and time to schedule the "{{
              scheduledExercise.name
            }}" exercise
          </p>
          <div class="field">
            <label for="date">Date: </label>
            <input
              type="text"
              name="date"
              class="input-field"
              placeholder="MM/DD/YYYY"
              v-model="scheduledExercise.date"
            />
          </div>
          <div class="field">
            <label for="time">Time: </label>
            <input
              type="text"
              name="time"
              class="input-field"
              placeholder="00:00 PM"
              v-model="scheduledExercise.time"
            />
          </div>
          <button
            v-bind:disabled="submitButtonDisabled"
            type="submit"
            class="save-button"
            @click="openCalendar()"
          >
            Save
          </button>
          <button class="cancel-button" @click="cancelForm">Cancel</button>
        </form>
      </div>

      <div id="search-menu" v-show="!showForm">
        <img
          id="search-icon"
          class="icon-button"
          src="../assets/search.png"
          alt="search-icon"
          title="Search"
          @click="toggleSearch"
          v-if="!showSearchBar && !calendarActive"
        />
        <img
          id="search-icon"
          class="schedule-icon-button"
          src="../assets/search.png"
          alt="search-icon"
          title="Search"
          @click="toggleSearch"
          v-if="!showSearchBar && calendarActive"
        />
        <button
          class="cancel-button"
          v-if="showSearchBar"
          @click="toggleSearch"
        >
          Cancel
        </button>
        <input
          type="text"
          id="exerciseNameFilter"
          placeholder="search exercises"
          v-model="filter.name"
          v-if="showSearchBar"
        />
        <img
          id="filter-icon"
          class="icon-button"
          src="../assets/filter.png"
          alt="filter-icon"
          title="Enable Filters"
          @click="toggleFilters"
          v-if="showSearchBar && !calendarActive"
        />
        <img
          id="filter-icon"
          class="schedule-icon-button"
          src="../assets/filter.png"
          alt="filter-icon"
          title="Enable Filters"
          @click="toggleFilters"
          v-if="showSearchBar && calendarActive"
        />
      </div>
    </div>

    <table id="table-of-exercises" v-show="!showForm">
      <thead>
        <tr>
          <th class="first-column">Exercise</th>
          <th>Target</th>
          <th>Body Part</th>
          <th class="last-column">Equipment</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>&nbsp;</td>
          <td>
            <select
              id="exerciseTargetFilter"
              class="exercise-filter"
              v-model="filter.target"
              v-if="showFilters"
            >
              <option value>Show All</option>
              <option value="abductors">Abductors</option>
              <option value="abs">Abs</option>
              <option value="adductors">Adductors</option>
              <option value="biceps">Biceps</option>
              <option value="calves">Calves</option>
              <option value="cardiovascular">Cardiovascular</option>
              <option value="delts">Delts</option>
              <option value="forearms">Forearms</option>
              <option value="glutes">Glutes</option>
              <option value="hamstrings">Hamstrings</option>
              <option value="lats">Lats</option>
              <option value="levator scapulae">Levator Scapulae</option>
              <option value="pectorals">Pectorals</option>
              <option value="quads">Quads</option>
              <option value="serratus anterior">Serratus Anterior</option>
              <option value="spine">Spine</option>
              <option value="traps">Traps</option>
              <option value="triceps">Triceps</option>
              <option value="upper back">Upper Back</option>
            </select>
          </td>
          <td>
            <select
              id="bodypartFilter"
              class="exercise-filter"
              v-model="filter.bodyPart"
              v-if="showFilters"
            >
              <option value>Show All</option>
              <option value="back">Back</option>
              <option value="cardio">Cardio</option>
              <option value="chest">Chest</option>
              <option value="lower arms">Lower Arms</option>
              <option value="lower legs">Lower Legs</option>
              <option value="neck">Neck</option>
              <option value="shoulders">Shoulders</option>
              <option value="upper arms">Upper Arms</option>
              <option value="upper legs">Upper Legs</option>
              <option value="waist">Waist</option>
            </select>
          </td>
          <td>
            <select
              id="equipmentFilter"
              class="exercise-filter"
              v-model="filter.equipment"
              v-if="showFilters"
            >
              <option value>Show All</option>
              <option value="assisted">Assisted</option>
              <option value="band">Band</option>
              <option value="barbell">Barbell</option>
              <option value="body weight">Body Weight</option>
              <option value="bosu ball">Bosu Ball</option>
              <option value="cable">Cable</option>
              <option value="dumbbell">Dumbbell</option>
              <option value="elliptical machine">Elliptical</option>
              <option value="ez barbell">EZ Barbell</option>
              <option value="hammer">Hammer</option>
              <option value="kettlebell">Kettlebell</option>
              <option value="leverage machine">Leverage Machine</option>
              <option value="medicine ball">Medicine Ball</option>
              <option value="olympic barbell">Olympic Barbell</option>
              <option value="resistance band">Resistance Band</option>
              <option value="roller">Roller</option>
              <option value="rope">Rope</option>
              <option value="skierg machine">Skierg Machine</option>
              <option value="sled machine">Sled Machine</option>
              <option value="smith machine">Smith Machine</option>
              <option value="stability ball">Stability Ball</option>
              <option value="stationary bike">Stationary Bike</option>
              <option value="stepmill machine">Stepmill</option>
              <option value="tire">Tire</option>
              <option value="trap bar">Trap Bar</option>
              <option value="upper body ergometer">Upper Body Ergometer</option>
              <option value="weighted">Weighted</option>
              <option value="wheel roller">Wheel Roller</option>
            </select>
          </td>
        </tr>

        <tr
          v-for="exercise in filteredList"
          v-bind:key="exercise.id"
          class="exercise-option"
        >
          <td class="table-cell first-column">
            <router-link
              :to="{ name: 'exercise', params: { id: exercise.id } }"
              v-if="!calendarActive"
            >
              <button class="exercise-name">{{ exercise.name }}</button>
            </router-link>
            <button
              class="exercise-schedule-name"
              v-if="calendarActive"
              @click="toggleShowForm(exercise)"
            >
              {{ exercise.name }}
            </button>
          </td>
          <td class="column-list table-cell">{{ exercise.target }}</td>
          <td class="column-list table-cell">{{ exercise.bodyPart }}</td>
          <td class="table-cell last-column">{{ exercise.equipment }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import exerciseService from "../services/ExerciseService.js";

export default {
  name: "exercise-table",
  data() {
    return {
      filter: {
        name: "",
        target: "",
        bodyPart: "",
        equipment: "",
      },
      scheduledExercise: {
        name: "",
        date: "",
        time: "",
      },
      scheduledExercises: [],
      showSearchBar: false,
      showFilters: false,
      calendarActive: false,
      showForm: false,
      // canDeleteExercise: false,
      cancelingExercise: false,
    };
  },
  created() {
    this.loadExercises();
  },
  computed: {
    checkEmpty() {
      let empty = false;
      if (this.scheduledExercises.length == 0) {
        empty = true;
      }
      return empty;
    },
    buttonText() {
      let calendarText = "Schedule";
      if (this.calendarActive == true) {
        calendarText = "Workout";
      }
      return calendarText;
    },
    // removeExerciseText() {
    //     let removeButtonText = "Cancel Scheduled Exercise"
    //     if (this.cancelingExercise == true){
    //         removeButtonText = "Stop Canceling"
    //     }
    //     return removeButtonText
    // },
    allExercises() {
      return this.$store.state.exerciseList;
    },
    filteredList() {
      let filteredExercises = this.allExercises;
      if (this.filter.name != "") {
        filteredExercises = filteredExercises.filter((exercise) =>
          exercise.name.toLowerCase().includes(this.filter.name.toLowerCase())
        );
      }
      if (this.filter.target != "") {
        filteredExercises = filteredExercises.filter((exercise) =>
          exercise.target
            .toLowerCase()
            .includes(this.filter.target.toLowerCase())
        );
      }
      if (this.filter.bodyPart != "") {
        filteredExercises = filteredExercises.filter((exercise) =>
          exercise.bodyPart
            .toLowerCase()
            .includes(this.filter.bodyPart.toLowerCase())
        );
      }
      if (this.filter.equipment != "") {
        filteredExercises = filteredExercises.filter((exercise) =>
          exercise.equipment
            .toLowerCase()
            .includes(this.filter.equipment.toLowerCase())
        );
      }
      return filteredExercises;
    },
    actionButtonDisabled() {
      return this.showForm;
    },
    scheduleButtonDisabled() {
      return this.cancelingExercise;
    },
    submitButtonDisabled() {
      let submitDisbaled = false;
      if (
        this.scheduledExercise.date == "" ||
        this.scheduledExercise.time == ""
      ) {
        submitDisbaled = true;
      }
      return submitDisbaled;
    },
    // enableDeletion() {
    //     let deleteDisabled = true
    //     if (this.canDeleteExercise == true) {
    //         deleteDisabled = false
    //     }
    //     return deleteDisabled
    // }
  },
  methods: {
    loadExercises() {
      if (this.$store.state.exerciseList == []) {
        exerciseService.getAllExercises().then((response) => {
          this.$store.commit("LOAD_EXERCISE_LIST", response.data);
          this.isLoading = false;
        });
      }
    },
    toggleSearch() {
      this.showSearchBar = !this.showSearchBar;
      this.filter = {
        name: "",
        target: "",
        bodyPart: "",
        equipment: "",
      };
      if (this.showFilters == true) {
        this.showFilters = false;
      }
    },
    toggleFilters() {
      this.showFilters = !this.showFilters;
    },
    openCalendar() {
      this.calendarActive = !this.calendarActive;
      if (this.showSearchBar == true) {
        this.showSearchBar = false;
        this.filter = {
          name: "",
          target: "",
          bodyPart: "",
          equipment: "",
        };
        if (this.showFilters == true) {
          this.showFilters = false;
        }
      }
    },
    toggleShowForm(exercise) {
      this.showForm = !this.showForm;
      if (this.scheduledExercise.name == "") {
        this.scheduledExercise.name = exercise.name;
      }
      this.filter = {
        name: "",
        target: "",
        bodyPart: "",
        equipment: "",
      };
    },
    cancelForm() {
      this.showForm = !this.showForm;

      this.scheduledExercise = {
        name: "",
        date: "",
        time: "",
      };

      if (this.showSearchBar == true) {
        this.showSearchBar = false;
        this.filter = {
          name: "",
          target: "",
          bodyPart: "",
          equipment: "",
        };
        if (this.showFilters == true) {
          this.showFilters = false;
        }
      }
    },
    saveExercise() {
      this.showForm = false;

      const exerciseToSave = {
        name: this.scheduledExercise.name,
        date: this.scheduledExercise.date,
        time: this.scheduledExercise.time,
      };

      if (
        this.scheduledExercise.name != "" &&
        this.scheduledExercise.date != "" &&
        this.scheduledExercise.time != ""
      ) {
        this.scheduledExercises.push(exerciseToSave);
      }

      this.scheduledExercise = {
        name: "",
        date: "",
        time: "",
      };
    },
    cancelExercise() {
      this.cancelingExercise = true;
    },
    stopCanceling() {
      this.cancelingExercise = false;
    },
    deleteSelected(item) {
      this.scheduledExercises.forEach((exercise) => {
        const index = this.scheduledExercises.indexOf(exercise);
        if (exercise.name == item.name) {
          this.scheduledExercises.splice(index, 1);
        }
      });
      if (this.checkEmpty == true) {
        this.cancelingExercise = false;
      }
    },
  },
};
</script>


<style scoped>
.black-background {
  background-color: rgb(29, 34, 39);
}
#workout-page-buttons {
  display: flex;
  justify-content: center;
}
#exercise-table {
  background-color: var(--blue);
  text-align: center;
  padding-bottom: 60%;
}

#table-of-exercises {
  border-spacing: 0;
}

#search-menu {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 15px;
}

.gym-instructions-container {
  display: flex;
  justify-content: center;
  padding-top: 20px;
}

#gym-instructions {
  font-size: 18px;
  color: sandybrown;
}

#alternate-instructions {
  font-size: 18px;
  color: deepskyblue;
}

#search-icon {
  height: 5%;
  width: 5%;
  margin-right: 2%;
}

.cancel-button {
  margin-right: 1%;
  border: none;
  outline: none;
}

.cancel-button:hover {
  color: indianred;
}
#filter-icon {
  height: 5%;
  width: 5%;
  margin-left: 2%;
}

.icon-button {
  filter: invert(30%) sepia(62%) saturate(444%) hue-rotate(333deg)
    brightness(200%) contrast(89%);
}

.schedule-icon-button {
  filter: invert(30%) sepia(62%) saturate(555%) hue-rotate(155deg)
    brightness(200%) contrast(89%);
}

.icon-button:hover {
  cursor: pointer;
  transform: scale(1.25);
  filter: invert(38%) sepia(62%) saturate(311%) hue-rotate(166deg)
    brightness(180%) contrast(89%);
}

.schedule-icon-button:hover {
  cursor: pointer;
  transform: scale(1.25);
  filter: invert(38%) sepia(62%) saturate(311%) hue-rotate(166deg)
    brightness(180%) contrast(89%);
}
.input-field {
  height: 35px;
  padding: 3px;
  margin: 5px;
  width: 170px;
  border-radius: 5px;
  background-color: transparent;
  border: 1px solid sandybrown;
  color: inherit;
}



th {
  height: 70px;
  border-top: 2px solid rgb(29, 61, 89);
  background-color: rgb(29, 61, 89);
  border-bottom: 4px solid rgb(29, 61, 89);
}
.exercise-name {
  color: sandybrown;
  border: none;
  outline: none;
}

.exercise-schedule-name {
  color: deepskyblue;
  border: none;
  outline: none;
}

.exercise-name:hover {
  color: lightblue;
}

.exercise-schedule-name:hover {
  color: lightblue;
}

.calendar-prompt {
  padding-top: 20px;
  font-size: 1.3em;
  margin-bottom: 10px;
}

button {
  color: white;
  background-color: rgba(29, 61, 89, 0);
  font-size: 17px;
}

button:hover {
  cursor: pointer;
  transform: scale(1.15);
  /* font-size: 18px; */
}

.gym-button {
  padding-top: 20px;
  display: flex;
  flex-direction: column;
}

#schedule-button {
  background-color: dodgerblue;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 5px;
  color: #fdfffc;
  opacity: 1;
  margin: 0 auto;
  font-size: 20px;
  padding: 15px 20px;
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: center;
  border-radius: 10px;
  border: none;
  height: 60px;
  width: 120px;
}

#workout-button {
  background-color: sandybrown;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 5px;
  color: #fdfffc;
  opacity: 1;
  margin: 0 auto;
  font-size: 20px;
  padding: 15px 20px;
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: center;
  border-radius: 10px;
  border: none;
  height: 60px;
  width: 120px;
}

#cancel-exercise-button {
  background-color: var(--red);
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 5px;
  color: #fdfffc;
  opacity: 1;
  margin: 0 auto;
  font-size: 20px;
  padding: 15px 20px;
  margin-top: 10px;
  margin-bottom: 10px;
  margin-left: 10px;
  text-align: center;
  border-radius: 10px;
  border: none;
}

#cancel-exercise-button:hover {
    transform: scale(1.09);
}

.table-cell {
  height: 70px;
  border-bottom: 1px solid rgba(29, 61, 89, 0.5);
}
.first-column {
  padding-left: 2%;
}
.column-list {
  width: 20%;
}
.last-column {
  width: 22%;
  padding-right: 2%;
}
tr:nth-child(odd) {
  background-color: rgba(29, 61, 89, 0.3);
}

#exerciseNameFilter {
  background-color: transparent;
  border: 1px sandybrown solid;
  padding: 7px 5px;
  border-radius: 5px;
  color: inherit;
}

#exerciseTargetFilter,
#bodypartFilter,
#equipmentFilter {
  background-color: transparent;
  padding: 5px;
  border: 1px sandybrown solid;
  color: inherit;
  border-radius: 5px;
}

#exerciseTargetFilter option,
#bodypartFilter option,
#equipmentFilter option {
  background-color: var(--blue);
}

#cancel-exercise-button {
  height: 60px;
}

.save-button {
    margin-right: 10px;
}
.save-button,
.cancel-button {
    padding: 5px;
    border: 1px solid sandybrown;
    border-radius: 5px;
    margin-top: 10px;
    margin-bottom: 10px;
}

#selectDateTime {
    margin-bottom: 10px;
}

</style>
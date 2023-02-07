<template>
  <div id='exercise-table'>
  <div id='search-menu'>
  <img id="search-icon" src="../assets/search.png" alt="search-icon" title="Search" @click="toggleSearch" v-if="!showSearchBar">
  <button id="cancel-button" v-if="showSearchBar" @click="toggleSearch">Cancel</button>
  <input type="text" id="exerciseNameFilter" placeholder="search exercises" v-model="filter.name" v-if="showSearchBar"/>
  <img id="filter-icon" src="../assets/filter.png" alt="filter-icon" title="Enable Filters" @click="toggleFilters" v-if="showSearchBar">
  </div>
       <table id="table-of-exercises">
            <thead>
                <tr>
                    <th>Exercise</th>
                    <th>Exercise Target</th>
                    <th>Body Part Used</th>
                    <th>Equipment Required</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        &nbsp;
                    </td>
                    <td>
                        <select id="exerciseTargetFilter" 
                        class="exercise-filter" 
                        v-model="filter.target"
                        v-if="showFilters">
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
                        <select id="bodypartFilter" 
                        class="exercise-filter"
                        v-model="filter.bodyPart"
                        v-if="showFilters">
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
                        <select id="equipmentFilter" 
                        class="exercise-filter"
                        v-model="filter.equipment"
                        v-if="showFilters">
                            <option value>Show All</option>
                            <option value='assisted'>Assisted</option>
                            <option value='band'>Band</option>
                            <option value='barbell'>Barbell</option>
                            <option value='body weight'>Body Weight</option>
                            <option value='bosu ball'>Bosu Ball</option>
                            <option value='cable'>Cable</option>
                            <option value='dumbbell'>Dumbbell</option>
                            <option value='elliptical machine'>Elliptical</option>
                            <option value='ez barbell'>EZ Barbell</option>
                            <option value='hammer'>Hammer</option>
                            <option value='kettlebell'>Kettlebell</option>
                            <option value='leverage machine'>Leverage Machine</option>
                            <option value='medicine ball'>Medicine Ball</option>
                            <option value='olympic barbell'>Olympic Barbell</option>
                            <option value='resistance band'>Resistance Band</option>
                            <option value='roller'>Roller</option>
                            <option value='rope'>Rope</option>
                            <option value='skierg machine'>Skierg Machine</option>
                            <option value='sled machine'>Sled Machine</option>
                            <option value='smith machine'>Smith Machine</option>
                            <option value='stability ball'>Stability Ball</option>
                            <option value='stationary bike'>Stationary Bike</option>
                            <option value='stepmill machine'>Stepmill</option>
                            <option value='tire'>Tire</option>
                            <option value='trap bar'>Trap Bar</option>
                            <option value='upper body ergometer'></option>
                            <option value='weighted'>Weighted</option>
                            <option value='wheel roller'>Wheel Roller</option>
                        </select>
                    </td>
                </tr>

                <tr
                    v-for="exercise in filteredList"
                    v-bind:key="exercise.id"
                    class='exercise-option'>
                    <td>
                        <router-link :to="{ name: 'exercise', params: {id:exercise.id} }">
                            <button class="exercise-name">{{ exercise.name }}</button>
                        </router-link>
                    </td>
                    <td class='column-list'>{{ exercise.target }}</td>
                    <td class='column-list'>{{ exercise.bodyPart }}</td>
                    <td class='column-list'>{{ exercise.equipment }}</td>
                </tr>
            </tbody>
        </table>
  </div>
</template>

<script>
import exerciseService from '../services/ExerciseService.js';

export default {
    name: 'exercise-table',
    data() {
        return {
            filter: {
                name: '',
                target: '',
                bodyPart: '',
                equipment: ''
            },
            showSearchBar: false,
            showFilters: false
        }
    },
    created() {
      this.loadExercises();
    },
    computed: {
        allExercises() {
            return this.$store.state.exerciseList;
        },
        filteredList() {
            let filteredExercises = this.allExercises;
            if (this.filter.name != "") {
                filteredExercises = filteredExercises.filter((exercise) => 
                    exercise.name
                        .toLowerCase()
                        .includes(this.filter.name.toLowerCase())
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
        }
    },
    methods: {
        loadExercises() {
            if (this.$store.state.exerciseList == []) {
                exerciseService
                    .getAllExercises()
                    .then(response => {
                        this.$store.commit("LOAD_EXERCISE_LIST", response.data);
                        this.isLoading = false;
                    })
            }
        },
        toggleSearch() {
            this.showSearchBar = !this.showSearchBar;
            this.filter = {
                name: '',
                target: '',
                bodyPart: '',
                equipment: ''
            }
            if (this.showFilters == true) {
                this.showFilters = false
            }
        },
        toggleFilters() {
            this.showFilters = !this.showFilters;
        }
    }

}
</script>


<style scoped>
#exercise-table {
  background-color: var(--blue);
  text-align: center;
  padding-bottom: 50%;
}

#table-of-exercises {
    margin-right: 7%;
}

#search-menu {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding-top: 5%;
  margin-left: 2%;
  margin-bottom: 2%;
}

#search-icon {
    height: 5%;
    width: 5%;
    margin-right: 2%
}

#cancel-button {
    height: 20px;
}

#filter-icon {
    height: 5%;
    width: 5%;
    margin-left: 2%
}

.exercise-name {
    width: 70%;
    margin-top: 20px;
}

button {
    color: white;
    background-color: var(--blue);
    font-size: 17px;
    border: none;
    outline: none;
}
button:hover {
    cursor: pointer;
    font-size: 18px;
    color: lightblue;
}

#search-icon:hover {
    filter: invert(100%) sepia(100%) saturate(100%) hue-rotate(0deg) brightness(100%) contrast(100%);
}

#filter-icon:hover {
    filter: invert(100%) sepia(100%) saturate(100%) hue-rotate(0deg) brightness(100%) contrast(100%);
}

</style>
<template>
  <div id='exercise-table'>
       <table id="exercise-table">
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
                        <input type="text" id="exerciseNameFilter" v-model="filter.name"/>
                    </td>
                    <td>
                        <select id="exerciseTargetFilter" v-model="filter.target">
                            <option value>Show All</option>
                            <option value="abs">Abs</option>
                            <option value="quads">Quads</option>
                            <option value="lats">Lats</option>
                        </select>
                    </td>
                    <td>
                        <select id="bodypartFilter" v-model="filter.bodyPart">
                            <option value>Show All</option>
                            <option value="waist">Waist</option>
                            <option value="upper legs">Upper Legs</option>
                            <option value="back">Back</option>
                        </select>
                    </td>
                    <td>&nbsp;</td>
                </tr>

                <tr
                    v-for="exercise in filteredList"
                    v-bind:key="exercise.id">
                    <td>
                        <router-link :to="{ name: 'exercise', params: {id:exercise.id} }">
                            <button>{{ exercise.name }}</button>
                        </router-link>
                    </td>
                    <td>{{ exercise.target }}</td>
                    <td>{{ exercise.bodyPart }}</td>
                    <td>{{ exercise.equipment }}</td>
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
            }
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
                filteredExercises = filteredExercises.filter((exercise) => {
                exercise.name
                .toLowerCase()
                .includes(this.filter.name.toLowerCase())
                }); 
            }
            if (this.filter.target != "") {
                filteredExercises = filteredExercises.filter((exercise) => {
                exercise.target === this.filter.target
                });
            }
            if (this.filter.bodyPart != "") {
                filteredExercises = filteredExercises.filter((exercise) => {
                exercise.bodyPart === this.filter.bodyPart
                });
            }
            return filteredExercises;
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

</style>
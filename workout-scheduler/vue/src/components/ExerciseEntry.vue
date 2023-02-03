<template>
  <section id='exercise-entry'>
      <h3>Log Exercise</h3>
      
      <div id='enter-time'>
        <div v-if='minutes'>
            <label for='time'>Time: </label>
            <input type='number' id='time-minutes' name='time' class='minutes, entry' placeholder='minutes' />
        </div>
        <div v-else>
            <label for='time'>Time: </label>
            <input type='number' id='time-hours' name='time' class='hours, entry' placeholder='hours' />
        </div>
      </div>

      <div id='enter-weight'>
        <div v-if='pounds'>
          <label for='weight'>Weight: </label>
          <input type='number' id='weight-pounds' name='weight' class='pounds, entry' placeholder='pounds' />
        </div>
        <div v-else>
          <label for='weight'>Weight: </label>
          <input type='number' id='weight-kilograms' name='weight' class='kilograms, entry' placeholder='kilograms' />
      </div>

      <div id='enter-reps'>
        <label for='reps'>Reps: </label>
        <input type='number' id='reps' name='reps' class='reps, entry' placeholder='number of reps' />
      </div>

      <div id='enter-distance'>
        <div v-if='miles'>
          <label for='distance'>Distance: </label>
          <input type='number' id='distance' name='distance' class='miles, entry' placeholder='miles' />
        </div>
        <div v-else>
          <label for='distance'>Distance: </label>
          <input type='number' id='distance' name='distance' class='kilometers, entry' placeholder='kilometers' />
        </div>
      </div>

    <button v-bind="id" @click='createEntry(id)' />
  </section>
</template>

<script>
export default {
    name: 'exercise-entry',
    data() {
      return {
        id: null
      }
    },
    methods: {
      createEntry(id) {
        id;
        // create metrics entry for exercise
      }
    }
}
</script>

<style>

</style>
<template>
  <section id='exercise-entry'>
      <h3>Log Exercise</h3>
      <div id='enter-time'>
        <div v-if='minutes'>
            <label for='time'>Time: </label>
            <input type='number' id='time-minutes' name='time' class='minutes' placeholder='minutes' />
        </div>
        <div v-else>
            <label for='time'>Time: </label>
            <input type='number' id='time-hours' name='time' class='hours' placeholder='hours' />
        </div>
      </div>

  </section>
</template>

<script>
export default {
    name: 'exercise-entry',
    props: {
    props: {
      exercise: {
        id: 0,
        name: '',
        target: '',
        bodyPart: '',
        equipment: '',
        gifUrl: ''
      }
    }
    }
}
</script>

<style>

</style>
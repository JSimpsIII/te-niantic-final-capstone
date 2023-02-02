<template>
  <section id='exercise-details'>
    <h1>{{exercise.name}}</h1>
    <div class='flexbox'>
      <p class='target'>{{exercise.target}}</p>
      <p class='bodypart'>{{exercise.bodyPart}}</p>
      <p class='eqipment'>{{exercise.equipment}}</p>
    </div>
    <div class='gif'>
      <img src='{{exercise.gifUrl}}'>
    </div>
  </section>
</template>

<script>
export default {
    name: 'exercise-details',
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
</script>

<style>

</style>
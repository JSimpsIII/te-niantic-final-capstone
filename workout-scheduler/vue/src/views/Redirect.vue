<template>
  <div>
      <h1>Would you like to edit your profile now?</h1>
      <button @click="$router.push('edit/profile')">Yes</button>
      <button @click="$router.push('/')">No</button>
  </div>
</template>

<script>
export default {
    name: 'redirect',
}
</script>

<style>

</style>
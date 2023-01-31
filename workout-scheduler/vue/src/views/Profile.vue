<template>
  <div>
    <div class="banner"></div>
    <img class="profile-img" :src="user.img" alt="profile-img">

    <div class="profile-container">
      Username: {{ user.username }}
      <form id="form-container">
        <label for="email">
          Email:
          <input type="text" v-model="user.email" id="email" name="email" disabled>
        </label>

        <label for="height">
          Height:
          <input type="text" v-model="user.height" id="height" name="height" disabled>
        </label>

        <label for="weight">
          Weight:
          <input type="text" v-model="user.weight" id="weight" name="weight" disabled>
        </label>

      </form>
      
    </div>
    
  </div>
</template>

<script>
export default {
    name: "profile",
    data() {
      return {
        user: {
          username: "pull username from store",
          img: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo30ulQk-69OJ5GGdowFt21Lsau4GfWzfbBSmsfE4hGrVxBbnVNOr12yOYULoq2Gb7XEU&usqp=CAU",
          name: "pull name from store",
          email: "pull email",
          height: "height here",
          weight: "weight here"
        }
      }
    }
}
</script>

<style>

.profile-img {
  width: 150px;
  height: 150px;
  object-fit: cover;
  border-radius: 100%;
  object-position: top;
}

#form-container {
  display: flex;
  flex-direction: column;
}

#form-container > label > input {
  
}
</style>
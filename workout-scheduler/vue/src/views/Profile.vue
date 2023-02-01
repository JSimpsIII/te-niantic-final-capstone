<template>
  <div>
    <div class="banner">
      <div class="profile-img-container">
        <img class="profile-img" :src="user.img" alt="profile-img">
      </div>
    </div>
    

    <div class="profile-container form-group">
      <div id="username">
        Username: {{ user.username }}
      </div>

      <form id="form-container">
        <label for="photo">Profile Picture</label>
        <select name="languages" id="lang" v-model="user.img" :disabled="!isEditting">
          <option value="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo30ulQk-69OJ5GGdowFt21Lsau4GfWzfbBSmsfE4hGrVxBbnVNOr12yOYULoq2Gb7XEU&usqp=CAU">Option 1</option>
          <option value="https://raisingwhasians.com/wp-content/uploads/2021/06/Black-widow-movie-review-safe-for-kids.jpg">Option 2</option>
          <option value="https://qph.cf2.quoracdn.net/main-qimg-25c5c8a37ca5ffcdf55fe24149ce1011.webp">Option 3</option>
          <option value="https://maactioncinema.com/wp-content/uploads/2021/02/blade_3.jpg">Option 4</option>
        </select>
        <label for="email">
          Email:
          <input 
            type="email" 
            v-model="user.email" 
            id="email" 
            name="email"
            :disabled="!isEditting">
        </label>

        <label for="height">
          Height:
          <input 
            type="text" 
            v-model="user.height" 
            id="height" 
            name="height" 
            :disabled="!isEditting">
        </label>

        <label for="weight">
          Weight:
          <input 
            type="text" 
            v-model="user.weight" 
            id="weight" 
            name="weight" 
            :disabled="!isEditting">
        </label>
        
        <button id="submit-btn" class="profile-btns" @click="toggleEditProfile" v-if="isEditting">Save Changes</button>

      </form>

      <button 
        id="edit-btn" 
        class="profile-btns" 
        @click="toggleEditProfile"
      >
        {{ isEditting ? "Cancel" : "Edit Profile" }}
      </button>
      
    </div>

    <nav-bar />
    
  </div>
</template>

<script>
import NavBar from '../components/NavBar.vue'

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
        },
        isEditting: false,
      }
    },
    components: {
      NavBar
    },
    methods: {
      toggleEditProfile() {
        this.isEditting = !this.isEditting;
      }
    }
}
</script>

<style scoped>

.banner {
  width: 100%;
  background-color: #295882;
  height: 150px;
  position: relative;
}

.profile-img-container {
  position: absolute;
  top: 50%;
  margin-left: 50px;
}

.profile-img {
  width: 150px;
  height: 150px;
  object-fit: cover;
  border-radius: 100%;
  object-position: top;
}

.profile-container {
  margin-top: 100px;
  margin-left: 50px;
}

#username {
  margin-bottom: 20px;
}

#form-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

#form-container > label > input {
  color: inherit;
  background-color: #2D2D2D;
  padding: 5px;
  border: var(--blue) 1px solid;
  border-radius: 5px;
}

#form-container > label > input:disabled {
  all: unset;
}

.profile-btns {
  width: 150px;
}

</style>
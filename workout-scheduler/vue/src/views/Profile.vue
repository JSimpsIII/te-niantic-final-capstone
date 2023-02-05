<template>
  <div>
    <div class="banner">
      <div class="profile-img-container">
        <img class="profile-img" :src="user.photo" alt="profile-img" />
      </div>
    </div>

    <div class="profile-container form-group">
      <div id="username">Username: {{ user.username }}</div>

      <form id="form-container">
        <label for="photo">Profile Picture</label>
        <select
          name="languages"
          id="photo"
          v-model="user.photo"
          :disabled="!isEditting"
        >
          <option
            value="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRo30ulQk-69OJ5GGdowFt21Lsau4GfWzfbBSmsfE4hGrVxBbnVNOr12yOYULoq2Gb7XEU&usqp=CAU"
          >
            Option 1
          </option>
          <option
            value="https://raisingwhasians.com/wp-content/uploads/2021/06/Black-widow-movie-review-safe-for-kids.jpg"
          >
            Option 2
          </option>
          <option
            value="https://qph.cf2.quoracdn.net/main-qimg-25c5c8a37ca5ffcdf55fe24149ce1011.webp"
          >
            Option 3
          </option>
          <option
            value="https://maactioncinema.com/wp-content/uploads/2021/02/blade_3.jpg"
          >
            Option 4
          </option>
        </select>

        <label for="name">Name:</label>
        <input
          type="name"
          v-model="user.name"
          id="name"
          name="name"
          :disabled="!isEditting"
        />

        <label for="email">Email:</label>
        <input
          type="email"
          v-model="user.email"
          id="email"
          name="email"
          :disabled="!isEditting"
        />

        <label for="height">Height:</label>
        <input
          type="text"
          v-model="user.height"
          id="height"
          name="height"
          :disabled="!isEditting"
        />

        <!-- <label for="weight">
                Weight:
                <input 
                    type="text" 
                    v-model="user.weight" 
                    id="weight" 
                    name="weight" 
                    :disabled="!isEditting">
                </label> -->

        <div id="profile-btn-container">
          <button
            id="submit-btn"
            class="profile-btns"
            @click.prevent="
              toggleEditProfile();
              saveChanges();
            "
            v-if="isEditting"
          >
            Save Changes
          </button>
          <button
            id="edit-btn"
            class="profile-btns"
            @click.prevent="toggleEditProfile"
            v-if="!isEditting"
          >
            Edit Profile
          </button>

          <button
            id="cancel-btn"
            class="profile-btns"
            @click.prevent="
              cancelForm();
              toggleEditProfile();
            "
            v-if="isEditting"
          >
            Cancel Edits
          </button>
        </div>
      </form>
    </div>

    <footer>
      <nav-bar />
    </footer>
  </div>
</template>

<script>
import NavBar from "../components/NavBar.vue";
import profileService from "../services/ProfileService";

export default {
  name: "profile",
  data() {
    return {
      user: {
        name: "",
        photo: "",
        email: "",
        height: "",
      },
      userBeforeEdit: {},
      isEditting: false,
    };
  },
  created() {
    profileService.getProfile(this.$store.state.user.username).then((res) => {
      const { username, name, photo, email, height } = res.data;
      this.user.username = username;
      this.user.name = name;
      this.user.photo = photo;
      this.user.email = email;
      this.user.height = height;
    });
  },
  components: {
    NavBar,
  },
  methods: {
    toggleEditProfile() {
      this.isEditting = !this.isEditting;
      this.userBeforeEdit = Object.assign({}, this.user);
    },
    cancelForm() {
      Object.assign(this.user, this.userBeforeEdit);
    },
    saveChanges() {
      profileService
        .saveProfileChanges(this.$store.state.customerId, this.user)
        .then((res) => {
          console.log(res.data);
        });
    },
  },
};
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
  background: var(--white);
  text-indent: 100%;
  white-space: nowrap;
  overflow: hidden;
}

.profile-container {
  margin: 100px 50px 0 50px;
}

#username {
  margin-bottom: 20px;
}

#form-container {
  display: flex;
  flex-direction: column;
}

#form-container > input,
#form-container > select {
  color: inherit;
  background-color: var(--blue);
  padding: 5px;
  border: var(--blue) 1px solid;
  border-radius: 5px;
  padding: 10px 8px;
  font-size: 14px;
  margin: 10px 0;
}

#form-container > input:disabled,
#form-container > select:disabled {
  all: unset;
  margin-bottom: 40px;
}

.profile-btns {
  width: 150px;
  margin-bottom: 60px;
}

#profile-btn-container {
  display: flex;
  justify-content: space-between;
}

.profile-btns {
  color: inherit;
  background-color: var(--blue);
  padding: 10px;
  border-radius: 10px;
  margin-top: 10px;
}

#cancel-btn {
  background-color: var(--smoke);
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
<template>
  <div>
    <div class="banner">
      <div class="profile-img-container">
        <img
          id="profile-img"
          class="profile-img"
          :src="user.photo"
          alt="profile-img"
        />
      </div>
      <div>
        <img
          src="@/assets/camera.png"
          alt="edit-icon"
          class="edit-icon"
          @click="toggleProfilePic"
        />
      </div>
    </div>

    <div class="profile-pic-container-parent" v-if="isChoosingProfilePic">
      <div class="profile-pic-container">
        <div v-for="img in profileImgOptions" :key="img.id">
          <img
            :src="img"
            alt="profile-pic-option"
            class="profile-pic-options"
            @click="selectNewProfileImg"
          />
        </div>
      </div>
    </div>

    <div id="profile-container" class="profile-container form-group">
      <div id="username-container">
        <div id="username">Username:</div>
        <div id="username-name">{{ user.username }}</div>
      </div>

      <form id="form-container">
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

        <!-- <label for="height">Height:</label>
        <input
          type="text"
          v-model="user.height"
          id="height"
          name="height"
          placeholder="in inches"
          :disabled="!isEditting"
        /> -->

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
      profileImgOptions: [
        "https://cdn.britannica.com/30/182830-050-96F2ED76/Chris-Evans-title-character-Joe-Johnston-Captain.jpg",
        "https://raisingwhasians.com/wp-content/uploads/2021/06/Black-widow-movie-review-safe-for-kids.jpg",
        "https://qph.cf2.quoracdn.net/main-qimg-25c5c8a37ca5ffcdf55fe24149ce1011.webp",
        "https://maactioncinema.com/wp-content/uploads/2021/02/blade_3.jpg",
        "https://i.pinimg.com/originals/d5/ec/19/d5ec19d2d312c83970b5c7d602c43d6f.jpg",
        "https://cdn.britannica.com/36/198336-050-A9B8AA86/Chadwick-Boseman-Tchalla-Black-Panther-Black.jpg",
        "https://i.pinimg.com/originals/66/94/41/6694412abd90da407960d0319d666889.jpg",
        "https://fivethirtyeight.com/wp-content/uploads/2018/02/shuri-1.jpg?w=712",
      ],
      userBeforeEdit: {},
      isEditting: false,
      isChoosingProfilePic: false,
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
    toggleProfilePic() {
      this.isChoosingProfilePic = true;
      document.getElementById("profile-container").classList.add("blurred");
      document.getElementById("profile-img").classList.add("blurred");

      const editBtn = document.getElementById("edit-btn");
      editBtn.disabled = true;
      editBtn.style.cursor = "auto";
    },
    toggleEditProfile() {
      this.isEditting = !this.isEditting;
      this.userBeforeEdit = Object.assign({}, this.user);
    },
    cancelForm() {
      Object.assign(this.user, this.userBeforeEdit);
    },
    saveChanges() {
      profileService.saveProfileChanges(
        this.$store.state.profile.customerId,
        this.user
      );
    },
    selectNewProfileImg(e) {
      const newImg = e.target.currentSrc;
      this.user.photo = newImg;
      this.saveChanges();
      this.isChoosingProfilePic = false;
      document.getElementById("profile-container").classList.remove("blurred");
      document.getElementById("profile-img").classList.remove("blurred");

      const editBtn = document.getElementById("edit-btn");
      editBtn.disabled = false;
      editBtn.style.cursor = "pointer";
    },
    closeImgSelection() {
      this.isChoosingProfilePic = false;
      document.getElementById("profile-container").classList.remove("blurred");
      document.getElementById("profile-img").classList.remove("blurred");
    },
  },
};
</script>

<style scoped>
.blurred {
  filter: blur(2px);
  -webkit-filter: blur(2px);
}

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
  background: var(--smoke);
  text-indent: 100%;
  white-space: nowrap;
  overflow: hidden;
}

.edit-icon {
  position: absolute;
  top: 135%;
  margin-left: 32%;
}

.edit-icon:hover {
  cursor: pointer;
}

.profile-container {
  margin: 110px 50px 0 50px;
}

#username-container {
  margin-bottom: 40px;
}

#username-name {
  color: lightblue;
}

#username-container,
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
  color: lightblue;
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

.profile-btns:hover {
  cursor: pointer;
}

footer {
  position: fixed;
  bottom: 0;
  width: 600px;
  height: 50px;
  background: var(--smoke);
  padding-top: 15px;
}

.profile-pic-options {
  width: 120px;
  height: 120px;
  object-fit: cover;
  object-position: top;
  cursor: pointer;
}

.profile-pic-container {
  display: flex;
  flex-wrap: wrap;
  width: 480px;
  position: absolute;
  left: 10%;
}

.profile-pic-container-parent {
  position: relative;
  z-index: 1;
}
</style>
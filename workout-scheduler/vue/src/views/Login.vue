<template>
  <div id="login" class="text-center">
    <div id="login-header">     
    <img id="login-img" src="https://www.creativefabrica.com/wp-content/uploads/2020/12/07/Gym-Logo-Graphics-7049759-1-580x386.jpg" />
    <h1 id="welcome" class="h3 mb-3 font-weight-normal">[Gym App Name]</h1>
    </div>
    <form class="form-signin" @submit.prevent="login">
      <h3 class="login-request" v-if="!this.$route.query.registration">Please Log In</h3>
      <h3  class="alert alert-success login-request"
        role="alert"
        v-if="this.$route.query.registration">Thank you for registering, please sign in.</h3>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <!-- <div
        class="alert alert-success login-request"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div> -->
      <div id="username">
      <label for="username" class="sr-only">Username </label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Your account username"
        v-model="user.username"
        required
        autofocus
      />
      </div>
      <div id="password">
      <label for="password" class="sr-only">Password </label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Enter your secure password"
        v-model="user.password"
        required
      />
      </div>
      <div id="to-register" v-if="!this.$route.query.registration">
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      </div>
      <div id="log-in">
      <button type="submit">Log In</button>
      </div>
    </form>
    <p id="gym-info">[Gym Information Here]</p>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            if (this.$route.query.registration) {
              this.$router.push("redirect");
            } else {
              this.$router.push("/");
            }
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.text-center {
  width: 600px;
  margin: 0 auto;
}

#login-header {
  display: flex;
  align-items: center;
  width: 90%;
  margin: 0 auto;
  margin-bottom: 20px;
}

#login-img {
  width: 50px;
  height: 50px;
  object-fit: cover;
  border-radius: 100%;
  object-position: top;
}

#welcome {
  margin-left: 10px;
}

.login-request {
  font-size: 25px;
  text-align: center;
  width: 90%;
  margin: 0 auto;
  margin-bottom: 20px;
}

#log-in {
  font-size: 25px;
  text-align: center;
  width: 90%;
  margin: 0 auto;
  margin-bottom: 20px;
}

#gym-info {
  font-size: 25px;
  text-align: center;
  width: 90%;
  margin: 0 auto;
  margin-bottom: 20px;
}

</style>

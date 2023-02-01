<template>
  <div id="register" class="text-center">
    <div id="register-header">     
    <img id="register-img" src="https://www.creativefabrica.com/wp-content/uploads/2020/12/07/Gym-Logo-Graphics-7049759-1-580x386.jpg" />
    <h1 id="welcome" class="h3 mb-3 font-weight-normal">[Gym App Name]</h1>
    </div>
    <form class="form-register" @submit.prevent="register">
      <h3 id="account-request">Create a New Account Below</h3>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div id="username">
      <label for="username" class="sr-only">Username </label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
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
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      </div>
      <div id="to-login">
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      </div>
      <div id="register-button">
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'An account with that username already exists. Please try another';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.text-center {
  width: 600px;
  margin: 0 auto;
}

#register-header {
  display: flex;
  align-items: center;
  width: 90%;
  margin: 0 auto;
  margin-bottom: 20px;
  margin-top: 20px;
}

#register-img {
  width: 50px;
  height: 50px;
  object-fit: cover;
  border-radius: 100%;
  object-position: top;
}

#welcome {
  margin-left: 10px;
}

#username {
  margin-bottom: 2px;
}

#password {
  margin-bottom: 2px;
}

#account-request {
  font-size: 25px;
  text-align: center;
  width: 90%;
  margin: 0 auto;
  margin-bottom: 20px;
}

#register-button {
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

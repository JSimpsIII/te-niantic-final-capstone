<template>
  <div id="register" class="text-center">
    <div id="register-header">
      <img
        id="register-img"
        src="https://www.creativefabrica.com/wp-content/uploads/2020/12/07/Gym-Logo-Graphics-7049759-1-580x386.jpg"
      />
      <h1 id="welcome" class="h3 mb-3 font-weight-normal">Uplift Fitness</h1>
    </div>

    <form class="form-register" @submit.prevent="register">
      <h3 id="account-request">Create a New Account Below</h3>

      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>

      <div id="name">
        <label for="name" class="sr-only">Name: </label>
        <input
          type="text"
          id="name"
          class="form-control"
          placeholder="Name"
          v-model="user.name"
          required
          autofocus
        />
      </div>

      <div id="username">
        <label for="username" class="sr-only">Username: </label>
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
        <label for="password" class="sr-only">Password: </label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <br />
        <label for="confirm-password">Confirm Password: </label>
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
        <router-link :to="{ name: 'login' }" id="login-link"
          >Have an account?</router-link
        >
      </div>

      <div id="register-button">
        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Create Account
        </button>
      </div>
    </form>
    <image-changer />
  </div>
</template>

<script>
import authService from "../services/AuthService";
import imageChanger from "../components/ImageChanger";

export default {
  name: "register",
  components: {
    imageChanger,
  },
  data() {
    return {
      user: {
        name: "",
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg =
                "An account with that username already exists. Please try another";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
.text-center {
  width: 600px;
  margin: 0 auto;
}

.form-register {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}

#register-header {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-bottom: 20px;
  padding-top: 20px;
  padding-bottom: 20px;
  background-color: var(--blue);
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

#name,
#username,
#password {
  margin: 8px;
}
.sr-only {
  width: 200px;
}
.form-control {
  width: 200px;
  height: 40px;
  border-radius: 10px;
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

#login-link {
  color: darkgoldenrod;
}

#name,
#username,
#password,
#confirmPassword {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0 auto;
  padding: 5px 10px;
  background-color: rgb(29, 61, 89);
  border-radius: 5px;
  width: 300px;
  margin-bottom: 15px;
}

#name input,
#username input,
#password input {
  width: 100%;
  border: none;
  background-color: var(--blue);
  color: white;
  border-radius: 5px;
  margin-top: 5px;
}

#name label,
#username label,
#password label {
  text-align: center;
}

#password input {
  margin-bottom: 5px;
}

#register {
  background-color: rgb(29, 34, 39);
}

#to-login {
  text-align: center;
}

#register-button button {
  background-color: var(--blue);
  border: none;
  color: inherit;
  padding: 6px 15px;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 15px;
}
</style>

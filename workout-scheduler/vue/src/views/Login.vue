<template>
    <div id="login">

        <div id="login-header">     
            <img id="login-img" src="https://www.creativefabrica.com/wp-content/uploads/2020/12/07/Gym-Logo-Graphics-7049759-1-580x386.jpg" />
            <h1 id="welcome" class="h3 mb-3 font-weight-normal">Uplift Fitness</h1>
        </div>

        <form class="form-signin" @submit.prevent="login">

            <h3 class="login-request" v-if="!this.$route.query.registration">Log In</h3>
          
            <h3  
                class="alert alert-success login-request"
                role="alert"
                v-if="this.$route.query.registration">
            Thank you for registering, please sign in.
            </h3>

            <div
                class="alert alert-danger"
                role="alert"
                v-if="invalidCredentials">
            Invalid username and password!
            </div>

            <!-- <div
                class="alert alert-success login-request"
                role="alert"
                v-if="this.$route.query.registration"
            >Thank you for registering, please sign in.</div> -->

            <div id='user-entry'>
                <div id="enter-username">
                    <label for="username" class="sr-only">Username: </label>
                    <input
                        type="text"
                        id="username"
                        class="form-control"
                        placeholder="Enter your username"
                        v-model="user.username"
                        required
                        autofocus
                    />
                </div>

                <div id="enter-password">
                    <label for="password" class="sr-only">Password: </label>
                    <input
                        type="password"
                        id="password"
                        class="form-control"
                        placeholder="Enter your password"
                        v-model="user.password"
                        required
                    />
                </div>

                <div id="to-register" v-if="!this.$route.query.registration">
                    <router-link :to="{ name: 'register' }" id="create-account-link">Need an account?</router-link>
                </div>
            </div>
            
            <div id="log-in">
                <button type="submit">Log In<span id="muscle-emoji"> 💪</span></button>
            </div>

        </form>

        <div id='image-changer-div'>
          <image-changer />
        </div>     
    </div>
</template>

<script>
import authService from "../services/AuthService";
import imageChanger from "../components/ImageChanger"

export default {
  name: "login",
  components: {
    imageChanger
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false,
    }
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

#login {
  white-space: pre-line;
  max-width: 600px;
  margin: 0;
  padding: 0;
  overflow:hidden;
  background-color: rgb(29, 34, 39);
  height: 100vh;
}

#login-header {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  margin-bottom: 20px;
  padding-top: 20px;
  padding-bottom: 20px;
  background-color: var(--blue);
}

.login-request {
  height: 55px;
  padding: 25px;
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

#password,
#username {
  font-size: 14px;
  margin: 5px;
  background-color: var(--blue);
  border: none;
  padding: 10px;
  border-radius: 5px;
  color: var(--inherit);
}

.sr-only {
  font-size: 24px;
  padding-right: 8px;
  text-transform: capitalize;
}

#user-entry {
  width: 300px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
}

#enter-username,
#enter-password {
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 5px 5px 5px 10px;
  margin-bottom: 15px;
  background-color: rgb(29, 61, 89);
  border-radius: 5px;
}

.form-signin {
  margin-bottom: 40px;
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

#create-account-link {
  color:darkgoldenrod;
}

#image-changer-div {
  max-width: 600px;
}

#log-in button {
  background-color: var(--blue);
  border: none;
  color: inherit;
  padding: 6px 15px;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 15px;
}

#muscle-emoji {
  display: none;
}

#log-in button:hover #muscle-emoji{
  display: inline;
}



</style>

<template>
    <div>
        <div id="header">     
            <img id="redirect-img" src="https://www.creativefabrica.com/wp-content/uploads/2020/12/07/Gym-Logo-Graphics-7049759-1-580x386.jpg" />
            <h1 id="app-name" class="h3 mb-3 font-weight-normal">Uplift Fitness</h1>
        </div>

        <h3 class="complete-profile">Would you like to complete your profile now?</h3>
        
        <div id="redirect-buttons">
            <button @click="$router.push('edit/profile')">Yes</button>
            <button @click="$router.push('/')">No</button>
        </div>
    </div>
</template>

<script>
import profileService from '../services/ProfileService';

export default {
    name: 'redirect',
    created() {
      profileService.getProfile(this.$store.state.user.username)
                    .then(res => {
                      const { customerId } = res.data.customer;
                      this.$store.commit("SET_CUSTOMER_ID", customerId);
                    })
    }
}
</script>

<style>
#header {
  display: flex;
  justify-content: center;
  width: 90%;
  margin: 0 auto;
  margin-bottom: 20px;
  margin-top: 20px;
}

#redirect-img {
  width: 50px;
  height: 50px;
  object-fit: cover;
  border-radius: 100%;
  object-position: top;
}

#app-name {
  margin-left: 10px;
}

.complete-profile {
  font-size: 25px;
  text-align: center;
  width: 90%;
  margin: 0 auto;
  margin-bottom: 20px;
}

#redirect-buttons {
  font-size: 25px;
  text-align: center;
  width: 90%;
  margin: 0 auto;
  margin-bottom: 20px;
}
</style>
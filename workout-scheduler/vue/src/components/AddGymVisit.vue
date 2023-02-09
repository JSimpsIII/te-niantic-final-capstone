<template>
    <div id='add-gym-visit'>

        <div v-if='(this.$store.state.gym.inGym == true)' id='clock-out' class='gym-button'>
            <p class='gym-greeting'>Heading out?</p>
            <button type='submit' id='clock-out-button' @click='clockOut'>
                Clock Out
            </button>
        </div>
        
        <div v-else id='clock-in' class='gym-button'>
            <p class='gym-greeting'>At the gym?</p>
            <button type='submit' id='clock-in-button' @click='clockIn'>
                Clock In
            </button>
        </div>
    
    </div>
</template>

<script>
import gymService from '../services/GymService.js'

export default {
    name: 'add-gym-visit',
    data(){
        return {
            visit: {
                customerId: null,
                minutes: null
            }
        }
    },
    methods: {
        clockIn() {
            this.$store.commit('TOGGLE_IN_GYM', true);
        },
        clockOut() {
            let checkIn = this.$store.state.gym.clockIn;
            console.log(checkIn);
            this.$store.commit('TOGGLE_IN_GYM', false);
            let checkOut = this.$store.state.gym.clockIn;
            console.log(checkOut);
            let mins = Math.floor((checkOut - checkIn)/60000);
            console.log(mins);

            this.visit.customerId = this.$store.state.profile.customerId;
            this.visit.date = this.$store.state.gym.checkIn;
            this.visit.minutes = mins;
            
            gymService
            .addVisit(this.visit.customerId, this.visit)
            .then((response) => {
                console.log(response.status);
                if (response.status == 201) {
                    this.visit = {
                        customerId: this.$store.state.profile.customerId,
                        date: null,
                        minutes: null
                    }
                }
            })
            .catch((error) => {
                console.error(error);
            });
        }
    }
}
</script>

<style scoped>
.gym-greeting {
    font-size: 1.3em;
    margin-bottom: 10px;
}
.gym-button {
    display: flex;
    flex-direction: column;
}
button {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 5px;
  color: #FDFFFC;
  opacity: 1;
  margin: 0 auto;
  font-size: 20px;
  padding: 15px 20px;
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: center;
  border-radius: 10px;
}
#clock-in-button {
    background-color: var(--green);
    border: none;
}
#clock-out-button {
    background-color: var(--red);
    border: none;
}
button::placeholder,
button:-ms-input-placeholder {
    color: white;
    opacity: 1;
}
button:hover {
    cursor: pointer;
    transform: scale(1.15);
}

</style>
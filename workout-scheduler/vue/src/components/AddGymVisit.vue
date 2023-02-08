<template>
    <div id='add-gym-visit'>

        <div v-if='(this.$store.state.gym.inGym == true)' id='clock-out' class='gym-button'>
            <p class='gym-greeting'>Heading out?</p>
            <button type='submit' id='clock-out-button' @click='clockOut()'>
                Clock Out
            </button>
        </div>
        
        <div v-else id='clock-in' class='gym-button'>
            <p class='gym-greeting'>At the gym?</p>
            <button type='submit' id='clock-in-button' @click='clockIn()'>
                Clock In
            </button>
        </div>
    
    </div>
</template>

<script>
import gymVisitService from '../services/GymVisitService.js'
export default {
    name: 'add-gym-visit',
    data() {
        return {
            gymVisit: {
                visitId: null,
                customerId: null,
                checkIn: null,
                checkOut: null
            }
        }
    },
    methods: {
        clockIn() {
            this.$store.commit('SET_IN_GYM', true);
            let clockinTime = Date.now();
            this.$store.commit('GYM_CLOCK_IN', clockinTime);
            let userId = this.$store.state.profile.customerId;
            let visitId = gymVisitService.newVisit(userId, clockinTime);
            this.$store.commit('SET_GYM_VISIT_ID', visitId);
            
        },
        clockOut() {
            this.$store.commit('SET_IN_GYM', false);
            let clockoutTime = Date.now();
            this.$store.commit('GYM_CLOCK_OUT', clockoutTime);
            this.gymVisit.checkIn = this.$store.state.gym.clockIn;
            this.gymVisit.checkOut = clockoutTime;
            this.visitId = this.$store.state.gymVisit.visitId;
            this.customerId = this.$store.state.profile.customerId;
            gymVisitService.newVisit(this.gymVisit.customerId, this.gymVisit.visitId, this.gymVisit);
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
}
#clock-out-button {
    background-color: var(--red);
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
<template>
    <div id='add-gym-visit'>

        <div v-if='(this.$store.state.inGym == true)' id='clock-out' class='gym-button'>
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
    methods: {
        clockIn() {
            this.$store.commit('TOGGLE_IN_GYM', true); let id = gymVisitService.checkIn(this.$store.state.profile.userId);
            this.$store.commit('SET_VISIT_ID', id);
        },
        clockOut() {
            this.$store.commit('TOGGLE_IN_GYM', false);
            gymVisitService.clockOut(this.$store.state.profile.userId, this.$store.state.visitId);
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
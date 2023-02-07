<template>
    <div id='add-gym-visit'>

        <div v-if='(this.$store.state.gym.inGym == true)' id='clock-out' class='gym-button'>
            Heading out?
            <button type='submit' id='clock-out-button' @click='clockOut()'>
                Clock Out
            </button>
        </div>
        
        <div v-else id='clock-in' class='gym-button'>
            At the gym?
            <button type='submit' id='clock-in-button' @click='clockIn()'>
                Clock In
            </button>
        </div>
    
    </div>
</template>

<script>
import metricService from '../services/MetricService.js'
export default {
    name: 'add-gym-visit',
    data() {
        return {
            metric: {
                customerId: null,
                exerciseId: null,
                date: null,
                reps: null,
                weight: null,
                time: null,
                distance: null,
                days: null,
                misc: ''
            }
        }
    },
    methods: {
        clockIn() {
            this.$store.commit('SET_IN_GYM', true);
            let clockinTime = Date.now();
            this.$store.commit('GYM_CLOCK_IN', clockinTime);
        },
        clockOut() {
            this.$store.commit('SET_IN_GYM', false);
            let clockoutTime = Date.now();
            this.$store.commit('GYM_CLOCK_OUT', clockoutTime);
        },
        createEntry() {
            let millis = this.$store.state.gym.clockoutTime - this.$store.state.gym.clockinTime;
            let minutes = parseFloat(this.milisecondsToMinutes(millis));
            this.metric.time = minutes;
            this.metric.customerId = this.$store.state.profile.customerId;
            this.metric.date = new Date().toISOString();
            this.metric.misc = 'at gym';
            metricService
                .logNewMetric(this.metric.customerId, this.metric)
        },
        milisecondsToMinutes(millis) {
            let minutes = Math.floor(millis / 60000);
            let seconds = ((millis % 60000) / 1000).toFixed(0);
            return minutes + '.' + (seconds < 10 ? '0' : '') + seconds;
        }
    }
}
</script>

<style scoped>
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
  margin-bottom: 15px;
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
}

</style>
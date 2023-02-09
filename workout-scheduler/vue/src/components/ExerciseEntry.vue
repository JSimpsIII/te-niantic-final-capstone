    <template>
    <section id='exercise-entry'>

        <h3>Log Exercise</h3>
        <div id="exercise-form">
        <form>
            <div id='enter-time' class='form-entry'>
                <div v-if='this.isMinutes' class='time-minutes form-element'>
                    <label for='time-minutes'>Time: </label>
                    <input type='number' min='0' id='time-minutes' name='time-minutes' class='time' placeholder='minutes' v-model='metric.time' />
                </div>
                <div v-else class='time-hours form-element'>
                    <label for='time-hours'>Time: </label>
                    <input type='number' min='0' id='time-hours' name='time-hours' class='time' 
                    placeholder='hours' v-model='metric.time' />
                </div>
            </div>

            <div id='enter-weight' class='form-entry'>
                <div v-if='this.isPounds' class='weight-pounds form-element'>
                    <label for='weight-pounds'>Weight: </label>
                    <input type='number' min='0' id='weight-pounds' name='weight-pounds' class='weight' placeholder='pounds' v-model='metric.weight' />
                </div>
                <div v-else class='weight-kilograms form-element'>
                    <label for='weight-kilograms'>Weight: </label>
                    <input type='number' min='0' id='weight-kilograms' name='weight-kilograms' class='weight' placeholder='kilograms' v-model='metric.weight' />
                </div>
            </div>

            <div id='enter-sets' class='form-entry form-element'>
                <label for='sets'>Sets: </label>
                <input type='number' min='0' id='sets' name='sets' class='sets' placeholder='number of sets' v-model='metric.sets' />
            </div>

            <div id='enter-reps' class='form-entry form-element'>
                <label for='reps'>Reps: </label>
                <input type='number' min='0' id='reps' name='reps' class='reps' placeholder='number of reps' v-model='metric.reps' />
            </div>

            <div id='enter-notes' class='form-entry'>
                <textarea id='misc-notes' name='notes' rows='4' cols='35' v-model='metric.misc'>
                    Enter any additional notes here.
                </textarea>
            </div>

            <button @click='createEntry()'>Log</button>
        </form>
</div>
    </section>
</template>

<script>
import metricService from '../services/MetricService.js'

export default {
    name: 'exercise-entry',
    data() {
        return {
            isMinutes: true,
            isPounds: true,
            isMiles: true,
            metric: {
                customerId: null,
                exerciseId: null,
                date: null,
                reps: null,
                weight: null,
                time: null,
                sets: null,
                days: null,
                misc: ''
            }
        }
    },
    methods: {
      createEntry() {
            this.metric.customerId = this.$store.state.profile.customerId;
            this.metric.exerciseId = this.$store.state.exercise.id;
            if (this.metric.date == null) {
                this.metric.date = new Date().toISOString();
            }
            metricService
                .logNewMetric(this.metric.customerId, this.metric)
                .then(this.$router.push('/gym'))
      }
    }
}
</script>

<style scoped>
#exercise-form{
  display: flex;
  width: 100%;
  text-align: center;
}
.form-entry {
    margin-top: 20px;
    width: 360px;
    color: white;
    background-color: var(--blue);
    border-radius: 10px;
    padding: 10px;
}

label {
    margin: 10px;
    display: inline-block;
    width: 80px;
    color: white;
    font-size: 18px;
}

input {
    height: 30px;
    width: 235px;
    font-size: 18px;
    color: white;
    border-top-style: hidden;
    border-right-style: hidden;
    border-left-style: hidden;
    border-bottom-style: hidden;
    background-color: var(--blue);
}

textarea {
    font-size: 16px;
    max-width: 100%;
    color: white;
    background-color: var(--blue);
    border: none;
    outline: none;
}

button {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 5px;
  color: #FDFFFC;
  opacity: 1;
  background-color: var(--green);
  width: 70%;
  margin: 0 auto;
  font-size: 20px;
  padding: 20px 0px;
  text-align: center;
  border-radius: 10px;
  margin-top: 25px;
  margin-bottom: 60px;
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
    <template>
    <section id='exercise-entry'>

        <h3>Log Exercise</h3>
        
        <form>
            <div id='enter-time' class='form-entry'>
                <div v-if='this.isMinutes' class='time-minutes form-element'>
                    <label for='time-minutes'>Time: </label>
                    <input type='number' min='0' id='time-minutes' name='time-minutes' class='time' placeholder='minutes' v-bind='this.metric.time' />
                </div>
                <div v-else class='time-hours form-element'>
                    <label for='time-hours'>Time: </label>
                    <input type='number' min='0' id='time-hours' name='time-hours' class='time' 
                    placeholder='hours' v-bind='this.metric.time' />
                </div>
            </div>

            <div id='enter-weight' class='form-entry'>
                <div v-if='this.isPounds' class='weight-pounds form-element'>
                    <label for='weight-pounds'>Weight: </label>
                    <input type='number' min='0' id='weight-pounds' name='weight-pounds' class='weight' placeholder='pounds' v-bind='this.metric.weight' />
                </div>
                <div v-else class='weight-kilograms form-element'>
                    <label for='weight-kilograms'>Weight: </label>
                    <input type='number' min='0' id='weight-kilograms' name='weight-kilograms' class='weight' placeholder='kilograms' v-bind='this.metric.weight' />
                </div>
            </div>

            <div id='enter-reps' class='form-entry form-element'>
                <label for='reps'>Reps: </label>
                <input type='number' min='0' id='reps' name='reps' class='reps' placeholder='number of reps' v-bind='this.metric.reps' />
            </div>

            <div id='enter-distance' class='form-entry'>
                <div v-if='this.isMiles' class='distance-miles form-element'>
                    <label for='distance-miles'>Distance: </label>
                    <input type='number' min='0' id='distance-miles' name='distance-miles' class='distance' placeholder='miles' v-bind='this.metric.distance' />
                </div>
                <div v-else class='distance-kilometers form-element'>
                    <label for='distance-kilometers'>Distance: </label>
                    <input type='number' min='0' id='distance-kilometers' name='distance-kilometers' class='distance' placeholder='kilometers' v-bind='this.metric.distance'/>
                </div>
            </div>

            <div id='enter-notes' class='form-entry'>
                <textarea id='misc-notes' name='notes' rows='4' cols='35' v-bind='metric.misc'>
                    Enter any additional notes here.
                </textarea>
            </div>

            <button @click='createEntry()'>Log</button>
        </form>

    </section>
</template>

<script>
import exerciseService from '../services/ExerciseService.js'

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
                Date: null,
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
      createEntry() {
            this.metric.customerId = this.$store.state.profile.customerId;
            this.metric.exerciseId = this.$store.state.exercise.id;
            exerciseService
                .log(this.metric.customerId, this.metric)
                .then(this.$router.push('/'))
      }
    }
}
</script>

<style scoped>

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
    <template>
    <section id='exercise-entry'>

        <h3>Log Exercise</h3>
        
        <form>
            <div id='enter-time' class='form-entry'>
                <div v-if='this.time == minutes' class='time-minutes form-element'>
                    <label for='time-minutes'>Time: </label>
                    <input type='number' id='time-minutes' name='time-minutes' class='time' placeholder='minutes' />
                </div>
                <div v-else class='time-hours form-element'>
                    <label for='time-hours'>Time: </label>
                    <input type='number' id='time-hours' name='time-hours' class='time' placeholder='hours' />
                </div>
            </div>

            <div id='enter-weight' class='form-entry'>
                <div v-if='this.weight == pounds' class='weight-pounds form-element'>
                    <label for='weight-pounds'>Weight: </label>
                    <input type='number' id='weight-pounds' name='weight-pounds' class='weight' placeholder='pounds' />
                </div>
                <div v-else class='weight-kilograms form-element'>
                    <label for='weight-kilograms'>Weight: </label>
                    <input type='number' id='weight-kilograms' name='weight-kilograms' class='weight' placeholder='kilograms' />
                </div>
            </div>

            <div id='enter-reps' class='form-entry form-element'>
                <label for='reps'>Reps: </label>
                <input type='number' id='reps' name='reps' class='reps' placeholder='number of reps' />
            </div>

            <div id='enter-distance' class='form-entry'>
                <div v-if='this.distance == miles' class='distance-miles form-element'>
                    <label for='distance-miles'>Distance: </label>
                    <input type='number' id='distance-miles' name='distance-miles' class='distance' placeholder='miles' />
                </div>
                <div v-else class='distance-kilometers form-element'>
                    <label for='distance-kilometers'>Distance: </label>
                    <input type='number' id='distance-kilometers' name='distance-kilometers' class='distance' placeholder='kilometers' />
                </div>
            </div>

            <button v-bind="id" @click='createEntry(id)'>Submit</button>
        </form>

    </section>
</template>

<script>
export default {
    name: 'exercise-entry',
    data() {
        return {
            units: {
                time: 'minutes',
                weight: 'pounds',
                distance: 'kilometers'
            },
            metric: {
                customerId: null,
                exerciseId: null,
                date: null,
                reps: 0,
                weight: 0,
                time: 0,
                distance: 0,
                days: 0,
                misc: ''
            }
        }
    },
    methods: {
      createEntry(id) {
        id;
        // create metrics entry for exercise
      }
    }
}
</script>

<style scoped>

.form-entry {
    margin-top: 20px;
    width: 360px;
}

label {
    margin-right: 10px;
    display: inline-block;
    width: 70px;
    color: #85C1E9;
}

input {
    width: 130px;
}

button {
    margin-top: 20px;
}

</style>
    <template>
    <section id='exercise-entry'>

        <h3>Log Exercise</h3>
        
        <div id='enter-time'>
            <div v-if='time-minutes'>
                <label for='time-minutes'>Time: </label>
                <input type='number' id='time-minutes' name='time-minutes' class='time' placeholder='minutes' />
            </div>
            <div v-else>
                <label for='time-hours'>Time: </label>
                <input type='number' id='time-hours' name='time-hours' class='time' placeholder='hours' />
            </div>
        </div>

        <div id='enter-weight'>
            <div v-if='pounds'>
                <label for='weight-pounds'>Weight: </label>
                <input type='number' id='weight-pounds' name='weight-pounds' class='weight' placeholder='pounds' />
            </div>
            <div v-else>
                <label for='weight-kilograms'>Weight: </label>
                <input type='number' id='weight-kilograms' name='weight-kilograms' class='weight' placeholder='kilograms' />
            </div>
        </div>

        <div id='enter-reps'>
            <label for='reps'>Reps: </label>
            <input type='number' id='reps' name='reps' class='reps' placeholder='number of reps' />
        </div>

        <div id='enter-distance'>
            <div v-if='miles'>
                <label for='distance-miles'>Distance: </label>
                <input type='number' id='distance-miles' name='distance-miles' class='distance' placeholder='miles' />
            </div>
            <div v-else>
                <label for='distance-kilometers'>Distance: </label>
                <input type='number' id='distance-kilometers' name='distance-kilometers' class='distance' placeholder='kilometers' />
            </div>
        </div>

        <button v-bind="id" @click='createEntry(id)'></button>

    </section>
</template>

<script>
export default {
    name: 'exercise-entry',
    data() {
      return {
        id: null
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

<style>

</style>
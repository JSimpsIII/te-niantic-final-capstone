<template>
  <div id="metrics">

    <div id="metrics-title">
      <h1>Metrics</h1>
    </div>

    <div id="metrics-container">

      <div class="sub-metric-container">
        <div id="days-metric" class="metric-headers">
          <h2>Days</h2>
          <img 
            :src="require(`@/assets/${days.arrowDirection}.png`)" 
            :alt="days.arrowDirection"
            @click="toggleDaysContainer" 
          />
        </div>
        <div class="sub-metric-charts" v-if="days.isShowing">
            <!-- Insert Charts for Days Section -->
        </div>
      </div>

      <div class="sub-metric-container">
        <div id="minutes-metric" class="metric-headers">
          <h2>Minutes</h2>
          <img 
            :src="require(`@/assets/${minutes.arrowDirection}.png`)" 
            :alt="minutes.arrowDirection"
            @click="toggleMinutesContainer" 
          />
        </div>
        <div class="sub-metric-charts" v-if="minutes.isShowing">
            <!-- Insert Charts for Minutes Section -->
            <metrics-minutes />
            <percent-body-target />
        </div>
      </div>

      <div class="sub-metric-container">
        <div id="machine-metric" class="metric-headers">
          <h2>Machine</h2>
          <img 
            :src="require(`@/assets/${machine.arrowDirection}.png`)" 
            :alt="machine.arrowDirection"
            @click="toggleMachineContainer" 
          />
        </div>
        <div class="sub-metric-charts" v-if="machine.isShowing">
            <!-- Insert Charts for Machine Section -->
            <metrics-machine />
        </div>
      </div>

      <div class="sub-metric-container">
        <div id="target-metric" class="metric-headers">
          <h2>Target</h2>
          <img 
            :src="require(`@/assets/${target.arrowDirection}.png`)" 
            :alt="target.arrowDirection"
            @click="toggleTargetContainer" 
          />
        </div>
        <div class="sub-metric-charts" v-if="target.isShowing">
            <exercises-by-body-part />
        </div>
      </div>

      <div class="sub-metric-container">
        <div id="records-metric" class="metric-headers">
          <h2>Personal Records</h2>
          <img 
            :src="require(`@/assets/${records.arrowDirection}.png`)" 
            :alt="records.arrowDirection"
            @click="toggleRecordsContainer" 
          />
        </div>
        <div class="sub-metric-charts" v-if="records.isShowing">
            <!-- Insert Charts for Personal Records Section -->
        </div>
      </div>

    </div>

    <footer>
      <nav-bar />
    </footer>
  </div>
</template>

<script>
import NavBar from '../components/NavBar.vue';
import MetricsMinutes from '../components/MetricsMinutes.vue';
import MetricsMachine from '../components/MetricsMachine.vue';
import ExercisesByBodyPart from '../components/ExercisesByBodyPart.vue';
import PercentBodyTarget from '../components/PercentBodyTarget.vue';

export default {
  name: "metrics",
  components: {
    NavBar,
    MetricsMinutes,
    MetricsMachine,
    ExercisesByBodyPart,
    PercentBodyTarget
  },
  data() {
      return {
          days: {
              isShowing: false,
              arrowDirection: "down-arrow"
          },
          minutes: {
              isShowing: false,
              arrowDirection: "down-arrow"
          },
          machine: {
              isShowing: false,
              arrowDirection: "down-arrow"
          },
          target: {
              isShowing: false,
              arrowDirection: "down-arrow"
          },
          records: {
              isShowing: false,
              arrowDirection: "down-arrow"
          }
      }
  },
  methods: {
      toggleDaysContainer() {
          this.days.isShowing = !this.days.isShowing;
          this.days.arrowDirection = (this.days.arrowDirection == "down-arrow") ? "up-arrow" : "down-arrow";
      },
      toggleMinutesContainer() {
          this.minutes.isShowing = !this.minutes.isShowing;
          this.minutes.arrowDirection = (this.minutes.arrowDirection == "down-arrow") ? "up-arrow" : "down-arrow";
      },
      toggleMachineContainer() {
          this.machine.isShowing = !this.machine.isShowing;
          this.machine.arrowDirection = (this.machine.arrowDirection == "down-arrow") ? "up-arrow" : "down-arrow";
      },
      toggleTargetContainer() {
          this.target.isShowing = !this.target.isShowing;
          this.target.arrowDirection = (this.target.arrowDirection == "down-arrow") ? "up-arrow" : "down-arrow";
      },
      toggleRecordsContainer() {
          this.records.isShowing = !this.records.isShowing;
          this.records.arrowDirection = this.records.arrowDirection == "down-arrow" ? "up-arrow" : "down-arrow";
      }
  }
};
</script>

<style>
#metrics-title {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100px;
  background-color: var(--red);
}

#metrics-container {
  margin-bottom: 70px;
}

.metric-headers {
  display: flex;
  justify-content: space-between;
  background-color: var(--red);
  padding: 10px;
}

#metrics-title,
.sub-metric-charts {
  margin-bottom: 20px;
}

.sub-metric-charts {
    background-color: var(--smoke);
    padding: 20px 10px;
}

footer {
  position: fixed;
  bottom: 0;
  width: 600px;
  height: 50px;
  background: var(--smoke);
  padding-top: 15px;
}

</style>
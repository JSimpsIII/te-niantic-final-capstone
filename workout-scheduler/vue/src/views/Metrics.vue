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
            src="../assets/down-arrow.png" 
            alt="down-arrow"
            @click="toggleDaysContainer" 
          />
        </div>
        <div class="sub-metric-charts" v-if="isDaysShowing">
            Charts Here
        </div>
      </div>

      <div class="sub-metric-container">
        <div id="minutes-metric" class="metric-headers">
          <h2>Minutes</h2>
          <img 
            src="../assets/down-arrow.png" 
            alt="down-arrow"
            @click="toggleMinutesContainer" 
          />
        </div>
        <div class="sub-metric-charts" v-if="isMinutesShowing">
            Charts Here
        </div>
      </div>

      <div class="sub-metric-container">
        <div id="machine-metric" class="metric-headers">
          <h2>Machine</h2>
          <img 
            src="../assets/down-arrow.png" 
            alt="down-arrow"
            @click="toggleMachineContainer" 
          />
        </div>
        <div class="sub-metric-charts" v-if="isMachineShowing">
            Charts Here
        </div>
      </div>

      <div class="sub-metric-container">
        <div id="target-metric" class="metric-headers">
          <h2>Target</h2>
          <img 
            src="../assets/down-arrow.png" 
            alt="down-arrow"
            @click="toggleTargetContainer" 
          />
        </div>
        <div class="sub-metric-charts" v-if="isTargetShowing">
            Charts Here
        </div>
      </div>

      <div class="sub-metric-container">
        <div id="records-metric" class="metric-headers">
          <h2>Personal Records</h2>
          <img 
            src="../assets/down-arrow.png" 
            alt="down-arrow"
            @click="toggleRecordsContainer" 
          />
        </div>
        <div class="sub-metric-charts" v-if="isRecordsShowing">
            Charts Here
        </div>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  name: "metrics",
  data() {
      return {
          isDaysShowing: false,
          isMinutesShowing: false,
          isMachineShowing: false,
          isTargetShowing: false,
          isRecordsShowing: false
      }
  },
  methods: {
      toggleDaysContainer() {
          this.isDaysShowing = !this.isDaysShowing;
      },
      toggleMinutesContainer() {
          this.isMinutesShowing = !this.isMinutesShowing;
      },
      toggleMachineContainer() {
          this.isMachineShowing = !this.isMachineShowing;
      },
      toggleTargetContainer() {
          this.isTargetShowing = !this.isTargetShowing;
      },
      toggleRecordsContainer() {
          this.isRecordsShowing = !this.isRecordsShowing;
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

</style>
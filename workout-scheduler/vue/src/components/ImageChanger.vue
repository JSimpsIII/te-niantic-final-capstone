<template>
    <div>
    <transition-group name="fade" tag="div">
      <div v-for="i in [currentIndex]" :key="i">
        <img id="image" :src="currentImg" />
      </div>
    </transition-group>
  </div>
</template>
<script>
export default {
  name: "Slider",
  data() {
    return {
      images: [
        "https://st.depositphotos.com/1001599/3238/v/450/depositphotos_32383447-stock-illustration-gym-room.jpg",
        "https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/845943/gym-clipart-md.png",
        "https://www.creativefabrica.com/wp-content/uploads/2020/12/07/Gym-Logo-Graphics-7049759-1-580x386.jpg"
      ],
      timer: null,
      currentIndex: 0
    };
  },

  mounted: function() {
    this.startSlide();
  },

  methods: {
    startSlide: function() {
      this.timer = setInterval(this.next, 4000);
    },

    next: function() {
      this.currentIndex += 1;
    },
    prev: function() {
      this.currentIndex -= 1;
    }
  },

  computed: {
    currentImg: function() {
      return this.images[Math.abs(this.currentIndex) % this.images.length];
    }
  }
};
</script>

<style scoped>
#image {
  width: 300px;
  height: 300px;
  object-fit: cover;
  border-radius: 100%;
  object-position: top;
  margin-left: 25%;
  margin-bottom: 2%;
}

.fade-enter-active, {
  transition: all 0.9s ease;
  overflow: hidden;
  visibility: visible;
  position: absolute;
  width:100%;
  opacity: 1;
}

.fade-enter,
.fade-leave-to {
  visibility: hidden;
  width:100%;
  opacity: 0;
}

img {
  height:600px;
  width:100%
}
</style>
<template>
    <div id='registration-image'>
      <img id="image" :src=imageSource />
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
      ]
    }
  },
  computed: {
    imageSource() {
      return this.images[Math.floor(Math.random() * Math.floor(this.images.length))];
    }
  },
  mounted: function() {
    this.imageSource;
  }
};
</script>

<style scoped>
.registration-image {
  position: fixed;
  top: 20px;
  height: 600px;
  width: 100%;
}
#image {
  width: 300px;
  height: 300px;
  object-fit: cover;
  border-radius: 100%;
  object-position: top;
  margin: 0 auto ;
}
</style>
<template>
    <div id='nav-bar' class="nav-container">

        <router-link :to="{name: 'home'}">
            <img id="home-icon" src="../assets/home.png" alt="home-icon">
        </router-link>

        <router-link :to="{name: 'gym'}">
            <img id="star-icon" src="../assets/star.png" alt="star-icon">
        </router-link>

        <router-link :to="{name: 'metrics'}">
            <img id="chart-icon" src="../assets/chart.png" alt="chart-icon">
        </router-link>

        <router-link :to="{name: 'goals'}">
            <img id="goal-icon" src="../assets/goal.png" alt="goal-icon">
        </router-link>
        
    </div>
</template>

<script>
export default {
    name: "nav-bar",
}
</script>

<style scoped>

.nav-container {
    display: flex;
    justify-content: space-evenly;
}

.nav-container > router-link > div {
    width: 150px;
}

#home-icon:hover {
    filter: invert(16%) sepia(88%) saturate(5475%) hue-rotate(288deg) brightness(88%) contrast(104%);
}

#star-icon:hover {
    filter: invert(29%) sepia(29%) saturate(1113%) hue-rotate(166deg) brightness(97%) contrast(89%);
}

#chart-icon:hover {
    filter: invert(23%) sepia(36%) saturate(1751%) hue-rotate(285deg) brightness(91%) contrast(96%);
}

#goal-icon:hover {
    filter: invert(38%) sepia(62%) saturate(444%) hue-rotate(96deg) brightness(95%) contrast(89%);
}


</style>
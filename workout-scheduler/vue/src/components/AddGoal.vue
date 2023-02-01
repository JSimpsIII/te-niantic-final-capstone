<template>
  <div class="add-goals">
    <div class="add-new-goal">
        <img src="../assets/add.png" alt="">
        <div>Go to the gym <input type="number" min=1 max=7> days a week</div>
    </div>

    <div class="add-new-goal">
        <img src="../assets/add.png" alt="">
        <div>Workout <input type="number" min=1 max=480> minutes a day</div>
    </div>

    <div class="add-new-goal">
        <img src="../assets/add.png" alt="">
        <div>Bench press <input type="number" min=1 max=480> pounds</div>
    </div>

    <div class="add-new-goal">
        <img src="../assets/add.png" alt="">
        <input type="text" placeholder="Custom goal (e.g. feel less stressed)">
    </div>

    

  </div>
</template>

<script>
export default {
    name: "add-goals",
    data() {
        return {
            newGoal: {
                goalName: "",
                goalRep: null,
                goalWeight: null,
                goalTime: null,
                goalDays: null,
                goalMisc: null 
            }
        }
    }
  
};
</script>

<style scoped>

.add-goals {
    width: 70%;
    margin: 0 auto;
}

.add-new-goal {
    display: flex;
    align-items: center;
    height: 60px;
    background-color: var(--green);
    border-radius: 10px;
    margin-bottom: 10px;
    padding-left: 10px;
}

.add-new-goal > div > input {
    color: inherit;
    background-color: transparent;
    border: 1px var(--white) solid;
    border-radius: 5px;
    height: 30px;
    width: 40px;
}

.add-new-goal > div > input::placeholder {
    color: inherit;
}

.add-new-goal > input {
    width: 75%;
    height: 30px;
    color: inherit;
    background-color: transparent;
    border: none;
}

.add-new-goal > input::placeholder {
    color: var(--white);
    font-style: italic;
}

input:focus {
    outline-color: white;

}

</style>
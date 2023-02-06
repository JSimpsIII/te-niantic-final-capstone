export default {

    // BY BODY PART
    getLabelArrayForBodyPart() {
        let array = ['back', 'cardio', 'chest', 'lower arms', 'lower legs', 'neck', 'shoulders', 'upper arms', 'upper legs', 'waist'];
        return array;
    },
    getPercentageArrayForBodyPart() {
        let minback = 0
        let mincardio = 0
        let minchest = 0
        let minlowerarms = 0
        let minlowerlegs = 0
        let minneck = 0
        let minshoulders = 0
        let minupperarms = 0
        let minupperlegs = 0
        let minwaist = 0
        let mintotal = 0
        this.$store.state.metricsList.forEach(metric => {
            let eId = metric.exerciseId;
            this.$store.state.exerciseList.forEach(exercise => {
                if (exercise.id == eId) {
                    switch (exercise.bodyPart) {
                        case 'back':
                            minback += metric.time;
                            mintotal += metric.time;
                            break;
                        case 'cardio':
                            mincardio += metric.time;
                            mintotal += metric.time;
                            break;
                        case 'chest':
                            minchest += metric.time;
                            mintotal += metric.time;
                            break;
                        case 'lower arms':
                            minlowerarms += metric.time;
                            mintotal += metric.time;
                            break;
                        case 'lower legs':
                            minlowerlegs += metric.time;
                            mintotal += metric.time;
                            break;
                        case 'neck':
                            minneck += metric.time;
                            mintotal += metric.time;
                            break;
                        case 'shoulders':
                            minshoulders += metric.time;
                            mintotal += metric.time;
                            break;
                        case 'upper arms':
                            minupperarms += metric.time;
                            mintotal += metric.time;
                            break;
                        case 'upper legs':
                            minupperlegs += metric.time;
                            mintotal += metric.time;
                            break;
                        case 'waist':
                            minwaist += metric.time;
                            mintotal += metric.time;
                            break;
                    }
                }
            })
        })
        let array = [
            (minback/mintotal) * 100, 
            (mincardio/mintotal) * 100, 
            (minchest/mintotal) * 100, 
            (minlowerarms/mintotal) * 100, 
            (minlowerlegs/mintotal) * 100, 
            (minneck/mintotal) * 100, 
            (minshoulders/mintotal) * 100, 
            (minupperarms/mintotal) * 100, 
            (minupperlegs/mintotal) * 100, 
            (minwaist/mintotal) * 100
        ];
        return array;
    },

    getPercentBack() {
        let array = this.getPercentageArrayForBodyPart();
        let back = array[0];
        return back * 100;
    },

    getPercentCardio() {
        let array = this.getPercentageArrayForBodyPart();
        let cardio = array[1];
        return cardio  * 100;
    },

    getPercentChest() {
        let array = this.getPercentageArrayForBodyPart();
        let chest = array[2];
        return chest * 100;
    },

    getPercentUpperArms() {
        let array = this.getPercentageArrayForBodyPart();
        let arms = array[7];
        return arms * 100;
    },

    getPercentLowerArms() {
        let array = this.getPercentageArrayForBodyPart();
        let arms = array[3];
        return arms * 100;
    },

    getPercentUpperLegs() {
        let array = this.getPercentageArrayForBodyPart();
        let legs = array[8];
        return legs * 100;
    },

    getPercentLowerLegs() {
        let array = this.getPercentageArrayForBodyPart();
        let legs = array[4];
        return legs * 100;
    },

    getPercentNeck() {
        let array = this.getPercentageArrayForBodyPart();
        let neck = array[5];
        return neck * 100;
    },

    getPercentShoulders() {
        let array = this.getPercentageArrayForBodyPart();
        let shoulders = array[6];
        return shoulders * 100;
    },

    getPercentWaist() {
        let array = this.getPercentageArrayForBodyPart();
        let waist = array[9];
        return waist * 100;
    },

    getPercentUpperLimbs() {
        let upperArms = this.getPercentUpperArms();
        let lowerArms = this.getPercentLowerArms();
        let shoulders = this.getPercentShoulders();
        return upperArms + lowerArms + shoulders;
    },

    getPercentLowerLimbs() {
        let upperLegs = this.getPercentUpperLegs();
        let lowerLegs = this.getPercentLowerLegs();
        return upperLegs + lowerLegs;
    },

    getPercentCore() {
        let back = this.getPercentBack();
        let neck = this.getPercentNeck();
        let waist = this.getPercentWaist();
        let chest = this.getPercentChest();
        return back + neck + waist + chest;
    },
    
    getPercentStrength() {
        let upperLimbs = this.getPercentUpperLimbs();
        let lowerLimbs = this.getPercentLowerLimbs();
        let core = this.getPercentCore();
        return upperLimbs + lowerLimbs + core;
    },

    // BY TARGET

    getPercentageArrayForBodyTarget() {
        let abductors = 0
        let abs = 0
        let adductors = 0
        let biceps = 0
        let calves = 0
        let cardiovascular = 0
        let delts = 0
        let forearms = 0
        let glutes = 0
        let hamstrings = 0
        let lats
        let levatorScapulae = 0
        let pectorals = 0
        let quads = 0
        let serratusAnterior = 0
        let spine= 0
        let traps= 0
        let triceps= 0
        let upperBack = 0
        let minTotal = 0
        this.$store.state.metricsList.forEach(metric => {
            let eId = metric.exerciseId;
            this.$store.state.exerciseList.forEach(exercise => {
                if (exercise.id == eId) {
                    switch (exercise.target) {
                        case 'abductors':
                            abductors += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'abs':
                            abs += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'adductors':
                            adductors += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'biceps':
                            biceps += metric.time;
                            minTotal += metric.time;
                            break; 
                        case 'calves':
                            calves += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'cardiovascular':
                            cardiovascular += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'delts':
                            delts += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'forearms':
                            forearms += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'glutes':
                            glutes += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'hamstrings':
                            hamstrings += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'lats':
                            lats += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'levator scapulae':
                            levatorScapulae += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'pectorals':
                            pectorals += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'quads':
                            quads += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'serratus anterior':
                            serratusAnterior += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'spine':
                            spine += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'traps':
                            traps += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'triceps':
                            triceps += metric.time;
                            minTotal += metric.time;
                            break;
                        case 'upper back':
                            upperBack += metric.time;
                            minTotal += metric.time;
                            break;
                    }
                }
            })
        })
        let array = [
            abductors/minTotal,
            abs/minTotal,
            adductors/minTotal,
            biceps/minTotal,
            calves/minTotal,
            cardiovascular/minTotal,
            delts/minTotal,
            forearms/minTotal,
            glutes/minTotal,
            hamstrings/minTotal,
            lats/minTotal,
            levatorScapulae/minTotal,
            pectorals/minTotal,
            quads/minTotal,
            serratusAnterior/minTotal,
            spine/minTotal,
            traps/minTotal,
            triceps/minTotal,
            upperBack/minTotal
        ];
        return array;
    }

}
class ChartsService {

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
        let array = [minback/mintotal, mincardio/mintotal, minchest/mintotal, minlowerarms/mintotal, minlowerlegs/mintotal, minneck/mintotal, minshoulders/mintotal, minupperarms/mintotal, minupperlegs/mintotal, minwaist/mintotal];
        return array;
    }

}
export default ChartsService;
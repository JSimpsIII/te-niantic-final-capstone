import axios from 'axios'

class ExerciseService
{
    getAllExercises(){
        return axios.get('/exercises')
    }

}
export default new ExerciseService()
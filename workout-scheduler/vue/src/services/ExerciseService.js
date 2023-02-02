import axios from 'axios'

class ExerciseService
{
    getAllExercises(){
        return axios.get('/exercises')
    }

    getExercise(id){
        return axios.get(`/exercises/${id}`)
    }

}
export default new ExerciseService()
import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
})


export default
{
    getAllExercises(){
        return http.get('/exercises')
    },

    getExerciseById(exerciseId){
        return http.get(`/exercises/${exerciseId}`)
    }
}
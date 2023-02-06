import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
})


export default
{
    getAllExercises(){
        return http.get('/exercises')
    },

    getExerciseById(id){
        return http.get(`/exercises/${id}`)
    }
}
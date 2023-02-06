import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
})


export default
{
    getAllExercises(){
        return http.get('/exercises')
    },

    getExercise(id){
        return http.get(`/exercises/${id}`)
    },

    log(id, exercise) {
        return http.post(`/users/${id}/metrics`, exercise)
    }

}
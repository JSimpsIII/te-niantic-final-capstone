import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
})
export default {

    createNewProfile(newUser) {
        return http.post('/users', newUser)
    }

}

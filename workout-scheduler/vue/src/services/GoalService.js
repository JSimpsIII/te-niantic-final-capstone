import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {

    getAllGoals(id) {
        return http.get(`/users/${id}/goals`);
    }

}

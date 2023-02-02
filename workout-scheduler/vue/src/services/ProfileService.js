import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {

    getProfile(username) {
        return http.get(`/users/${username}`);
    }

}

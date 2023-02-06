import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {
    
    getAllMetrics(userId) {
        return http.get(`/users/${userId}/metrics`);
    },

    logNewMetric(userId, metric) {
        return http.post(`/users/${userId}/log`, metric);
    }

}
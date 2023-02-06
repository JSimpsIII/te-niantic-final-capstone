import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {
    
    getAllMetrics(id) {
        return http.get(`/users/${id}/metrics`);
    },

    addNewMetric(id, newMetric) {
        return http.post(`/users/${id}/metrics`, newMetric);
    }
}
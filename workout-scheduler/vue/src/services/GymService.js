import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {

    getAllVisits(userId) {
        return http.get(`/users/${userId}/gym`);
    },

    addVisit(userId, visit) {
        return http.post(`/users/${userId}/gym`, visit);
    },

    updateVisit(userId, visitId, visit) {
        return http.put(`/users/${userId}/gym/${visitId}`, visit);
    },
    
    deleteVisit(userId, visitId) {
        return http.delete(`/users/${userId}/gym/${visitId}`);
    }

}
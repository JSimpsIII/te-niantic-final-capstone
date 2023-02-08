import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {

    getAllVisits(userId) {
        return http.get(`/users/${userId}/gymlogs`);
    },

    newVisit(userId) {
        return http.post(`/users/${userId}/gymlogs`);
    },

    updateVisit(userId, visitId, visit) {
        return http.put(`/users/${userId}/gymlogs/${visitId}`, visit);
    },
    
    deleteVisit(userId, visitId) {
        return http.delete(`/users/${userId}/gymlogs/${visitId}`);
    }

}
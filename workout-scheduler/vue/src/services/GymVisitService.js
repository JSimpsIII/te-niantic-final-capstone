import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {

    getAllVisits(userId) {
        return http.get(`/users/${userId}/gymlogs`);
    },

    addVisit(userId, visit) {
        return http.get(`/users/${userId}/gymlogs`, visit);
    },
    
    deleteVisit(userId, visitId) {
        return http.delete(`/users/${userId}/gymlogs/${visitId}`);
    }

}
import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {

    getAllVisits(userId) {
        return http.get(`/users/${userId}/gymlogs`);
    },

    checkIn(userId) {
        return http.get(`/users/${userId}/gymlogs/checkin`);
    },

    checkOut(userId, visitId) {
        return http.get(`/users/${userId}/gymlogs/${visitId}/checkout`);
    },
    
    deleteVisit(userId, visitId) {
        return http.delete(`/users/${userId}/gymlogs/${visitId}`);
    }

}
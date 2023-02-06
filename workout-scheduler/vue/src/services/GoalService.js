import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
})


export default {

    getAllGoals(userId) {
        return http.get(`/users/${userId}/goals`);
    },

    addNewGoal(userId, goal) {
        return http.post(`/users/${userId}/goals`, goal);
    },

    deleteGoal(userId, goalId) {
        return http.delete(`/users/${userId}/goals/${goalId}`);
    }

}

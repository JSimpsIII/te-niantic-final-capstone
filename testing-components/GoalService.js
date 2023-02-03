import axios from 'axios';

export default {

    getAllGoals(id) {
        return axios.get(`/users/${id}/goals`);
    },

    addNewGoal(id, newGoal) {
        return axios.post(`/users/${id}/goals`, newGoal);
    }

}

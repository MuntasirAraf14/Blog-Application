import axios from 'axios';

const API_URL = 'http://localhost:9090/api/v1/auth';

const register = (userData) => {
    return axios.post(API_URL + '/register', userData);
};

/**
 * Sends user login credentials to the backend.
 * @param {object} credentials - The user's login details (username, password).
 */
const login = (credentials) => {
    return axios.post(API_URL + '/login', credentials)
        .then(response => {
            // If the login is successful, the response will contain the token.
            if (response.data.token) {
                // Store the user details (including the token) in localStorage.
                // localStorage only stores strings, so we must stringify the object.
                localStorage.setItem('user', JSON.stringify(response.data));
            }
            return response.data;
        });
};

/**
 * Removes the user's data from localStorage, effectively logging them out.
 */
const logout = () => {
    localStorage.removeItem('user');
};

/**
 * Retrieves the current user's data from localStorage.
 * @returns The user object (including token) or null if not logged in.
 */
const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem('user'));
};

export default {
    register,
    login,
    logout,
    getCurrentUser,
};
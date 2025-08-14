import axios from 'axios';
import authService from './authService';

const API_URL = 'http://localhost:9090/api/v1';

// Helper function to get the auth token from localStorage
const getAuthHeader = () => {
    const user = authService.getCurrentUser();
    if (user && user.token) {
        return { Authorization: 'Bearer ' + user.token };
    } else {
        return {};
    }
};

// --- PUBLIC-FACING API CALLS ---

const getAllPosts = () => {
    return axios.get(API_URL + '/posts');
};

const getCategories = () => {
    return axios.get(API_URL + '/categories');
};

const getPostById = (postId) => {
    return axios.get(`${API_URL}/posts/${postId}`);
};


// --- AUTHENTICATED API CALLS ---

const createPost = (postData, categoryId) => {
    const headers = getAuthHeader();
    const url = `${API_URL}/category/${categoryId}/posts`;
    return axios.post(url, postData, { headers });
};

const createComment = (postId, commentData) => {
    const headers = getAuthHeader();
    return axios.post(`${API_URL}/post/${postId}/comments`, commentData, { headers });
};

const getMyPosts = () => {
    const headers = getAuthHeader();
    return axios.get(API_URL + '/user/posts', { headers });
};


// --- ADMIN API CALLS ---

const getPendingPosts = () => {
    const headers = getAuthHeader();
    return axios.get(API_URL + '/admin/posts/pending', { headers });
};

const approvePost = (postId) => {
    const headers = getAuthHeader();
    return axios.put(API_URL + `/admin/posts/${postId}/approve`, null, { headers });
};

const rejectPost = (postId) => {
    const headers = getAuthHeader();
    return axios.put(API_URL + `/admin/posts/${postId}/reject`, null, { headers });
};


export default {
    getAllPosts,
    getCategories,
    createPost,
    getPostById,
    createComment,
    getMyPosts,
    getPendingPosts,
    approvePost,
    rejectPost,
    // uploadPostImage function is now removed
};
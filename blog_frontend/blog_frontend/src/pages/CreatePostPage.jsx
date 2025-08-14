import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import postService from '../services/postService';
import Spinner from '../components/Spinner';

function CreatePostPage() {
    const navigate = useNavigate();
    const [postData, setPostData] = useState({ title: '', content: '' });
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        postService.getCategories()
            .then(response => {
                setCategories(response.data);
                if (response.data.length > 0) {
                    setSelectedCategory(response.data[0].categoryId);
                }
            })
            .catch(err => {
                console.error("Failed to fetch categories", err);
                setError("Could not load categories. Please try again.");
            });
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setPostData(prevState => ({ ...prevState, [name]: value }));
    };

    const handleCategoryChange = (e) => {
        setSelectedCategory(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!selectedCategory) {
            setError("Please select a category.");
            return;
        }
        setLoading(true);
        setError(null);

        postService.createPost(postData, selectedCategory)
            .then(() => {
                setLoading(false);
                navigate('/', { 
                    state: { message: "Your event has been submitted for approval!" } 
                });
            })
            .catch(err => {
                console.error("Failed to create post", err);
                setError(err.response?.data?.message || "Failed to create post.");
                setLoading(false);
            });
    };

    return (
        <div className="max-w-2xl mx-auto">
            <h1 className="text-3xl font-bold mb-6">Create a New Event Post</h1>
            <form onSubmit={handleSubmit} className="bg-white p-8 rounded-lg shadow-md space-y-6">
                <div>
                    <label htmlFor="title" className="block text-sm font-medium text-gray-700 text-left">Event Title</label>
                    <input type="text" id="title" name="title" value={postData.title} onChange={handleChange} required className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500" />
                </div>
                <div>
                    <label htmlFor="category" className="block text-sm font-medium text-gray-700 text-left">Category</label>
                    <select id="category" value={selectedCategory} onChange={handleCategoryChange} required className="mt-1 block w-full px-3 py-2 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500">
                        <option value="" disabled>Select a category</option>
                        {categories.map(cat => (<option key={cat.categoryId} value={cat.categoryId}>{cat.categoryTitle}</option>))}
                    </select>
                </div>
                <div>
                    <label htmlFor="content" className="block text-sm font-medium text-gray-700 text-left">Event Description</label>
                    <textarea id="content" name="content" value={postData.content} onChange={handleChange} required rows="6" className="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500" />
                </div>
                {error && <p className="text-red-500 text-sm">{error}</p>}
                <div>
                    <button type="submit" disabled={loading} className="w-full flex justify-center items-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 disabled:bg-blue-300">
                        {loading ? <Spinner /> : 'Submit for Approval'}
                    </button>
                </div>
            </form>
        </div>
    );
}

export default CreatePostPage;
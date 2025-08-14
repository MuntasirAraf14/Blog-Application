import React, { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import postService from '../services/postService';

function HomePage() {
    const location = useLocation();
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [successMessage, setSuccessMessage] = useState('');

    useEffect(() => {
        if (location.state?.message) {
            setSuccessMessage(location.state.message);
            window.history.replaceState({}, document.title);
        }

        postService.getAllPosts()
            .then(response => {
                setPosts(response.data.content);
                setLoading(false);
            })
            .catch(error => {
                console.error("There was an error fetching the posts!", error);
                setError("Could not load events. Please try again later.");
                setLoading(false);
            });
    }, [location.state]);

    if (loading) {
        return <div className="text-center p-10">Loading events...</div>;
    }

    if (error) {
        return <div className="text-center p-10 text-red-500">{error}</div>;
    }

    return (
        <div className="px-4 sm:px-6 lg:px-8">
            <h1 className="text-4xl font-bold mb-8 text-center">Upcoming Campus Events</h1>
            
            {successMessage && (
                <div className="bg-blue-100 border border-blue-400 text-blue-700 px-4 py-3 rounded-md mb-6 text-center">
                    <span>{successMessage}</span>
                </div>
            )}

            {/* Responsive grid */}
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                {posts.length > 0 ? (
                    posts.map(post => (
                        <Link to={`/posts/${post.postId}`} key={post.postId}>
                            <div className="bg-white border border-gray-200 rounded-lg p-5 shadow-sm hover:shadow-md transition-shadow duration-200 h-full flex flex-col">
                                <h2 className="text-xl font-semibold text-slate-800">{post.title}</h2>
                                <p className="text-gray-600 my-4 flex-grow">{post.content.substring(0, 150)}...</p>
                                <small className="text-gray-500">Posted by: {post.user.name}</small>
                            </div>
                        </Link>
                    ))
                ) : (
                    <p className="text-center text-gray-500 col-span-full">No events posted yet. Check back soon!</p>
                )}
            </div>
        </div>
    );
}

export default HomePage;

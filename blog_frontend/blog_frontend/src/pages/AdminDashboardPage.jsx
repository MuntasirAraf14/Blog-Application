import React, { useState, useEffect } from 'react';
import postService from '../services/postService';

function AdminDashboardPage() {
    const [pendingPosts, setPendingPosts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [message, setMessage] = useState(''); // For success/error messages

    const fetchPendingPosts = () => {
        setLoading(true);
        postService.getPendingPosts()
            .then(response => {
                setPendingPosts(response.data.content);
                setLoading(false);
            })
            .catch(err => {
                console.error("Failed to fetch pending posts", err);
                setError("Could not load pending posts. You may not be an admin.");
                setLoading(false);
            });
    };

    // Fetch posts when the component mounts
    useEffect(() => {
        fetchPendingPosts();
    }, []);

    const handleApprove = (postId) => {
        postService.approvePost(postId)
            .then(() => {
                setMessage(`Post #${postId} has been approved.`);
                // Refresh the list of pending posts
                fetchPendingPosts(); 
            })
            .catch(err => setError(`Failed to approve post #${postId}.`));
    };

    const handleReject = (postId) => {
        postService.rejectPost(postId)
            .then(() => {
                setMessage(`Post #${postId} has been rejected.`);
                // Refresh the list
                fetchPendingPosts();
            })
            .catch(err => setError(`Failed to reject post #${postId}.`));
    };

    if (loading) return <p>Loading moderation queue...</p>;
    if (error) return <p className="text-red-500">{error}</p>;

    return (
        <div className="max-w-4xl mx-auto">
            <h1 className="text-3xl font-bold mb-6">Admin Dashboard - Pending Posts</h1>
            
            {message && <p className="text-green-500 mb-4">{message}</p>}

            <div className="space-y-4">
                {pendingPosts.length > 0 ? (
                    pendingPosts.map(post => (
                        <div key={post.postId} className="bg-white p-5 rounded-lg shadow-md flex justify-between items-center">
                            <div>
                                <h2 className="text-xl font-semibold">{post.title}</h2>
                                <p className="text-sm text-gray-600">By: {post.user.name} ({post.user.email})</p>
                            </div>
                            <div className="flex gap-4">
                                <button 
                                    onClick={() => handleApprove(post.postId)}
                                    className="bg-green-500 hover:bg-green-600 text-white font-bold py-2 px-4 rounded"
                                >
                                    Approve
                                </button>
                                <button
                                    onClick={() => handleReject(post.postId)}
                                    className="bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded"
                                >
                                    Reject
                                </button>
                            </div>
                        </div>
                    ))
                ) : (
                    <p>The moderation queue is empty. Great job!</p>
                )}
            </div>
        </div>
    );
}

export default AdminDashboardPage;
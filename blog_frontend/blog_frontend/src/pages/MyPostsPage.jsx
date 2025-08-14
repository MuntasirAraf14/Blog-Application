import React, { useState, useEffect } from 'react';
import postService from '../services/postService';
import { Link } from 'react-router-dom';

// Helper component for the status badge
const StatusBadge = ({ status }) => {
    const statusStyles = {
        PENDING: 'bg-yellow-100 text-yellow-800',
        APPROVED: 'bg-green-100 text-green-800',
        REJECTED: 'bg-red-100 text-red-800',
    };
    return (
        <span className={`px-2 py-1 text-xs font-semibold rounded-full ${statusStyles[status] || 'bg-gray-100 text-gray-800'}`}>
            {status}
        </span>
    );
};

function MyPostsPage() {
    const [myPosts, setMyPosts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        postService.getMyPosts()
            .then(response => {
                setMyPosts(response.data);
                setLoading(false);
            })
            .catch(err => {
                console.error("Failed to fetch user posts", err);
                setError("Could not load your posts. Please try again later.");
                setLoading(false);
            });
    }, []);

    if (loading) return <p>Loading your posts...</p>;
    if (error) return <p className="text-red-500">{error}</p>;

    return (
        <div>
            <h1 className="text-3xl font-bold mb-6">My Event Submissions</h1>
            <div className="space-y-4">
                {myPosts.length > 0 ? (
                    myPosts.map(post => (
                        <div key={post.postId} className="bg-white p-5 rounded-lg shadow-md flex justify-between items-center">
                            <div>
                                <h2 className="text-xl font-semibold">{post.title}</h2>
                                <p className="text-sm text-gray-600">
                                    Submitted on: {new Date(post.addedDate).toLocaleDateString()}
                                </p>
                            </div>
                            <div>
                                <StatusBadge status={post.status} />
                            </div>
                        </div>
                    ))
                ) : (
                    <div className="text-center p-8 bg-white rounded-lg shadow-md">
                        <p>You haven't submitted any events yet.</p>
                        <Link to="/create-post" className="mt-4 inline-block bg-blue-600 text-white font-bold py-2 px-4 rounded hover:bg-blue-700">
                            Create Your First Event
                        </Link>
                    </div>
                )}
            </div>
        </div>
    );
}

export default MyPostsPage;
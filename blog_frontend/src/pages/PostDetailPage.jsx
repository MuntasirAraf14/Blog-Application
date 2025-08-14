import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import postService from '../services/postService';
import authService from '../services/authService';
import CommentForm from '../components/CommentForm';

function PostDetailPage() {
    const { postId } = useParams();
    
    const [post, setPost] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [currentUser, setCurrentUser] = useState(null);

    useEffect(() => {
        setCurrentUser(authService.getCurrentUser());

        if (postId) {
            postService.getPostById(postId)
                .then(response => {
                    setPost(response.data);
                    setLoading(false);
                })
                .catch(err => {
                    console.error("Failed to fetch post details", err);
                    setError("Could not load the event. It may not exist or is not approved.");
                    setLoading(false);
                });
        }
    }, [postId]);

    const handleCommentAdded = (newComment) => {
        setPost(prevPost => ({
            ...prevPost,
            comments: [...prevPost.comments, newComment]
        }));
    };

    // --- THIS IS THE CORRECTED LOGIC ---
    if (loading) {
        return <div className="text-center p-10">Loading event details...</div>;
    }

    if (error) {
        return <div className="text-center p-10 text-red-500">{error}</div>;
    }
    
    // If loading is finished and there's still no post, it means it wasn't found.
    if (!post) {
        return <div className="text-center p-10">Event not found.</div>;
    }
    // ------------------------------------

    // By the time we reach this return statement, we are GUARANTEED that 'post' is an object.
    return (
        <div className="bg-white p-8 rounded-lg shadow-md max-w-3xl mx-auto">
            <h1 className="text-4xl font-bold mb-4">{post.title}</h1>
            <div className="text-sm text-gray-500 mb-6">
                <span>Posted by: {post.user.name}</span> | 
                <span> Category: {post.category.categoryTitle}</span>
            </div>
            
            <div className="prose max-w-none text-left border-b pb-8 mb-8">
                <p>{post.content}</p>
            </div>
            
            <div className="comments-section text-left">
                <h2 className="text-2xl font-bold mb-6">Comments ({post.comments.length})</h2>
                
                {post.comments.length > 0 ? (
                    <div className="space-y-4">
                        {post.comments.map(comment => (
                            <div key={comment.id} className="bg-gray-50 p-4 rounded-md border">
                                <p className="text-gray-800">{comment.content}</p>
                                <small className="text-gray-500">by: {comment.user ? comment.user.name : 'Anonymous'}</small>
                            </div>
                        ))}
                    </div>
                ) : (
                    <p className="text-gray-500">No comments yet. Be the first to comment!</p>
                )}

                {currentUser ? (
                    <CommentForm postId={postId} onCommentAdded={handleCommentAdded} />
                ) : (
                    <p className="mt-8 text-center text-gray-600">
                        You must be <a href="/login" className="text-blue-600 hover:underline">logged in</a> to leave a comment.
                    </p>
                )}
            </div>
        </div>
    );
}

export default PostDetailPage;
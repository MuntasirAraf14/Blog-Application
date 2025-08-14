import React, { useState } from 'react';
import postService from '../services/postService';
import Spinner from './Spinner';

// This component receives the postId and a function to call when a comment is successfully added
function CommentForm({ postId, onCommentAdded }) {
    const [content, setContent] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const handleSubmit = (e) => {
        e.preventDefault();
        setLoading(true);
        setError(null);

        postService.createComment(postId, { content })
            .then(response => {
                setLoading(false);
                setContent(''); // Clear the textarea
                onCommentAdded(response.data); // Pass the new comment back to the parent page
            })
            .catch(err => {
                console.error("Failed to post comment", err);
                setError("Could not post comment. Please try again.");
                setLoading(false);
            });
    };

    return (
        <form onSubmit={handleSubmit} className="mt-8">
            <h3 className="text-xl font-semibold mb-4 text-left">Leave a Comment</h3>
            <textarea
                value={content}
                onChange={(e) => setContent(e.target.value)}
                placeholder="Write your comment here..."
                required
                rows="4"
                className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
            {error && <p className="text-red-500 text-sm mt-2">{error}</p>}
            <button
                type="submit"
                disabled={loading}
                className="mt-4 flex items-center justify-center px-4 py-2 bg-blue-600 text-white font-semibold rounded-md hover:bg-blue-700 disabled:bg-blue-300"
            >
                {loading ? <Spinner size={20} /> : 'Post Comment'}
            </button>
        </form>
    );
}

export default CommentForm;
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import CreatePostPage from './pages/CreatePostPage';
import AdminDashboardPage from './pages/AdminDashboardPage';
import PostDetailPage from './pages/PostDetailPage';
import MyPostsPage from './pages/MyPostsPage';
import Navbar from './components/Navbar';
import ProtectedRoute from './components/ProtectedRoute';

function App() {
    return (
        <Router>
            <div className="font-sans text-gray-800 bg-gray-100 min-h-screen">
                {/* Fixed Navbar */}
                <Navbar />
                
                {/* Main Content */}
                <main className="w-full px-4 sm:px-6 lg:px-8 pt-24 pb-10">
                    <Routes>
                        {/* Public Routes */}
                        <Route path="/" element={<HomePage />} />
                        <Route path="/login" element={<LoginPage />} />
                        <Route path="/register" element={<RegisterPage />} />
                        <Route path="/posts/:postId" element={<PostDetailPage />} />
                        
                        {/* Protected Routes */}
                        <Route element={<ProtectedRoute />}>
                            <Route path="/create-post" element={<CreatePostPage />} />
                            <Route path="/admin-dashboard" element={<AdminDashboardPage />} />
                            <Route path="/my-posts" element={<MyPostsPage />} />
                        </Route>
                    </Routes>
                </main>
            </div>
        </Router>
    );
}

export default App;

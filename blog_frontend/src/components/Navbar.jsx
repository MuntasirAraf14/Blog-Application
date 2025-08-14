import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import authService from '../services/authService';

function Navbar() {
    const navigate = useNavigate();
    const location = useLocation();
    const [currentUser, setCurrentUser] = useState(undefined);

    useEffect(() => {
        const user = authService.getCurrentUser();
        if (user) {
            setCurrentUser(user);
        }
    }, []);

    const handleLogout = () => {
        authService.logout();
        setCurrentUser(undefined);
        navigate('/');
        window.location.reload();
    };

    const isAdmin = currentUser?.user?.roles?.some(role => role.name === 'ROLE_ADMIN');

    const linkClass = (path) =>
        `px-3 py-2 rounded-md text-sm font-medium transition-colors ${
            location.pathname === path
                ? 'bg-blue-600 text-white'
                : 'text-slate-700 hover:bg-blue-100'
        }`;

    return (
        <nav className="bg-white shadow-md fixed top-0 left-0 w-full z-50">
            <div className="w-full flex justify-between items-center px-4 sm:px-6 lg:px-8 py-3">
                {/* Logo */}
                <Link to="/" className="text-xl font-bold text-blue-600">
                    Campus Events
                </Link>

                {/* Links */}
                <div className="flex gap-3 items-center">
                    {currentUser ? (
                        <>
                            <span className="text-slate-600 hidden sm:inline">
                                Welcome, <span className="font-semibold">{currentUser.user.name}</span>
                            </span>

                            {isAdmin && (
                                <Link to="/admin-dashboard" className={linkClass('/admin-dashboard')}>
                                    Admin Dashboard
                                </Link>
                            )}

                            <Link to="/my-posts" className={linkClass('/my-posts')}>
                                My Posts
                            </Link>

                            <Link to="/create-post" className={linkClass('/create-post')}>
                                Create Event
                            </Link>

                            <button
                                onClick={handleLogout}
                                className="px-3 py-2 text-sm font-medium text-slate-700 hover:bg-red-100 rounded-md transition-colors"
                            >
                                Logout
                            </button>
                        </>
                    ) : (
                        <>
                            <Link to="/login" className={linkClass('/login')}>
                                Login
                            </Link>
                            <Link to="/register" className={linkClass('/register')}>
                                Register
                            </Link>
                        </>
                    )}
                </div>
            </div>
        </nav>
    );
}

export default Navbar;

import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import authService from '../services/authService';

// This component checks if a user is logged in.
// If they are, it renders the child components (the actual page).
// If not, it redirects them to the login page.
function ProtectedRoute() {
    const currentUser = authService.getCurrentUser();

    if (!currentUser) {
        // User not logged in, redirect to login page.
        // We can also pass a message to the login page.
        return <Navigate to="/login" state={{ message: "You must be logged in to view this page." }} />;
    }

    // User is logged in, so render the component they were trying to access.
    // The <Outlet /> component is a placeholder for the actual page component.
    return <Outlet />;
}

export default ProtectedRoute;
import React from 'react';
import { TailSpin } from 'react-loader-spinner';

// A simple, reusable spinner component
function Spinner({ size = 20, color = "#ffffff" }) {
    return (
        <TailSpin
            height={size}
            width={size}
            color={color}
            ariaLabel="loading"
        />
    );
}

export default Spinner;
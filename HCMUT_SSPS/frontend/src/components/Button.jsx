import { Link } from 'react-router-dom';

/* eslint-disable react/prop-types */
function Button({ color = "primary", label = "Click Me", link = "" }) {
  const colorClasses = {
    primary: "bg-primary-500 hover:bg-primary-600",
    green: "bg-green-500 hover:bg-green-600",
    red: "bg-red-500 hover:bg-red-600",
    gray: "bg-gray-500 hover:bg-gray-600",
  };

  const buttonClass = colorClasses[color] || colorClasses["primary"];

  return (
    <Link
      to={link}
      className={`text-white font-semibold py-2 px-4 rounded ${buttonClass}`}
    >
      {label}
    </Link>
  );
}

export default Button;

/* eslint-disable react/prop-types */
import { useNavigate } from 'react-router-dom';

import Navbar from '~/components/Navbar';

function LayoutLanding({
  children,
  title,
  leftLabel,
  leftLink,
  rightLabel,
  rightLink,
  isSubmit = false,
  submitButtonId,
}) {
  const navigate = useNavigate();

  return (
    <div>
      <Navbar />
      <p className="text-center text-3xl font-bold py-6">{title}</p>

      {children}

    </div>
  );
}

export default LayoutLanding;

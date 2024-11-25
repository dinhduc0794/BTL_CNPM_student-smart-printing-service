/* eslint-disable react/prop-types */
import { useNavigate } from 'react-router-dom';
import Button from '~/components/Button';
import Navbar from '~/components/Navbar';

function Layout({
  children,
  title,
  leftColor = "red",
  leftLabel,
  leftLink,
  rightColor = "primary",
  rightLabel,
  rightLink,
  isSubmit = false,
  submitButtonId,
}) {
  const navigate = useNavigate();

  const handleNextClick = () => {
    if (isSubmit && submitButtonId) {
      const hiddenSubmitButton = document.getElementById(submitButtonId);
      if (hiddenSubmitButton) {
        hiddenSubmitButton.click();
      }
    } else if (!isSubmit) {
      navigate(rightLink);

    }
  };

  return (
    <div>
      <Navbar />
      <p className="text-center text-3xl font-bold py-6">{title}</p>

      {children}

      <div className="fixed bottom-0 left-0 right-0 mx-auto flex max-w-7xl justify-between px-8 pb-8">
        <Button color={leftColor} label={leftLabel} link={leftLink} />
        <button
          className={`text-white font-semibold py-2 px-4 rounded bg-${rightColor}-500 hover:bg-${rightColor}-600`}
          onClick={handleNextClick}
        >
          {rightLabel}
        </button>
      </div>
    </div>
  );
}

export default Layout;

/* eslint-disable react/prop-types */

function SubmitButton({ children }) {
  return (
    <div>
      <button
        type="submit"
        className="flex w-full h-11 justify-center items-center rounded-xl bg-primary-500 px-3 py-1.5 text-sm/6 font-semibold text-white shadow-sm hover:bg-primary-400 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
      >
        {children}
      </button>
    </div>
  );
}

export default SubmitButton;

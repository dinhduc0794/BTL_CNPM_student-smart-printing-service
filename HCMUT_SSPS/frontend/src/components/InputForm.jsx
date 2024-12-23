/* eslint-disable react/prop-types */
import { Icon } from '@iconify/react';
import { useState } from 'react';

function InputForm({ id, name, type, placeholder }) {
  const [isPasswordShown, setIsPasswordShown] = useState(false);

  return (
    <div className="mt-2 relative">
      <input
        id={id}
        name={name}
        type={type === "password" ? isPasswordShown ? "text" : "password" : type}
        required
        placeholder={placeholder}
        className="px-3 block w-full h-11 rounded-xl border-none bg-gray-100 py-1.5 text-gray-900 shadow-sm ring-inset outline-none placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-primary-300 sm:text-sm/6"
      />
      {type === "password" && (
        <Icon
          onClick={() => setIsPasswordShown((prevState) => !prevState)}
          icon={
            isPasswordShown
              ? "fluent:eye-20-filled"
              : "fluent:eye-off-20-filled"
          }
          className="absolute top-1/2 right-3 transform -translate-y-1/2 text-gray-500 cursor-pointer hover:text-gray-700"
        />
      )}
    </div>

  );
}

export default InputForm;
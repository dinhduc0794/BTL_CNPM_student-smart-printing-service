import logo from '~/assets/logo.png'
import InputForm from '~/components/InputForm'
import SubmitButton from '~/components/SubmitButton'
import { Form, Link } from 'react-router-dom'

function Register() {
  return (
    <div className="flex h-screen overflow-hidden">
      <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
        <div className="sm:mx-auto sm:w-full sm:max-w-sm">
          <img
            alt="Arya logo"
            src={logo}
            className="mx-auto h-10 w-auto"
          />
          <h2 className="mt-10 text-center text-2xl/9 font-bold tracking-tight text-gray-900">
            Đăng ký tài khoản
          </h2>
        </div>

        <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          <Form method="POST" className="space-y-6">
            <div className="flex justify-between w-full">
              <div>
                <InputForm id={"name"} name={"name"} type={"text"} placeholder={"Họ và tên"} />

                <InputForm id={"faculty"} name={"faculty"} type={"text"} placeholder={"Khoa"} />

                <InputForm id={"username"} name={"username"} type={"text"} placeholder={"User name"} />

                <InputForm id={"phone"} name={"phone"} type={"phone"} placeholder={"Số điện thoại"} />
              </div>

              <div>
                <InputForm id={"email"} name={"email"} type={"email"} placeholder={"Email"} />

                <InputForm id={"password"} name={"password"} type={"password"} placeholder={"Mật khẩu"} />

                <InputForm id={"confirm-password"} name={"confirm-password"} type={"password"} placeholder={"Nhập lại mật khẩu"} />
              </div>
            </div>

            <SubmitButton>Đăng ký</SubmitButton>
          </Form>

          <p className="mt-8 text-center text-sm/6 text-gray-500">
            Đã có tài khoản?{' '}
            <Link to="/login" className="font-semibold text-primary-500 hover:text-primary-400">
              Đăng nhập
            </Link>
          </p>
        </div>
      </div>
      {/* <div className="w-full sm:w-2/3 max-w-screen-md bg-primary-100">
        <img src={login} alt="Login image" className="w-full h-auto" />
      </div> */}
    </div>
  );
}

export default Register;
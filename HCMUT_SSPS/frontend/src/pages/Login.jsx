import login from '~/assets/login-page.png'
import logo from '~/assets/logo.png'
import InputForm from '~/components/InputForm'
import SubmitButton from '~/components/SubmitButton'
import { Form, Link } from 'react-router-dom'

function Login() {
  return (
    <div className="flex h-screen overflow-hidden">
      <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
        <div className="sm:mx-auto sm:w-full sm:max-w-sm">
          <img
            src={logo}
            className="mx-auto h-10 w-auto"
          />
          <h2 className="mt-10 text-center text-2xl/9 font-bold tracking-tight text-gray-900">
            Đăng nhập
          </h2>
        </div>

        <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          <Form method="POST" className="space-y-6">
            <InputForm id={"username"} name={"username"} type={"text"} placeholder={"User name"} />

            <InputForm id={"password"} name={"password"} type={"password"} placeholder={"Mật khẩu"} />

            <div className="text-sm text-right">
              <a href="#" className="font-semibold text-primary-600 hover:text-primary-500">
                Quên mật khẩu?
              </a>
            </div>

            <SubmitButton>Đăng nhập</SubmitButton>
          </Form>

          <p className="mt-8 text-center text-sm/6 text-gray-500">
            Chưa có tài khoản?{' '}
            <Link to="/register" className="font-semibold text-primary-500 hover:text-primary-400">
              Đăng ký
            </Link>
          </p>
        </div>
      </div>
      <div className="w-full sm:w-2/3 max-w-screen-md bg-primary-100">
        <img src={login} alt="Login image" className="w-full h-auto" />
      </div>
    </div>
  );
}

export default Login;

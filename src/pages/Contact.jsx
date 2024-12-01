
import Layout from './Layout';
import callIcon from '~/assets/phonecall.png';
import emailIcon from '~/assets/email.png';
import locationIcon from '~/assets/location.png';

function Contact() {

    return (
        <div>
            <Layout
                title="Liên hệ"
                leftLabel="Trang chủ"
                leftLink="/"
                rightLabel="Bắt đầu in"
                rightLink="/printer"
            >
                <div className="min-h-[20vh] flex items-center justify-center  p-4 -mt-5">
                    <div className="w-full max-w-4xl ">
                        <p className="text-center text-gray-600 mb-6 text-[13px]">
                            Bạn có thể liên hệ trực tiếp với chúng tôi thông qua các kênh trò chuyện nhanh hoặc nếu có lỗi hay đề xuất có thể gửi cho chúng tôi thông qua biểu mẫu
                        </p>
                        <div className="grid grid-cols-1 md:grid-cols-[1.5fr_2fr] bg-white shadow-lg rounded-lg overflow-hidden">
                            <div className="bg-blue-500 text-white rounded-lg p-6">
                                <h2 className="text-2xl font-bold mb-4">Thông tin liên hệ</h2>
                                <ul>
                                    <li className="mb-2 flex items-center">
                                        <span className="mr-3">
                                            <img src={callIcon} alt="PhoneCall" className="w-5 h-5" style={{ filter: 'brightness(0) invert(1)' }} />
                                        </span>
                                        <span>09123456789</span>
                                    </li>
                                    <li className="mb-2 flex items-center">
                                        <span className="mr-3">
                                            <img src={emailIcon} alt="Email" className="w-5 h-5" style={{ filter: 'brightness(0) invert(1)' }} />
                                        </span>
                                        <span>abcxyz@gmail.com</span>
                                    </li>
                                    <li className="flex items-center">
                                        <span className="mr-3">
                                            <img src={locationIcon} alt="Location" className="w-12 h-6" style={{ filter: 'brightness(0) invert(1)' }} />
                                        </span>
                                        <span>
                                            Trường Đại học Bách khoa - ĐHQG-HCM Khu phố Tân Lập, Phường
                                            Đông Hòa, TP. Dĩ An, Tỉnh Bình Dương
                                        </span>
                                    </li>
                                </ul>
                            </div>

                            <div className="p-6">
                                <form className="space-y-4">
                                    <div className="grid grid-cols-2 gap-4">
                                        <div>
                                            <label className="block text-sm font-medium text-gray-700">Họ</label>
                                            <input
                                                type="text"
                                                className="mt-1 w-full border-b border-gray-300 p-1 "
                                            />
                                        </div>
                                        <div>
                                            <label className="block text-sm font-medium text-gray-700">Tên</label>
                                            <input
                                                type="text"
                                                className="mt-1 w-full border-b border-gray-300 p-1"
                                            />
                                        </div>
                                    </div>
                                    <div className="grid grid-cols-2 gap-4">
                                        <div>
                                            <label className="block text-sm font-medium text-gray-700">Email</label>
                                            <input
                                                type="email"
                                                className="mt-1 w-full border-b border-gray-300 p-1 "
                                            />
                                        </div>
                                        <div>
                                            <label className="block text-sm font-medium text-gray-700">Số điện thoại</label>
                                            <input
                                                type="tel"
                                                className="mt-1 w-full border-b border-gray-300 p-1 "
                                            />
                                        </div>
                                    </div>
                                    <div>
                                        <label className="block text-sm font-medium text-black-700">Nội dung</label>
                                        <div className="flex items-center space-x-4 mt-1">
                                            <label className="flex items-center">
                                                <input
                                                    type="radio"
                                                    name="content"
                                                    value="Báo lỗi"
                                                    className="h-4 w-4 text-blue-500 "
                                                />
                                                <span className="ml-2">Báo lỗi</span>
                                            </label>
                                            <label className="flex items-center">
                                                <input
                                                    type="radio"
                                                    name="content"
                                                    value="Hỗ trợ"
                                                    className="h-4 w-4 text-blue-500"
                                                />
                                                <span className="ml-2">Hỗ trợ</span>
                                            </label>
                                            <label className="flex items-center">
                                                <input
                                                    type="radio"
                                                    name="content"
                                                    value="Góp ý"
                                                    className="h-4 w-4 text-blue-500"
                                                />
                                                <span className="ml-2">Góp ý</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div>
                                        <label className="block text-sm font-medium text-gray-700">Tin nhắn</label>
                                        <textarea
                                            rows="1"
                                            className="mt-1 block w-full border-b border-gray-300  shadow-sm p-1"
                                            placeholder="Viết tin nhắn của bạn tại đây ..."
                                        ></textarea>
                                    </div>
                                    <div className="text-right">
                                        <button
                                            type="submit"
                                            className="w-[90px] text-white font-semibold py-2 px-4 rounded bg-primary-500 hover:bg-primary-600"
                                        >
                                            Gửi
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </Layout>
        </div>
    )

}
export default Contact;
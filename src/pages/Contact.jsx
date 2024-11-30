import Layout from './Layout';
import callIcon from '~/assets/phonecall.png';
import emailIcon from '~/assets/email.png';
import locationIcon from '~/assets/location.png';
import { useState } from 'react';

function Contact(){
    const [formData, setFormData] = useState({
        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        message: "",
    });
    
    const [notification, setNotification] = useState({
        message: "",
        type: "",
        visible: false,
    });
    
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };
    
    const handleSubmit = () => {
        const { firstName, lastName, email, phone } = formData;
        if (!firstName || !lastName || !email || !phone) {
            setNotification({
                message: "Vui lòng điền đầy đủ thông tin",
                type: "error",
                visible: true,
            });
            setTimeout(() => {
                setNotification({ ...notification, visible: false });
            }, 2000);
            } else {
            setNotification({
                message: "Đã gửi thành công",
                type: "success",
                visible: true,
            });
            setTimeout(() => {
                setNotification({ ...notification, visible: false });
            }, 2000);
            setFormData({
                firstName: "",
                lastName: "",
                email: "",
                phone: "",
                message: "",
            });
        }
    };
    return (
        <div>
            <Layout
                title="Liên hệ"
                leftLabel="Trang chủ"
                leftLink="/home"
                rightLabel="Bắt đầu in"
                rightLink="/printer"
            >
                <div className="min-h-[20vh] flex items-center justify-center p-4 -mt-5">
                    <div className="w-full max-w-4xl relative">
                        <p className="text-center text-gray-600 mb-6 text-[13px]">
                            Bạn có thể liên hệ trực tiếp với chúng tôi thông qua các kênh trò chuyện nhanh hoặc nếu có lỗi hay đề xuất có thể gửi cho chúng tôi thông qua biểu mẫu
                        </p>
                        {notification.visible && (
                            <div
                            className={`fixed top-4 right-4 py-2 px-4 rounded shadow-lg ${
                                notification.type === "success" ? "bg-blue-500 text-white" : "bg-red-500 text-white"
                            }`}
                            >
                            {notification.message}
                            </div>
                        )}
                        <div className="grid grid-cols-1 md:grid-cols-[1.3fr_2fr] bg-white shadow-lg rounded-lg overflow-hidden relative">
                            <div className="bg-blue-500 text-white rounded-lg p-6 relative  overflow-hidden">
                                <h2 className="text-2xl font-bold mb-4">Thông tin liên hệ</h2>
                                <ul>
                                    <li className="mb-2 flex items-center">
                                        <span className="mr-3">
                                            <img src={callIcon} alt="PhoneCall" className="w-5 h-5 invert" />
                                        </span>
                                        <span>09123456789</span>
                                    </li>
                                    <li className="mb-2 flex items-center">
                                        <span className="mr-3">
                                            <img src={emailIcon} alt="Email" className="w-5 h-5 invert" />
                                        </span>
                                        <span>abcxyz@gmail.com</span>
                                    </li>
                                    <li className="flex items-center">
                                        <span className="mr-3">
                                            <img src={locationIcon} alt="Location" className="w-12 h-6 invert" />
                                        </span>
                                        <span>
                                            Trường Đại học Bách khoa - ĐHQG-HCM Khu phố Tân Lập, Phường Đông Hòa, TP. Dĩ An, Tỉnh Bình Dương
                                        </span>
                                    </li>
                                </ul>
                                <div className="flex justify-start space-x-2 mt-4  absolute bottom-4 left-6">
                                    <span className="w-4 h-4 bg-black rounded-full"></span>
                                    <span className="w-4 h-4 bg-white rounded-full"></span>
                                    <span className="w-4 h-4 bg-black rounded-full"></span>
                                </div>
                                <div >
                                    <div className="absolute w-28 h-28 bg-blue-600  rounded-full -bottom-5 -right-5"></div>
                                    <div className="w-14 h-14 bg-gray-700 opacity-45 rounded-full absolute bottom-12 right-12"></div>
                                </div>
                            </div>
                            <div className="p-6">
                                <form className="space-y-4">
                                    <div className="grid grid-cols-2 gap-4">
                                        <div>
                                            <label className="block text-sm font-medium text-gray-500">Họ</label>
                                            <input type="text" 
                                                name="firstName"
                                                value={formData.firstName}
                                                onChange={handleChange}
                                                className="mt-1 w-full border-b border-gray-500 p-1" />
                                        </div>
                                        <div>
                                            <label className="block text-sm font-medium text-gray-500">Tên</label>
                                            <input type="text"
                                                name="lastName"
                                                value={formData.lastName}
                                                onChange={handleChange}
                                                className="mt-1 w-full border-b border-gray-500 p-1" />
                                        </div>
                                    </div>
                                    <div className="grid grid-cols-2 gap-4">
                                        <div>
                                            <label className="block text-sm font-medium text-gray-500">Email</label>
                                            <input type="email"
                                                name="email"
                                                value={formData.email}
                                                onChange={handleChange}
                                                className="mt-1 w-full border-b border-gray-500 p-1" />
                                        </div>
                                        <div>
                                            <label className="block text-sm font-medium text-gray-500">Số điện thoại</label>
                                            <input type="tel"
                                                name="phone"
                                                value={formData.phone}
                                                onChange={handleChange}
                                                className="mt-1 w-full border-b border-gray-500 p-1" />
                                        </div>
                                    </div>
                                    <div>
                                        <label className="block text-sm font-medium text-black-700">Nội dung</label>
                                        <div className="flex items-center space-x-4 mt-1">
                                            <label className="flex items-center">
                                                <input type="radio" name="content" value="Báo lỗi" className="h-4 w-4 text-blue-500" />
                                                <span className="ml-2">Báo lỗi</span>
                                            </label>
                                            <label className="flex items-center">
                                                <input type="radio" name="content" value="Hỗ trợ" className="h-4 w-4 text-blue-500" />
                                                <span className="ml-2">Hỗ trợ</span>
                                            </label>
                                            <label className="flex items-center">
                                                <input type="radio" name="content" value="Góp ý" className="h-4 w-4 text-blue-500" />
                                                <span className="ml-2">Góp ý</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div>
                                        <label className="block text-sm font-medium text-gray-700">Tin nhắn</label>
                                        <textarea
                                            name="message"
                                            value={formData.message}
                                            onChange={handleChange}
                                            rows="1" 
                                            className="mt-1 block w-full border-b border-gray-300 shadow-sm p-1" 
                                            placeholder="Viết tin nhắn của bạn tại đây ...">
                                        </textarea>
                                    </div>
                                    <div className="text-right">
                                        <button type="button"
                                            onClick={handleSubmit} 
                                            className="w-[90px] text-white font-semibold py-2 px-4 rounded bg-blue-500 hover:bg-blue-600">
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

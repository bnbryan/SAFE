import { Button, Form, Input, message, Modal } from 'antd'
import React, { useState } from 'react';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import {getIdByEmail, login} from '../utils'



function Login({ onSuccess }) {
    const [displayModal, setDisplayModal] = useState(false)


    const handleCancel = () => {
        setDisplayModal(false)
    }


    const signinOnClick = () => {
        setDisplayModal(true)
    }


    const onFinish = (data) => {
        console.log(data)
        login(data)
            .then(() => {
                getIdByEmail(data.cemail).then((userInfo) => {
                    setDisplayModal(false); // 关闭模态框
                    message.success('Welcome back'); // 显示欢迎信息
                    console.log(userInfo);
                    onSuccess(data.cemail, userInfo.customer.cid); // 调用 onSuccess
                }).catch((err) => {
                    message.error('Error fetching account data: ' + err.message); // 错误处理
                });
            }).catch((err) => {
            message.error(err.message)
        })

    }


    return (
        <>
            <Button shape="round" onClick={signinOnClick} style={{ marginRight: '20px' }}>
                Login
            </Button>
            <Modal
                title="Log in"
                visible={displayModal}
                onCancel={handleCancel}
                footer={null}
                destroyOnClose={true}
            >
                <Form
                    name="normal_login"
                    onFinish={onFinish}
                    preserve={false}
                >
                    <Form.Item
                        name="cemail"
                        rules={[{ required: true, message: 'Please input your email!' }]}
                    >
                        <Input prefix={<UserOutlined />} placeholder="Username" />
                    </Form.Item>
                    <Form.Item
                        name="cpassword"
                        rules={[{ required: true, message: 'Please input your password!' }]}
                    >
                        <Input.Password
                            prefix={<LockOutlined />}
                            placeholder="Password"
                        />
                    </Form.Item>
                    <Form.Item>
                        <Button type="primary" htmlType="submit">
                            Login
                        </Button>
                    </Form.Item>
                </Form>
            </Modal>
        </>
    )
}


export default Login
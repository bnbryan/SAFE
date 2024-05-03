import { Button, Form, Input, message, Modal } from 'antd'
import React, { useState } from 'react';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import {adminLogin, login} from '../utils'


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
        adminLogin(data)
            .then(() => {
                setDisplayModal(false)
                onSuccess()
                message.success(`Welcome back`)
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
                        name="username"
                        rules={[{ required: true, message: 'Please input your admin account!' }]}
                    >
                        <Input prefix={<UserOutlined />} placeholder="Admin account" />
                    </Form.Item>
                    <Form.Item
                        name="password"
                        rules={[{ required: true, message: 'Please input your password!' }]}
                    >
                        <Input.Password
                            prefix={<LockOutlined />}
                            placeholder="Admin password"
                        />
                    </Form.Item>
                    <Form.Item>
                        <Button type="primary" htmlType="submit">
                            ADMIN
                        </Button>
                    </Form.Item>
                </Form>
            </Modal>
        </>
    )
}


export default Login
import { Button, Form, Input, message, Modal } from 'antd';
import React, { useState } from 'react';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { register } from '../utils';


function Register() {
    const [displayModal, setDisplayModal] = useState(false)


    const handleCancel = () => {
        setDisplayModal(false)
    }


    const signupOnClick = () => {
        setDisplayModal(true)
    }


    const onFinish = (values) => {
        //data.append('valid','1')
        const formData = new FormData();
        Object.keys(values).forEach(key => {
            formData.append(key, values[key]);
        });
        formData.append('valid', 1); // 添加额外的数据
        console.log(Object.fromEntries(formData)); // 查看formData的内容
       register(Object.fromEntries(formData))
            .then(() => {
                setDisplayModal(false)
                message.success('Successfully signed up');
            }).catch((err) => {
            message.error(err.message);
        })
    }


    return (
        <>
            <Button shape="round" type="primary" onClick={signupOnClick}>
                Register</Button>
            <Modal
                title="Register"
                visible={displayModal}
                onCancel={handleCancel}
                footer={null}
                destroyOnClose={true}
            >
                <Form
                    name="normal_register"
                    initialValues={{ remember: true }}
                    onFinish={onFinish}
                    preserve={false}
                >
                    <Form.Item
                        name="cemail"
                        rules={[
                            {
                                type: 'email',
                                message: 'The input is not a valid email!',
                            },
                            {
                                required: true,
                                message: 'Please input your email!',
                            },
                        ]}
                    >
                        <Input prefix={<UserOutlined />} placeholder="Email" />
                    </Form.Item>
                    <Form.Item
                        name="cpassword"
                        rules={[{ required: true, message: 'Please input your Password!' }]}
                        hasFeedback
                    >
                        <Input.Password prefix={<LockOutlined />} placeholder="Password" />
                    </Form.Item>
                    <Form.Item
                        name="confirm"
                        dependencies={['cpassword']}
                        hasFeedback
                        rules={[
                            {
                                required: true,
                                message: 'Please confirm your password!',
                            },
                            ({ getFieldValue }) => ({
                                validator(_, value) {
                                    if (!value || getFieldValue('cpassword') === value) {
                                        return Promise.resolve();
                                    }
                                    return Promise.reject(new Error('The two passwords that you entered do not match!'));
                                },
                            }),
                        ]}
                    >
                        <Input.Password prefix={<LockOutlined />} placeholder="Confirm Password" />
                    </Form.Item>
                    <Form.Item
                        name="clname"
                        rules={[{ required: true, message: 'Please input your Firstname!' }]}
                    >
                        <Input
                            placeholder="firstname"
                        />
                    </Form.Item>
                    <Form.Item
                        name="cfname"
                        rules={[{ required: true, message: 'Please input your Lastname!' }]}
                    >
                        <Input
                            placeholder="lastname"
                        />
                    </Form.Item>
                    <Form.Item
                        name="securityQuestion"
                        rules={[{ required: true, message: 'Please input your security question!' }]}
                    >
                        <Input placeholder = "security question"
                        />
                    </Form.Item>
                    <Form.Item
                        name="answer"
                        rules={[{ required: true, message: 'Please input your security question!' }]}
                    >
                        <Input placeholder = "answer"/>
                    </Form.Item>
                    <Form.Item>
                        <Button type="primary" htmlType="submit">
                            Register
                        </Button>
                    </Form.Item>
                </Form>
            </Modal>
        </>


    )
}


export default Register;
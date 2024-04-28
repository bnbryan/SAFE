import React, {useState} from "react";
import {Button, Form, Input, message, Modal, Select} from "antd";
import {LockOutlined, UserOutlined} from "@ant-design/icons";
import {passwordReset} from '../utils'


function PasswordReset({securityQuestions}){
    const { Option } = Select;
    const [displayModal, setDisplayModal] = useState(false)


    const handleCancel = () => {
        setDisplayModal(false)
    }
    const onFinish = (data) => {

        console.log(data)
        passwordReset(data)
            .then(() => {
                setDisplayModal(false)
                message.success(`Reset Success!`)
            }).catch((err) => {
            message.error(err.message)
        })
    }

    const signinOnClick = () => {
        setDisplayModal(true)
    }
    return(
    <>
        <Button shape="round" onClick={signinOnClick} style={{ marginRight: '20px' }}>
            Password Reset
        </Button>
        <Modal
            title="password reset"
            visible={displayModal}
            onCancel={handleCancel}
            footer={null}
            destroyOnClose={true}
        >
            <Form
                name="passreset"
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
                    rules={[{ required: true, message: 'Please input your new password!' }]}
                >
                    <Input.Password
                        prefix={<LockOutlined />}
                        placeholder="Password"
                    />
                </Form.Item>
                <Form.Item
                    name="securityQuestion"
                    rules={[{ required: true, message: 'Please input your security question!' }]}
                >
                    <Select
                        placeholder="Select a security question"
                        style={{ width: `100%` }}
                        allowClear
                    >
                        {securityQuestions.map((question, index) => (
                            <Option key={index} value={question}>{question}</Option>
                        ))}
                    </Select>
                </Form.Item>
                <Form.Item
                    name="answer"
                    rules={[{ required: true, message: 'Please input your security question answer!' }]}
                >
                    <Input.Password
                        prefix={<LockOutlined />}
                        placeholder="answer"
                    />
                </Form.Item>

                <Form.Item>
                    <Button type="primary" htmlType="submit">
                        Reset
                    </Button>
                </Form.Item>
            </Form>
        </Modal>
    </>
    )


}
export default PasswordReset
import React, {useState} from "react";
import {Button, Form, Input, message, Modal} from "antd";
import {LockOutlined, UserOutlined} from "@ant-design/icons";
import {passwordReset} from '../utils'


function WithdrawForm(){
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
                        <Input.Password
                            prefix={<LockOutlined />}
                            placeholder="security question"
                        />
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
        </>
    )


}
export default WithdrawForm
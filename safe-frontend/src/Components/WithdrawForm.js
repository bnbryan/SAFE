import React, {useState} from "react";
import {Button, Form, Input, message, Modal, Select} from "antd";
import {LockOutlined, UserOutlined,CreditCardOutlined,DollarOutlined} from "@ant-design/icons";
import {withdraw} from '../utils'


function WithdrawForm(){
    const [displayModal, setDisplayModal] = useState(false)

    const [form] = Form.useForm();
    const options=[
        {
            value: 'C',
            label: 'Checking Account',
        },
        {
            value: 'S',
            label: 'Saving Account'
        }
    ]
    const onFinish = (data) => {

        console.log(data)
        withdraw(data)
            .then(() => {
                setDisplayModal(false)
                message.success(`withdraw Success!`)
            }).catch((err) => {
            message.error(err.message)
        })
        form.resetFields();
    }

    const signinOnClick = () => {
        setDisplayModal(true)
    }
    return(
        <>
                <Form
                    name="withdraw"
                    onFinish={onFinish}
                    preserve={false}
                >

                    <Form.Item
                        name="anum"
                        rules={[{ required: true, message: 'Please input your account number!' }]}
                    >
                        <Input prefix={<CreditCardOutlined />} placeholder="accountnumber" />
                    </Form.Item>
                    <Form.Item
                        name="atype"
                        rules={[{ required: true, message: 'Please select your account type!' }]}
                    >
                        <Select
                            style={{
                                width: '100%',
                            }}
                            placeholder="Tags Mode"
                            options={options}
                        />
                    </Form.Item>
                    <Form.Item
                        name="abalance"
                        rules={[{ required: true, message: 'Please input your withdraw amount!' }]}
                    >
                        <Input
                            prefix={<DollarOutlined />}
                            placeholder="withdraw amount"
                        />
                    </Form.Item>
                    <Form.Item>
                        <Button type="primary" htmlType="submit">
                            withdraw
                        </Button>
                    </Form.Item>
                </Form>
        </>
    )


}
export default WithdrawForm
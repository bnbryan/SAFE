import React, {useState} from "react";
import {Button, Form, Input, message, Modal, Select} from "antd";
import {LockOutlined, UserOutlined,CreditCardOutlined,DollarOutlined} from "@ant-design/icons";
import {transfer} from '../utils'


function TransferForm(){
    const [form] = Form.useForm();
    const handleChange = (value) => {
        console.log(`selected ${value}`);
    };
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
        transfer(data)
            .then(() => {
                message.success(`Transfer Success!`)
            }).catch((err) => {
            message.error(err.message)
        })
        form.resetFields();
    }

    return(
        <>
                <Form
                    name="transfer"
                    onFinish={onFinish}
                    preserve={false}
                >
                    <Form.Item
                        name="fromAccountNum"
                        rules={[{ required: true, message: 'Please input your account number!' }]}
                    >
                        <Input prefix={<CreditCardOutlined />} placeholder="Account number" />
                    </Form.Item>
                    <Form.Item
                        name="fromAccountType"
                        rules={[{ required: true, message: 'Please select your account type!' }]}
                    >
                        <Select
                            style={{
                                width: '100%',
                            }}
                            placeholder="Tags Mode"
                            onChange={handleChange}
                            options={options}
                        />
                    </Form.Item>
                    <Form.Item
                        name="toAccountNum"
                        rules={[{ required: true, message: 'Please input the account number your send!' }]}
                    >
                        <Input
                            prefix={<CreditCardOutlined />}
                            placeholder="to account"
                        />
                    </Form.Item>
                    <Form.Item
                        name="amount"
                        rules={[{ required: true, message: 'Please input your transfer amount!' }]}
                    >
                        <Input
                            prefix={<DollarOutlined />}
                            placeholder="amount"
                        />
                    </Form.Item>

                    <Form.Item>
                        <Button type="primary" htmlType="submit">
                            Transfer
                        </Button>
                    </Form.Item>
                </Form>
        </>
    )


}
export default TransferForm
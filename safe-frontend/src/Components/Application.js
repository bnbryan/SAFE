import React, { useEffect, useState } from "react";
import {Button, Form, Input, InputNumber, message, Select} from "antd";
import { postApplication } from '../utils';
import {DollarOutlined} from "@ant-design/icons";  // Assuming corrected spelling from 'postAllication' to 'postAllocation'

function Application({ accountID }) {
    const [form] = Form.useForm();

    useEffect(() => {
        // 自动填充表单中的 CID
        form.setFieldsValue({ cid: accountID });
    }, [form, accountID]);

    const onFinish = (data) => {
        console.log(JSON.stringify(data))
        postApplication(data)  // Make sure this is correctly spelled in your imports and usage
            .then(() => {
                message.success(`Submit Success!`);
                form.resetFields();  // Move reset inside success callback to ensure form resets only on successful submission
            }).catch((err) => {
            message.error(`Submission failed: ${err.message}`);
        });
    };

    return (
        <Form
            form={form}
            name="application"
            onFinish={onFinish}
            layout="vertical"
        >
            <Form.Item
                name="cid"
                noStyle
            >
                <Input type="hidden" />
            </Form.Item>
            <Form.Item
                name="type"
                label="Account Type"
                rules={[{ required: true, message: 'Please select your account type!' }]}
            >
                <Select placeholder="Select a type">
                    <Select.Option value='C'>Checking</Select.Option>
                    <Select.Option value='S'>Saving</Select.Option>
                    <Select.Option value='L'>Loaning</Select.Option>
                </Select>
            </Form.Item>

            <Form.Item
                name="income"
                label="Income"
                rules={[{ required: true, message: 'Please input your income!' }]}
            >
                <InputNumber
                    prefix={<DollarOutlined />}
                    style={{ width: '100%' }}
                    min={0}  // 设置最小值为0
                    precision={2}  // 允许两位小数
                />
            </Form.Item>

            <Form.Item
                name="career"
                label="Career"
                rules={[{ required: true, message: 'Please input your career!' }]}
            >
                <Input />
            </Form.Item>

            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Submit Application
                </Button>
            </Form.Item>
        </Form>
    );
}

export default Application;
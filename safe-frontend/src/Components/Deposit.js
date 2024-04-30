import React, { useEffect, useState } from "react";
import { Button, Form, Input, message, Select } from "antd";
import { DollarOutlined } from "@ant-design/icons";
import {allAccount, deposit, withdraw} from '../utils';

function DepositForm({ accountEmail }) {
    const [form] = Form.useForm();
    const [accounts, setAccounts] = useState([]);

    useEffect(() => {
        async function fetchAccounts() {
            try {
                const result = await allAccount(accountEmail);
                setAccounts(result.data.map(account => ({
                    label: `${account.aname} (${account.atype === 'C' ? 'Checking' : 'S' ? 'Saving' : 'Loan'})`,
                    value: account.anum,
                })));
            } catch (err) {
                message.error('Error fetching account data: ' + err.message);
            }
        }

        fetchAccounts();
    }, [accountEmail]);

    const onFinish = async (values) => {
        try {
            await deposit(values);
            message.success('Deposit successful!');
            form.resetFields();
        } catch (err) {
            message.error(err.message);
        }
    };

    return (
        <Form
            form={form}
            name="deposit"
            onFinish={onFinish}
            layout="vertical"
        >
            <Form.Item
                name="accountNum"
                label="to Account"
                rules={[{ required: false, message: 'Please select the account from which to deposit!' }]}
            >
                <Select
                    placeholder="Select account"
                    options={accounts}
                />
            </Form.Item>
            <Form.Item
                name="amount"
                label="Amount"
                rules={[{ required: false, message: 'Please input the amount to deposit!' }]}
            >
                <Input
                    prefix={<DollarOutlined />}
                    placeholder="Amount"
                />
            </Form.Item>
            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Deposit
                </Button>
            </Form.Item>
        </Form>
    );
}

export default DepositForm;

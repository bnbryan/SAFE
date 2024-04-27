import React, { useEffect, useState } from "react";
import { Button, Form, Input, message, Select } from "antd";
import { DollarOutlined } from "@ant-design/icons";
import { allAccount, withdraw } from '../utils';

function WithdrawForm({ accountEmail }) {
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
            await withdraw(values);
            message.success('Withdrawal successful!');
            form.resetFields();
        } catch (err) {
            message.error(err.message);
        }
    };

    return (
        <Form
            form={form}
            name="withdraw"
            onFinish={onFinish}
            layout="vertical"
        >
            <Form.Item
                name="anum"
                label="From Account"
                rules={[{ required: false, message: 'Please select the account from which to withdraw!' }]}
            >
                <Select
                    placeholder="Select account"
                    options={accounts}
                />
            </Form.Item>
            <Form.Item
                name="abalance"
                label="Amount"
                rules={[{ required: false, message: 'Please input the amount to withdraw!' }]}
            >
                <Input
                    prefix={<DollarOutlined />}
                    placeholder="Amount"
                />
            </Form.Item>
            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Withdraw
                </Button>
            </Form.Item>
        </Form>
    );
}

export default WithdrawForm;

import React, { useEffect, useState } from "react";
import {Button, Form, Input, InputNumber, message, Select} from "antd";
import { DollarOutlined } from "@ant-design/icons";
import {allAccount, deposit, withdraw} from '../utils';

function DepositForm({ accountEmail }) {
    const [form] = Form.useForm();
    const [accounts, setAccounts] = useState([]);
    const accountTypeMap = {
        'S': 'Saving',
        'L': 'Loan',
        'C': 'Checking',
    };

    useEffect(() => {
        async function fetchAccounts() {
            try {
                const result = await allAccount(accountEmail);
                if (result.data && Array.isArray(result.data)) {
                    // Combine all accounts into one array
                    const allAccounts = result.data.map(account => ({
                        ...account,
                        label: `${account.aname} - ${accountTypeMap[account.atype]}`,
                        value: account.anum,
                    }));
                    setAccounts(allAccounts); // Set combined accounts
                } else {
                    throw new Error('Data is not in expected format');
                }
            } catch (err) {
                message.error('Error fetching account data: ' + err.message);
            }
        }
        fetchAccounts();
    }, [accountEmail]);


    const onFinish = async (values) => {
        try {
            console.log(values)
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

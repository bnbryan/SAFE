import React, { useEffect, useState } from "react";
import { Button, Form, Input, message, Select } from "antd";
import { CreditCardOutlined, DollarOutlined } from "@ant-design/icons";
import { allAccount, transfer } from '../utils';

function TransferForm({ accountEmail }) {
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

    const onFinish = (data) => {
        transfer(data)
            .then(() => {
                message.success(`Transfer Success!`);
            }).catch((err) => {
            message.error(err.message);
        });
        form.resetFields();
    };

    return (
        <Form
            form={form}
            name="transfer"
            onFinish={onFinish}
            preserve={false}
            layout="vertical"
        >
            <Form.Item
                name="fromAccountNum"
                label="From Account"
                rules={[{ required: false, message: 'Please select your account!' }]}
            >
                <Select
                    placeholder="Select Account"
                    options={accounts}
                    onChange={(value) => {
                        // Find the selected account
                        const account = accounts.find(acc => acc.value === value);
                        // Set the type field in the form based on the selected account
                        form.setFieldsValue({ fromAccountType: account ? account.atype : null });
                    }}
                />
            </Form.Item>
            <Form.Item
                shouldUpdate={(prevValues, currentValues) => prevValues.fromAccountType !== currentValues.fromAccountType}
                noStyle
            >
                {() => (
                    <Form.Item
                        name="fromAccountType"
                        rules={[{ required: true, message: 'Please input your account type!' }]}
                        hidden
                    >
                        <Input
                            prefix={<CreditCardOutlined />}
                            placeholder="Account type"
                            disabled
                            value={accountTypeMap[form.getFieldValue('fromAccountType')] || ''}
                        />
                    </Form.Item>
                )}
            </Form.Item>
            <Form.Item
                name="toAccountNum"
                label="To Account"
                rules={[{ required: false, message: 'Please input your receiver account number !' }]}
            >
                <Input
                    prefix={<CreditCardOutlined />}
                    placeholder="receiver account number"
                />
            </Form.Item>

            <Form.Item
            name="amount"
            label="Transfer Amount"
            rules={[{ required: false, message: 'Please input your transfer amount!' }]}
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
    );
}

export default TransferForm;


import React, { useEffect, useState } from "react";
import { Button, Form, Input, message, Select } from "antd";
import { DollarOutlined } from "@ant-design/icons";
import { allAccount, withdraw } from '../utils';

function WithdrawForm({ accountEmail }) {
    const [form] = Form.useForm();
    const [accounts, setAccounts] = useState([]);
    const accountTypeMap = {
        'S': 'Saving',
        'L': 'Loan',
        'C': 'Checking',
    };
/*
have some bug to fix
 */
    useEffect(() => {
        async function fetchAccounts() {
            try {
                const result = await allAccount(accountEmail);
                if (result.data && Array.isArray(result.data)) {
                    // 先使用 filter 函数移除 atype 为 'H' 的账户
                    const filteredAccounts = result.data.filter(account => account.atype !== 'L');

                    // 然后将剩余账户组合成一个新数组
                    const allAccounts = filteredAccounts.map(account => ({
                        ...account,
                        label: `${account.aname} - ${accountTypeMap[account.atype]}`,
                        value: account.anum,
                    }));
                    setAccounts(allAccounts); // 设置组合后的账户数组
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
            await withdraw(values);
            message.success('Withdraw successful!');
            form.resetFields();
        } catch (err) {
            message.error(err.message);
        }
    };
    const onAccountChange = (anum) => {
        const account = accounts.find(acc => acc.value === anum);
        form.setFieldsValue({ atype: account ? account.atype : '' });
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
                    onChange={onAccountChange}
                />
            </Form.Item>
            <Form.Item
                name="atype"
                noStyle
            >
                <Input type="hidden" />
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

import React, { useEffect, useState } from "react";
import {Button, DatePicker, Form, Input, message, Select} from "antd";

import {postLoanApplication} from '../utils';
const { Option } = Select;
function AppLoan({ accountID }) {
    const [type, setType] = useState('')
    const [form] = Form.useForm();
    const companyNames = [
        "SecureInsure",
        "HouseSafe",
        "PropertyProtect",
        "HomeCare",
        "TrustAssure",
        "SecureLive",
        "HouseCover",
        "AssetShield",
        "FirstSafety",
        "ProtectFirst"
    ];
    const onTypeChange = (value) => {
        setType(value);
        if (value === 'H') {
            // 选 'H' 时禁用学生相关字段
            form.setFieldsValue({
                stuid: null,
                stutype: null,
                stugraddate: null,
                uname: null
            });
        } else if (value === 'S') {
            // 选 'S' 时禁用家庭相关字段
            form.setFieldsValue({
                insurance: null,
                laiaaccount: null,
                lacomname: null,
                premium: null
            });
        }
    };
    useEffect(() => {
        // 自动填充表单中的 CID
        form.setFieldsValue({ cid: accountID,
            lavalid: null, });
    }, [form, accountID]);

    const onFinish = (data) => {
        console.log(JSON.stringify(data))
        postLoanApplication(data)  // Make sure this is correctly spelled in your imports and usage
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
            name="loanApplication"
            onFinish={onFinish}
            layout="vertical"
        >
            <Form.Item name="cid" noStyle>
                <Input type="hidden" />
            </Form.Item>
            <Form.Item name="lamount" label="Loan amount">
                <Input  />
            </Form.Item>
            <Form.Item name="lmonths" label="Loan mouth" >
                <Input  />
            </Form.Item>
            <Form.Item name="lavalid" noStyle>
                <Input type="hidden" />
            </Form.Item>
            <Form.Item name="ltype" label="type" disabled={type === 'S'}>
                <Select onChange={onTypeChange} disabled={type === 'S'}>
                    <Option value="H">House</Option>
                    <Option value="S">Student</Option>
                </Select>
            </Form.Item>
            <Form.Item label="Insurance" name="hinsurance" disabled={type === 'S'}>
                <Input disabled={type === 'S'} />
            </Form.Item>
            <Form.Item label="Insurance Account" name="laiaccount" disabled={type === 'S'}>
                <Input disabled={type === 'S'} />
            </Form.Item>
            <Form.Item name="lacomname" label="Company">
                <Select
                    style={{ width: 200 }}
                    placeholder="Select a company"
                    disabled={type === 'S'}
                    // 可以添加 onChange 事件处理函数来处理选项变化
                >
                    {companyNames.map(name => (
                        <Option key={name} value={name}>
                            {name}
                        </Option>
                    ))}
                </Select>
            </Form.Item>
            <Form.Item label="House date" name="hyear" disabled={type === 'S'}>
                <DatePicker disabled={type === 'S'}
                            showTime={{ format: 'HH:mm' }} // 显示时间选择器，具体时间格式
                            format="YYYY-MM-DDTHH:mm:ss" // 定义日期时间的格式
                            style={{ width: '100%' }}
                />
            </Form.Item>

            <Form.Item label="Premium" name="ipremium" disabled={type === 'S'}>
                <Input disabled={type === 'S'} />
            </Form.Item>

            <Form.Item label="Study Type" name="stutype" disabled={type === 'H'}>
                <Input disabled={type === 'H'} />
            </Form.Item>
            <Form.Item label="Study id" name="stuid" disabled={type === 'H'}>
                <Input disabled={type === 'H'} />
            </Form.Item>
            <Form.Item label="Study Grad Date" name="stugraddate" disabled={type === 'H'}>
                <DatePicker disabled={type === 'H'}
                    showTime={{ format: 'HH:mm' }} // 显示时间选择器，具体时间格式
                    format="YYYY-MM-DDTHH:mm:ss" // 定义日期时间的格式
                    style={{ width: '100%' }}
                />
            </Form.Item>
            <Form.Item label="Username" name="uname" disabled={type === 'H'}>
                <Input disabled={type === 'H'} />
            </Form.Item>
            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Submit Application
                </Button>
            </Form.Item>
        </Form>
    );
}

export default AppLoan;
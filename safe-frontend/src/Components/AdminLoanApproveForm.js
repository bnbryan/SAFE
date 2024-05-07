import React, {useEffect, useState} from "react";
import {adminApprove, adminLoanApprove} from "../utils";
import { Button, Form, Input, message, Modal } from "antd";
import moment from "moment";

function AdminLoanApproveForm({ laid,displayModal,setDisplayModal}) {

    const [form] = Form.useForm();

    const handleApproveCancel = () => {
        setDisplayModal(false);
    };

    const handleFormSubmit = async (values) => {
        console.log(values)
        try {
            await adminLoanApprove({
                ...values,
               laid:laid
            });
            message.success("Approved successfully");
            setDisplayModal(false);
        } catch (error) {
            message.error(error.message);
        }
        form.resetFields()

    };

    return (
        <>
            <Modal title="Approve Application"
                   visible={displayModal}
                   onCancel={handleApproveCancel}
                   footer={null}
            >
                <Form form={form} onFinish={handleFormSubmit}>
                    <Form.Item
                        name="lrate"
                        rules={[
                            {
                                message: "Please input the loan rate!",
                            },
                        ]}
                    >
                        <Input
                               placeholder="loan rate"
                        />
                    </Form.Item>
                    <Form.Item name="laid" noStyle>
                        <Input type="hidden"></Input>
                    </Form.Item>
                    <Form.Item>
                        <Button
                            type="primary"
                            htmlType="submit"
                            style={{ width: "100%" }}
                        >
                            Approve
                        </Button>
                    </Form.Item>
                </Form>
            </Modal>
        </>
    );
}

export default AdminLoanApproveForm;

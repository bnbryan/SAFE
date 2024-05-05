import React, { useState } from "react";
import { adminApprove } from "../utils";
import { Button, Form, Input, message, Modal } from "antd";
import moment from "moment";

function AdminApproveForm({ appId, cid, type }) {
    const [displayModal, setDisplayModal] = useState(false);
    const [form] = Form.useForm();

    const handleApproveOnClick = () => {
        // 在显示模态框之前设置初始值
        form.setFieldsValue({
            adate: Date.now(),
            appId: appId,
            type: type,
            cid: cid
        });
        setDisplayModal(true);
    };

    const handleApproveCancel = () => {
        setDisplayModal(false);
    };

    const handleFormSubmit = async (values) => {
        try {
            await adminApprove({
                ...values,
                appId: appId,
                type: type,
                cid: cid
            });
            message.success("Approved successfully");
            setDisplayModal(false);
        } catch (error) {
            message.error(error.message);
        }
    };

    return (
        <>
            <Button onClick={handleApproveOnClick}
                    type="link"
                    style={{ padding: 0 }}>
                Approve
            </Button>
            <Modal title="Approve Application"
                   visible={displayModal}
                   onCancel={handleApproveCancel}
                   footer={null}
            >
                <Form form={form} onFinish={handleFormSubmit}>
                    <Form.Item
                        name="ccharge"
                        rules={[
                            {
                                required: true,
                                message: "Please input the charge rate!",
                            },
                        ]}
                    >
                        <Input
                            placeholder="Charge rate"
                        />
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

export default AdminApproveForm;

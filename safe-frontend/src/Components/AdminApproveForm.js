import React, {useEffect, useState} from "react";
import { adminApprove } from "../utils";
import { Button, Form, Input, message, Modal } from "antd";
import moment from "moment";

function AdminApproveForm({ appId, cid, type,displayModal,setDisplayModal}) {

    const [form] = Form.useForm();
    useEffect(() => {
        if (type === 'C') {
            // 选 'H' 时禁用学生相关字段
            form.setFieldsValue({
               srate:null
            });
        } else if (type === 'S') {
            // 选 'S' 时禁用家庭相关字段
            form.setFieldsValue({
                ccharge:null
            });
        }
    }, []);


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
        console.log(values)
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
            <Modal title="Approve Application"
                   visible={displayModal}
                   onCancel={handleApproveCancel}
                   footer={null}
            >
                <Form form={form} onFinish={handleFormSubmit}>
                    <Form.Item
                        disabled={type === 'S'}
                        name="ccharge"
                        rules={[
                            {
                                message: "Please input the charge rate!",
                            },
                        ]}
                    >
                        <Input disabled= {type === 'S'}
                            placeholder="Charge rate"
                        />
                    </Form.Item>
                    <Form.Item
                        disabled={type === 'C'}
                        name="srate"
                        rules={[
                            {
                                message: "Please input the saving rate!",
                            },
                        ]}
                    >
                        <Input disabled= {type === 'C'}
                            placeholder="Saving rate"
                        />
                    </Form.Item>
                    <Form.Item name="adate" noStyle>
                        <Input type="hidden"></Input>
                    </Form.Item>
                    <Form.Item name="appId" noStyle></Form.Item>
                    <Input type="hidden"></Input>
                    <Form.Item name="cid" noStyle></Form.Item>
                    <Input type="hidden"></Input>
                    <Form.Item name="type" noStyle></Form.Item>
                    <Input type="hidden"></Input>

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

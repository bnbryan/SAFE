import React, { useEffect, useState } from 'react';
import { Table, Button, message, Modal } from 'antd';
import {adminGetApp, adminReject, allAccount, allApplication} from "../utils";
import AdminApproveForm from './AdminApproveForm';

function ReviewApplication() {
    const [applications, setApplications] = useState([]);
    const [isModalVisible, setIsModalVisible] = useState(false);
    const [currentApplication, setCurrentApplication] = useState(null);

    useEffect(() => {
        async function fetchApplications() {
            try {
                const result = await adminGetApp();
                setApplications(result.data);
            } catch (err) {
                message.error('Error fetching account data: ' + err.message);
            }
        }
        fetchApplications();
    }, []);
    useEffect(() => {
        if (currentApplication) {
            setIsModalVisible(true);
        }
    }, [currentApplication]);

    const handleClickDeny = async (appId) => {
        try {
            await adminReject({ appId });
            message.success("Application rejected successfully");
            setApplications(applications.filter(app => app.appId !== appId));
        } catch (err) {
            message.error('Error rejecting application: ' + err.message);
        }
    };

    const handleApprove = (app) => {
        console.log(app)
        setCurrentApplication(app);
        setIsModalVisible(true);
    };

    const handleCloseModal = () => {
        setIsModalVisible(false);
    };

    const columns = [
        { title: 'App ID', dataIndex: 'appId', key: 'appId' },
        { title: 'Customer ID', dataIndex: 'cid', key: 'cid' },
        { title: 'Type', dataIndex: 'type', key: 'type' },
        { title: 'Income', dataIndex: 'income', key: 'income' },
        { title: 'Career', dataIndex: 'career', key: 'career' },
        { title: 'Status', dataIndex: 'status', key: 'status', render: text => text || 'Pending' },
        {
            title: 'Actions',
            key: 'actions',
            render: (_, record) => (
                <>
                    <Button type="link" onClick={() => handleApprove(record)}>Approve</Button>
                    <Button type="link" onClick={() => handleClickDeny(record.appId)}>Deny</Button>
                </>
            )
        },
    ];

    return (
        <>
            <Table dataSource={applications} columns={columns} rowKey="appId" />

                {currentApplication && (
                    <AdminApproveForm
                        appId={currentApplication.appId}
                        cid={currentApplication.cid}
                        type={currentApplication.type}
                        displayModal={isModalVisible}
                        setDisplayModal={setIsModalVisible}
                    />
                )}
        </>
    );
}

export default ReviewApplication;

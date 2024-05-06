import React, {useEffect, useState} from "react";
import {adminGetApp, adminGetLoanApp, adminLoanReject, adminReject} from "../utils";
import {Button, message, Table} from "antd";
import moment from "moment";
import AdminApproveForm from "./AdminApproveForm";
import AdminLoanApproveForm from "./AdminLoanApproveForm";

function ReviewLoan(){
    const [houseLoan, setHouseLoan] = useState()
    const [studentLoan, setStudentLoan] = useState()
    const [currentApplication, setCurrentApplication] = useState(null);
    const [isModalVisible, setIsModalVisible] = useState(false);
    const typeMap ={'S':'Student','H':'House'}
    const typeStuMap ={'U':'Undergrad','G':'Graduate'}
    useEffect(() => {
        async function fetchApplications() {
            try {
                const result = await adminGetLoanApp();
                const HL = result.data.filter(item => item.ltype==='H');
                const SL = result.data.filter(item =>  item.ltype==='S');
                setHouseLoan(HL)
                setStudentLoan(SL)
            } catch (err) {
                message.error('Error fetching account data: ' + err.message);
            }
        }
        fetchApplications();
    }, []);
    const handleClickDeny = async (laid) => {
        console.log(laid)
        try {
            await adminLoanReject( {laid} );
            message.success("Application rejected successfully");
        } catch (err) {
            message.error('Error rejecting application: ' + err.message);
        }
    };
    const handleApprove = (app) => {
        setCurrentApplication(app);
        setIsModalVisible(true);
    };

    const handleCloseModal = () => {
        setIsModalVisible(false);
    };
    const columnsForHouse = [
        { title: 'App ID', dataIndex: 'laid', key: 'laid' },
        { title: 'Customer ID', dataIndex: 'cid', key: 'cid' },
        { title: 'Loan Type', dataIndex: 'ltype', key: 'ltype',render: text => typeMap[text] },
        { title: 'Loan Rate', dataIndex: 'lrate', key: 'lrate' },
        { title: 'House Year', dataIndex: 'hyear', key: 'hyear', render: date =>moment(date).year()  },
        { title: 'House Insurance', dataIndex: 'hinsurance', key: 'hinsurance' },
        { title: 'Insurance Account', dataIndex: 'laiaccount', key: 'laiaccount' },
        { title: 'Company name', dataIndex: 'lacomname', key: 'lacomname' },
        { title: 'Insurance Premium', dataIndex: 'ipremium', key: 'ipremium' },
        { title: 'Status', dataIndex: 'lavalid', key: 'lavalid', render: text => text || 'Pending' },
        {
            title: 'Actions',
            key: 'actions',
            render: (_, record) => (
                <>
                    <Button type="link" onClick={() => handleApprove(record)}>Approve</Button>
                    <Button type="link" onClick={() => handleClickDeny(record.laid)}>Deny</Button>
                </>
            )
        },
    ];
    const columnsForHStudent = [
        { title: 'App ID', dataIndex: 'laid', key: 'laid' },
        { title: 'Customer ID', dataIndex: 'cid', key: 'cid' },
        { title: 'Loan Type', dataIndex: 'ltype', key: 'ltype',render: text => typeMap[text] },
        { title: 'Loan Rate', dataIndex: 'lrate', key: 'lrate' },
        { title: 'Student Id', dataIndex: 'stuid', key: 'stuid' },
        { title: 'Student Type', dataIndex: 'stutype', key: 'stutype' ,render: text => typeStuMap[text]},
        { title: 'Graduate Year', dataIndex: 'stugraddate', key: 'stugraddate', render: date =>moment(date).year()  },
        { title: 'University Name', dataIndex: 'uname', key: 'uname' },
        { title: 'Status', dataIndex: 'lavalid', key: 'lavalid', render: text => text || 'Pending' },
        {
            title: 'Actions',
            key: 'actions',
            render: (_, record) => (
                <>
                    <Button type="link" onClick={() => handleApprove(record)}>Approve</Button>
                    <Button type="link" onClick={() => handleClickDeny(record.laid)}>Deny</Button>
                </>
            )
        },
    ];
    return (
        <div style={{margin: 20}}>
            <h2>House Loan</h2>
            <Table dataSource={houseLoan} columns={columnsForHouse} rowKey="laid"/>
            <h2>StudentLoan</h2>
            <Table dataSource={studentLoan} columns={columnsForHStudent} rowKey="laid"/>
            {currentApplication && (
                <AdminLoanApproveForm
                    laid={currentApplication.laid}

                    displayModal={isModalVisible}
                    setDisplayModal={setIsModalVisible}
                />
            )}
        </div>

    );

}
export default ReviewLoan
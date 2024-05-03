import React, {useEffect, useState} from "react";
import {allAccount, allApplication, allLoanApplication} from "../utils";
import {message, Table} from "antd";
import { List, Card } from 'antd';

function AllApplication({accountID}){
    const [applications, setApplications] = useState([]);
    const [loanapps,setLoanApps] = useState([])
    const accountTypeMap = {
        'S': 'Saving',
        'L': 'Loan',
        'C': 'Checking',
    };
    const LoanTypeMap = {
        'S': 'Studen',
        'H': 'House',
    };
    const columnsForApp = [
        {
            title: 'App ID',
            dataIndex: 'appId',
            key: 'appId',
        },
        {
            title: 'Type',
            dataIndex: 'type',
            key: 'type',
            render: type => accountTypeMap[type] || type
        },
        {
            title: 'Status',
            dataIndex: 'status',
            key: 'status',
            render: status => {
                let color = 'black'; // 默认颜色
                let text = status; // 默认显示状态

                if (status === null) {
                    color = 'gray'; // 灰色对应null
                    text = 'Pending'; // 显示为Pending
                } else if (status === 'P') {
                    color = 'green'; // 绿色对应'P'
                    text = 'Passed'; // 显示为Passed
                } else if (status === 'D') {
                    color = 'red'; // 红色对应'D'
                    text = 'Denied'; // 显示为Denied
                }

                return <span style={{ color }}>{text}</span>;
            }
        },
    ];
    const columnsForLoan = [
        {
            title: 'Loan Application ID',
            dataIndex: 'laid',
            key: 'laid',
        },
        {
            title: 'Type',
            dataIndex: 'type',
            key: 'type',
            render: type => LoanTypeMap[type] || type
        },
        {
            title: 'Status',
            dataIndex: 'status',
            key: 'status',
            render: status => {
                let color = 'black'; // 默认颜色
                let text = status; // 默认显示状态

                if (status === null) {
                    color = 'gray'; // 灰色对应null
                    text = 'Pending'; // 显示为Pending
                } else if (status === 'P') {
                    color = 'green'; // 绿色对应'P'
                    text = 'Passed'; // 显示为Passed
                } else if (status === 'D') {
                    color = 'red'; // 红色对应'D'
                    text = 'Denied'; // 显示为Denied
                }

                return <span style={{ color }}>{text}</span>;
            }
        },
    ];

    useEffect(() => {
        async function fetchApplications() {
            try {
                const result = await allApplication(accountID);

                    setApplications(result.data.userApps)
                    console.log(applications)
            }
            catch (err) {
                message.error('Error fetching account data: ' + err.message);
            }

        }
        async function fetchLoans() {
            try {
                const result = await allLoanApplication(accountID);

                setLoanApps(result.data.userApps)
                console.log(applications)
            }
            catch (err) {
                message.error('Error fetching account data: ' + err.message);
            }

        }
        fetchApplications();
        fetchLoans()

    }, [accountID]);

    return (
        <div style={{margin: 20}}>
            <h2>Checking&Saving applications</h2>
            <Table dataSource={applications} columns={columnsForApp} rowKey="appId"/>
            <h2>Loan applications</h2>
            <Table dataSource={loanapps} columns={columnsForLoan} rowKey="laid"/>
        </div>
    );

}

export default AllApplication
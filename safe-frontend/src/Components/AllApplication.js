import React, {useEffect, useState} from "react";
import {allAccount, allApplication} from "../utils";
import {message, Table} from "antd";
import { List, Card } from 'antd';

function AllApplication({accountID}){
    const [applications, setApplications] = useState([]);
    const accountTypeMap = {
        'S': 'Saving',
        'L': 'Loan',
        'C': 'Checking',
    };
    const columns = [
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
                if (status === null) {
                    color = 'gray'; // 灰色对应null
                } else if (status === 'p') {
                    color = 'green'; // 绿色对应'p'
                } else if (status === 'd') {
                    color = 'red'; // 红色对应'd'
                }
                return <span style={{ color }}>{status === null ? 'Pending' : status}</span>;

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
        fetchApplications();

    }, [accountID]);

    return (
        <Table dataSource={applications} columns={columns} rowKey="appId" />
    );

}
export default AllApplication
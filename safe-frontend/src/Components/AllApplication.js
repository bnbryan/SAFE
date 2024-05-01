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
    const colomns =[
        {
            title: 'Application Number',
            dataIndex: 'appid',
            key: 'appid',
        },
        {
            title: 'Account Type',
            dataIndex: 'type',
            key: 'appid',
        },
        {
            title: 'Application Number',
            dataIndex: 'appid',
            key: 'appid',
        },

    ]
    useEffect(() => {
        async function fetchApplications() {
            try {
                const result = await allApplication(accountID);
                if (result.data && Array.isArray(result.data)){
                    setApplications(result.data.userApps)
                }
            }
            catch (err) {
                message.error('Error fetching account data: ' + err.message);
            }

        }
        fetchApplications();

    }, [accountID]);

    return (
        <Table dataSource={applications} columns={colomns}></Table>
    );

}
export default AllApplication
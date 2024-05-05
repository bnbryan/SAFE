import React, {useEffect, useState} from "react";
import {allActivites, allApplication} from "../utils";
import {message, Table} from "antd";
import { List, Card } from 'antd';

function AllActivities({email}){
    const [activities, setActivites] = useState([]);
    useEffect(() => {
        async function fetchActivities() {
            try {
                const result = await allActivites(email);
                    setActivites(result.data)
            }
            catch (err) {
                message.error('Error fetching account data: ' + err.message);
            }

        }
        fetchActivities();

    }, [email]);
    const columns = [
        {
            title: 'Record Id',
            dataIndex: 'rid',
            key: 'rid',
        },
        {
            title: 'Transaction Type',
            key: 'transactionType',
            render: (_, record) => {
                if (record.anum === null) {
                    return 'Deposit';
                }
                else if(record.toanum===null){
                    return 'Withdraw'

                }else {
                    return 'Transfer';
                }
            }
        },
        {
            title: 'Amount',
            dataIndex: 'ramount',
            key: 'ramount',
        },
        {
            title: 'Account Type',
            dataIndex: 'ratype',
            key: 'ratype',
            render: ratype => {
                switch (ratype) {
                    case 'C':
                        return 'Checking';
                    case 'S':
                        return 'Saving';
                    case 'L':
                        return 'Loan';
                }
            }
        },
        {
            title: 'Time',
            dataIndex: 'rtime',
            key: 'rtime',
            render: time => new Date(time).toLocaleString()
        }
    ];

    return (
        <Table dataSource={activities} columns={columns} rowKey="rid" />
    );

}
export default AllActivities
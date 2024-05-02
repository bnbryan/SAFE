import React, {useEffect, useState} from "react";
import {allActivites, allApplication} from "../utils";
import {message, Table} from "antd";
import { List, Card } from 'antd';

function AllApplication({email}){
    const [activities, setActivites] = useState([]);
    useEffect(() => {
        async function fetchApplications() {
            try {
                const result = await allActivites(email);
                    setActivites(result.data)
            }
            catch (err) {
                message.error('Error fetching account data: ' + err.message);
            }

        }
        fetchApplications();

    }, [email]);
    const columns = [
        {
            title: 'RID',
            dataIndex: 'rid',
            key: 'rid',
        },
        {
            title: 'Transaction Type',
            key: 'transactionType',
            render: (_, record) => {
                if (record.anum === null) {
                    return record.ramount > 0 ? 'Deposit' : 'Withdraw';
                } else {
                    return 'Transfer';
                }
            }
        },
        {
            title: 'Ramount',
            dataIndex: 'ramount',
            key: 'ramount',
        },
        {
            title: 'Ratype',
            dataIndex: 'ratype',
            key: 'ratype',
            render: ratype => {
                switch (ratype) {
                    case 'C':
                        return 'Credit';
                    case 'D':
                        return 'Debit';
                    default:
                        return ratype;
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
export default AllApplication
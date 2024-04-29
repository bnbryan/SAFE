import React, {useEffect, useState} from "react";
import {allAccount, allApplication} from "../utils";
import {message} from "antd";
import { List, Card } from 'antd';

function AllApplication({accountID}){
    const [applications, setApplications] = useState([]);
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
        <List
            dataSource={applications}
            renderItem={item => (
                <List.Item>
                    <Card title={`Application ID: ${item.appId}`}>
                        <p>Type: {item.type}</p>
                        <p>Status: {item.status || 'N/A'}</p>
                    </Card>
                </List.Item>
            )}
        />
    );

}
export default AllApplication
import React, {useEffect, useState} from "react";
import {allActivites, allApplication} from "../utils";
import {message} from "antd";
import { List, Card } from 'antd';

function AllApplication({accountID}){
    const [activities, setActivites] = useState([]);
    useEffect(() => {
        async function fetchApplications() {
            try {
                const result = await allActivites(accountID);
                if (result.data && Array.isArray(result.data)){
                    setActivites(result.data.userApps)
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
            dataSource={activities}
            renderItem={item => (
                <List.Item>
                    <Card title={`Application ID: ${item.appId}`}>

                    </Card>
                </List.Item>
            )}
        />
    );

}
export default AllApplication
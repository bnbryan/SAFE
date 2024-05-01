import React, { useEffect, useState } from "react";
import {Button, Card, Form, Input, message, Select} from "antd";
import { CreditCardOutlined, DollarOutlined } from "@ant-design/icons";
import {allAccount, getIdByEmail, transfer} from '../utils';

function userInfo({ accountEmail }) {
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const[userInfo, setUserInfo] = useState([])

    // eslint-disable-next-line react-hooks/rules-of-hooks
    useEffect(() => {
        async function fetchUser() {
            try {
                const userInfo =await getIdByEmail(accountEmail)
                setUserInfo(userInfo.customer)
                console.log(userInfo.customer)
            } catch (err) {
                message.error('Error fetching user data: ' + err.message);
            }
        }
        fetchUser()
    }, [accountEmail]);



    return (
        <Card title={`Customer: ${userInfo.clname}`} bordered={true} style={{ width: 300 }}>
            <p><strong>Customer ID:</strong> {userInfo.cid}</p>
            <p><strong>Name:</strong> {userInfo.cfname} {userInfo.clname}</p>
            <p><strong>Email:</strong> {userInfo.cemail}</p>
        </Card>

    );
}

export default userInfo;


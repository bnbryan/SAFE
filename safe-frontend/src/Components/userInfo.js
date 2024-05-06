import React, { useEffect, useState } from "react";
import {Card, message} from "antd";
import { getIdByEmail } from '../utils';

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
        <Card title={`Customer: ${userInfo.clname}`} bordered={true} style={{width: 300}}>
            <p><strong>Customer ID:</strong> {userInfo.cid}</p>
            <p><strong>Name:</strong> {userInfo.cfname} {userInfo.clname}</p>
            <p><strong>Email:</strong> {userInfo.cemail}</p>
            <p><strong>Apartment:</strong> {userInfo.apt ? userInfo.apt : 'N/A'}</p>
            <p><strong>Street:</strong> {userInfo.street ? userInfo.street : 'N/A'}</p>
            <p><strong>City:</strong> {userInfo.city ? userInfo.city : 'N/A'}</p>
            <p><strong>State:</strong> {userInfo.state ? userInfo.state : 'N/A'}</p>
            <p><strong>Zipcode:</strong> {userInfo.zip ? userInfo.zip : 'N/A'}</p>
        </Card>

    );
}

export default userInfo;


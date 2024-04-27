import {useEffect, useState} from "react";
import {allAccount} from "../utils"
import {Card, List, message} from "antd";

function Accountsinfo({accountEmail}){
    const [savings, setSavings] = useState([]);
    const [checkings, setCheckings] = useState([]);
    const [loans, setLoans] = useState([]);
    const accountTypeMap = {
        'S': 'Saving',
        'C': 'Checking',
        'L': 'Loan' // 假设'L'代表贷款账户
    };
    function formatDate(dateString) {
        const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
        return new Date(dateString).toLocaleDateString('en-GB', options);
    }


    useEffect(() => {
        async function fetchAccounts() {
            try {
                const result = await allAccount(accountEmail);
                if (result.data && Array.isArray(result.data)) {
                    setSavings(result.data.filter(item => item.atype === 'S'));
                    console.log(savings)
                    setCheckings(result.data.filter(item => item.atype === 'C'));
                    setLoans(result.data.filter(item => item.atype === 'L'));
                } else {
                    throw new Error('Data is not in expected format');
                }
            } catch (err) {
                // Error handling
                message.error('Error fetching account data: ' + err.message);
            }
        }
        fetchAccounts()
    }, [accountEmail]);

    return (
        <div style={{ padding: '20px' }}>
            <h2>Welcome! Here are your accounts information</h2>
            <div style={{ marginBottom: '20px' }}>
                <h2>Savings Accounts</h2>
                {savings.length > 0 ? (
                    <List
                        grid={{ gutter: 16, column: 1 }}
                        dataSource={savings}
                        renderItem={item => (
                            <List.Item>
                                <Card title={`Account Number: ${item.anum}`}>
                                    <div style={{display: 'flex', justifyContent: 'space-between', width: '100%'}}>
                                        <span>Account Name: {item.aname}</span>
                                        <span>Open Date: {formatDate(item.adate)}</span>
                                        <span style={{marginRight: '16px'}}>
                                    Type: {accountTypeMap[item.atype] || item.atype}
                                </span>
                                    </div>
                                </Card>
                            </List.Item>
                        )}
                    />
                ) : (
                    <p>You don't have this type of account.</p>
                )}
            </div>
            <div style={{ marginBottom: '20px' }}>
                <h2>Checking Accounts</h2>
                {checkings.length > 0 ? (
                    <List
                        grid={{ gutter: 16, column: 1 }}
                        dataSource={checkings}
                        renderItem={item => (
                            <List.Item>
                                <Card title={`Account Number: ${item.anum}`}>
                                    <div style={{ display: 'flex', justifyContent: 'space-between', width: '100%' }}>
                                        <span>Account Name: {item.aname}</span>
                                        <span>Open Date: {formatDate(item.adate)}</span>
                                        <span style={{ marginRight: '16px' }}>Type: {accountTypeMap[item.atype] || item.atype}</span>
                                    </div>
                                </Card>
                            </List.Item>
                        )}
                    />
                ) : (
                    <p>You don't have this type of account.</p>
                )}
            </div>
            <div style={{ marginBottom: '20px' }}>
                <h2>Loans</h2>
                {loans.length > 0 ? (
                    <List
                        grid={{ gutter: 16, column: 1 }}
                        dataSource={loans}
                        renderItem={item => (
                            <List.Item>
                                <div style={{display: 'flex', justifyContent: 'space-between', width: '100%'}}>
                                    <span>Account Name: {item.aname}</span>
                                    <span>Open Date: {formatDate(item.adate)}</span>
                                    <span
                                        style={{marginRight: '16px'}}>Type: {accountTypeMap[item.atype] || item.atype}</span>
                                </div>
                            </List.Item>
                        )}
                    />
                ) : (
                    <p>You don't have this type of account.</p>
                )}
            </div>
        </div>
    );


}

export default Accountsinfo
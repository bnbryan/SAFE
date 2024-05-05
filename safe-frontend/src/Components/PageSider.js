// SiderMenu.js
import React from 'react';
import {Layout, Menu} from 'antd';
import {
    UserOutlined,
    TransactionOutlined,
    BookOutlined,
    AccountBookOutlined
} from '@ant-design/icons';



const { Sider } = Layout;
const { SubMenu } = Menu;
const PageSider = ({ onMenuClick }) => {

    const handleMenuClick = e => {
        onMenuClick(e.key);
    };

    return (
        <Sider width={300} className="site-layout-background" >
            <Menu
                mode="inline"
                style={{ height: '100%', borderRight: 0 }}
                onClick={handleMenuClick}
            >
                <SubMenu key="Accounts" icon={<AccountBookOutlined/>} title="Accounts">
                    <Menu.Item key="accountinfo">Accounts Infomation</Menu.Item>
                    <Menu.Item key="application">New Application</Menu.Item>
                    <Menu.Item key="appLoan">New Loan Application</Menu.Item>
                    <Menu.Item key="allApplication">View Applications</Menu.Item>
                </SubMenu>
                <SubMenu key="Transaction" icon={<TransactionOutlined />} title="Transaction">
                    <Menu.Item key="withdraw">Withdraw</Menu.Item>
                    <Menu.Item key="transfer">Transfer</Menu.Item>
                    <Menu.Item key="deposit">Deposit</Menu.Item>
                </SubMenu>
                <SubMenu key="Record" icon={<BookOutlined />} title="Record">
                    <Menu.Item key="activityRecords">Activities </Menu.Item>
                </SubMenu>
                <SubMenu key="User" icon={<UserOutlined />} title="User">
                    <Menu.Item key="userInfo"> User Info</Menu.Item>
                </SubMenu>

            </Menu>
        </Sider>
    );
};

export default PageSider;
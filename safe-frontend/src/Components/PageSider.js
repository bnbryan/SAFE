// SiderMenu.js
import React, {useState} from 'react';
import {Layout, Menu, Modal, Transfer} from 'antd';
import {
    UserOutlined,
    LaptopOutlined,
    NotificationOutlined,
    TransactionOutlined,
    BookOutlined,
} from '@ant-design/icons';



const { Sider } = Layout;
const { SubMenu } = Menu;
const PageSider = ({ onMenuClick }) => {

    const handleMenuClick = e => {
        onMenuClick(e.key);
    };

    return (
        <Sider width={300} className="site-layout-background">
            <Menu
                mode="inline"
                style={{ height: '100%', borderRight: 0 }}
                onClick={handleMenuClick}
            >
                <SubMenu key="Transaction" icon={<TransactionOutlined />} title="Transaction">
                    <Menu.Item key="withdraw">withdraw</Menu.Item>
                    <Menu.Item key="transfer">transfer</Menu.Item>
                    <Menu.Item key="deposit">deposit</Menu.Item>
                </SubMenu>
                <SubMenu key="Record" icon={<BookOutlined />} title="Record">
                    <Menu.Item key="activityRecords">activities </Menu.Item>
                </SubMenu>
                <SubMenu key="User" icon={<UserOutlined />} title="User">
                    <Menu.Item key="accountInfo"> accountInfo</Menu.Item>
                </SubMenu>

            </Menu>
        </Sider>
    );
};

export default PageSider;
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
const AdminPageSider = ({ onMenuClick }) => {

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
                <SubMenu key="Customer Applications" icon={<AccountBookOutlined/>} title="Applications">
                    <Menu.Item key="csapplication">Review Applications</Menu.Item>
                    <Menu.Item key="lapplication">Review Loan Applications</Menu.Item>
                </SubMenu>
            </Menu>
        </Sider>
    );
};

export default AdminPageSider;
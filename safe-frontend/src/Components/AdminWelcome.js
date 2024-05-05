import React from 'react';
import { Typography, Alert } from 'antd';
import { FileDoneOutlined } from '@ant-design/icons';

const { Title, Text } = Typography;
function AdminWelcome(){
    return (
        <div style={{ margin: '20px', padding: '20px', textAlign: 'center' }}>
            <Title level={2}>Administrator Actions Needed</Title>
            <Text type="secondary" style={{ fontSize: '16px' }}>
                Please review the applications pending your approval.
            </Text>
            <Alert
                style={{ margin: '24px 0' }}
                message="Click on the sidebar to start approving applications."
                type="info"
                showIcon
                icon={<FileDoneOutlined style={{ color: '#108ee9' }} />}
            />
        </div>
    )

}
export default AdminWelcome
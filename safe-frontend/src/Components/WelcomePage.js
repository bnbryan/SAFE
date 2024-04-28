import React from 'react';
import { Layout, Typography } from 'antd';
import welcomeImage from '../Logo/welcome.jpg'

const { Header, Content } = Layout;
const { Title, Paragraph,Text } = Typography;

function welcomePage(){
    return (
        <Layout>
            <Header style={{ background: `url(${welcomeImage}) no-repeat center/cover`, padding: 0 }}>
                <div style={{ background: 'rgba(0, 0, 0, 0.5)', textAlign: 'center' }}>
                    <Title style={{ color: '#fff', lineHeight: '64px', margin: 0 }}>Welcome to SAFE Bank</Title>
                </div>
            </Header>
            <Content style={{ padding: '50px', maxWidth: '600px', margin: 'auto' }}>
                <div style={{ background: '#fff', padding: 24, minHeight: 380, borderRadius: '8px' }}>
                    <Title level={2}>Welcome!</Title>
                    <Paragraph>
                        We are delighted to see you! SAFE Bank offers comprehensive financial services designed to help you achieve financial freedom. Our tools and features are designed to enhance your efficiency and financial management.
                    </Paragraph>
                    <Paragraph>
                        Whether you are managing personal finances or corporate assets, our services can provide the support you need. Start exploring now to discover more possibilities!
                    </Paragraph>
                    <Paragraph style={{ textAlign: 'center', fontWeight: 'bold',fontSize:'19px' }}>
                        Press Login or Register to Start!
                    </Paragraph>
                    {/* Here you can place some CTAs (like a "Get Started" button) or other information */}
                </div>
            </Content>
        </Layout>
    );
}
export default welcomePage()
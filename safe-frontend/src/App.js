import React, {useEffect, useState} from 'react';
import {Layout, message} from 'antd';
import PageHeader from "./Components/PageHeader";
import {logout} from "./utils";
import PageSider from "./Components/PageSider";
import HomePage from "./Components/HomePage";


const { Header, Content, Sider } = Layout;


function App() {
    const [loggedIn, setLoggedIn] = useState(false)
    const [selectedKey, setSelectedKey] = useState('')
    const [accountEmail, setAccountEmail] = useState("")
    const handleMenuClick = (key) => {
        console.log(key)
        setSelectedKey(key);
    };


    const signinOnSuccess = (email) => {
        setLoggedIn(true);
        setAccountEmail(email)
        console.log(accountEmail)
        /*
        这里要做的事是获取所有的账户信息，
         */
    }
    useEffect(() => {
        if (accountEmail) {
            console.log(accountEmail); // This will log the updated email
            // You can also perform any actions that depend on the updated accountEmail here
        }
    }, [accountEmail]); // Depend on accountEmail to rerun this effect



    const signoutOnClick = () => {
        message.success("to imp")
        setLoggedIn(false)
       /* logout().then(() => {

            message.success('Successfully Signed out')
        }).catch((err) => {
            message.error(err.message)
        })*/
    }

    return (
        <Layout>
            <Header>
                <PageHeader  loggedIn={loggedIn}
                             signoutOnClick={signoutOnClick}
                             signinOnSuccess={signinOnSuccess}></PageHeader>
            </Header>
            <Layout>
                <Sider width={300} className="site-layout-background">
                   <PageSider onMenuClick={handleMenuClick}></PageSider>
                </Sider>
                <Layout style={{ padding: '24px' }}>
                    <Content
                        className="site-layout-background"
                        style={{
                            padding: 24,
                            margin: 0,
                            height: 800,
                            overflow: 'auto'
                        }}
                    >
                        <HomePage activeMenuKey={selectedKey} loggedIn={loggedIn} accountEmail={accountEmail}/>
                    </Content>
                </Layout>
            </Layout>
        </Layout>
    )
}


export default App;
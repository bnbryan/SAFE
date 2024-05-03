import React, {useEffect, useState} from 'react';
import {Layout, message} from 'antd';
import PageHeader from "./Components/PageHeader";
import {logout} from "./utils";
import PageSider from "./Components/PageSider";
import HomePage from "./Components/HomePage";
import WelcomePage from "./Components/WelcomePage";
import AdminPageSider from "./Components/AdminPageSider";


const { Header, Content, Sider } = Layout;


function App() {
    const [loggedIn, setLoggedIn] = useState(false)
    const [selectedKey, setSelectedKey] = useState('')
    const [accountEmail, setAccountEmail] = useState("")
    const [adminLoggedIn,setAdminLoggedIn] = useState(false)
    const [userId,setUserId] = useState('')
    const handleMenuClick = (key) => {
        console.log(key)
        setSelectedKey(key);
    };

    const signinOnSuccessAdmin=()=>{
        setAdminLoggedIn(true)
    }
    const signinOnSuccess = (email,id) => {
        setLoggedIn(true);
        setAccountEmail(email)
        setUserId(id)
        console.log(accountEmail)
        console.log(userId)

        /*
        这里要做的事是获取所有的账户信息，
         */
    }
    useEffect(() => {
        if (accountEmail) {
            console.log(accountEmail);
            console.log(userId)// This will log the updated email
            // You can also perform any actions that depend on the updated accountEmail here
        }
    }, [accountEmail,userId]); // Depend on accountEmail to rerun this effect



    const signoutOnClick = () => {

        setLoggedIn(false)
        setAccountEmail("")
        setAdminLoggedIn(false)
        setUserId("")
        localStorage.removeItem('token')
        message.success("Logged Out!")
    }

    return (

        <Layout>
            <Header>
                <PageHeader  loggedIn={loggedIn}
                             signoutOnClick={signoutOnClick}
                             signinOnSuccess={signinOnSuccess}
                signinOnSuccessAdmin={signinOnSuccessAdmin} adminloggedIn={adminLoggedIn}></PageHeader>
            </Header>
            {/* to imp  welcomepage*/}
            {!(loggedIn||adminLoggedIn)?
                (WelcomePage):(
                <Layout>
                <Sider width={300} className="site-layout-background">
                    {adminLoggedIn ? (
                        <AdminPageSider onMenuClick={handleMenuClick}> </AdminPageSider>
                    ) : (

                        <PageSider onMenuClick={handleMenuClick}></PageSider>
                    )}
                </Sider>
                <Layout style={{padding: '24px'}}>
                    <Content
                        className="site-layout-background"
                        style={{
                            padding: 24,
                            margin: 0,
                            height: 800,
                            overflow: 'auto'
                        }}
                    >
                        {adminLoggedIn ? (
                            <div> to imp</div>
                        ) : (
                            <HomePage activeMenuKey={selectedKey} loggedIn={loggedIn} accountEmail={accountEmail} userId={userId}/>
                        )
                        }
                    </Content>
                </Layout>
            </Layout>)}
        </Layout>
    )
}


export default App;
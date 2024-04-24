import { Layout, Row, Col, Button } from 'antd'
import Register from './Register'
import Login from './Login'
import PasswordReset from './passwordReset'
import React from 'react'


const { Header } = Layout


function PageHeader({ loggedIn, signoutOnClick, signinOnSuccess }) {
    return (
        <Header>
            <Row justify="space-between">
                <Col>
                    <img src="../logo.svg" alt="Logo" style={{height: '50px'}}/>
                </Col>
                <Col>
                    {loggedIn && (
                        <>
                            <Button shape="round" onClick={signoutOnClick}>Logout</Button>
                        </>
                    )}
                    {!loggedIn && (
                        <>
                            <Login onSuccess={signinOnSuccess} />
                            <Register />
                            <PasswordReset/>
                        </>
                    )}
                </Col>
            </Row>
        </Header>
    )
}


export default PageHeader
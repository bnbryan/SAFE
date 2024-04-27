import { Layout, Row, Col, Button } from 'antd'
import Register from './Register'
import Login from './Login'
import PasswordReset from './passwordReset'
import React from 'react'


const { Header } = Layout


function PageHeader({ loggedIn, signoutOnClick, signinOnSuccess }) {
    const securityQuestions = [
        "What's your mother's name",
        "What was the name of your first pet",
        "What city were you born in",
        "What's the name of your childhood friend",
        "What was the model of your first car"
    ];
    return (
        <Header>
            <Row justify="space-between">
                <Col>

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
                            <Register securityQuestions={securityQuestions}/>
                            <PasswordReset securityQuestions={securityQuestions}/>
                        </>
                    )}
                </Col>
            </Row>
        </Header>
    )
}


export default PageHeader
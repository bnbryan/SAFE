import WithdrawForm from "./WithdrawForm";
import TransferForm from "./TransferForm";
import {Card, Tabs} from "antd";
import React from "react";
import TabPane from "antd/lib/tabs/TabPane";
const  HomePage=({ activeMenuKey })=>{
    /*
    根据
     */
    const renderContent = (key) => {
        switch (key) {
            case 'withdraw':
                return <WithdrawForm/>;
            case 'transfer':
                return <TransferForm/>;
            // ...更多case...
            default:
                return <div>Select a service from the sider</div>;
        }
    }
    return(
        <div>{renderContent(activeMenuKey)}</div>

    )


}
export default HomePage;
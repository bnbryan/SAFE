import WithdrawForm from "./WithdrawForm";
import TransferForm from "./TransferForm";
import AccountsInfo from "./AccountsInfo";
import {Card, Tabs} from "antd";
import React from "react";
import DepositForm from "./Deposit";
import userInfo from "./userInfo";
import AllApplication from "./AllApplication";
import AllActivities from "./AllActivities";
import Application from "./Application";
import UserInfo from "./userInfo";
import AppLoan from "./AppLoan";
import AdminWelcome from "./AdminWelcome";
const  HomePage=({activeMenuKey})=>{
    /*
    根据
     */
    const renderContent = (key) => {
        switch (key) {
            case 'csapplication':
                return <div> to imp cs appls</div>
            case 'lapplication':
                return <div> to imp loan appls</div>
            default:
                return <AdminWelcome/>;
        }
    }
    return(
        <div>{renderContent(activeMenuKey)}</div>

    )


}
export default HomePage;
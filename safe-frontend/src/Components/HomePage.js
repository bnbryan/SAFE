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
const  HomePage=({ loggedIn, activeMenuKey, accountEmail, userId})=>{
    /*
    根据
     */
    const renderContent = (key) => {
            switch (key) {
                case 'withdraw':
                    return <WithdrawForm accountEmail={accountEmail}/>;
                case 'transfer':
                    return <TransferForm accountEmail={accountEmail}/>;
                // ...更多case...
                case 'accountinfo':
                    return <AccountsInfo accountEmail={accountEmail}/>;
                case 'deposit':
                    return <DepositForm accountEmail={accountEmail}/>
                case 'activityRecords':
                    return <AllActivities email={accountEmail}/>
                case 'allApplication':
                    return <AllApplication accountID={userId}/>
                case 'userInfo':
                    return <UserInfo accountEmail={accountEmail}/>
                case 'application':
                    return <Application accountID={userId}/>
                case'appLoan':
                    return <AppLoan accountID={userId}/>
                default:
                    return <AccountsInfo accountEmail={accountEmail}/>;
            }
    }
    return(
        <div>{renderContent(activeMenuKey)}</div>

    )


}
export default HomePage;
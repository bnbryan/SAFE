import WithdrawForm from "./WithdrawForm";
import TransferForm from "./TransferForm";
import AccountsInfo from "./AccountsInfo";
import {Card, Tabs} from "antd";
import React from "react";
import TabPane from "antd/lib/tabs/TabPane";
import DepositForm from "./Deposit";
const  HomePage=({ loggedIn, activeMenuKey, accountEmail })=>{
    /*
    根据
     */
    const renderContent = (key) => {
        if(!loggedIn) {
            return <div>Please log in or register to get start!</div>
        }
        else {
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
                    return <div>to imp</div>
                case 'allApplication':
                    return <div>to imp</div>
                case 'userInfo':
                    return <div>to imp</div>
                case 'application':
                    return <div>to imp</div>
                default:
                    return <AccountsInfo accountEmail={accountEmail}/>;
            }
        }
    }
    return(
        <div>{renderContent(activeMenuKey)}</div>

    )


}
export default HomePage;
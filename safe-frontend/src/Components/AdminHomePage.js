
import React from "react";
import AdminWelcome from "./AdminWelcome";
import ReviewApplication from "./ReviewApplication";
import ReviewLoan from "./ReviewLoan";
const  HomePage=({activeMenuKey})=>{
    /*
    根据
     */
    const renderContent = (key) => {
        switch (key) {
            case 'csapplication':
                return <ReviewApplication/>
            case 'lapplication':
                return <ReviewLoan/>
            default:
                return <AdminWelcome/>;
        }
    }
    return(
        <div>{renderContent(activeMenuKey)}</div>

    )


}
export default HomePage;
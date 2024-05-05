
import React from "react";
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
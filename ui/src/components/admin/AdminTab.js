import React from 'react';
import {Tab} from 'semantic-ui-react';
import {UserTable} from './UserTable';
import {ServiceTable} from './ServiceTable';
import {ServiceForm} from "../misc/ServiceForm";

// import {BackButton} from "../misc/BackButton";

export function AdminTab(props) {
    const {handleInputChange} = props;
    const {isUsersLoading, users, usernameSearch, handleDeleteUser, handleSearchUser,} = props;
    const {
        serviceTitle,
        addressStreet,
        addressCity,
        serviceManager,
        service,
        handleCreateService,
        services,
        createService,
        showServiceForm,
        handleDeleteService,
        handleEditService,
    } = props;


    const panes = [
        {
            menuItem: {key: 'users', icon: 'users', content: 'Users'},
            render: () => (
                <Tab.Pane loading={isUsersLoading}>
                    <UserTable
                        users={users}
                        usernameSearch={usernameSearch}
                        handleInputChange={handleInputChange}
                        handleDeleteUser={handleDeleteUser}
                        handleSearchUser={handleSearchUser}
                    />
                </Tab.Pane>
            )
        },
        {
            menuItem: {key: 'services', icon: 'laptop', content: 'Services'},

            render: () => !showServiceForm ? (

                <Tab.Pane >
                    <ServiceTable

                        services={services}

                        createService={createService}
                        handleDeleteService={handleDeleteService}
                        handleEditService={handleEditService}

                    />
                </Tab.Pane>) :(
                <Tab.Pane >
                   <ServiceForm
                       serviceTitle={serviceTitle}
                       addressStreet={addressStreet}
                       addressCity={addressCity}
                       serviceManager={serviceManager}
                       service={service}
                       handleCreateService={handleCreateService}
                       handleInputChange={handleInputChange}
                   />
                </Tab.Pane>)

        },

    ]

    return (
        <Tab menu={{attached: 'top'}} panes={panes}/>
    )
}


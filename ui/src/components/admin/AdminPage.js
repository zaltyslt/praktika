import React, {useContext, useEffect, useState} from 'react'
import {Navigate} from 'react-router-dom'
import {Container} from 'semantic-ui-react'
import {AuthContext} from '../context/AuthContext'
import {authApi} from '../misc/AuthApi'
import {AdminTab} from './AdminTab'
import {handleLogError} from '../misc/Helpers'


export function AdminPage() {
    // static contextType = AuthContext
    const Auth = useContext(AuthContext);
    const [users, setUsers] = useState([]);
    const [usernameSearch, setUserNameSearch] = useState('');
    const [isAdmin, setIsAdmin] = useState(true);
    const [isUsersLoading, setIsUsersLoading] = useState(false);
    // const [messageText, setMessageText] = useState(undefined);
    const [services, setServices] = useState([]);
    const emptyService = {
        id: null,
        title: '',
        manager: '',
        addressStreet: '',
        addressCity: '',
        workers:[],
    }
    const [service, setService] = useState(emptyService);

    const [serviceId, setServiceId] = useState('');
    const [serviceTitle, setServiceTitle] = useState('');
    const [addressStreet, setAddressStreet] = useState('');
    const [addressCity, setAddressCity] = useState('');
    const [serviceManager, setServiceManager] = useState('');
    const [servicesLoading, setServicesLoading] = useState(false);
    const [showServiceForm, setShowServiceForm] = useState(false);




    useEffect(() => {
        const user = Auth.getUser();
        const isAdmin = user.data.rol[0] === 'ADMIN';
        setIsAdmin(isAdmin);

        handleGetUsers();
        handleGetServices();

    }, [Auth]);


    const handleInputChange = (e , {name, value} ) => {
            console.log(`name: ${name} value: ${value}`);
            const tempService = {...service, [name]: value}
            console.log(tempService);
            setService(tempService);

    }
    const createService = ()=>{
        const tempService = {...service, edit: false,}
        setService(tempService);
        setShowServiceForm(true);
    }

    const handleCreateService = () => {
        const user = Auth.getUser();
        console.log(service);
        // let serviceTitleTemp = service.serviceTitle.trim;
        // if (!serviceTitleTemp) {
        //     return;
        // }
        const serviceToCreate = {
            id: null,
            title: service.title,
            manager: service.manager,
            addressStreet: service.addressStreet,
            addressCity: service.addressCity,
            workers:[],
        };

        authApi.createService(user, serviceToCreate)
            .then(() => {
                handleGetServices();
                setServiceTitle('');
                setShowServiceForm(false);
            })
            .catch(error => {
                handleLogError(error);
            })
    }
    const handleGetServices = () => {

        const user = Auth.getUser();

        setServicesLoading(true);
        authApi.getServices(user)
            .then(response => {
                setServices(response.data);
                console.log(response.data);
            })
            .catch(error => {
                handleLogError(error);
            })
            .finally(() => {
                setServicesLoading(false);
            })
    }
    const handleGetUsers = () => {

        const user = Auth.getUser();

        setIsUsersLoading(true);
        authApi.getUsers(user)
            .then(response => {
                setUsers(response.data);
            })
            .catch(error => {
                handleLogError(error);
            })
            .finally(() => {
                setIsUsersLoading(false);
            })
    }



    const handleDeleteUser = (username) => {
        // const Auth = this.context
        const user = Auth.getUser()

        authApi.deleteUser(user, username)
            .then(() => {
                handleGetUsers();
            })
            .catch(error => {
                handleLogError(error);
            })
    }

    const handleSearchUser = () => {
        const user = Auth.getUser();

        const username = usernameSearch;
        authApi.getUsers(user, username)
            .then(response => {
                const data = response.data;
                const users = data instanceof Array ? data : [data];
                setUsers(users);
            })
            .catch(error => {
                handleLogError(error)
                setUsers([]);
            })
    }

    const handleEditService = (serviceToEdit) => {
        console.log(service);
        console.log(serviceToEdit);
        const tempService = {...service, serviceEdit: true,}
       setService(tempService);
       setShowServiceForm(true);
        // const user = Auth.getUser()
        // // setIsOrdersLoading(true);
        // authApi.updateOrder(user, order)
        //     .then(response => {
        //         console.log(response.data);
        //
        //         // setOrders(response.data)
        //         handleGetServices();
        //         setIsOrderEdited(false);
        //     })
        //     .catch(error => {
        //         handleLogError(error)
        //     })
        //     .finally(() => {
        //         setIsOrdersLoading(false);
        //     })
    }
    const handleDeleteService = (id) => {
console.log(id);
        const user = Auth.getUser()

        console.log(id);

        authApi.deleteService(user, id)
            .then(() => {
                handleGetServices()
            })
            .catch(error => {
                handleLogError(error)
            })
    }




    if (!isAdmin) {
        return <Navigate to='/'/>
    } else {

        return (
            <Container>
                <AdminTab
                    isUsersLoading={isUsersLoading}
                    users={users}
                    usernameSearch={usernameSearch}
                    handleDeleteUser={handleDeleteUser}
                    handleSearchUser={handleSearchUser}

                    serviceTitle={serviceTitle}
                    addressStreet={addressStreet}
                    addressCity={addressCity}
                    serviceManager={serviceManager}
                    service={service}
                    handleCreateService={handleCreateService}
                    services={services}
                    createService={createService}
                    showServiceForm={showServiceForm}
                    handleDeleteService={handleDeleteService}
                    handleEditService={handleEditService}

                    handleInputChange={handleInputChange}


                />
            </Container>
        );
    }

}


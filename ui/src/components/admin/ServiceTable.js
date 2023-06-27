import React from 'react';
import {Grid, Form, Button, Input, Icon, Table} from 'semantic-ui-react';
import {ServiceForm} from '../misc/ServiceForm';

import {format} from "date-fns";


export function ServiceTable({
                                 // serviceTitle,
                                 // addressStreet,
                                 // addressCity,
                                 // serviceManager,
                                 // handleCreateService,
                                 // handleInputChange,
                                 createService,
                                 handleDeleteService,
                                 handleEditService,
                                 services,

                           }) {
    const undefinedIcon = <Icon loading name='spinner' color={'blue'}/>;
    const confirmedIcon = <Icon name='check' color={'green'}/>;
    const canceledIcon = <Icon name='dont' color={'red'}/>;


    let servicesList;
    if (services.length === 0) {
        servicesList = (
            <Table.Row key='no-services'>
                <Table.Cell collapsing textAlign='center' colSpan='5'>No services to display</Table.Cell>
            </Table.Row>
        )
    } else {
        // console.log(services);
        servicesList = services.map(service => {
            return (
                <Table.Row
                    onClick={() => handleEditService(service)}
                    key={service.id}>

                    <Table.Cell>{service.title}</Table.Cell>
                    <Table.Cell>{service.addressStreet}</Table.Cell>
                    <Table.Cell>{service.addressCity}</Table.Cell>

                    <Table.Cell>{service.manager}</Table.Cell>
                    <Table.Cell>
                        <Button
                            circular
                            color='red'
                            size='small'
                            icon='trash'
                            onClick={() => handleDeleteService(service.id)}
                        />
                    </Table.Cell>
                    {/*<Table.Cell><Input style={{ width: "50px" }}>*/}

                    {/*</Input></Table.Cell>*/}

                </Table.Row>
            )
        })
    }

    return (
        <>
            <Grid stackable divided>
                <Grid.Row columns='2'>
                    <Button
                        icon
                        labelPosition='right'
                    onClick={createService}>
                        Create<Icon name='add'/>
                    </Button>
                </Grid.Row>
            </Grid>
            <Table compact striped selectable>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell width={2}>Title</Table.HeaderCell>
                        <Table.HeaderCell width={3}>Address</Table.HeaderCell>
                        <Table.HeaderCell width={3}>City</Table.HeaderCell>
                        <Table.HeaderCell width={2}>Manager</Table.HeaderCell>
                        <Table.HeaderCell width={1}>Action</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    {servicesList}
                </Table.Body>
            </Table>
            {/*<Button*/}
            {/*    circular*/}
            {/*    color='yellow'*/}
            {/*    size='small'*/}
            {/*    icon='send'*/}
            {/*    onClick={handleUpdateOrder}*/}
            {/*/>*/}
        </>
    );
}


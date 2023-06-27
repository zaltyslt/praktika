import React from 'react'
import {Form, Button, Icon} from 'semantic-ui-react'

export function ServiceForm({

                                service,
                                handleInputChange,
                                handleCreateService,
                                editService
                            }) {
    const createBtnDisabled = service.title.trim() === '';
    console.log(service);
    return (
        <Form onSubmit={ service.edit === false ? (handleCreateService) : (editService)}>
            <Form.Group>
                <Form.Input
                    label={"Title"}
                    name='title'
                    placeholder='Title *'
                    value={service.title}
                    onChange={handleInputChange}
                />
            </Form.Group>
            <Form.Group>
                <Form.Input
                    label={'Address'}
                    name='addressStreet'
                    placeholder='Street address'
                    value={service.addressStreet}
                    onChange={handleInputChange}
                />
                <Form.Input
                    label={'City'}
                    name='addressCity'
                    placeholder='City'
                    value={service.addressCity}
                    onChange={handleInputChange}
                />
            </Form.Group><Form.Group>
            <Form.Input
                label={'Manager'}
                name='manager'
                placeholder='Manager'
                value={service.manager}
                onChange={handleInputChange}
            />
            <Button icon labelPosition='right' disabled={createBtnDisabled}>
                Save<Icon name='add'/>
            </Button>
        </Form.Group>
        </Form>
    );
}


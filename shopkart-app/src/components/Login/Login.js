import React, { useState } from 'react'
import Card from 'react-bootstrap/Card'
import CardHeader from 'react-bootstrap/CardHeader'
import Form from 'react-bootstrap/Form'
import { Button, FormControl, FormGroup, FormLabel } from 'react-bootstrap'
import axios from 'axios'
import { useNavigate } from 'react-router'

const Login = () => {

    const navigate = useNavigate()

    const [username, setUsername] = useState(null)
    const [password, setPassword] = useState(null)


    const userLogin = () => {
        console.log("userLogin function called")
        const login_api = "http://localhost:8080/authLogin"

        const request = {
            "userId": username,
            "password": password
        }
        console.log(request)

        axios.post(login_api, request)
            .then(response => {
                response = response.data
                if (response.statusCode == "200") {
                    console.log("Login successful")
                    // redirect to home page
                    navigate("/")
                } else {
                    console.log("Login failed")
                }
            })
            .catch(e => console.log("error " + e))
    }

    const usernameHandler = (e) => {
        setUsername(e.target.value)
    }

    const passwordHandler = (e) => {
        setPassword(e.target.value)
    }

    return (
        <div className='container'>
            <Card>
                <CardHeader>
                    Login
                </CardHeader>
                <Card.Body>
                    <Form>
                        <FormGroup className='mb-3'>
                            <FormLabel>Username</FormLabel>
                            <FormControl type='text' placeholder='Username' onChange={usernameHandler} />
                        </FormGroup>
                        <FormGroup className='mb-3'>
                            <FormLabel>Password</FormLabel>
                            <FormControl type='password' placeholder='Password' onChange={passwordHandler} />
                        </FormGroup>
                        <Button variant="success" type="button" onClick={userLogin}>
                            Login
                        </Button>
                    </Form>
                </Card.Body>
            </Card>
        </div>
    )
}

export default Login
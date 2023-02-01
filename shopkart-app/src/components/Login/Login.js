import React, { useState } from 'react'
import Card from 'react-bootstrap/Card'
import CardHeader from 'react-bootstrap/CardHeader'
import Form from 'react-bootstrap/Form'
import { Alert, Button, FormControl, FormGroup, FormLabel, Spinner } from 'react-bootstrap'
import axios from 'axios'
import { useNavigate } from 'react-router'
import './login.css'

const Login = () => {

    const navigate = useNavigate()

    const [username, setUsername] = useState(null)
    const [password, setPassword] = useState(null)
    const [loginError, setLoginError] = useState(false)
    const [loading, setLoading] = useState(false)


    const userLogin = () => {
        setLoading(true)
        console.log("userLogin function called")
        const login_api = "http://localhost:8080/authLogin"

        const request = {
            "username": username,
            "password": password
        }
        console.log(request)

        axios.post(login_api, request)
            .then(response => {
                response = response.data

                console.log("Login successful")
                // redirect to home page
                setLoading(false)
                navigate("/")
            })
            .catch(e => {
                setLoginError(true)
                setLoading(false)
                console.log("Login failed")
                console.log("error " + e)
            })
    }

    const usernameHandler = (e) => {
        setUsername(e.target.value)
    }

    const passwordHandler = (e) => {
        setPassword(e.target.value)
    }

    return (
        <div className='container loginContainer paddingTop30' id="loginForm">
            <Card>
                <CardHeader>
                    Login
                </CardHeader>
                <Card.Body>
                    <Form>
                        {loginError && <Alert key='danger' variant='danger'> Incorrect username or password</Alert>
                        }
                        <FormGroup className='mb-3'>
                            <FormLabel>Username</FormLabel>
                            <FormControl type='text' placeholder='Username' onChange={usernameHandler} />
                        </FormGroup>
                        <FormGroup className='mb-3'>
                            <FormLabel>Password</FormLabel>
                            <FormControl type='password' placeholder='Password' onChange={passwordHandler} />
                        </FormGroup>
                        <Button variant="success" type="button" onClick={userLogin}>
                            {(loading) ? <Spinner animation="border" /> : "Login"}
                        </Button>
                    </Form>
                </Card.Body>
            </Card>
        </div>
    )
}

export default Login
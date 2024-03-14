import axios from "axios";

const baseurl = "http://localhost:8080";

export const submissionRequest = (user) => {
    const config = {
        headers: {
            "Content-type": "application/json",
        },
    };
    return axios.post(baseurl + "/submissions", user, config);
}

export const calculationsRequest = (token) => {
    const config = {
        headers: {
            "Content-type": "application/json",
            "Authorization": "Bearer " + token
        },
    };
    return axios.get(baseurl + "/calculate", config)
}

export const calculateRequest = (calculation, token) => {
    const config = {
        params: {
            "eq": calculation
        },
        headers: {
            "Content-type": "application/json",
            "Authorization": "Bearer " + token
        },
    };
    return axios.get(baseurl + "/calculate", config)
}

export const loginRequest = (loginForm) => {
    const config = {
        headers: {
            "Content-type": "application/json",
        },
    };
    return axios.post(baseurl + "/login", loginForm, config)
}
export const signupRequest = (signupForm) => {
    const config = {
        headers: {
            "Content-type": "application/json",
        },
    };
    return axios.post(baseurl + "/signup", signupForm, config)
}

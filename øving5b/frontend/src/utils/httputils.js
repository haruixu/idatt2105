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
            "Authorization": "Bearer " + sessionStorage.getItem("token")
        },
    };
    return axios.get(baseurl + "/calculations", config)
}

export const calculateRequest = (calc, token) => {
    const calculation = JSON.stringify({ expression: calc });

    const config = {
        headers: {
            "Content-type": "application/json",
            "Authorization": "Bearer " + token
        },
    };
    console.log(token)
    return axios.post(baseurl + "/calculate", calculation, config)
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

// contents of utils/httputils.js file
import axios from "axios";

const baseurl = "http://localhost:8888";

export const submissionRequest = (user) => {
    const config = {
        headers: {
            "Content-type": "application/json",
        },
    };
    return axios.post(baseurl + "/submissions", user, config);
}

export const calculationRequest = (calculation) => {
    const config = {
        params: {
            "eq": calculation
        },
        headers: {
            "Content-type": "application/json",
        },
    };
    return axios.get(baseurl + "/calculate", config)
}

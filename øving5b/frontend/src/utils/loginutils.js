// contents of utils/loginutils.js file

export function isSubmittingSuccessful(responseStatus) {
    return responseStatus === 201;
}

export function isLoginSuccessful(response) {
    if (response == undefined || response == null ) return false;
    return response.status == 200;

}
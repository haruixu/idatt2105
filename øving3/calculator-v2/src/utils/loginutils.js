// contents of utils/loginutils.js file

export function isSubmittingSuccessful(response) {
    return response.status === 201;
}

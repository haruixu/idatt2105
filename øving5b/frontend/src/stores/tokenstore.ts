import { defineStore } from "pinia";
import { loginRequest, signupRequest } from '@/utils/httputils'
import { ref } from 'vue'

export const useTokenStore = defineStore("token", () => {
  const state = ref({
    jwtToken: null,
    loggedInUser: null,
  });


  /**
   * Signup token
   * @param userCredentials
   */
  const getToken = async  (userCredentials) => {
    try {
      const response = await signupRequest(userCredentials).data;
      const data = response.data;
      if (data != null && data != '' && data != undefined) {
        state.value.jwtToken = data;
      }
      return data;
    } catch (err) {
      console.log(err)
    }
  };

  /**
   * Login token
   * @param userCredentials
   */
  const getTokenAndSaveInStore = async (userCredentials) => {
    try {
      const response = await loginRequest(userCredentials);
      const data = response.data;
      if (data != null && data != '' && data != undefined) {
        state.value.jwtToken = data;
        state.value.loggedInUser = userCredentials.value.username;
      }
    } catch (err) {
      console.log(err)
    }
  };
  return {
    state,
    getToken,
    getTokenAndSaveInStore,
  };
});

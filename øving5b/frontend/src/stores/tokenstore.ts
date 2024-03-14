import { defineStore } from "pinia";
import { loginRequest } from '../utils/httputils.js'
import { ref } from 'vue'

export const useTokenStore = defineStore("token", () => {
  const state = ref({
    jwtToken: null,
    loggedInUser: null,
  });

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
    getTokenAndSaveInStore,
  };
});

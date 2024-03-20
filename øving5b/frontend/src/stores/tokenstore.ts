import { defineStore } from "pinia";
import { computed, ref } from 'vue'
import { loginRequest, signupRequest } from '@/utils/httputils'

export const useTokenStore = defineStore("token", () => {
  const state = ref({
    jwtToken: null,
    loggedInUser: null,
    });

    const hasLoggedInUser = computed(() => {
    return state.value.loggedInUser != null;
  });

  const clearTokenStore = () => {
    state.value.jwtToken = null;
    state.value.loggedInUser = null;
    sessionStorage.clear()
  }

  /**
   * Signup token
   * @param userCredentials
   */
  const getToken = async  (userCredentials) => {
      const response = await signupRequest(userCredentials);
        const data = response.data;
        const token = data.token;
        console.log(token)
      if (data != '' && data != undefined) {
          state.value.jwtToken = token;
        sessionStorage.setItem("token", token);
        sessionStorage.setItem("loggedInUser", userCredentials.username)
      }
      return token;
  };

  /**
   * Login token
   * @param userCredentials
   */
  const getTokenAndSaveInStore = async (userCredentials) => {
      const response = await loginRequest(userCredentials);
      const data = response.data;
      const token = data.token
      if (data != '' && data != undefined) {
        state.value.jwtToken = token;
        state.value.loggedInUser = userCredentials.username;
        sessionStorage.setItem("loggedInUser", userCredentials.username)
        sessionStorage.setItem("token", token)
        console.log(token)
      }
  };

  return {
    state,
    hasLoggedInUser,
    clearTokenStore,
    getToken,
    getTokenAndSaveInStore,
  };
});

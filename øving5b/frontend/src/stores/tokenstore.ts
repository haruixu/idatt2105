import { defineStore } from "pinia";
import { computed, ref } from 'vue'
import { loginRequest, signupRequest } from '@/utils/httputils'

export const useTokenStore = defineStore("token", () => {
  const state = ref({
    jwtToken: null,
    loggedInUser: null,
  });

  const hasLoggedInUser = computed(() => {
    return state.value.loggedInUser !== null;
  });

  const clearTokenStore = () => {
    state.value.jwtToken = null;
    state.value.loggedInUser = null;
  }

  /**
   * Signup token
   * @param userCredentials
   */
  const getToken = async  (userCredentials) => {
      const response = await signupRequest(userCredentials);
      const data = response.data;
      if (data != null && data != '' && data != undefined) {
        state.value.jwtToken = data;
      }
      return data;
  };

  /**
   * Login token
   * @param userCredentials
   */
  const getTokenAndSaveInStore = async (userCredentials) => {
      const response = await loginRequest(userCredentials);
      const data = response.data;
      if (data != null && data != '' && data != undefined) {
        state.value.jwtToken = data;
        state.value.loggedInUser = userCredentials.username;
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

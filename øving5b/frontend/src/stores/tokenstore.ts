import { defineStore } from "pinia";
import {getJwtToken} from "../utils/httputils.js"

export const useTokenStore = defineStore("token", {
  state: () => ({
    jwtToken: null,
    loggedInUser: null,
  }),

  actions: {
    async getTokenAndSaveInStore(username, password) {
      try{
        // TODO: change to login or signyup
        const response = await getJwtToken(username, password);
        const data = response.data;
        if(data != null && data != '' && data != undefined){
          this.jwtToken = data;
          this.loggedInUser = user
        }
      } catch (err){
        console.log(err)
      }
    }
  },
});

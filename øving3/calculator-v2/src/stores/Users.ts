import { ref, computed } from 'vue'
import { defineStore } from 'pinia'


export const useCounterStore = defineStore('users', () => {
  const count = ref([] as string[])
  function addForm() {
  }
  return { count, addForm }
})

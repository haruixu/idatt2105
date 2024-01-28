import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

interface User {
    name: string;
    email: string;
}

export const useUserStore = defineStore('users', () => {
    const users = ref<User[]>([]);
    function addForm(name: string, email: string): void {
        const newUser: User = { name, email };
        users.value = [...users.value, newUser];
    }

    return { users, addForm };
});
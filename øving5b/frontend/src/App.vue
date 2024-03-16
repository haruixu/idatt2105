<template>
    <nav>
      <RouterLink to="/calculator">Calculator</RouterLink>
      <RouterLink to="/forms">Forms</RouterLink>
      <button id="homeBtn" @click="switchHome">Home</button>
      <div id="topRight">
        <div v-if="!hasLoggedInUser">
          <button id="loginBtn" @click="switchLogin">Log in</button>
          <button id="signupBtn" @click="switchSignup">Sign up</button>
        </div>
        <div v-else>
          <button id="logoutBtn" @click="logout">Log out</button>
        </div>
      </div>
    </nav>
    <RouterView />

</template>

<style scoped>

#homeBtn {
  position: absolute;
  left: 10px;
}

#topRight {
  display: flex;
  flex-direction: row;
  position: absolute;
  right: 10px;
}

nav {
  display: flex;
  flex-direction: row;
  justify-content: center;
  column-gap: 5px;
}

nav a.router-link-exact-active {
  font-weight: bold;
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}


/*nav {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  padding: 1rem 0; // Add padding for better appearance
  z-index: 1000; // Ensure it's on top of other elements
}
nav a.router-link-exact-active {
  font-weight: bold; // Highlight the active link
}

@media (max-width: 600px) {
  nav {
    flex-direction: row;
    position: relative;
    justify-content: space-around;
    width: 100%;
  }

  nav a {
    margin: 0.5rem; // Reduce margin for smaller screens
  }
}*/

</style>

<script setup lang="ts">
  import { RouterLink, RouterView } from 'vue-router'
  import router from '@/router';
  import { useTokenStore } from '@/stores/tokenstore'

  const hasLoggedInUser = useTokenStore().hasLoggedInUser
  const switchHome = () => {
    router.push("/");
  }

  const switchLogin = () => {
    router.push("/login");
  }

  const switchSignup = () => {
    router.push("/signup");
  }

  const logout = () => {
    useTokenStore().clearTokenStore();
    switchLogin();
  }

</script>

<!--
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
-------------------
  <header>
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />

    <div class="wrapper">
      <HelloWorld msg="You did it!" />

      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView />
---------------
header {
  line-height: 1.5;
  max-height: 100vh;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
-->

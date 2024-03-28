<template>
  <div id="salamtransit-app">
    <div id="nav">
      <router-link :to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link :to="{ name: 'logout' }" v-if="$store.state.token">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

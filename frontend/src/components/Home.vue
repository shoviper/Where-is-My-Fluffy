<template>
  <div
    class="mx-auto my-32 border-solid border-2 border-slate-100 p-6 rounded-lg max-w-96 place-content-center bg-white"
  >
    <h1 class="text-PINK font-semibold text-2xl">Home</h1>
    <div v-if="user">
      <img :src="user.picture" alt="User Picture"  class="items-center"/>
      <h2 class="text-lg font-semibold">Welcome, {{ user.name }}!</h2>
      <p>Email: {{ user.email }}</p>
      <!-- <p>All data: {{ user }}</p> -->
      <button
        class="inline-flex items-center rounded-md bg-pink-50 px-2 py-1 text-xs font-medium text-pink-600 ring-1 ring-inset ring-pink-700/10"
        @click="logout"
      >
        Logout
      </button>
    </div>
    <div v-else>
      <p>Loading user profile...</p>
    </div>
    <button
      class="inline-flex items-center rounded-md bg-pink-50 px-2 py-1 text-xs font-medium text-pink-600 ring-1 ring-inset ring-pink-700/10"
      @click="goToLogin"
    >
      Login
    </button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      user: null,
    };
  },
  mounted() {
    this.fetchUserProfile();
  },
  methods: {
    async fetchUserProfile() {
      try {
        const response = await axios.get("http://localhost:8080/profile", {
          withCredentials: true,
        });
        this.user = response.data;
      } catch (error) {
        console.error("Error fetching user profile:", error);
      }
    },

    goToLogin() {
      this.$router.push("/login");
    },

    logout() {
      window.location.href = "http://localhost:8080/logout";
    },
  },
};
</script>

<template>
  <div
    class="mx-auto my-32 border-solid border-2 border-slate-100 p-6 rounded-lg max-w-96 place-content-center bg-white flex flex-col items-center justify-center "
  >
    <h1 class="text-pink-500 font-semibold text-2xl">Home</h1>
    
    <!-- Show user profile and logout button if user is logged in -->
    <div v-if="user" class="flex flex-col items-center justify-center">
      <img :src="user.picture" alt="User Picture" class="w-18 h-18 rounded-full mb-4"/>
      <h2 class="text-lg font-semibold">Welcome, {{ user.name }}!</h2>
      <p>Email: {{ user.email }}</p>
      <button
        class="mt-4 items-center rounded-md bg-pink-50 px-4 py-2 text-xs font-medium text-pink-600 ring-1 ring-inset ring-pink-700/10"
        @click="logout"
      >
        Logout
      </button>
    </div>

    <!-- Show login button if user is not logged in -->
    <div v-else>
      <button
        class="inline-flex items-center rounded-md bg-pink-50 px-4 py-2 text-xs font-medium text-pink-600 ring-1 ring-inset ring-pink-700/10"
        @click="goToLogin"
      >
        Login
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      user: null,  // Initially, the user is not logged in
    };
  },
  mounted() {
    this.fetchUserProfile();  // Fetch user profile when component is mounted
  },
  methods: {
    async fetchUserProfile() {
      try {
        const response = await axios.get("http://localhost:8080/profile", {
          withCredentials: true,
        });
        this.user = response.data;  // Set user data if logged in
      } catch (error) {
        console.error("Error fetching user profile:", error);
        this.user = null;  // Ensure user is null if there's an error
      }
    },

    goToLogin() {
      this.$router.push("/login");  // Redirect to the login page
    },

    logout() {
      axios.get("http://localhost:8080/logout", { withCredentials: true })
        .then(() => {
          this.user = null;  // Clear the user data on logout
          this.$router.push("/login");  // Optionally redirect to login after logout
        })
        .catch(error => {
          console.error("Error logging out:", error);
        });
    },
  },
};
</script>

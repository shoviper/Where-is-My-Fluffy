<template>
  <div
    class="mx-auto my-32 border-solid border-2 border-slate-100 p-6 rounded-lg max-w-96 place-content-center bg-white flex flex-col items-center justify-center"
  >
    <h1 class="text-pink-500 font-semibold text-2xl">Home</h1>

    <!-- Show user profile and logout button if user is logged in -->
    <div v-if="user" class="flex flex-col items-center justify-center">
      <img
        :src="user.picture"
        alt="User Picture"
        class="w-18 h-18 rounded-full mb-4"
      />
      <h2 class="text-lg font-semibold">Welcome, {{ user.name }}!</h2>
      <p>Email: {{ user.email }}</p>
      <div>
        <!-- Modal toggle -->
        <button
          @click="toggleModal"
          class="mt-4 mr-4 items-center rounded-md bg-pink-50 px-4 py-2 text-xs font-medium text-pink-600 ring-1 ring-inset ring-pink-700/10"
        >
          Add pet
        </button>

        <!-- Show AddPetModal component when modal is visible -->
        <AddPetModal
          v-if="isModalVisible"
          :pettypes="pettypes"
          @close="toggleModal"
        />

        <button
          class="mt-4 items-center rounded-md bg-pink-50 px-4 py-2 text-xs font-medium text-pink-600 ring-1 ring-inset ring-pink-700/10"
          @click="logout"
        >
          Logout
        </button>
      </div>
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
import AddPetModal from "./AddPetModal.vue"; // Import the new component

export default {
  components: {
    AddPetModal,
  },
  data() {
    return {
      user: null,
      isModalVisible: false,
    };
  },
  mounted() {
    this.fetchUserProfile();
  },
  methods: {
    async fetchUserProfile() {
      try {
        const response = await axios.get("http://localhost:8080/users/me", {
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
      axios
        .get("http://localhost:8080/logout", { withCredentials: true })
        .then(() => {
          this.user = null;
          this.$router.push("/login");
        })
        .catch((error) => {
          console.error("Error logging out:", error);
        });
    },
    toggleModal() {
      this.isModalVisible = !this.isModalVisible;
    },

  },
};
</script>

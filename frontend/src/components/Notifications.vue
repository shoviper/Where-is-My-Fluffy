<template>
  <div class="bg-white p-4 mt-4 rounded-md">
    <!-- Header -->
    <div class="flex justify-between items-center mb-4">
      <h2 class="text-base font-semibold">Notifications</h2>
      <a href="#" class="text-base text-red-500">Mark all as read</a>
    </div>

    <!-- Notifications List -->
    <div class="space-y-4">
      

      <div class="border-t border-gray-300 py-2" v-for="(mesg, index) in notification" :key="index">
        <div>
          <p class="text-sm text-TEXTCOLOR text-left">
            {{ mesg.message }}
          </p>
          <!-- <p class="text-xs text-gray-500 text-left">Last Wednesday at 11:00 p.m.</p> -->
        </div>
      </div>

      
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: "d",
  components: {},
  data() {
    return {
      notification: [],
    };
  },
  mounted() {
    this.getPetType();
    // this.addPet();
  },
  methods: {
    async getPetType() {
      try {
        const response = await axios.get("http://localhost:8080/notifications/me", {
          withCredentials: true,
        });
        this.notification = response.data;
      } catch (error) {
        console.error("Error fetching animal types:", error);
      }
    },
  },
};
</script>
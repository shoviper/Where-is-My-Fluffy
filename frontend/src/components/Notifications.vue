<template>
  <div class="bg-white px-4 pb-4 mt-4 rounded-md  h-80 overflow-y-auto scroll-smooth ">
    <!-- Header -->
    <div class="flex justify-between bg-white py-6 items-center m sticky top-0">
      <h2 class="text-base font-semibold">Notifications</h2>
      <a href="/seeallnotification" class="text-base text-red-500">Mark all as read</a>
    </div>

    <!-- Notifications List -->
    <div class="space-y-4 ">
      

      <div class="border-t border-gray-300 py-2 cursor-pointer" v-for="(mesg, index) in notification" :key="index">
        <div
          :class="{ 'cursor-not-allowed': mesg.notificationType !== 'NOTIFICATION_PENDING' }"
          @click="mesg.notificationType === 'NOTIFICATION_PENDING' && goto({ path: `/seeeachnotification/${mesg.id}` })"
        >
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
    
    goto(page) {
          if (page.name && page.name !== this.$route.name) {
            this.$router.push({ name: page.name });
            return;
          }
          if (page.path && page.path !== this.$route.path) {
            this.$router.push({ path: page.path });
            return;
          }
        },
  },
};
</script>
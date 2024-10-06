<template>
    <div class="min-h-screen bg-[#FFF3EB] flex flex-col items-center p-6">
      <!-- Back button -->
      <button
        class="mt-24 mb-4 text-sm text-blue-600 flex items-center"
        @click="goto({ path: `/mainpage` })"
      >
        <Icon icon="mdi:chevron-left" class="w-5 h-5 mr-1" /> Back to notifications
      </button>
  
      <!-- Notification details box -->
      <div class="w-full max-w-2xl bg-white shadow-md rounded-lg p-6">
        <!-- Title section -->
        <div class="mb-4 border-b pb-2">
          <h2 class="text-2xl font-semibold text-gray-800">
            {{ notification.title }}
          </h2>
        </div>
  
        <!-- Message section -->
        <div class="mb-4">
            <p class="text-lg text-gray-500 mb-2">{{ notification.from }}</p>
            <p class="text-lg text-gray-500 mb-2">{{ notification.to }}</p>
            <p class="text-base text-gray-700 whitespace-pre-line">
                {{ notification.message }}
            </p>
            <div class="mt-4">
                <a href="#"
                    class="py-3 px-6 ml-2 text-sm font-medium text-white bg-green-600 rounded-lg hover:bg-green-700"
                    @click="goto({ path: '/payment' })"
                    >
                    Accept
                </a>
                <a href="#"
                    class="py-3 px-6 ml-2 text-sm font-medium text-white bg-red-600 rounded-lg hover:bg-red-700">
                    Reject
                </a>
            </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import { Icon } from "@iconify/vue";
  
  export default {
    name: 'seeeachnotification',
    components: {
      Icon,
    },
    data() {
      return {
        notification: {},
      };
    },
    mounted() {
      const notificationId = this.$route.params.id; // Get the ID from the route params
      this.fetchNotification(notificationId);
    },
    methods: {
      async fetchNotification(id) {
        try {
          const response = await axios.get(`http://localhost:8080/notifications/${id}`, {
            withCredentials: true,
          });
  
          const data = response.data;
          this.notification = {
            title: `Your pet has been found!`,
            from: `From: pet founder`,
            to: `To: ${data.notificationOwner.name}`,
            message: `${data.message}
            Please confirm that if this is your pet`,
            type: data.notificationType,
            // owner: `${data.notificationOwner.name} (${data.notificationOwner.email})`,
            timestamp: data.timestamp || null,
          };
        } catch (error) {
          console.error('Error fetching notification details:', error);
        }
      },
      formatDate(dateString) {
        if (!dateString) return 'No date available';  
        const date = new Date(dateString);
        return date.toLocaleString("en-US", {
          year: "numeric",
          month: "long",
          day: "numeric",
          hour: "2-digit",
          minute: "2-digit",
          second: "2-digit",
          hour12: true,
        });
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
  
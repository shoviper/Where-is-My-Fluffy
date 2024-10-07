<template>
    <div class="min-h-screen bg-[#FFF3EB] flex flex-col items-center p-6">
      <h2 class="text-2xl font-semibold mb-4">Payment Details</h2>
      
      <div class="w-full max-w-md bg-white shadow-md rounded-lg p-6">
        <h3 class="text-lg font-semibold">Notification ID: {{ notificationId }}</h3>
        <p class="text-gray-600 mb-4">Reward Amount: ${{ rewardAmount }}</p>
        <!-- Additional payment details can go here -->
  
        <button
          class="py-3 px-6 text-sm font-medium text-white bg-blue-600 rounded-lg hover:bg-blue-700"
          @click="confirmPayment"
        >
          Confirm Payment
        </button>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'payment',
    data() {
      return {
        notificationId: this.$route.params.id, // Get notification ID from route parameters
        rewardAmount: 0, // Default reward amount
      };
    },
    mounted() {
      this.fetchPaymentDetails();
    },
    methods: {
      async fetchPaymentDetails() {
        try {
          const response = await axios.get(`http://localhost:8080/notifications/${this.notificationId}`, {
            withCredentials: true,
          });
          // Assuming the reward amount is in the response
          this.rewardAmount = response.data.rewardAmount; // Adjust based on your API response
        } catch (error) {
          console.error('Error fetching payment details:', error);
        }
      },
      confirmPayment() {
        // Logic for confirming payment goes here
        console.log('Payment confirmed for Notification ID:', this.notificationId);
        // Redirect or perform any other actions as needed
      },
    },
  };
  </script>
  
<template>
  <div class="min-h-screen bg-[#FFF3EB] flex flex-col items-center p-6">
    <!-- Title -->
    <h2 class="text-2xl font-semibold mb-4">Payment Details</h2>

    <!-- Payment details box -->
    <div class="w-full max-w-md bg-white shadow-md rounded-lg p-6">
      <!-- Display Notification ID -->
      <h3 class="text-lg font-semibold">Notification ID: {{ notificationId }}</h3>

      <!-- Reward Amount -->
      <p class="text-gray-600 mb-4">Reward Amount: ${{ rewardAmount }}</p>
      
      <!-- Owner's Name and Pet Information -->
      <div v-if="notificationOwner">
        <p class="text-gray-600 mb-4">Pet Owner: {{ notificationOwner.name }}</p>
      </div>

      <!-- Confirm Payment Button -->
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
      rewardAmount: null, // Default reward amount
      notificationOwner: null, // Store pet owner information
    };
  },
  mounted() {
    this.fetchPaymentDetails();
  },
  methods: {
    async fetchPaymentDetails() {
      try {
        const response = await axios.get(`http://localhost:8080/notifications/${id}`, {
          withCredentials: true,
        });

        return response.data;

      } catch (error) {
        console.error('Error fetching payment details:', error);
      }
    },
    confirmPayment() {
      // Logic for confirming payment goes here
      console.log('Payment confirmed for Notification ID:', this.notificationId);
      // Redirect or perform any other actions as needed after payment confirmation
    },
  },
};
</script>

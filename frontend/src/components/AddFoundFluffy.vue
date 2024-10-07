<template>
    <div class="flex items-center justify-center min-h-screen">
      <div class="bg-white p-6 rounded-lg shadow-lg max-w-md w-full">
        <!-- TITLE -->
        <label class="block text-gray-700 text-sm font-bold mb-2">Title</label>
        <input
          v-model="modalData.title"
          class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          type="text"
          placeholder="Enter title"
        />
  
        <!-- MESSAGE -->
        <label class="block text-gray-700 text-sm font-bold mt-4 mb-2">Message</label>
        <!-- Only render the user.id when user is not null -->
        <p v-if="user">User ID: {{ user.id }}</p>
        <textarea
          v-model="modalData.message"
          class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          placeholder="Enter message"
        ></textarea>
  
        <!-- IMAGE -->
        <label class="block text-gray-700 text-sm font-bold mt-4 mb-2">Upload Image</label>
        <input
          @change="onFileChange"
          ref="file_input"
          class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          type="file"
          accept="image/*"
        />
  
        <div class="mt-4 flex justify-end">
          <button @click="sendMessage" class="px-4 py-2 bg-pink-500 text-white rounded-lg">
            Send
          </button>
          <button @click="closeModal" class="px-4 py-2 bg-gray-600 text-white rounded-lg ml-2">
            Close
          </button>
        </div>
      </div>
    </div>
  </template>
  <script>
  import axios from "axios";
  
  export default {
    name: "AddFoundFluffy",
    props: {
      postId: {
        type: Number,
        required: true,
      },
      userId: {
        type: Number,
        required: true,
      },
    },
    data() {
      return {
        modalData: {
          title: "",
          message: "",
          image: null,
        },
        rewardAmountToPay: null, // Assuming 0 by default
        user: null, // Initially null, will be set by the GET request
      };
    },
    mounted() {
      this.fetchUserProfile();
    },
    methods: {
      // Fetch the notification sender ID (user ID) from the /users/me endpoint
      async fetchUserProfile() {
        try {
          const response = await axios.get("http://localhost:8080/users/me", {
            withCredentials: true,
          });
          this.user = response.data; // Set the fetched user data
          console.log("Fetched user profile:", this.user);
        } catch (error) {
          console.error("Error fetching user profile:", error);
        }
      },

      onFileChange(event) {
        this.modalData.image = event.target.files[0]; // Store the selected file in modalData
      },

      async fetchReward() {
      try {
        const response = await axios.get('http://localhost:8080/posts', {
          params: {
            page: 0, // you can adjust this as needed
            size: 10, // adjust as needed
            sortBy: 'id',
            sortDir: 'asc',
          },
          withCredentials: true, // make sure "true" is lowercase
        });
        // Assuming you want the first post's reward amount
        return response.data[0].rewardAmount; // Modify this if you want a different post's reward
      } catch (error) {
        console.log("Error fetching reward", error);
        return null;
      }
    },

  
    async sendMessage() {
      if (!this.user || !this.user.id) {
        console.error("Notification sender ID is not set yet.");
        return;
      }

      if (!this.modalData.image) {
        alert("Please upload an image before sending the message.");
        return;
      }

      console.log("Sending message...");
      console.log("Post ID:", this.postId);
      console.log("User ID:", this.userId);
      console.log("Notification Sender ID:", this.user.id);

      try {
        // Fetch the reward data first
        const rewardAmount = await this.fetchReward();
        
        if (rewardAmount === null) {
          alert("Failed to fetch reward amount");
          return;
        }

        const notificationResponse = await axios.post(
          `http://localhost:8080/notifications/${this.userId}/user`,
          {
            title: this.modalData.title,
            message: this.modalData.message,
            notificationType: "NOTIFICATION_PENDING",
            notificationSenderId: this.user.id, // Sender ID from GET request
            rewardAmountToPay: rewardAmount, // Use the rewardAmount from fetchReward
          }
        );
        console.log("Notification sent:", notificationResponse.data);

        const notificationId = notificationResponse.data.id;
      console.log("Notification created successfully:", notificationResponse.data);
      

        // Step 2: Upload the image with the notificationId
        const formData = new FormData();
        formData.append("file", this.modalData.image);

        await axios.put(
          `http://localhost:8080/notifications/${notificationId}/uploadImage`,
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
            withCredentials: true,
          }
        );

        console.log("Image uploaded successfully");
  
          // Close the modal after successful operations
          this.closeModal();
        } catch (error) {
          console.error("Error sending message or notification:", error);
        }
      },
  
      closeModal() {
        this.$emit("close");
      },
    },
  };
  </script>
  
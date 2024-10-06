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
      <label class="block text-gray-700 text-sm font-bold mt-4 mb-2"
        >Message{{ postId }}   </label
      >
      <textarea
        v-model="modalData.message"
        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
        placeholder="Enter message"
      ></textarea>

      <!-- IMAGE -->
      <label class="block text-gray-700 text-sm font-bold mt-4 mb-2"
        >Upload Image</label
      >
      <input
        @change="onFileChange"
        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
        type="file"
        accept="image/*"
      />

      <div class="mt-4 flex justify-end">
        <button
          @click="sendMessage"
          class="px-4 py-2 bg-pink-500 text-white rounded-lg"
        >
          Send
        </button>
        <button
          @click="closeModal"
          class="px-4 py-2 bg-gray-600 text-white rounded-lg ml-2"
        >
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
    postId: { // Add prop to receive postId from parent
      type: Number,
      required: true,
    },
  },
  data() {
    return {
      posts: [],
      modalData: {
        title: "",
        message: "",
        image: null,
      },
    };
  },
  mounted() {
  },
  methods: {
    async sendMessage() {
        console.log('Sending message...');
      console.log('Post ID:', this.postId); // Verify the correct postId is being passed
      console.log('Data:', this.modalData);  // Verify that the form data is correct
      try {
        // Perform the PUT request with the postId
        const response = await axios.put(`http://localhost:8080/posts/${this.postId}`, {
          title: this.modalData.title,
          content: this.modalData.message,
          type: 'MISSING', // You can dynamically assign the type as needed
          petId: this.postId, // Assuming you want to use the postId as petId
        });

        console.log('Response:', response.data);
        this.closeModal();
      } catch (error) {
        console.error('Error sending message:', error);
      }
    },
    closeModal() {
      this.$emit("close"); // Emit the close event to the parent
    },


  },
};
</script>

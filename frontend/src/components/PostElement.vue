<template>
  <div>
    <div v-if="showModal" class="fixed z-10 inset-0 overflow-y-auto">
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

      <!-- MEAASAGE -->
      <label class="block text-gray-700 text-sm font-bold mt-4 mb-2">Message</label>
      <textarea 
        v-model="modalData.message" 
        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" 
        placeholder="Enter message"
      ></textarea>

      <!-- IMAGE -->
      <label class="block text-gray-700 text-sm font-bold mt-4 mb-2">Upload Image</label>
      <input 
        @change="onFileChange" 
        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" 
        type="file" 
        accept="image/*"
      />

      <div class="mt-4 flex justify-end">
        <button @click="sendMessage" class="px-4 py-2 bg-PINK text-white rounded-lg">
          Send
        </button>
        <button @click="closeModal" class="px-4 py-2 bg-gray-600 text-white rounded-lg ml-2">
          Close
        </button>
      </div>
    </div>
  </div>
</div>
  </div>
  <div
    v-for="(post, index) in posts"
    :key="index"
    class="block p-6 bg-white border-gray-200 rounded-lg shadow my-4"
  >
    <div class="flex justify-between h-10">
      <div class="flex flex-row items-center">
        <Icon
          icon="bx:bx-user"
          class="text-4xl text-gray-700 dark:text-gray-400 p-1 bg-black rounded-full"
        />
        <div class="ml-2">
          <h5 class="text-base font-bold text-TEXTCOLOR">
            {{ post.user.name }}
          </h5>
          <p class="text-sm text-gray-400 text-left">
            {{ formatTimestamp(post.timestamp) }}
          </p>
        </div>
      </div>
      <button
        class="inline-flex items-center justify-center rounded-md bg-PINK hover:bg-PURPLE px-2 py-1 my-2 text-sm font-medium text-white ring-1 ring-inset ring-pink-700/10"
        @click="openModal(post)"
      >
        Found Fluffy !
      </button>
    </div>
    <p class="font-normal text-left mt-4 text-TEXTCOLOR">
      {{ post.content }}
    </p>
    <div class="w-full h-64 mt-4">
      <img
        v-if="post.petImage"
        :src="post.petImage"
        alt="Pet image"
        class="w-full h-full object-contain rounded-lg"
      />
      <Icon v-else icon="bx:bx-image" class="w-full h-64" />
    </div>
    <!-- NOT MOCK -->
    <!-- <p v-if="post.reward" class="text-lg text-green-600 mt-4">
      Reward: ฿{{ post.reward }}
    </p> -->
    <!-- MOCK -->
    <p class="text-lg text-red-500 mt-4">
      <!-- Reward: ฿{{ post.reward }} -->
      Reward: ฿500
    </p>
    <div class="flex justify-between border-t">
      <!-- <div class="flex flex-row items-center mt-2">
        <Icon
          icon="mdi:heart-outline"
          class="text-red-500 hover:text-red-950"
        />
        <p class="mx-2">{{ post.likes }}</p>
      </div>
      <div class="flex flex-row mt-2">
        <p class="hover:text-PINK">{{ post.comments }} comments</p>
        <p class="mx-2 hover:text-PINK">{{ post.shares }} shares</p>
      </div> -->
    </div>
  </div>
</template>
<script>
import { Icon } from "@iconify/vue";
import axios from "axios";

export default {
  name: "PostElement",
  components: {
    Icon,
  },
  data() {
  return {
    posts: [],
    showModal: false,
    modalData: {
      title: '',   
      message: '',
      image: null 
    }
  };
},
  mounted() {
    this.fetchPosts();
  },
  methods: {
    async fetchPosts() {
      try {
        const response = await axios.get(
          "http://localhost:8080/posts?page=0&size=10&sortBy=id&sortDir=asc",
          {
            headers: {
              Accept: "application/json",
            },
          }
        );

        const posts = response.data;

        // Fetch pet images for each post individually
        for (let post of posts) {
          if (post.pet && post.pet.id) {
            const petResponse = await this.fetchPetImage(post.pet.id);
            if (petResponse && petResponse.image) {
              post.petImage = `data:image/jpeg;base64,${petResponse.image}`; // Add the pet image to the post
            }
          }
        }

        this.posts = posts; // Update posts with images
        console.log("Posts with pet images:", this.posts);
      } catch (error) {
        console.error(
          "Error fetching posts:",
          error.response?.data || error.message
        );
      }
    },
    async fetchPetImage(petId) {
      try {
        const response = await axios.get(`http://localhost:8080/pets/${petId}`);
        const data = response.data;
        console.log(data.image);
        return data; // Return pet data including the image
      } catch (error) {
        console.error(`Error fetching pet image for pet ID ${petId}:`, error);
        return null;
      }
    },
    formatTimestamp(timestamp) {
      const date = new Date(timestamp);
      return date.toLocaleString("en-US", {
        year: "numeric",
        month: "long",
        day: "numeric",
        hour: "2-digit",
        minute: "2-digit",
        second: "2-digit",
        hour12: true, // Use 12-hour format
      });
    },
    openModal() {
      this.modalData = {
      title: '',       
      message: '',     
      imageFile: null
    };
    this.showModal = true; 
  },

  closeModal() {
    this.showModal = false;
  },

  sendMessage() {
    alert('Message sent with title: ' + this.modalData.title);
    this.closeModal(); 
  }
},
};
</script>
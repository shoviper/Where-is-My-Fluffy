<template>
  <div>
    <div v-if="showModal" class="fixed z-10 inset-0 overflow-y-auto">
      <AddFoundFluffy @close="closeModal" :post-id="selectedPostId"/>
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
    <div class="flex justify-between border-t"></div>
  </div>
</template>
<script>
import { Icon } from "@iconify/vue";
import axios from "axios";
import AddFoundFluffy from "./AddFoundFluffy.vue";
export default {
  name: "PostElement",
  components: {
    Icon,
    AddFoundFluffy
  },
  data() {
    return {
      posts: [],
      showModal: false,
      selectedPostId: null,
      modalData: {
        title: "",
        message: "",
        image: null,
      },
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
    openModal(post) {
      this.modalData = {
        title: "",
        message: "",
        imageFile: null,
      };
      
      this.selectedPostId = post.id;
      this.showModal = true;
    },

    closeModal() {
      this.showModal = false;
      this.selectedPostId = null
    },

    sendMessage() {
      alert("Message sent with title: " + this.modalData.title);
      this.closeModal();
    },
  },
};
</script>

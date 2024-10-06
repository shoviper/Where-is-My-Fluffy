<template>
  <div class="min-h-screen bg-[#FFF3EB] flex flex-col">
    <header class="bg-[#FFF3EB] w-full z-10 fixed top-0 left-0">
      <div
        class="mx-auto px-8 flex items-center justify-between md:h-20 w-full bg-[#fff3eb]"
      >
        <img src="../assets/fluffy.png" alt="home pic" class="w-32 h-16" />
        <button
          class="w-10 h-10 bg-white rounded-full text-white flex items-center justify-center"
        >
          <Icon icon="tabler:home" class="text-slate-700 w-10 h-7" />
        </button>

        <div class="flex-grow mx-4">
          <input
            type="text"
            placeholder="Where's My Fluffy"
            class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
          />
        </div>

        <button
          class="w-10 h-10 bg-white rounded-full text-white flex items-center justify-center"
        >
          <Icon
            icon="iconamoon:profile"
            class="text-slate-700 w-10 h-7"
            @click="goto({ path: '/' })"
          />
        </button>
      </div>
    </header>
    
    <div class="flex-grow flex p-4 gap-4 h-full mt-12">
      <div class="w-1/2 py-4 pr-4 flex-grow flex flex-col items-center">
        <div class="w-11/12 h-auto bg-white p-4 rounded-md">
          <div class="flex justify-between items-center mb-4">
            <h2 class="text-2xl font-semibold">Lost Pets</h2>
            <a href="/mainpage" class="text-base text-blue-500">Back</a>
          </div>

          <div class="flex flex-wrap -mx-2">
            <!-- Loop through filtered pets with status 'MISSING' -->
            <div
              v-for="(pet, index) in filteredPets"
              :key="index"
              class="w-full sm:w-1/2 lg:w-1/4 px-2 mb-4 flex items-center py-2"
            >
              <div class="w-full max-w-sm bg-white border border-black rounded-lg">
                <div class="flex justify-end px-4 pt-4"></div>
                <div class="flex flex-col items-center pb-10">
                  <!-- Modify the img tag to use base64 or the direct image URL from the pet object -->
                  <img
                    :src="`data:image/jpeg;base64,${pet.image}`"
                    :alt="`Image of ${pet.name}`"
                    class="w-20 h-20 object-cover rounded-md"
                  />
                  <h5 class="mt-4 mb-1 text-xl font-medium text-black dark:text-black">
                    {{ pet.name }}
                  </h5>
                  <span class="text-md text-gray-500 dark:text-gray-400">{{ pet.age }} years old</span>
                  <div class="flex mt-4 md:mt-6">
                    <a href="#"
                      class="inline-flex items-center px-4 py-2 text-sm font-medium text-center text-white bg-[#FB7091] rounded-lg hover:bg-[#E15F81] focus:ring-4 focus:outline-none focus:ring-[#FB7091]">
                      Found Fluffy!
                    </a>
                    <a href="#"
                      class="py-2 px-4 ml-2 text-sm font-medium text-white focus:outline-none bg-[#8761A5] rounded-lg border border-[#8761A5] hover:bg-[#755297] focus:z-10 focus:ring-4 focus:ring-[#755297]">
                      Message
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Icon } from "@iconify/vue";

export default {
  name: "seeallpet",
  components: {
    Icon,
  },
  data() {
    return {
      pets: [], 
    };
  },
  computed: {
    filteredPets() {
      return this.pets.filter(pet => pet.status === 'MISSING');
    }
  },
  mounted() {
    this.getAllPets();
  },
  methods: {
    async getAllPets() {
      try {
        const response = await axios.get("http://localhost:8080/pets");
        const data = await response.data;
        // Assuming that the API response includes the image field in Base64 format
        this.pets = data;
      } catch (error) {
        console.error("Error fetching pets:", error.response ? error.response.data : error.message);
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

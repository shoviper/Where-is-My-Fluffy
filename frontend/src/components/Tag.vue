<template>
  <div class="px-10">
    <p class="font-semibold text-gray-500 mb-2 flex items-start">
      All animal types
    </p>
    <div class="flex flex-wrap space-x-2">
      <!-- Flex container to arrange items in a row -->
      <div v-for="(pettag, index) in tag" :key="index" class="mb-2">
        <!-- {{ pettag }} -->
        <a
          href="#"
          class="bg-white hover:bg-red-100 text-pink-400 text-xs font-semibold px-3 py-1 rounded border border-pink-100 inline-flex items-center justify-center"
        >
          {{ pettag.type }}
        </a>
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
      tag: [],
    };
  },
  mounted() {
    this.getPetType();
    // this.addPet();
  },
  methods: {
    async getPetType() {
      try {
        const response = await axios.get("http://localhost:8080/animal-type", {
          withCredentials: true,
        });
        this.tag = response.data;
      } catch (error) {
        console.error("Error fetching animal types:", error);
      }
    },
  },
};
</script>

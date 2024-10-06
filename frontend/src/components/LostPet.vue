<template>
  <div class="w-5/6 h-auto bg-white p-4 rounded-md">
    <div class="flex justify-between items-center mb-4">
      <h2 class="text-base font-semibold">Lost Pets</h2>
      <a href="/seealllostpet" class="text-base text-blue-500">See all</a>
    </div>
    <div>
      <div
        v-for="(pet, index) in limitedPets"
        :key="index"
        class="flex items-center border-t border-gray-300 py-2"
      >
        <img
          :src="pet.image ? `data:image/jpeg;base64,${pet.image}` : '../assets/pets/default-pet-image.png'"
          alt="Pet Image"
          class="w-11 h-11 object-cover rounded-md"
        />
        <div class="flex flex-col items-start pl-2">
          <p class="text-base font-semibold">{{ pet.name }}</p>
          <p class="text-sm text-gray-500">{{ pet.age }} years old</p>
        </div>
      </div>
      <div v-if="limitedPets.length === 0" class="text-gray-500">
        No missing pets to display.
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "LostPet",
  data() {
    return {
      pets: [],
    };
  },
  computed: {
    limitedPets() {
      return this.pets.slice(0, 7);
    },
  },
  mounted() {
    this.seeallLostPets();
  },
  methods: {
    async seeallLostPets() {
      try {
        const response = await axios.get("http://localhost:8080/pets", {
          withCredentials: true,
        });
        const data = response.data.filter((pet) => pet.status === "MISSING");
        this.pets = data;
      } catch (error) {
        console.error("Error fetching pets:", error);
      }
    },
  },
};
</script>

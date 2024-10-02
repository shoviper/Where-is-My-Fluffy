<template>
  <div class="w-10/12 h-auto bg-white p-4 rounded-md">
    <div class="flex justify-between items-center mb-4">
      <h2 class="text-base font-semibold">Lost Pets</h2>
      <a href="/seealllostpet" class="text-base text-blue-500">See all</a>
    </div>
    <div>
      <!-- <LostPetComponent /> -->
      <!-- Loop through pets data -->
      <div
        v-for="(pet, index) in pets"
        :key="index"
        class="flex items-center border-t border-gray-300 py-2"
      >
        <!-- <img :src="pet.img" :alt="`Image of ${pet.name}`" class="w-11 h-11 object-cover rounded-md" /> -->
        <img
          src="../assets/pets/petpic1.png"
          alt="Pet 1"
          class="w-11 h-11 object-cover rounded-md"
        />
        <div class="flex flex-col items-start pl-2">
          <p class="text-base font-semibold l-0">{{ pet.name }}</p>
          <p class="text-sm text-gray-500">missing on {{ pet.date }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import LostPetComponent from "./LostPetComponent.vue";
export default {
  name: "d",
  components: {
  },
  data() {
    return {
      pets: [
        { name: "Lola", date: "21/07/23", img: "../assets/pets/petpic1.png" },
        { name: "Lucy", date: "02/12/18", img: "../assets/pets/petpic2.png" },
        { name: "Sweed", date: "29/09/23", img: "../assets/pets/petpic3.png" },
        { name: "Khun Somsri", date: "11/01/24", img: "../assets/pets/petpic4.png" },
      ],
    };
  },
};
</script>

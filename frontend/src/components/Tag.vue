<template>
  <div class="px-10">
    <p class="font-semibold text-gray-500 mb-2 flex items-start">All animal types</p>
    <div class="flex flex-wrap space-x-2"> <!-- Flex container to arrange items in a row -->
      <div v-for="(pettag, index) in tag" :key="index" class="mb-2">
        <a
          href="#"
          class="bg-white hover:bg-red-100 text-pink-400 text-xs font-semibold  px-3 py-1 rounded border border-pink-100 inline-flex items-center justify-center"
        >
          {{ pettag.name }}
        </a>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "d",
  components: {},
  data() {
    return {
      tag: [
        { name:"Dog" },
        { name: "Cat"},
        { name:"Rabbit" },
        { name: "Hasmter"},
        { name:"Bird" },
        { name: "Horse"},
      ],
    };
  },
};
</script>

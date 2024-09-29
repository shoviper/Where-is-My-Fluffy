<template>
  <div class="w-1/4 h-2/4 bg-white p-4 shadow-md flex-grow mt-4 rounded-md">
    <div class="flex justify-start items-center mb-4">
      <h2 class="text-base font-semibold">Trends for you</h2>
    </div>
    <div>
      <div
        v-for="(trend, index) in pets"
        :key="index"
        class="flex justify-between border-t  border-gray-300 py-2"
      >
        <div class="flex flex-col items-start px-2">
          <p class="text-sm">Trending in {{ trend.category }}</p>
          <p class="text-base font-semibold">{{ trend.tag }}</p>
          <p class="text-sm text-gray-500">{{ trend.numpost }} posts</p>
        </div>
        <div><Icon icon="ri:more-line" class="hover:text-PURPLE" /></div>
      </div>
    </div>
    <h2 class="text-base text-start text-PINK hover:text-PURPLE font-bold ite">Show more</h2>
  </div>
</template>
<script>
import { Icon } from "@iconify/vue";
export default {
  name: "d",
  components: {
    Icon,
  },
  data() {
    return {
      pets: [
        { tag: "#GoldenRetiever", category: "Dog", numpost: "2,066" },
        { tag: "#OrangeCat", category: "Cat", numpost: "1,978" },
        { tag: "#Bangkok", category: "Rabbit", numpost: "567" },
        { tag: "#SQUID", category: "Hamster", numpost: "237" },
      ],
    };
  },
};
</script>

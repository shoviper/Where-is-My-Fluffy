<template>
  <div class="min-h-screen bg-[#FFF3EB] flex flex-col">
    <header class="bg-[#FFF3EB] py-5">
      <div class="max-w-7xl mx-auto px-4 flex items-center justify-between">
        <img src="../assets/fluffy.png" alt="home pic" class="w-32 h-16" />
        <button
          class="w-10 h-10 bg-white rounded-full text-white flex items-center justify-center"
        >
        <Icon icon="tabler:home"  class="text-slate-700 w-10 h-7"/>
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
          <Icon icon="iconamoon:profile" class="text-slate-700 w-10 h-7"/>
        </button>
      </div>
    </header>

    <div class="flex-grow flex p-4 gap-4 h-full">
      <div class="w-1/4 bg-[#FFF3EB] p-4 flex-grow flex flex-col items-center">
        <!-- Sidebar 1 - Lost Pets -->
        <LostPets />

        <!-- Sidebar 2 - Notifications -->
        <Notifiactions />
      </div>

      <!-- Main Content Area (Scrollable) -->
      <div class="w-1/2 bg-white p-4 shadow-md flex-grow mt-4 overflow-y-auto h-[80vh]">
      </div>

      <!-- Sidebar 3 - Trend and Category -->
      <Trends />
    </div>
  </div>
</template>

<script>
import LostPets from "./LostPet.vue";
import Notifiactions from "./Notifiactions.vue";
import Trends from "./Trends.vue";
import { Icon } from "@iconify/vue";

export default {
  name: "d",
  components: {
    LostPets,
    Notifiactions,
    Trends,
    Icon,
  },
};
</script>

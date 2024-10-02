<template>
  <div class="min-h-screen bg-[#FFF3EB] flex flex-col overflow-hidden">
    <!-- Header Section -->
    <header class="bg-[#FFF3EB] w-full z-10 fixed top-0 left-0">
      <div
        class="mx-auto px-8 flex items-center justify-between md:h-20 w-full bg-[#fff3eb]"
      >
        <img src="../assets/fluffy.png" alt="home pic" class="w-32 h-16" />
        <button
          class="w-10 h-10 bg-white rounded-full text-white flex items-center justify-center"
        >
          <Icon icon="tabler:home" class="text-slate-700 w-10 h-7"/>
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
          <Icon icon="iconamoon:profile" class="text-slate-700 w-10 h-7" @click="goto({ path: '/' })"/>
        </button>
      </div>
    </header>

    <!-- Main Content Section -->
    <div class="flex-grow flex mt-20 overflow-hidden">
      <!-- Sidebar Section (Left) -->
      <div class="w-1/4 py-4 pr-4 flex flex-col fixed h-full overflow-y-auto">
        <LostPets class="w-full mb-4 ml-8" />
        <Tag />
      </div>

      <!-- Scrollable Main Content -->
      <div
        class="grid grid-cols-8 justify-center flex-grow mt-4 ml-1/4 pr-4 overflow-y-auto h-full"
      >
        <div class="w-full col-start-3 col-span-4">
          <div class="bg-white">
            
          </div>
          <div>
            <PostElement />
          </div>
        </div>
      </div>

      <!-- Notifications Section (Right) -->
      <div class="fixed top-20 right-0 w-1/5 h-full overflow-y-auto mr-10">
        <Notifications />
      </div>
    </div>
  </div>
</template>


<script>
import LostPets from "./LostPet.vue";
import Trends from "./Trends.vue";
import { QuillEditor } from "@vueup/vue-quill";
import "@vueup/vue-quill/dist/vue-quill.snow.css";
import { Icon } from "@iconify/vue";
import PostElement from "./PostElement.vue";
import Notifications from "./Notifications.vue";
import Tag from "./Tag.vue";

export default {
  name: "d",
  components: {
    LostPets,
    Notifications,
    Trends,
    Icon,
    PostElement,
    QuillEditor,
    Tag,
  },
  data: () => ({
    content: "",
    editorOption: {
      debug: "info",
      placeholder: "Write your information here...",
    },
  }),
  methods: {
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

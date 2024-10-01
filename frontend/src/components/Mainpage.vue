<template>
  <div class="min-h-screen bg-[#FFF3EB] flex flex-col">
    <header class="bg-[#FFF3EB] relative w-full">
      <div
        class="mx-auto px-8 flex items-center justify-between fixed md:h-20 md:w-full bg-[#fff3eb]"
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
          <Icon icon="iconamoon:profile" class="text-slate-700 w-10 h-7" />
        </button>
      </div>
    </header>

    <div class="flex-grow flex p-4 gap-4 h-full mt-12">
      <div class="w-1/4 py-4 pr-4 flex-grow flex flex-col items-center fixed">
        <!-- Sidebar 1 - Lost Pets -->
        <LostPets class="w-1/4" />
      </div>

      <!-- Main Content Area (Scrollable) -->
      <div
        class="grid grid-cols-8 justify-center inset-0 shadow-md flex-grow mt-4 overflow-y-auto h-[120vh]"
      >
        <!-- <div class="bg-black w-full"> 1</div> -->
        <div class="w-full col-start-3 col-span-4 pr-4">
          <div class="bg-white">
            <QuillEditor
              theme="snow"
              v-model="content"
              ref="myQuillEditor"
              :options="editorOption"
            />
          </div>
          <div>
            <PostElement />
          </div>
        </div>
        <!-- <div class="bg-black w-full"> 3</div> -->
      </div>

      <!-- Sidebar 3 - Trend and Category -->
      <!-- <Trends class="fixed right-0 mr-4"/> -->
      <!-- <div class="w-1/4 py-4 pr-4 flex-grow flex flex-col items-center "> -->
      <!-- Sidebar 1 - Lost Pets -->
      <Notifications class="right-0 mr-4 fixed w-1/4" />
      <!-- </div> -->
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

export default {
  name: "d",
  components: {
    LostPets,
    Notifications,
    Trends,
    Icon,
    PostElement,
    QuillEditor,
  },
  data: () => ({
    content: "",
    editorOption: {
      debug: "info",
      placeholder: "Write your information here...",
    },
  }),
};
</script>

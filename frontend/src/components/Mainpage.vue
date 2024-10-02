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
      <div class="w-1/4 py-4 pr-4 flex flex-col items-center fixed">
        <LostPets class="w-full mb-4" />
        <Trends class="w-full" />
      </div>

      <div
        class="grid grid-cols-8 justify-center inset-0 shadow-md flex-grow mt-4 overflow-y-auto h-[120vh]"
      >
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
      </div>

      <Notifications class="right-0 mr-4 fixed w-1/4" />
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

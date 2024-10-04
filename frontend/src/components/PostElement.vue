<template>
  <div
    v-for="(post, index) in posts"
    :key="index"
    class="block p-6 bg-white border-gray-200 rounded-lg shadow my-4"
  >
    <div class="flex justify-between h-10">
      <div class="flex flex-row items-center">
        <Icon
          icon="bx:bx-user"
          class="text-4xl text-gray-700 dark:text-gray-400 p-1 bg-black rounded-full"
        />
        <div class="ml-2">
          <h5 class="text-base font-bold text-TEXTCOLOR">{{ post.name }}</h5>
          <p class="text-sm text-gray-400 text-left">{{ post.time }}</p>
        </div>
      </div>
      <button
        class="inline-flex items-center justify-center rounded-md bg-PINK hover:bg-PURPLE px-2 py-1 my-2 text-sm font-medium text-white ring-1 ring-inset ring-pink-700/10"
      >
        Found Fluffy !
      </button>
    </div>
    <p class="font-normal text-left mt-4 text-TEXTCOLOR">
      {{ post.content }}
    </p>
    <Icon icon="bx:bx-image" class="w-full h-64 mt-4" />
    <div class="flex justify-between border-t ">
      <!-- <div class="flex flex-row items-center mt-2">
        <Icon
          icon="mdi:heart-outline"
          class="text-red-500 hover:text-red-950"
        />
        <p class="mx-2">{{ post.likes }}</p>
      </div>
      <div class="flex flex-row mt-2">
        <p class="hover:text-PINK">{{ post.comments }} comments</p>
        <p class="mx-2 hover:text-PINK">{{ post.shares }} shares</p>
      </div> -->
    </div>
  </div>
</template>
<script>
import { Icon } from "@iconify/vue";

export default {
  name: "PostElement",
  components: {
    Icon,
  },
  data() {
    return {
      posts: [
        {
          name: "Arayan Tiwari",
          time: "2 hours ago",
          content:
            "Here are the biggest enterprise technology acquisitions of 2021 so far, in reverse chronological order.",
          image: "bx:bx-image",
          likes: 62,
          comments: 12,
          shares: 4,
        },
        {
          name: "Thachapol Chatthanas",
          time: "23 hours ago",
          content:
            "Here are the biggest enterprise technology acquisitions of 2021 so far, in reverse chronological order.",
          image: "bx:bx-image",
          likes: 123,
          comments: 2,
          shares: 42,
        },
        {
          name: "Inthat Sappiphat",
          time: "2 days ago",
          content:
            "Here are the biggest enterprise technology acquisitions of 2021 so far, in reverse chronological order.",
          image: "bx:bx-image",
          likes: 233,
          comments: 30,
          shares: 27,
        },
      ],
    };
  },
};
</script>

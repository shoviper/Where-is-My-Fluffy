<template>
  <div
    class="mx-auto my-32 border-solid border-2 border-slate-100 p-6 rounded-lg max-w-96 place-content-center bg-white flex flex-col"
  >
    <h1 class="text-PINK font-semibold text-2xl">Login</h1>
    <button
      @click="loginWithGoogle"
      class="inline-flex items-center justify-center rounded-md bg-pink-50 px-2 py-1 mt-4 text-base font-medium text-PINK ring-1 ring-inset ring-pink-700/10"
    >
      Login with Google
    </button>
    <button
      @click="loginWithGithub"
      class="inline-flex items-center justify-center rounded-md bg-pink-50 px-2 py-1 mt-2 text-base font-medium text-PINK ring-1 ring-inset ring-pink-700/10"
    >
      Login with GitHub
    </button>
  </div>
</template>

<script>
import axios from "axios";
import { ref, onMounted } from "vue";

export default {
  setup() {
    const user = ref(null);

    const loginWithGoogle = () => {
      window.location.href =
        "http://localhost:8080/oauth2/authorization/google";
    };

    const loginWithGithub = () => {
      window.location.href =
        "http://localhost:8080/oauth2/authorization/github";
    };

    return { loginWithGoogle, loginWithGithub };
  },
};
</script>

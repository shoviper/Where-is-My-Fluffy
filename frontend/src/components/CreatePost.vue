<template>
  <div>
    <!-- Modal for Success Message -->
    <div v-if="showSuccessModal" class="fixed z-10 inset-0 overflow-y-auto">
      <div class="flex items-center justify-center min-h-screen px-4">
        <div class="bg-white p-6 rounded-lg shadow-lg max-w-md w-full">
          <h2 class="text-lg font-semibold text-gray-800">Success!</h2>
          <p class="text-sm text-gray-600 mt-2">Post created successfully!</p>
          <div class="mt-4 flex justify-end">
            <button @click="goToMainPage" class="px-4 py-2 bg-purple-600 text-white rounded-lg">
              Go to Main Page
            </button>
          </div>
        </div>
      </div>
    </div>
    <div
      class="absolute inset-x-0 -z-10 transform-gpu overflow-hidden blur-3xl sm:-top-80"
      aria-hidden="true"
    >
      <div
        class="relative left-[calc(50%-11rem)] aspect-[1155/678] w-[36.125rem] -translate-x-1/2 rotate-[30deg] bg-gradient-to-r from-[#ffd2be] to-[#f1eeff] sm:left-[calc(50%-30rem)] sm:w-[72.1875rem] dark:from-[#ff80b5] dark:to-[#9089fc] dark:opacity-30"
        style="
          clip-path: polygon(
            74.1% 44.1%,
            100% 61.6%,
            97.5% 26.9%,
            85.5% 0.1%,
            80.7% 2%,
            72.5% 32.5%,
            60.2% 62.4%,
            52.4% 68.1%,
            47.5% 58.3%,
            45.2% 34.5%,
            27.5% 76.7%,
            0.1% 64.9%,
            17.9% 100%,
            27.6% 76.8%,
            76.1% 97.7%,
            74.1% 44.1%
          );
        "
      ></div>
    </div>
    <div class="bg-white p-12 m-10 rounded-xl">
      <h1 class="text-2xl text-PURPLE font-semibold">Create post</h1>
      <div class="flex flex-col items-start">
        <div class="flex flex-row">
          <p class="text-TEXTCOLOR text-lg mt-1.5">choose your pet:</p>
          <select v-model="formData.petId" class="border-2 rounded-md ml-2">
            <option disabled value="">Please select one</option>
            <option v-for="pet in pets" :key="pet.id" :value="pet.id">
              {{ pet.name }}
            </option>
          </select>
        </div>
        <div class="flex flex-row mt-1.5">
          <p class="text-gray-400 text-sm ">Don't create yet?</p>
          <p class="text-sm text-PINK underline ml-2 cursor-pointer" @click="toggleModal">Add here</p>
          <AddPetModal
          v-if="isModalVisible"
          :pettypes="pettypes"
          @close="toggleModal"
          @refresh="fetchPets"
        />
        </div>

        <p class="text-TEXTCOLOR text-lg my-2">Title {{ message }}</p>
        <input
          v-model="formData.title"
          placeholder=" your title"
          class="border-2 rounded-md"
        />
        <p class="text-TEXTCOLOR text-lg my-2">Content</p>
        <div class="h-20 w-full">
          <input
            v-model="formData.content"
            placeholder=" write your content"
            class="border-2 rounded-md h-full w-full"
          />
        </div>
        <!-- add reward section -->
        <div class="flex flex-row mt-1.5">
          <p class="text-TEXTCOLOR text-lg mt-1.5">Reward:</p>
          <input
            v-model="formData.rewardAmount"
            placeholder=" reward"
            class="border-2 rounded-md ml-2"
          /></div>
      </div>
      <div class="flex justify-end">
        <button
          class="inline-flex items-center rounded-md bg-gray-50 px-4 py-2 mt-4 mx-4 text-xs font-semibold text-gray-500 ring-1 ring-inset ring-pink-700/10"
          @click="goto({ path: '/mainpage' })"
        >
          cancel
        </button>
        <button
          class="inline-flex items-center rounded-md bg-purple-50 px-4 py-2 mt-4 text-xs font-semibold text-PURPLE ring-1 ring-inset ring-pink-700/10"
          @click="submitForm"
        >
          post!
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import AddPetModal from './AddPetModal.vue';

export default {
  components: {
    AddPetModal,
  },
  data() {
    return {
      formData: {
        title: '',
        content: '',
        petId: null, // Stores the selected petId
        rewardAmount: null,
      },
      pets: [],
      showSuccessModal: false,
      isModalVisible: false,   // Controls the visibility of the modal
      pettypes: []
    };
  },
  mounted() {
    this.fetchPets();
  },
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
    async fetchPets() {
      try {
        const response = await axios.get('http://localhost:8080/pets/me', {
          withCredentials: true,
        });
        this.pets = response.data;
      } catch (error) {
        console.error('Error fetching pets:', error);
      }
    },
    async submitForm() {
  // Ensure all fields are filled before submitting
  if (!this.formData.title || !this.formData.content || !this.formData.petId || !this.formData.rewardAmount) {
    alert('Please fill out all fields before submitting the form.');
    return;
  }

  try {
    this.formData.type = "MISSING";
    console.log('Submitting formData:', this.formData);

    // Make the POST request
    const response = await axios.post('http://localhost:8080/posts', this.formData, {
      headers: {
        'Content-Type': 'application/json',
      },
      withCredentials: true,
    });

    console.log('Form submitted successfully:', response.data);

    // Clear the form after successful submission
    this.formData = {
      title: '',
      content: '',
      petId: null,
      rewardAmount: 500,  // Reset the reward to default value
    };

    // Show success modal
    this.showSuccessModal = true;
  } catch (error) {
    if (error.response) {
      console.error('Error response data:', error.response.data);
    } else {
      console.error('Error submitting form:', error.message);
    }
  }
},

    goToMainPage() {
      this.showSuccessModal = false;
      this.$router.push({ path: '/mainpage' });
    },
    toggleModal() {
      this.isModalVisible = !this.isModalVisible;
    },
  },
};
</script>
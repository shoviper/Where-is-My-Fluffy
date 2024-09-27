<template>
  <div class="min-h-screen bg-[#FFF3EB] flex flex-col">
    <header class="bg-[#FFF3EB] py-5">
      <div class="max-w-7xl mx-auto px-4 flex items-center justify-between">
        <img src="../assets/fluffy.png" alt="home pic" class="w-32 h-16">
        <button class="w-10 h-10 bg-white rounded-full text-white flex items-center justify-center">
          <img src="../assets/home.png" alt="home pic">
        </button>

        <div class="flex-grow mx-4">
          <input
            type="text"
            placeholder="Where's My Fluffy"
            class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
          />
        </div>

        <button class="w-10 h-10 bg-white rounded-full text-white flex items-center justify-center">
          <img src="../assets/profile.png" alt="profile pic">
        </button>
      </div>
    </header>

    <div class="flex-grow flex p-4 gap-4 h-full">
      <!-- Sidebar 1 - Lost Pets -->
      <div class="w-1/4 bg-[#FFF3EB] p-4 flex-grow flex flex-col items-center">
        <div class="w-full h-auto bg-white p-4 shadow-md">
          <div class="flex justify-between items-center mb-4">
            <h2 class="text-base font-semibold">Lost Pets</h2>
            <a href="#" class="text-base text-blue-500">See all</a>
          </div>
          <div>
            <div class="flex items-center border-t border-b border-gray-300 py-2">
              <img src="../assets/pets/petpic1.png" alt="Pet 1" class="w-11 h-11 object-cover rounded-md">
              <div class="ml-8">
                <p class="text-base font-semibold">Lola</p>
                <p class="text-sm text-gray-500">missing on xx/xx/xx</p>
              </div>
            </div>
            <div class="flex items-center border-t border-b border-gray-300 py-2">
              <img src="../assets/pets/petpic2.png" alt="Pet 2" class="w-11 h-11 object-cover rounded-md">
              <div class="ml-8">
                <p class="text-base font-semibold">Lucy</p>
                <p class="text-sm text-gray-500">missing on xx/xx/xx</p>
              </div>
            </div>
            <div class="flex items-center border-t border-b border-gray-300 py-2">
              <img src="../assets/pets/petpic3.png" alt="Pet 3" class="w-11 h-11 object-cover rounded-md">
              <div class="ml-8">
                <p class="text-base font-semibold">Sweed</p>
                <p class="text-sm text-gray-500">missing on xx/xx/xx</p>
              </div>
            </div>
            <div class="flex items-center border-t border-b border-gray-300 py-2">
              <img src="../assets/pets/petpic4.png" alt="Pet 4" class="w-11 h-11 object-cover rounded-md">
              <div class="ml-8">
                <p class="text-base font-semibold">Khun somsri</p>
                <p class="text-sm text-gray-500">missing on xx/xx/xx</p>
              </div>
            </div>
          </div>
        </div>

        <!-- Sidebar 2 - Notifications -->
        <div class="w-full h-80 bg-white p-4 shadow-md mt-8">
          <div class="flex justify-between items-center mb-4">
            <h2 class="text-base font-semibold">Notifications</h2>
            <a href="#" class="text-base text-red-500">Mark all as read</a>
          </div>
        </div>
      </div>

      <!-- Main Content Area (Scrollable) -->
      <div class="w-1/2 bg-white p-4 shadow-md flex-grow mt-4 overflow-y-auto h-[80vh]">
        Post and Content
        <h1 class="text-lg mt-8">Below is for testing scrollbar (can delete)</h1>
        <div class="w-full h-80 bg-white p-4 shadow-md mt-8">
          <div class="flex justify-between items-center mb-4">
            <h2 class="text-base font-semibold">Notifications</h2>
            <a href="#" class="text-base text-red-500">Mark all as read</a>
          </div>
        </div>
        <div class="w-full h-80 bg-white p-4 shadow-md mt-8">
          <div class="flex justify-between items-center mb-4">
            <h2 class="text-base font-semibold">Notifications</h2>
            <a href="#" class="text-base text-red-500">Mark all as read</a>
          </div>
        </div>
      </div>

      <!-- Sidebar 3 - Trend and Category -->
      <div class="w-1/4 bg-white p-4 shadow-md flex-grow mt-4">
        Trend and Category
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: 'd',
};
</script>

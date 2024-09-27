<template>
    <div class="min-h-screen bg-[#FFF3EB] flex flex-col">
      <header class="bg-[#FFF3EB] py-7">
        <div class="max-w-7xl mx-auto px-4 flex items-center justify-between">
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
        <div class="w-1/4 bg-white p-4 shadow-md flex-grow">
          Lost pet and Notification
        </div>
    
        <div class="w-1/2 bg-white p-4 shadow-md flex-grow">
          Post and Content
        </div>
    
        <div class="w-1/4 bg-white p-4 shadow-md flex-grow">
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
  
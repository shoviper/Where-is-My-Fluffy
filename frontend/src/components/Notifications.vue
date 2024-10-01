<template>
  <div class="  bg-white p-4 shadow-md mt-4 rounded-md">
    <!-- Header -->
    <div class="flex justify-between items-center mb-4">
      <h2 class="text-base font-semibold">Notifications</h2>
      <a href="#" class="text-base text-red-500">Mark all as read</a>
    </div>

    <!-- Notifications List -->
    <div class="space-y-4">
      <div class="border-t border-gray-300 py-2">
        <div>
          <p class="text-sm text-left">
            <span class="font-semibold">Mable</span> replied to <span class="font-semibold">Christ</span>
          </p>
          <p class="text-sm text-gray-700 italic text-left">“Oh, I finished de-bugging the phones, but the system's compiling for eighteen minutes, or twenty. So, some minor systems may go on and off for a while.”</p>
          <p class="text-xs text-gray-500 text-left">Yesterday at 4:30 p.m.</p>
        </div>
      </div>

      <div class="border-t border-gray-300 py-2">
        <div>
          <p class="text-sm text-left">
            <span class="font-semibold">John</span> shared your post.
          </p>
          <p class="text-xs text-gray-500 text-left">Last Wednesday at 11:00 p.m.</p>
        </div>
      </div>

      <div class="border-t border-gray-300 py-2">
        <div>
          <p class="text-sm text-left">A new post has been created</p>
          <p class="text-xs text-gray-500 text-left">Today at 10:15 a.m.</p>
        </div>
      </div>
    </div>
  </div>
</template>

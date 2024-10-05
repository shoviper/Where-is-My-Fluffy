<template>
    <div class="mt-8 ">
      <img
        v-if="adsImage"
        :src="adsImage"
        alt="ads Image"
        class="w-86 h-full object-contain"
      />
      <!-- Optional: For debugging purposes -->
      <p v-else>No image available</p>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        adsImage: null, // Store the image URL here
      };
    },
    mounted() {
      this.fetchAdsImage(); // Fetch the image when component is mounted
    },
    methods: {
      async fetchAdsImage() {
        try {
          const response = await axios.get('http://localhost:8080/ads');
          const data = response.data;
  
          console.log(data); // Log the full response to inspect the data
  
          const AdsImageData = data.ads; // Assuming the ads property holds the image URL
  
          if (AdsImageData) {
            // Assign the image URL directly to adsImage
            this.adsImage = AdsImageData;
          } else {
            this.adsImage = null;
          }
        } catch (error) {
          console.error('Error fetching ads:', error);
        }
      },
    },
  };
  </script>
  
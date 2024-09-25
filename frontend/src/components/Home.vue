<template>
	<div>
		<h1>Home</h1>
		<div v-if="user">
			<h2>Welcome, {{ user.name }}!</h2>
			<p>Email: {{ user.email }}</p>
		</div>
		<div v-else>
			<p>Loading user profile...</p>
		</div>
	</div>
</template>

<script>
import axios from 'axios'; // Import Axios

export default {
	data() {
		return {
			user: null, // Initialize user data
		};
	},
	mounted() {
		this.fetchUserProfile(); // Call the method to fetch user profile when the component mounts
	},
	methods: {
		async fetchUserProfile() {
			try {
				const response = await axios.get(
					'http://localhost:8080/api/v1/profile',
					{
						withCredentials: true, // Add this line to include credentials
					}
				);
				this.user = response.data; // Assign the response data to the user property
			} catch (error) {
				console.error('Error fetching user profile:', error); // Handle errors
			}
		},
	},
};
</script>

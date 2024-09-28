<template>
	<div>
		<h1>Home</h1>
		<div v-if="user">
			<img :src="user.picture" alt="User Picture" />
			<h2>Welcome, {{ user.name }}!</h2>
			<p>Email: {{ user.email }}</p>
			<p>All data: {{ user }}</p>
			<button @click="logout">Logout</button>
		</div>
		<div v-else>
			<p>Loading user profile...</p>
		</div>
		<button @click="goToLogin">Login</button>
	</div>
</template>

<script>
import axios from 'axios';

export default {
	data() {
		return {
			user: null,
		};
	},
	mounted() {
		this.fetchUserProfile();
	},
	methods: {
		async fetchUserProfile() {
			try {
				const response = await axios.get(
					'http://localhost:8080/users/me',
					{
						withCredentials: true,
					}
				);
				this.user = response.data;
			} catch (error) {
				console.error('Error fetching user profile:', error);
			}
		},

		goToLogin() {
			this.$router.push('/login');
		},

		logout() {
			window.location.href = 'http://localhost:8080/logout';
		},
	},
};
</script>

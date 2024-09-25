<template>
	<div>
		<h1>Login</h1>
		<button @click="loginWithGoogle">Login with Google</button>
		<button @click="loginWithGithub">Login with GitHub</button>
		<div v-if="user">
			<h2>Welcome, {{ user.name }}!</h2>
			<p>Email: {{ user.email }}</p>
		</div>
	</div>
</template>

<script>
import axios from 'axios';
import { ref, onMounted } from 'vue';

export default {
	setup() {
		const user = ref(null);

		const loginWithGoogle = async () => {
			try {
				const response = await axios.get(
					'http://localhost:8080/login/oauth2/code/google',
					{ withCredentials: true }
				);
				console.log('Login request sent, awaiting backend OAuth flow');
			} catch (error) {
				console.error('Error during Google login:', error);
			}
		};

		const loginWithGithub = async () => {
			try {
				const response = await axios.get(
					'http://localhost:8080/login/oauth2/code/github',
					{ withCredentials: true }
				);
				console.log('Login request sent, awaiting backend OAuth flow');
			} catch (error) {
				console.error('Error during GitHub login:', error);
			}
		};

		const fetchUser = async () => {
			try {
				const response = await axios.get('http://localhost:8080/user', {
					withCredentials: true,
				});
				if (response.data) {
					user.value = response.data;
				}
			} catch (error) {
				console.error('Error fetching user data:', error);
			}
		};

		onMounted(() => {
			fetchUser(); // Check if user is already logged in
		});

		return { loginWithGoogle, loginWithGithub, user };
	},
};
</script>

<style>
button {
	margin: 10px;
	padding: 10px 20px;
	font-size: 16px;
}
</style>

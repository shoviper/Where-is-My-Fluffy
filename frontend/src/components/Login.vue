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

		const loginWithGoogle = () => {
			window.location.href =
				'http://localhost:8080/oauth2/authorization/google';
		};

		const loginWithGithub = () => {
			window.location.href =
				'http://localhost:8080/oauth2/authorization/github';
		};

		// Function to retrieve user data from URL query params
		const getUserDataFromUrl = () => {
			const params = new URLSearchParams(window.location.search);
			const name = params.get('name');
			const email = params.get('email');

			if (name && email) {
				user.value = { name, email };
			}
		};

		// Call the function on component mount
		onMounted(getUserDataFromUrl);

		return { loginWithGoogle, loginWithGithub, user };
	},
};
</script>

<style>
/* Add your styles here */
button {
	margin: 10px;
	padding: 10px 20px;
	font-size: 16px;
}
</style>

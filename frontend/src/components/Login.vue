<template>
	<div>
		<h1>Login</h1>
		<button @click="loginWithGoogle">Login with Google</button>
		<button @click="loginWithGithub">Login with GitHub</button>
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

		return { loginWithGoogle, loginWithGithub };
	},
};
</script>

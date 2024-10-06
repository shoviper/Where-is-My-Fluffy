<template>
	<div
		class="mx-auto my-32 border-solid border-2 border-slate-100 p-6 rounded-lg max-w-96 place-content-center bg-white flex flex-col items-center justify-center"
	>
		<h1 class="text-pink-500 font-semibold text-2xl">Home</h1>

		<!-- Show user profile and logout button if user is logged in -->
		<div v-if="user" class="flex flex-col items-center justify-center">
			<img
				:src="`http://localhost:8080/users/${user.email}/image`"
				alt="User Picture"
				class="w-18 h-18 rounded-full mb-4"
			/>
			<h2 class="text-lg font-semibold">Welcome, {{ user.name }}!</h2>
			<p>Email: {{ user.email }}</p>
			<div class="flex">
				<button
					class="mt-4 mr-4 items-center rounded-md bg-pink-50 py-2 text-xs font-medium text-pink-600 ring-1 ring-inset ring-pink-700/10"
				>
					<Icon
						icon="tabler:home"
						class="w-10 h-4"
						@click="goto({ path: '/mainpage' })"
					/>
				</button>
				<!-- Modal toggle -->
				<button
					@click="toggleModal"
					class="mt-4 mr-4 items-center rounded-md bg-pink-50 px-4 py-2 text-xs font-medium text-pink-600 ring-1 ring-inset ring-pink-700/10"
				>
					Add pet
				</button>

				<!-- Show AddPetModal component when modal is visible -->
				<AddPetModal
					v-if="isModalVisible"
					:pettypes="pettypes"
					@close="toggleModal"
				/>

				<button
					class="mt-4 items-center rounded-md bg-pink-50 px-4 py-2 text-xs font-medium text-pink-600 ring-1 ring-inset ring-pink-700/10"
					@click="logout"
				>
					Logout
				</button>
			</div>
		</div>

		<!-- Show login button if user is not logged in -->
		<div v-else>
			<button
				class="inline-flex items-center rounded-md bg-pink-50 px-4 py-2 text-xs font-medium text-pink-600 ring-1 ring-inset ring-pink-700/10"
				@click="goToLogin"
			>
				Login
			</button>
		</div>
	</div>

	<div
		class="mx-auto my-32 border-solid border-2 border-slate-100 p-6 rounded-lg max-w-96 place-content-center bg-white flex flex-col items-center justify-center"
	>
		<!-- Show pet image here -->
		<h2 class="text-lg font-semibold mb-4">Pet Image</h2>
		<div v-if="petData">
			<p>id: {{ petData.id }}</p>
			<p>name: {{ petData.name }}</p>
			<p>age: {{ petData.age }}</p>
			<p>animal-type: {{ petData.animalType }}</p>
			<p>description: {{ petData.description }}</p>
			<p>status: {{ petData.status }}</p>
			<img
				v-if="petImage"
				:src="petImage"
				alt="Pet Image"
				class="w-64 h-64 object-contain"
			/>
			<p v-else>No image available</p>
		</div>
		<p v-else>No data available</p>
	</div>
	<div
		class="mx-auto my-32 border-solid border-2 border-slate-100 p-6 rounded-lg max-w-96 place-content-center bg-white flex flex-col items-center justify-center"
	>
		<!-- Show pet image here -->
		<h2 class="text-lg font-semibold mb-4">Notification Test</h2>
		<div v-if="notificationData">
			<p>id: {{ notificationData.id }}</p>
			<p>title: {{ notificationData.title }}</p>
			<p>message: {{ notificationData.message }}</p>
			<p>notificationType: {{ notificationData.notificationType }}</p>
			<img
				v-if="notificationImage"
				:src="notificationImage"
				alt="Notification Image"
				class="w-64 h-64 object-contain"
			/>
			<p v-else>No image available</p>
		</div>
		<p v-else>No data available</p>
	</div>
	<div
		class="mx-auto my-32 border-solid border-2 border-slate-100 p-6 rounded-lg max-w-96 place-content-center bg-white flex flex-col items-center justify-center"
	>
		<form @submit.prevent="submitNotificationImage">
			<label
				class="block my-2 text-sm text-left font-medium text-gray-900"
				for="file_input"
				>Upload image</label
			>
			<input
				class="block w-full bg-gray-50 p-4 text-sm text-gray-500 border border-gray-300 rounded-lg cursor-pointer"
				id="file_input"
				ref="file_input"
				type="file"
			/>

			<button
				type="submit"
				class="w-full text-white bg-pink-400 hover:bg-pink-500 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5"
			>
				Add
			</button>
		</form>
	</div>
</template>

<script>
import axios from 'axios';
import AddPetModal from './AddPetModal.vue'; // Import the new component
import { Icon } from '@iconify/vue';

export default {
	components: {
		AddPetModal,
		Icon,
	},
	data() {
		return {
			user: null,
			isModalVisible: false,
			petData: null,
			petImage: null,
			notificationData: null,
			notificationImage: null,
		};
	},
	mounted() {
		this.fetchUserProfile();
		//this.fetchPetImage(2);
		this.fetchNotification(1);
	},
	methods: {
		async fetchUserProfile() {
			try {
				const response = await axios.get('http://localhost:8080/users/me', {
					withCredentials: true,
				});
				const data = await response.data;
				this.user = data;
			} catch (error) {
				console.error('Error fetching user profile:', error);
			}
		},
		async fetchPetImage(petId) {
			try {
				const response = await axios.get(`http://localhost:8080/pets/${petId}`);
				const data = await response.data;

				const petImageData = data.image;

				// Assuming petData.imageBase64 contains the base64-encoded image string
				if (data) {
					this.petData = data;
					this.petImage = `data:image/jpeg;base64,${petImageData}`;
				} else {
					this.petData = null;
					this.petImage = null;
				}
			} catch (error) {
				console.error(`Error fetching pet image for pet ID ${petId}:`, error);
			}
		},
		async fetchNotification(notificationId) {
			try {
				const response = await axios.get(
					`http://localhost:8080/notifications/${notificationId}`
				);
				const data = await response.data;
				console.log(data);

				const notificationImageData = data.image;

				if (data) {
					this.notificationData = data;
					this.notificationImage = `data:image/jpeg;base64,${notificationImageData}`;
				} else {
					this.notificationData = null;
					this.notificationImage = null;
				}
			} catch (error) {
				console.error(
					`Error fetching notification for noti ID ${notificationId}:`,
					error
				);
			}
		},
		async submitNotificationImage() {
			try {
				// Step 2: Upload the pet image
				const fileInput = this.$refs.file_input.files[0];
				const formData = new FormData();
				formData.append('file', fileInput);
				formData.append('notificationId', 1);

				await axios.put(
					`http://localhost:8080/notifications/1/uploadImage`,
					formData,
					{
						headers: {
							'Content-Type': 'multipart/form-data',
						},
						withCredentials: true,
					}
				);

				alert('Notification image uploaded successfully!');
			} catch (error) {
				alert('Error occurred during notification image upload.');
			}
		},
		goToLogin() {
			this.$router.push('/login');
		},
		logout() {
			axios
				.get('http://localhost:8080/logout', { withCredentials: true })
				.then(() => {
					this.user = null;
					this.$router.push('/login');
				})
				.catch((error) => {
					console.error('Error logging out:', error);
				});
		},
		toggleModal() {
			this.isModalVisible = !this.isModalVisible;
		},
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
	},
};
</script>

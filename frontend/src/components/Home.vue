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

			<!-- Location and Phone input fields (show if not already set) -->
			<div class="flex flex-col items-center mt-4">
				<label for="location" class="text-gray-700">Location:</label>
				<input
					v-model="location"
					id="location"
					type="text"
					:placeholder="user.location || 'Enter your location'"
					class="border-2 border-gray-300 p-2 mt-2 rounded-md"
				/>

				<label for="phone" class="text-gray-700 mt-4">Phone Number:</label>
				<input
					v-model="phone"
					id="phone"
					type="text"
					:placeholder="user.phone || 'Enter your phone number'"
					class="border-2 border-gray-300 p-2 mt-2 rounded-md"
				/>

				<!-- Save button to update location and phone -->
				<button
					@click="saveDetails"
					class="mt-4 bg-pink-500 text-white px-4 py-2 rounded"
				>
					Save Details
				</button>
			</div>

			<!-- Navigation and Add Pet buttons -->
			<div class="flex mt-4">
				<!-- Go to Main Page Button -->
				<button
					class="mt-4 mr-4 items-center rounded-md bg-pink-50 py-2 px-2 text-xs font-medium text-pink-600 ring-1 ring-inset ring-pink-700/10"
				>
					<Icon
						icon="tabler:home"
						class="w-10 h-4"
						@click="gotoMainPage"
					/>
				</button>

				<!-- Modal toggle -->
				<button
					@click="toggleModal"
					class="mt-4 mr-4 items-center rounded-md bg-pink-50 px-4 py-2 text-xs font-medium text-pink-600 ring-1 ring-inset ring-pink-700/10"
				>
					Add Pet
				</button>

				<!-- Show AddPetModal component when modal is visible -->
				<AddPetModal
					v-if="isModalVisible"
					:pettypes="pettypes"
					@close="toggleModal"
				/>

				<button
					@click="logout"
					class="mt-4 items-center rounded-md bg-pink-50 px-4 py-2 text-xs font-medium text-pink-600 ring-1 ring-inset ring-pink-700/10"
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
			location: '',
			phone: '',
			isModalVisible: false,
			canProceed: true, // Allow navigation but show alert if needed
		};
	},
	mounted() {
		this.fetchUserProfile();
	},
	methods: {
		async fetchUserProfile() {
			try {
				const response = await axios.get('http://localhost:8080/users/me', {
					withCredentials: true,
				});
				const data = await response.data;
				this.user = data;

				// Check if location and phone are already set
				this.location = this.user.location || '';
				this.phone = this.user.phone || '';
			} catch (error) {
				console.error('Error fetching user profile:', error);
			}
		},
		saveDetails() {
			// Ensure both fields are filled before saving
			if (!this.location || !this.phone) {
				alert('Both location and phone number are required.');
				return;
			}

			// Update location and phone number in the backend
			Promise.all([
				axios.put('http://localhost:8080/users/update-location', { location: this.location }, { withCredentials: true }),
				axios.put('http://localhost:8080/users/update-phone-number', { phone: this.phone }, { withCredentials: true })
			])
				.then(() => {
					alert('Details saved successfully!');
				})
				.catch((error) => {
					alert('Error saving details. Please try again.');
					console.error('Error saving details:', error);
				});
		},
		gotoMainPage() {
			// Check if location is filled
			if (!this.location) {
				alert('Please save your location before proceeding.');
				return;
			}

			// Proceed to main page if location is set
			this.$router.push('/mainpage');
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
		goToLogin() {
			this.$router.push('/login');
		},
		toggleModal() {
			this.isModalVisible = !this.isModalVisible;
		},
	},
};
</script>
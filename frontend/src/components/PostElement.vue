<template>
	<div>
		<div v-if="showModal" class="fixed z-10 inset-0 overflow-y-auto">
			<AddFoundFluffy
				@close="closeModal"
				:post-id="selectedPostId"
				:user-id="selectedUserId"
			/>
		</div>

		<div
			v-if="showMap !== null"
			class="fixed z-50 inset-0 flex items-center justify-center overflow-y-auto"
		>
			<div
				class="fixed inset-0"
				aria-hidden="true"
				@click="closeMapModal"
			></div>
			<div
				class="relative bg-white rounded-lg overflow-hidden shadow-xl transform transition-all max-w-3xl w-full"
				@click.stop
			>
				<iframe
					v-if="posts[showMap].pet.location.addressUrl"
					:src="
						formatGoogleMapsEmbedUrl(
							posts[showMap].pet.location.addressUrl
						)
					"
					width="100%"
					height="450"
					style="border: 0; display: block; margin: 0 auto"
					allowfullscreen=""
					loading="lazy"
					referrerpolicy="no-referrer-when-downgrade"
				></iframe>
				<button
					@click="closeMapModal"
					class="mt-4 bg-PINK text-white p-2 rounded mx-auto block mb-5"
				>
					Close Map
				</button>
			</div>
		</div>
	</div>
	<div
		v-for="(post, index) in posts"
		:key="index"
		class="block p-6 bg-white border-gray-200 rounded-lg shadow my-4"
	>
		<div class="flex justify-between h-10">
			<div class="flex flex-row items-center">
				<img
					:src="`http://localhost:8080/users/${post.user.email}/image`"
					alt="User Picture"
					class="w-12 text-4xl text-gray-700 dark:text-gray-400 p-1 rounded-full"
				/>
				<div class="ml-2">
					<h5 class="text-base text-left font-bold text-TEXTCOLOR">
						{{ post.user.name }}
					</h5>
					<p class="text-sm text-gray-400 text-left">
						{{ formatTimestamp(post.timestamp) }}
					</p>
				</div>
			</div>
			<div class="flex flex-row">
				<div
					class="bg-PINK text-white px-2 py-1 my-2 rounded-full mr-2 cursor-pointer"
					@click="toggleMap(index)"
				>
					<Icon icon="material-symbols:map" class="text-white"></Icon>
				</div>
				<button
					v-if="
						post.pet.status !== 'FOUND' &&
						post.pet.user.id !== user.id
					"
					class="inline-flex items-center justify-center rounded-md bg-PINK hover:bg-PURPLE px-2 py-1 my-2 text-sm font-medium text-white ring-1 ring-inset ring-pink-700/10"
					@click="openModal(post)"
				>
					Found Fluffy !
				</button>
				<div
					class="inline-flex items-center justify-center rounded-md bg-PINK px-2 py-1 my-2 text-sm font-medium text-white ring-1 ring-inset ring-pink-700/10"
					v-if="
						post.pet.status === 'MISSING' &&
						post.pet.user.id === user.id
					"
				>
					Hope your {{ post.pet.name }} is fine.
				</div>
				<div
					class="inline-flex items-center justify-center rounded-md bg-PINK px-2 py-1 my-2 text-sm font-medium text-white ring-1 ring-inset ring-pink-700/10"
					v-if="post.pet.status === 'FOUND'"
				>
					{{ post.pet.name }} has been found by
					{{ post.pet.founder.name }}
				</div>
			</div>
		</div>
		<div class="mt-2 h-0.5 bg-slate-100"></div>
		<p class="font-bold text-left mt-4 text-TEXTCOLOR">
			{{ post.title }}
			<!-- {{ post }} -->
		</p>
		<p class="font-normal text-left mt-4 text-TEXTCOLOR">
			{{ post.content }}
			<!-- {{ post }} -->
		</p>
		<div class="w-full h-64 mt-4">
			<img
				v-if="post.petImage"
				:src="post.petImage"
				alt="Pet image"
				class="w-full h-full object-contain rounded-lg"
			/>
			<Icon v-else icon="bx:bx-image" class="w-full h-64" />
		</div>
		<!-- NOT MOCK -->
		<p v-if="post.pet.status !== 'FOUND'" class="text-lg text-PINK mt-4">
			Reward: ฿{{ post.rewardAmount }}
		</p>
		<p v-else class="text-lg text-green-600 font-bold mt-4">
			Reward acquired!
		</p>
		<div class="flex justify-between border-t"></div>
	</div>
</template>
<script>
import { Icon } from '@iconify/vue';
import axios from 'axios';
import AddFoundFluffy from './AddFoundFluffy.vue';
export default {
	name: 'PostElement',
	components: {
		Icon,
		AddFoundFluffy,
	},
	data() {
		return {
			user: null,
			posts: [],
			showModal: false,
			selectedPostId: null,
			selectedUserId: null,
			showMap: null,
		};
	},
	mounted() {
		this.fetchUserProfile();
		this.fetchPosts();
	},
	methods: {
		async fetchPosts() {
			try {
				const response = await axios.get(
					'http://localhost:8080/posts?page=0&size=10&sortBy=id&sortDir=asc',
					{
						headers: {
							Accept: 'application/json',
						},
					}
				);

				const posts = response.data.reverse();

				console.log(posts);

				// Fetch pet images for each post individually
				for (let post of posts) {
					if (post.pet && post.pet.id) {
						const petResponse = await this.fetchPetImage(
							post.pet.id
						);
						if (petResponse && petResponse.image) {
							post.petImage = `data:image/jpeg;base64,${petResponse.image}`; // Add the pet image to the post
						}
					}
				}

				this.posts = posts; // Update posts with images
			} catch (error) {
				console.error(
					'Error fetching posts:',
					error.response?.data || error.message
				);
			}
		},
		async fetchPetImage(petId) {
			try {
				const response = await axios.get(
					`http://localhost:8080/pets/${petId}`
				);
				const data = response.data;
				return data; // Return pet data including the image
			} catch (error) {
				console.error(
					`Error fetching pet image for pet ID ${petId}:`,
					error
				);
				return null;
			}
		},
		async fetchUserProfile() {
			try {
				const response = await axios.get(
					'http://localhost:8080/users/me',
					{
						withCredentials: true,
					}
				);
				const data = await response.data;
				this.user = data;
			} catch (error) {
				console.error('Error fetching user profile:', error);
			}
		},
		formatTimestamp(timestamp) {
			const date = new Date(timestamp);
			return date.toLocaleString('en-US', {
				year: 'numeric',
				month: 'long',
				day: 'numeric',
				hour: '2-digit',
				minute: '2-digit',
				second: '2-digit',
				hour12: true, // Use 12-hour format
			});
		},
		formatGoogleMapsEmbedUrl(addressUrl) {
			// Extracting the apiKey and encodedAddress from the given URL
			const url = new URL(addressUrl);

			// Extract the query parameters
			const apiKey = url.searchParams.get('key');
			const encodedAddress = url.searchParams.get('q');

			// Now you can format a new URL or return it as needed
			return `https://www.google.com/maps/embed/v1/place?key=${apiKey}&q=${encodedAddress}`;
		},
		openModal(post) {
			this.modalData = {
				title: '',
				message: '',
				imageFile: null,
			};
			this.selectedUserId = post.user.id;
			this.selectedPostId = post.id;
			this.showModal = true;
		},

		closeModal() {
			this.showModal = false;
			this.selectedUserId = null;
			this.selectedPostId = null;
		},

		sendMessage() {
			alert('Message sent with title: ' + this.modalData.title);
			this.closeModal();
		},
		toggleMap(index) {
			this.showMap = index; // Show the map modal for the clicked post
		},

		closeMapModal() {
			this.showMap = null; // Close the map modal
		},
	},
};
</script>

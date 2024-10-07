<template>
	<div class="min-h-screen bg-[#FFF3EB] flex flex-col items-center p-6">
		<!-- Back button -->
		<button
			class="mt-24 mb-4 text-sm text-blue-600 flex items-center"
			@click="goto({ path: `/seeallnotification` })"
		>
			<Icon icon="mdi:chevron-left" class="w-5 h-5 mr-1" /> Back to
			notifications
		</button>

		<!-- Notification details box -->
		<div class="w-full max-w-2xl bg-white shadow-md rounded-lg p-6">
			<!-- Title section -->
			<div class="mb-4 border-b pb-2">
				<h2 class="text-2xl font-semibold text-gray-800">
					{{ notification.title }}
				</h2>
			</div>

			<div v-if="notification.image" class="mb-4">
				<img
					:src="notification.image"
					alt="Pet Image"
					class="w-full h-64 object-cover rounded-lg"
				/>
			</div>

			<!-- Message section -->
			<div class="mb-4">
				<p class="text-lg text-gray-500 mb-2">{{ notification.to }}</p>
				<p
					v-if="notification.from !== null"
					class="text-lg text-gray-500 mb-2"
				>
					From {{ notification.from }},
				</p>
				<p class="text-base text-gray-700 whitespace-pre-line mb-10">
					{{ notification.message }}
				</p>
				<div
					v-if="notification.type === 'NOTIFICATION_PENDING'"
					class="mt-4"
				>
					<a
						href="#"
						@click="acceptNotification()"
						class="py-3 px-6 ml-2 text-sm font-medium text-white bg-green-600 rounded-lg hover:bg-green-700"
					>
						Accept
					</a>
					<a
						href="#"
						@click="notificationUpdate('NOTIFICATION_REJECTED')"
						class="py-3 px-6 ml-2 text-sm font-medium text-white bg-red-600 rounded-lg hover:bg-red-700"
					>
						Reject
					</a>
				</div>
				<div
					v-if="notification.type === 'NOTIFICATION_APPROVED'"
					class="mt-10 font-bold uppercase text-xl"
				>
					Approved
				</div>
				<div
					v-if="notification.type === 'NOTIFICATION_REJECTED'"
					class="mt-10 font-bold uppercase text-xl"
				>
					Rejected
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import axios from 'axios';
import { Icon } from '@iconify/vue';

export default {
	name: 'seeeachnotification',
	components: {
		Icon,
	},
	data() {
		return {
			notification: {},
		};
	},
	mounted() {
		const notificationId = this.$route.params.id; // Get the ID from the route params
		this.fetchNotification(notificationId);
	},
	methods: {
		async fetchNotification(id) {
			try {
				const response = await axios.get(
					`http://localhost:8080/notifications/${id}`,
					{
						withCredentials: true,
					}
				);

				const data = response.data;
				this.notification = {
					title: data.title,
					to: `To: ${data.notificationOwner.name}`,
					message: data.message,
					type: data.notificationType,
					timestamp: data.timestamp || null,
					from: data.notificationSender?.name || null,
				};

				// Fetch the pet image if available
				const petImageResponse = await this.fetchPetImage(id);
				if (petImageResponse && petImageResponse.image) {
					this.notification.image = `data:image/jpeg;base64,${petImageResponse.image}`;
				}
			} catch (error) {
				console.error('Error fetching notification details:', error);
			}
		},

		async fetchPetImage(id) {
			try {
				const response = await axios.get(
					`http://localhost:8080/notifications/${id}`,
					{
						headers: {
							Accept: 'application/json',
						},
					}
				);
				return response.data; // Assuming this contains the Base64 image
			} catch (error) {
				console.error('Error fetching pet image:', error);
			}
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

		async acceptNotification() {
			try {
				const notificationId = this.$route.params.id; // Get the ID from the route params
				await this.notificationUpdate('NOTIFICATION_APPROVED'); // Update the notification status
				this.goto({ path: `/payment/${notificationId}` }); // Redirect to the payment page with the notification ID
			} catch (error) {
				console.error('Error accepting notification:', error);
			}
		},

		async notificationUpdate(notificationType) {
			try {
				const notificationId = this.$route.params.id;
				if (!notificationId) {
					console.error('Notification ID is undefined');
					return;
				}

				// API call to update the notification status
				const response = await axios.put(
					`http://localhost:8080/notifications/${notificationId}/update-notification-status/${notificationType}`,
					{},
					{
						withCredentials: true,
					}
				);

				console.log(
					`Notification updated to: ${notificationType}`,
					response.data
				);

				if (notificationType === 'NOTIFICATION_REJECTED') {
					this.goto({ path: '/mainpage' }); // Redirect to the main page if rejected
				}
			} catch (error) {
				console.error('Error updating notification status:', error);
			}
		},
	},
};
</script>

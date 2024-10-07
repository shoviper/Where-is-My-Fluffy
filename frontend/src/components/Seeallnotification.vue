<template>
	<div class="min-h-screen bg-[#FFF3EB] flex flex-col">
		<!-- Header section -->
		<header class="bg-[#FFF3EB] w-full z-10 fixed top-0 left-0">
			<div
				class="mx-auto px-8 flex items-center justify-between md:h-20 w-full bg-[#fff3eb]"
			>
				<img
					src="../assets/fluffy.png"
					alt="home pic"
					class="w-32 h-16"
				/>
				<button
					class="w-10 h-10 bg-white rounded-full text-white flex items-center justify-center"
				>
					<Icon
						icon="tabler:home"
						class="text-slate-700 w-10 h-7"
						@click="
							goto({
								path: `/mainpage`,
							})
						"
					/>
				</button>
				<div class="flex-grow mx-4">
					<input
						type="text"
						placeholder="Where's My Fluffy"
						class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
					/>
				</div>
				<button
					class="w-10 h-10 bg-white rounded-full text-white flex items-center justify-center"
				>
					<Icon
						icon="iconamoon:profile"
						class="text-slate-700 w-10 h-7"
						@click="goto({ path: '/' })"
					/>
				</button>
			</div>
		</header>

		<!-- Main notification box -->
		<div class="flex-grow flex p-4 gap-4 h-full mt-12">
			<div class="w-1/2 py-4 pr-4 flex-grow flex flex-col items-center">
				<div class="w-11/12 h-auto bg-white p-4 rounded-md">
					<!-- Notification header -->
					<div class="flex justify-between items-center mb-4">
						<h2 class="text-2xl font-semibold">Notifications</h2>
					</div>

					<!-- Notifications List -->
					<div>
						<div
							class="border-t border-gray-300 cursor-pointer hover:bg-slate-100"
							v-for="(mesg, index) in notification"
							:key="index"
						>
							<div
								class="flex items-start space-x-2 py-4"
								@click="
									goto({
										path: `/seeeachnotification/${mesg.id}`,
									})
								"
							>
								<div class="w-full">
									<p
										class="text-sm text-gray-800 text-left font-medium"
									>
										{{ mesg.title }}
									</p>
									<p
										class="text-sm text-gray-800 text-left font-medium"
									>
										{{ mesg.message }}
									</p>
									<p class="text-xs text-gray-500 text-left">
										{{ formatDate(mesg.timestamp) }}
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import axios from 'axios';
import { Icon } from '@iconify/vue';

export default {
	name: 'seeallnotification',
	components: {
		Icon,
	},
	data() {
		return {
			notification: [],
		};
	},
	mounted() {
		this.fetchNotifications();
	},
	methods: {
		async fetchNotifications() {
			try {
				const response = await axios.get(
					'http://localhost:8080/notifications/me',
					{
						withCredentials: true,
					}
				);

				this.notification = response.data.reverse();
			} catch (error) {
				console.error('Error fetching notifications:', error);
			}
		},

		formatDate(dateString) {
			if (!dateString) return 'No date available';
			const date = new Date(dateString);
			return date.toLocaleString('en-US', {
				year: 'numeric',
				month: 'long',
				day: 'numeric',
				hour: '2-digit',
				minute: '2-digit',
				second: '2-digit',
				hour12: true,
			});
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

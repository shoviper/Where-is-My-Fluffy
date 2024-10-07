<template>
	<div
		class="bg-white px-4 pb-4 mt-4 rounded-md h-80 overflow-y-auto scroll-smooth"
	>
		<!-- Header -->
		<div
			class="flex justify-between bg-white py-6 items-center m sticky top-0"
		>
			<h2 class="text-base font-semibold">Notifications</h2>
			<a href="/seeallnotification" class="text-base text-red-500"
				>See all</a
			>
		</div>

		<!-- Notifications List -->
		<div v-if="notification.length != 0" class="space-y-4">
			<div
				class="border-t border-gray-300 py-2 cursor-pointer"
				v-for="(mesg, index) in notification"
				:key="index"
			>
				<div
					:class="{
						'cursor-not-allowed':
							mesg.notificationType !== 'NOTIFICATION_PENDING',
					}"
					@click="
						mesg.notificationType === 'NOTIFICATION_PENDING' &&
							goto({ path: `/seeeachnotification/${mesg.id}` })
					"
				>
					<p class="text-sm font-bold text-TEXTCOLOR text-left">
						{{ mesg.title }}
					</p>
					<p class="text-sm text-TEXTCOLOR text-left text-slate-500">
						{{ mesg.message }}
					</p>
					<!-- <p class="text-xs text-gray-500 text-left">Last Wednesday at 11:00 p.m.</p> -->
				</div>
			</div>
		</div>
		<div v-else class="text-slate-400">No Notifications</div>
	</div>
</template>

<script>
import axios from 'axios';
export default {
	name: 'd',
	components: {},
	data() {
		return {
			notification: [],
		};
	},
	mounted() {
		this.getPetType();
		// this.addPet();
	},
	methods: {
		async getPetType() {
			try {
				const response = await axios.get(
					'http://localhost:8080/notifications/me',
					{
						withCredentials: true,
					}
				);
				this.notification = response.data.reverse();
			} catch (error) {
				console.error('Error my notification:', error);
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
	},
};
</script>

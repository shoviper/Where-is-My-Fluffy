<template>
	<div class="min-h-screen bg-[#FFF3EB] flex flex-col overflow-hidden">
		<!-- Header Section -->
		<header class="bg-[#FFF3EB] w-full z-50 fixed pt-4 left-0">
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
					@click="scrollToTop"
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

		<!-- Main Content Section -->
		<div class="flex-grow flex mt-20 overflow-hidden">
			<!-- Sidebar Section (Left) -->
			<div
				class="w-1/4 py-4 pr-4 flex flex-col fixed h-full overflow-y-auto z-20"
			>
				<LostPets class="w-5/6 mb-4 ml-8" />
				<Tag />
			</div>

			<!-- Scrollable Main Content -->
			<div
				class="grid grid-cols-8 justify-center flex-grow mt-4 ml-1/4 pr-4 overflow-y-auto h-full z-10"
			>
				<div class="w-full col-start-3 col-span-4">
					<div
						class="bg-white rounded-md h-20 flex flex-col items-center justify-center cursor-pointer"
						@click="goto({ path: '/createpost' })"
					>
						<span>
							<Icon
								icon="streamline:add-circle-solid"
								class="h-8 w-auto text-red-200"
							/>
						</span>
						<p class="font-semibold">create post</p>
					</div>
					<div>
						<PostElement />
					</div>
				</div>
			</div>

			<!-- Notifications Section (Right) -->
			<div class="fixed top-20 right-0 w-1/5 h-full z-20 mr-10">
				<Notifications />
				<Ads />
			</div>
		</div>

		<div class="custom-shape-divider-bottom-1728151299 fixed z-0">
			<svg
				data-name="Layer 1"
				xmlns="http://www.w3.org/2000/svg"
				viewBox="0 0 1200 120"
				preserveAspectRatio="none"
			>
				<path
					d="M0,0V46.29c47.79,22.2,103.59,32.17,158,28,70.36-5.37,136.33-33.31,206.8-37.5C438.64,32.43,512.34,53.67,583,72.05c69.27,18,138.3,24.88,209.4,13.08,36.15-6,69.85-17.84,104.45-29.34C989.49,25,1113-14.29,1200,52.47V0Z"
					opacity=".25"
					class="shape-fill"
				></path>
				<path
					d="M0,0V15.81C13,36.92,27.64,56.86,47.69,72.05,99.41,111.27,165,111,224.58,91.58c31.15-10.15,60.09-26.07,89.67-39.8,40.92-19,84.73-46,130.83-49.67,36.26-2.85,70.9,9.42,98.6,31.56,31.77,25.39,62.32,62,103.63,73,40.44,10.79,81.35-6.69,119.13-24.28s75.16-39,116.92-43.05c59.73-5.85,113.28,22.88,168.9,38.84,30.2,8.66,59,6.17,87.09-7.5,22.43-10.89,48-26.93,60.65-49.24V0Z"
					opacity=".5"
					class="shape-fill"
				></path>
				<path
					d="M0,0V5.63C149.93,59,314.09,71.32,475.83,42.57c43-7.64,84.23-20.12,127.61-26.46,59-8.63,112.48,12.24,165.56,35.4C827.93,77.22,886,95.24,951.2,90c86.53-7,172.46-45.71,248.8-84.81V0Z"
					class="shape-fill"
				></path>
			</svg>
		</div>
	</div>
</template>

<style lang="css">
.custom-shape-divider-bottom-1728151299 {
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	overflow: hidden;
	line-height: 0;
	transform: rotate(180deg);
}

.custom-shape-divider-bottom-1728151299 svg {
	position: relative;
	display: block;
	width: calc(100% + 1.3px);
	height: 345px;
}

.custom-shape-divider-bottom-1728151299 .shape-fill {
	fill: #ffd8e1;
}
</style>

<script>
import LostPets from './LostPet.vue';
import Trends from './Trends.vue';
import { QuillEditor } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';
import { Icon } from '@iconify/vue';
import PostElement from './PostElement.vue';
import Notifications from './Notifications.vue';
import Tag from './Tag.vue';
import Ads from './Ads.vue';
export default {
	name: 'd',
	components: {
		LostPets,
		Notifications,
		Trends,
		Icon,
		PostElement,
		QuillEditor,
		Tag,
		Ads,
	},
	data: () => ({
		content: '',
		editorOption: {
			debug: 'info',
			placeholder: 'Write your information here...',
		},
	}),
	methods: {
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
		scrollToTop() {
			window.scrollTo({ top: 0, behavior: 'smooth' });
		},
	},
};
</script>

<template>
	<div
		v-for="pet in pets"
		:key="pet.id"
		class="mx-auto border-solid border-2 border-slate-100 p-6 rounded-lg max-w-20 h-20 place-content-center bg-white grid items-center justify-center"
	>
		<!-- {{ pet.name }} -->
		<!-- Conditional rendering for different animal types -->
		<div
			class="p-2 flex justify-center items-center flex-col"
			v-if="pet.animalType == 'ant'"
		>
			<Icon icon="noto-v1:ant" class="text-black=100 h-10 w-auto" />
			<div>{{ pet.name }}</div>
		</div>
		<div
			class="p-2 flex justify-center items-center flex-col"
			v-if="pet.animalType == 'bird'"
		>
			<Icon icon="noto-v1:bird" class="text-black=100 h-10 w-auto" />
			<div>{{ pet.name }}</div>
		</div>
		<div
			class="p-2 flex justify-center items-center flex-col"
			v-if="pet.animalType == 'cat'"
		>
			<Icon icon="noto-v1:cat-face" class="text-black=100 h-10 w-auto" />
			<div>{{ pet.name }}</div>
		</div>
		<div
			class="p-2 flex justify-center items-center flex-col"
			v-if="pet.animalType == 'crocodile'"
		>
			<Icon icon="noto-v1:crocodile" class="text-black=100 h-10 w-auto" />
			<div>{{ pet.name }}</div>
		</div>
		<div
			class="p-2 flex justify-center items-center flex-col"
			v-if="pet.animalType == 'dog'"
		>
			<Icon icon="noto-v1:dog-face" class="text-black=100 h-10 w-auto" />
			<div>{{ pet.name }}</div>
		</div>

		<div
			class="p-2 flex justify-center items-center flex-col"
			v-if="pet.animalType == 'fish'"
		>
			<Icon
				icon="noto-v1:tropical-fish"
				class="text-black=100 h-10 w-auto"
			/>
			<div>{{ pet.name }}</div>
		</div>
		<div
			class="p-2 flex justify-center items-center flex-col"
			v-if="pet.animalType == 'frog'"
		>
			<Icon icon="noto:frog" class="text-black=100 h-10 w-auto" />
			<div>{{ pet.name }}</div>
		</div>
		<div
			class="p-2 flex justify-center items-center flex-col"
			v-if="pet.animalType == 'lion'"
		>
			<Icon icon="noto-v1:lion" class="text-black=100 h-10 w-auto" />
			<div>{{ pet.name }}</div>
		</div>
		<div
			class="p-2 flex justify-center items-center flex-col"
			v-if="pet.animalType == 'monkey'"
		>
			<Icon
				icon="noto-v1:monkey-face"
				class="text-black=100 h-10 w-auto"
			/>
			<div>{{ pet.name }}</div>
		</div>
		<div
			class="p-2 flex justify-center items-center flex-col"
			v-if="pet.animalType == 'pig'"
		>
			<Icon icon="noto:pig-face" class="text-black=100 h-10 w-auto" />
			<div>{{ pet.name }}</div>
		</div>
		<div
			class="p-2 flex justify-center items-center flex-col"
			v-if="pet.animalType == 'rabbit'"
		>
			<Icon
				icon="noto-v1:rabbit-face"
				class="text-black=100 h-10 w-auto"
			/>
			<div>{{ pet.name }}</div>
		</div>
		<div
			class="p-2 flex justify-center items-center flex-col"
			v-if="pet.animalType == 'turtle'"
		>
			<Icon icon="noto-v1:turtle" class="text-black=100 h-10 w-auto" />
			<div>{{ pet.name }}</div>
		</div>
	</div>
</template>

<script>
import { Icon } from '@iconify/vue';
import axios from 'axios';

export default {
	name: 'petprofile',
	data() {
		return {
			pets: [], // Initialize pets as an empty array
		};
	},
	components: {
		Icon,
	},
	mounted() {
		this.fetchPets();
	},
	methods: {
		async fetchPets() {
			try {
				const response = await axios.get(
					'http://localhost:8080/pets/me',
					{
						withCredentials: true,
					}
				);
				this.pets = response.data; // Assign fetched pets to the pets array
				console.log(this.pets); // Log the pets correctly
			} catch (error) {
				console.error('Error fetching pets:', error);
			}
		},
	},
};
</script>

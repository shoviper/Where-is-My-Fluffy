<template>
	<!-- Main modal -->
	<div
		tabindex="-1"
		class="fixed top-0 right-0 left-0 z-50 flex justify-center items-center w-full h-[calc(100%-1rem)] max-h-screen bg-black bg-opacity-50"
	>
		<div class="relative p-4 w-full max-w-96 max-h-full">
			<!-- Modal content -->
			<div class="relative bg-white rounded-lg shadow">
				<!-- Modal header -->
				<div
					class="flex items-center justify-between p-4 md:p-5 border-b rounded-t"
				>
					<h3 class="text-xl font-semibold text-TEXTCOLOR">
						Adding your fluffy!
					</h3>
					<button
						@click="closeModal"
						type="button"
						class="text-gray-400 hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 inline-flex justify-center items-center"
					>
						<svg
							class="w-3 h-3"
							xmlns="http://www.w3.org/2000/svg"
							fill="none"
							viewBox="0 0 14 14"
						>
							<path
								stroke="currentColor"
								stroke-linecap="round"
								stroke-linejoin="round"
								stroke-width="2"
								d="M1 1l6 6m0 0l6 6M7 7L13 1M7 7L1 13"
							/>
						</svg>
					</button>
				</div>

				<!-- Modal body -->
				<div class="p-4 md:p-5">
					<form @submit.prevent="submitPet" class="space-y-4">
						<div>
							<label
								for="name"
								class="block mb-2 text-sm text-left font-medium text-gray-900"
								>Name</label
							>
							<input
								v-model="name"
								type="text"
								id="name"
								class="bg-gray-50 border border-gray-300 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
								placeholder="laila"
								required
							/>
						</div>

						<div class="grid grid-cols-2">
							<div class="mr-4">
								<label
									for="age"
									class="block mb-2 text-sm text-left font-medium text-gray-900"
									>Age</label
								>
								<input
									v-model="age"
									type="number"
									id="age"
									class="bg-gray-50 border border-gray-300 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
									placeholder="5"
									required
								/>
							</div>

							<div>
								<label
									for="animalType"
									class="block mb-2 text-sm text-left font-medium text-gray-900"
									>Type</label
								>
								<select
									v-model="selected"
									class="bg-gray-50 border border-gray-300 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
								>
									<option disabled value="">
										Please select one
									</option>
									<option
										v-for="(type, index) in pettypes"
										:key="index"
										:value="type.type"
									>
										{{ type.type }}
									</option>
								</select>
							</div>
						</div>

						<div>
							<label
								for="description"
								class="block mb-2 text-sm text-left font-medium text-gray-900"
								>Description</label
							>
							<input
								v-model="description"
								type="text"
								id="description"
								placeholder="a cute dog"
								class="bg-gray-50 border border-gray-300 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
								required
							/>
						</div>
						<div>
							<label
								for="location"
								class="block mb-2 text-sm text-left font-medium text-gray-900"
								>Location</label
							>
							<input
								v-model="location"
								type="text"
								id="location"
								placeholder="KMITL"
								class="bg-gray-50 border border-gray-300 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
								required
							/>
						</div>
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
			</div>
		</div>
	</div>
</template>

<script>
import axios from 'axios';
export default {
	props: {
		pettypes: Array, // Passed down from the parent component
	},
	data() {
		return {
			name: '',
			age: null,
			selected: '',
			description: '',
			location: '',
			pettypes: [],
		};
	},
	mounted() {
		this.getPetType();
		// this.addPet();
	},
	methods: {
		closeModal() {
			this.$emit('close'); // Emit event to close the modal
		},
		async submitPet() {
			const newPet = {
				name: this.name,
				age: this.age,
				animalType: this.selected,
				description: this.description,
				location: this.location,
				status: 'MISSING', // Hardcoded status for missing pets
			};

			try {
				// Step 1: Create the pet
				const response = await axios.post(
					'http://localhost:8080/pets',
					newPet,
					{
						withCredentials: true,
					}
				);

				const petData = await response.data;
				console.log('Creating success', petData);

				// Step 2: Upload the pet image
				const fileInput = this.$refs.file_input.files[0];
				const formData = new FormData();
				formData.append('file', fileInput);
				formData.append('petId', petData.id); // Assuming petData contains the ID of the created pet

				await axios.put(
					`http://localhost:8080/pets/${petData.id}/uploadImage`,
					formData,
					{
						headers: {
							'Content-Type': 'multipart/form-data',
						},
						withCredentials: true,
					}
				);

				alert('Pet and image uploaded successfully!');
			} catch (error) {
				console.log(
					'Error creating new pet or uploading image:',
					error
				);
				alert('Error occurred during pet creation or image upload.');
			}
		},
		async getPetType() {
			try {
				const response = await axios.get(
					'http://localhost:8080/animal-type',
					{
						withCredentials: true,
					}
				);
				this.pettypes = response.data;
			} catch (error) {
				console.error('Error fetching animal types:', error);
			}
		},
	},
};
</script>

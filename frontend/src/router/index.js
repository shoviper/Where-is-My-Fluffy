import { createRouter, createWebHistory } from 'vue-router';
import Login from '../components/Login.vue';
import Home from '../components/Home.vue';
import Mainpage from '../components/Mainpage.vue';
import Seealllostpet from '../components/Seealllostpet.vue';
import Profile from '../components/Profile.vue';

const routes = [
	{
		path: '/',
		name: 'Home',
		component: Home,
	},
	{
		path: '/login',
		name: 'Login',
		component: Login,
	},
	{
		path: '/mainpage',
		name: 'Mainpage',
		component: Mainpage,
	},
	{
		path: '/seealllostpet',
		name: 'Seealllostpet',
		component: Seealllostpet,
	},
	{
		path: '/profile',
		name: 'Profile',
		component: Profile,
	}
];

const router = createRouter({
	history: createWebHistory(),
	routes,
});

export default router;

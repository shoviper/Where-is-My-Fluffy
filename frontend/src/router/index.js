import { createRouter, createWebHistory } from 'vue-router';
import Login from '../components/Login.vue';
import Home from '../components/Home.vue';
import Mainpage from '../components/Mainpage.vue';

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
	}
];

const router = createRouter({
	history: createWebHistory(),
	routes,
});

export default router;

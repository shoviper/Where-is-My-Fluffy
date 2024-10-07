import { createRouter, createWebHistory } from 'vue-router';
import Login from '../components/Login.vue';
import Home from '../components/Home.vue';
import Mainpage from '../components/Mainpage.vue';
import Seealllostpet from '../components/Seealllostpet.vue';
import Profile from '../components/Profile.vue';
import CreatePost from '../components/CreatePost.vue';
import Seeallnotification from '../components/Seeallnotification.vue';
import Seeeachnotification from '../components/Seeeachnotification.vue';
import Payment from '../components/Payment.vue';

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
	},
	{
		path: '/createpost',
		name: 'CreatePost',
		component: CreatePost,
	},
	{
		path: '/seeallnotification',
		name: 'Seeallnotification',
		component: Seeallnotification,
	},
	{
		path: '/seeeachnotification/:id',
		name: 'Seeeachnotification',
		component: Seeeachnotification,
	},
	{
		path: '/payment',
		name: 'Payment',
		component: Payment,
	}
	
];

const router = createRouter({
	history: createWebHistory(),
	routes,
});

export default router;

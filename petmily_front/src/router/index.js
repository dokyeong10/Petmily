import { createRouter, createWebHistory } from "vue-router";

// main
import Home from "@/views/home/Home";
import Error from "@/views/Error.vue";

// Login & Signup & ChangePassword
import Login from "@/views/accounts/Login";
import SignupTerms from "@/views/accounts/SignupTerms";
import AgencyForm from "@/views/accounts/AgencyForm.vue";
import PrivateForm from "@/views/accounts/PrivateForm.vue";
import SignupSuccess from "@/views/accounts/SignupSuccess.vue";
import FindPassword from "@/views/accounts/FindPassword.vue";
import ChangePassword from "@/views/accounts/ChangePassword.vue";

// Profile
import ProfilePrivate from "@/views/accounts/ProfilePrivate.vue";
import ProfileAgency from "@/views/accounts/ProfileAgency.vue";
import ConfirmPassword from "@/views/accounts/ConfirmPassword.vue";
import PrivateModify from "@/views/accounts/PrivateModify.vue";
import AgencyModify from "@/views/accounts/AgencyModify.vue";

// Animal
import AnimalList from "@/views/animal/AnimalList.vue";
import AnimalDetail from "@/views/animal/AnimalDetail.vue";
import AnimalRegister from "@/views/animal/AnimalRegister.vue";
import AnimalRegisterSuccess from "@/views/animal/AnimalRegisterSuccess.vue";
import AnimalModify from "@/views/animal/AnimalModify.vue";
import AnimalDeleteSuccess from "@/views/animal/AnimalDeleteSuccess.vue";

//live
import Live from "@/views/live/Live.vue";
import LiveRoom from "@/views/live/LiveRoom.vue";

// Shelter
import ShelterDetail from "@/views/shelter/ShelterDetail.vue";

// Community
import Community from "@/views/community/Community.vue";
import CommunityRegister from "@/views/community/CommunityRegister.vue";
import BoardDetail from "@/views/community/BoardDetail.vue";
import BoardDetailModify from "@/views/community/BoardDetailModify.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
  },
  {
    path: "/login",
    name: "login",
    component: Login,
  },
  {
    path: "/signupterms",
    name: "signupterms",
    component: SignupTerms,
  },
  {
    path: "/agencyform",
    name: "agencyform",
    component: AgencyForm,
  },
  {
    path: "/privateform",
    name: "privateform",
    component: PrivateForm,
  },
  {
    path: "/signupsuccess",
    name: "signupsuccess",
    component: SignupSuccess,
  },
  {
    path: "/findpassword",
    name: "findpassword",
    component: FindPassword,
  },
  {
    path: "/changepassword",
    name: "changepassword",
    component: ChangePassword,
  },
  {
    path: "/animallist",
    name: "animallist",
    component: AnimalList,
  },
  {
    path: "/animal/details/:id",
    name: "animaldetail",
    component: AnimalDetail,
  },
  {
    path: "/animalregister",
    name: "animalregister",
    component: AnimalRegister,
  },
  {
    path: "/animalregistersuccess",
    name: "animalregistersuccess",
    component: AnimalRegisterSuccess,
  },
  {
    path: "/animal/:no",
    name: "animalmodify",
    component: AnimalModify,
    props: true,
  },
  {
    path: "/animaldeletesuccess",
    name: "animaldelete",
    component: AnimalDeleteSuccess,
  },
  {
    path: "/profileprivate",
    name: "profileprivate",
    component: ProfilePrivate,
  },
  {
    path: "/profileagency",
    name: "profileagency",
    component: ProfileAgency,
  },
  {
    path: "/confirmpassword",
    name: "confirmpassword",
    component: ConfirmPassword,
  },
  {
    path: "/privatemodify",
    name: "privatemodify",
    component: PrivateModify,
  },
  {
    path: "/agencymodify",
    name: "agencymodify",
    component: AgencyModify,
  },
  {
    path: "/live",
    name: "live",
    component: Live,
  },
  {
    path: "/shelterdetail/:agencycode",
    name: "shelterdetail",
    component: ShelterDetail,
  },
  {
    path: "/community",
    name: "community",
    component: Community,
  },
  {
    path: "/communityregister",
    name: "communityregister",
    component: CommunityRegister,
  },
  {
    path: "/liveroom/:agencycode",
    name: "liveroom",
    component: LiveRoom,
  },
  {
    path: "/boarddetail/:boardno",
    name: "boarddetail",
    component: BoardDetail,
  },
  {
    path: "/error",
    name: "error",
    component: Error,
  },
  {
    path: "/:pathMatch(.*)*",
    redirect: "/error",
  },
  {
    path: "/boarddetailmodify/:boardno",
    name: "boarddetailmodify",
    component: BoardDetailModify,
  },

  // {
  //   path: '/',
  //   name: 'Home',
  //   component: Home
  // },
  // {
  //   path: '/about',
  //   name: 'About',
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
  // component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
];

const router = createRouter({
  mode: "history",
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;

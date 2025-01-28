import express from "express";
import {signUp, signIn} from "../controllers/user-controller.js"

const router = express.Router();

router.post('/user/signup', signUp);
router.post('/user/login', signIn);

export default router;
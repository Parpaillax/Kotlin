import {User} from '../models/user.js';
import argon2 from 'argon2';
import jwt from 'jsonwebtoken';
import dotenv from 'dotenv';
dotenv.config();

// Inscription d'un user
export const signUp = async (req, res) => {
    const user = req.body;
    try {
        user.password = await argon2.hash(user.password)
        const newUser = new User(user);
        await newUser.save();
        return res.status(201).json(newUser);
    } catch(err) {
        console.error(err);
        res.status(500).json({message : "Erreur lors de l'inscription"})
    }
}

// Connexion d'un user
export const signIn = async (req, res) => {
    const user = req.body;
    try {
        const userExist = await User.findOne({email: user.email})
        const isGoodPassword = userExist?.password && await argon2.verify(userExist?.password, req.body.password)
        if (!userExist || !isGoodPassword) {
            return res.status(403).json({message : "Email ou mot de passe incorrect"})
        }
        const token = jwt.sign({userId: userExist.customId}, process.env.JWT_SECRET, {expiresIn: '15m'});
        console.log(token);
        return res.status(200).json(token)
    } catch(err) {
        console.error(err);
        res.status(500).json({message : "Erreur lors de l'authentification"});
        throw err;
    }
}
// server.js
import express from "express";
import mongoose from "mongoose";
import bodyParser from 'body-parser';
import movieRoutes from './routes/movie-routes.js';

// Création de l'application Express
const app = express();

// Middleware pour parser le body des requêtes en JSON
app.use(bodyParser.json());

// Connexion à la base de données MongoDB
mongoose.connect('mongodb://localhost:27017/MoviesKotlin')
    .then(() => {
        console.log('Connecté à la base de données');
    })
    .catch((error) => {
        console.error('Erreur de connexion à la base de données', error);
    });

// Routes pour les films
app.use('/api', movieRoutes);

// Démarrer le serveur
const port = 3000;
app.listen(port, '0.0.0.0', () => {
    console.log(`Le serveur est démarré sur le port ${port}`);
});

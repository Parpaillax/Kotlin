// models/Movie.js
import mongoose from "mongoose";

// Définition du schéma pour le modèle Movie
const movieSchema = new mongoose.Schema({
    customId: {Type: Number},
    title: { type: String, required: true },
    description: { type: String, required: true },
    duration: { type: Number, required: true },
    year: { type: Number, required: true },
});

// Création du modèle Movie basé sur le schéma
const Movie = mongoose.model('Movie', movieSchema);

export default Movie;
export { Movie };
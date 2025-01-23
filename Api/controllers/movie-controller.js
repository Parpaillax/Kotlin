// controllers/movieController.js
import {Movie} from '../models/movie.js';

// Récupérer tous les films
export const getAllMovies = async (req, res) => {
    try {
        const movies = await Movie.find();
        res.status(200).json(movies);
    } catch (error) {
        res.status(500).json({ message: 'Erreur lors de la récupération des films' });
    }
};

// Récupérer un film par ID
export const getMovieById = async (req, res) => {
    const { id } = req.params;
    try {
        const numericId = parseInt(id, 10);
        const movie = await Movie.findOne({customId: numericId});
        if (!movie) {
            return res.status(404).json({ message: 'Film non trouvé' });
        }
        res.status(200).json(movie);
    } catch (error) {
        res.status(500).json({ message: 'Erreur lors de la récupération du film' });
    }
};

// Sauvegarder un film (création ou mise à jour)
export const saveMovie = async (req, res) => {
    const { title, description, duration, year } = req.body;
    try {
        const movie = new Movie({ title, description, duration, year });
        await movie.save();
        res.status(201).json(movie);
    } catch (error) {
        res.status(500).json({ message: 'Erreur lors de la sauvegarde du film' });
    }
};

export const editMovie = async (req, res) => {
    const { id } = req.params;
    const { title, description, duration, year } = req.body;
    try {
        const numericId = parseInt(id, 10);
        const newMovie = await Movie.findOneAndUpdate({customId: numericId},{title: title, description: description, duration: duration, year: year});
        res.status(200).json(newMovie);
    } catch(err) {
        console.error(err);
        res.status(500).json({ message: 'Erreur lors de la modification du film' });
    }
}

export const deleteMovie = async (req, res) => {
    const { id } = req.params;
    try {
        const numericId = parseInt(id, 10);
        await Movie.deleteOne({customId: numericId});
        res.status(200).json(true);
    } catch(err) {
        console.error(err);
        res.status(500).json({ message: 'Erreur lors de la suppression du film' });
    }
}

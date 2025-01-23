// routes/movieRoutes.js
import express from 'express';
import {getAllMovies, saveMovie, getMovieById, editMovie, deleteMovie} from '../controllers/movie-controller.js';

const router = express.Router();

// Récupérer tous les films
router.get('/movies', getAllMovies);

// Récupérer un film par ID
router.get('/movie/:id', getMovieById);

// Modification d'un film par son ID
router.patch('/movie/edit/:id', editMovie);

// Suppression d'un film par son ID
router.delete('/movie/delete/:id', deleteMovie);

// Sauvegarder un film (création ou mise à jour)
router.post('/movie', saveMovie);

export default router;

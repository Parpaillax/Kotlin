import mongoose from 'mongoose';

const userSchema = new mongoose.Schema({
    customId: {Type: Number},
    email: {type: String, required: true},
    pseudo: {type: String, required: true},
    password: {type: String, required: true},
    cityCode: {type: String, required: false},
    city: {type: String, required: false},
    phoneNumber: {type: String, required: false},
})

const User = mongoose.model('User', userSchema);
export default User;
export {User};
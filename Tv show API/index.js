const express = require('express');
const mongoose = require('mongoose');
const dotenv = require('dotenv');
const tvshowRoute = require('./routes/tvshow');
const cors = require('cors'); // Import the cors middleware

const app = express();
app.use(express.urlencoded({extended: true}));
app.use(express.json());
app.use(cors());
dotenv.config();


mongoose.connect(process.env.MONGO_URL)
.then(()=>{ console.log('Database connection Successful !!') })
.catch((err)=>{
    console.error(err);
});


//Api endpoint Get request
app.use('/api/tvshows' , tvshowRoute);


app.listen(process.env.PORT || 5000 , () => {
    console.log(`Server listening on port ${process.env.PORT}`);
});


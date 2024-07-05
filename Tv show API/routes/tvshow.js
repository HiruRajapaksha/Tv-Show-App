const router = require('express').Router();
const TvShow = require('../models/Tvshow');


// Create TvShow
router.post('/create' , async (req,res) => {

    try{
        const newTVShow = req.body;
        const createdTVShow = await TvShow.create(newTVShow);
        res.status(201).json(createdTVShow);
    }catch(err){
        res.status(500).json(err);
    }

});


// Get All TvShows
router.get('/' , async (req,res)=>{
    
    try{
        const tvshows = await TvShow.find();
        res.status(200).json(tvshows);
    }catch(err){
        res.status(500).json(err);
    }
});


module.exports = router;








// // UPDATE
// router.put('/:id' , verifyTokenAndAuth, async (req,res) => {
//     if(req.body.password){
//         req.body.password = CryptoJS.AES.encrypt(req.body.password, process.env.PASS_SEC).toString();
//     }

//     try{
//         const updatedUser = await User.findByIdAndUpdate(req.params.id, {
//             $set: req.body
//         }, {new: true});

//         res.status(200).json(updatedUser);
//     }catch(err){
//         res.status(500).json(err);
//
























// //Get Request
// router.get('/usertest' , (req,res) => {
//     res.send('User test is successfull !!');
// });

// //Post Request
// router.post('/userposttest' , (req,res) => {
//     const username = req.body.username;
//     res.send(`Your username is ${username}`);

// });
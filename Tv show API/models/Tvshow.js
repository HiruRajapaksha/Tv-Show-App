const mongoose = require('mongoose');

const tvShowSchema = new mongoose.Schema({
  id: Number,
  name: String,
  permalink: String,
  description: String,
  start_date: Date,
  end_date: Date,
  country: String,
  status: String,
  runtime: Number,
  network: String,
  image_path: String,
  image_thumbnail_path: String,
  rating: String,
  genres: [String],
  pictures: [String]
});

module.exports = mongoose.model('TvShow', tvShowSchema);

// runtime: Number,
// description: String,
// rating: String,
// genres: [String],
// pictures: [String]









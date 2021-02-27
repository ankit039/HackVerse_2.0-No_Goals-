const sqlite3 = require("sqlite3").verbose();
const database = new sqlite3.Database("db.db");
const bcrypt = require('bcrypt');
const auth = require("../authjwt");

exports.password=(req,res)=>{
     //for updating password
}

exports.skills=(req,res)=>{
    //for updating skills
}
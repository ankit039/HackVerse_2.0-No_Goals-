const express = require("express");
var bodyParser = require("body-parser");
const bcrypt = require('bcrypt');

const sqlite3 = require("sqlite3").verbose();
const database = new sqlite3.Database("db.db");

const saltRounds = Math.floor(Math.random() * 7) + 5;

exports.regsiter = (req, res) => {
  //for signup
  var {
    username,
    fullName,
    emailId,
    password,
    skills,
    image,
    connect,
    accept,
    reject,
  } = req.body;
  const hashpw = bcrypt.hashSync(password, saltRounds);
  database.run(
    `insert into user (username, fullName, emailId, password, image, skills, connect, accept , reject) VALUES (
          "${username}","${fullName}","${emailId}","${hashpw}","${image}","${skills}","${connect}","${accept}","${reject}"
        );`,
    (err) => {
      if (err) {
        res.statusCode = 403;
        res.setHeader("Content-Type", "application/json");
        res.json([{ sucess: false, msg: err.message }]);
      } else {
        res.statusCode = 200;
        res.setHeader("Content-Type", "application/json");
        res.json([{ sucess: true, msg: "Register Sucess" }]);
      }
    }
  );
};

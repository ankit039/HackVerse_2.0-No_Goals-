const express = require("express");
var bodyParser = require("body-parser");
const sqlite3 = require("sqlite3").verbose();
const bcrypt = require("bcrypt");
const auth = require("../authjwt");

const database = new sqlite3.Database("./db.db");
const saltRounds = Math.floor(Math.random() * 7) + 5;

const apiRouter = express.Router();
apiRouter.use(bodyParser.json());
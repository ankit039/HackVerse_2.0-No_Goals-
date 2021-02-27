const express = require("express");
var bodyParser = require("body-parser");
const sqlite3 = require("sqlite3").verbose();
const bcrypt = require("bcrypt");
const auth = require("./authjwt");

const database = new sqlite3.Database("./db.db");
const saltRounds = Math.floor(Math.random() * 7) + 5;

const apiRouter = express.Router();
apiRouter.use(bodyParser.json());

apiRouter.route("/login").post((req, res, next) => {
  //for login
  var { credential, password } = req.body;
  if (credential.includes("@")) {
    database.get(
      `SELECT * FROM user WHERE emailId = "${credential}";`,
      function (err, rows) {
        if (err) {
          res.statusCode = 403;
          res.setHeader("Content-Type", "application/json");
          res.json([{ sucess: false, msg: err.message }]);
        } else {
          if (!rows) {
            //email not found
            res.statusCode = 403;
            res.setHeader("Content-Type", "application/json");
            res.json([{ sucess: false, msg: "E-mail is not regsitered." }]);
          } else {
            //email found
            database.get(
              `SELECT * FROM user WHERE emailID = "${credential}";`,
              function (err, rows) {
                if (err) {
                  res.statusCode = 403;
                  res.setHeader("Content-Type", "application/json");
                  res.json([{ sucess: false, msg: err.message }]);
                } else {
                  const pwd_check = bcrypt.compareSync(password, rows.password);
                  if (pwd_check == false) {
                    //if password not matched
                    res.statusCode = 403;
                    res.setHeader("Content-Type", "application/json");
                    res.json([{ sucess: false, msg: "Password not matched." }]);
                  } else {
                    //password matched
                    // const token_enrno = auth.verifyToken(req, res, next);
                    auth.getToken(credential).then((token) => {
                      res.statusCode = 200;
                      res.setHeader("Content-Type", "application/json");
                      res.json([
                        {
                          sucess: true,
                          msg: "Login Sucess",
                          username: rows.username,
                          fullName: rows.fullName,
                          emailId: rows.emailId,
                          skills:
                            rows.skills == "" ? [] : rows.skills.split(","),
                          connect:
                            rows.connect == "" ? [] : rows.connect.split(","),
                          reject:
                            rows.reject == "" ? [] : rows.reject.split(","),
                          accept:
                            rows.accept == "" ? [] : rows.accept.split(","),
                          token: token,
                          expiration: "1hr",
                        },
                      ]);
                    });
                  }
                }
              }
            );
          }
        }
      }
    );
  } else {
  }
});

apiRouter.route("/register").post((req, res, next) => {
  //for signup
  var {
    username,
    fullName,
    emailId,
    password,
    skills,
    connect,
    accept,
    reject,
  } = req.body;
  const hashpw = bcrypt.hashSync(password, saltRounds);
  database.run(
    `insert into user (username, fullName, emailId, password, skills, connect, accept , reject) VALUES (
          "${username}","${fullName}","${emailId}","${hashpw}","${skills}","${connect}","${accept}","${reject}"
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
});

module.exports = apiRouter;

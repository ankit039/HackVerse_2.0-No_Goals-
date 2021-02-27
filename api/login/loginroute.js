const sqlite3 = require("sqlite3").verbose();
const database = new sqlite3.Database("db.db");
const bcrypt = require("bcrypt");
const auth = require("../authjwt");

//`"${process.env.db}"`

exports.login = (req, res) => {
  //for login
  var { credential, password } = req.body;
  if (credential.includes("@")) {
    database.get(
      `SELECT * FROM user WHERE emailId = "${credential}";`,
      function (err, rows) {
        if (err) {
          res.statusCode = 403;
          res.setHeader("Content-Type", "application/json");
          res.json({ sucess: false, msg: err.message });
        } else {
          if (!rows) {
            //email not found
            res.statusCode = 403;
            res.setHeader("Content-Type", "application/json");
            res.json({ sucess: false, msg: "E-mail is not regsitered." });
          } else {
            //email found
            database.get(
              `SELECT * FROM user WHERE emailID = "${credential}";`,
              function (err, rows) {
                if (err) {
                  res.statusCode = 403;
                  res.setHeader("Content-Type", "application/json");
                  res.json({ sucess: false, msg: err.message });
                } else {
                  const pwd_check = bcrypt.compareSync(password, rows.password);
                  if (pwd_check == false) {
                    //if password not matched
                    res.statusCode = 403;
                    res.setHeader("Content-Type", "application/json");
                    res.json({ sucess: false, msg: "Password not matched." });
                  } else {
                    //password matched
                    // const token_enrno = auth.verifyToken(req, res, next);
                    auth.getToken(credential).then((token) => {
                      res.statusCode = 200;
                      res.setHeader("Content-Type", "application/json");
                      res.json({
                        sucess: true,
                        msg: "Login Sucess",
                        userName: rows.userName,
                        fullName: rows.fullName,
                        emailId: rows.emailId,
                        image: rows.image,
                        skills: rows.skills,
                        connect: rows.connect,
                        reject: rows.reject,
                        accept: rows.accept,
                        token: token,
                        expiration: "1hr",
                      });
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
    database.get(
      `SELECT * FROM user WHERE username = "${credential}";`,
      function (err, rows) {
        if (err) {
          res.statusCode = 403;
          res.setHeader("Content-Type", "application/json");
          res.json({ sucess: false, msg: err.message });
        } else {
          if (!rows) {
            //email not found
            res.statusCode = 403;
            res.setHeader("Content-Type", "application/json");
            res.json({ sucess: false, msg: "E-mail is not regsitered." });
          } else {
            //email found
            database.get(
              `SELECT * FROM user WHERE username = "${credential}";`,
              function (err, rows) {
                if (err) {
                  res.statusCode = 403;
                  res.setHeader("Content-Type", "application/json");
                  res.json({ sucess: false, msg: err.message });
                } else {
                  const pwd_check = bcrypt.compareSync(password, rows.password);
                  if (pwd_check == false) {
                    //if password not matched
                    res.statusCode = 403;
                    res.setHeader("Content-Type", "application/json");
                    res.json({ sucess: false, msg: "Password not matched." });
                  } else {
                    //password matched
                    // const token_enrno = auth.verifyToken(req, res, next);
                    auth.getToken(credential).then((token) => {
                      res.statusCode = 200;
                      res.setHeader("Content-Type", "application/json");
                      res.json({
                        sucess: true,
                        msg: "Login Sucess",
                        userName: rows.userName,
                        fullName: rows.fullName,
                        emailId: rows.emailId,
                        image: rows.image,
                        skills: rows.skills,
                        connect: rows.connect,
                        reject: rows.reject,
                        accept: rows.accept,
                        token: token,
                        expiration: "1hr",
                      });
                    });
                  }
                }
              }
            );
          }
        }
      }
    );
  }
};

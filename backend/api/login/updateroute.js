const sqlite3 = require("sqlite3").verbose();
const database = new sqlite3.Database("db.db");
const bcrypt = require("bcrypt");
const auth = require("../authjwt");

const saltRounds = Math.floor(Math.random() * 7) + 5;

exports.feild = (req, res) => {
  const {
    userName,
    fullName,
    emailId,
    image,
    skills,
    connect,
    accept,
    reject,
    uid,
  } = req.body;
  //for updating data
  database.all(
    `UPDATE user SET 
    userName= "${userName}", 
    fullName= "${fullName}", 
    emailId= "${emailId}", 
    image= "${image}", 
    skills= "${skills}", 
    connect= "${connect}", 
    accept= "${accept}", 
    reject= "${reject}" 
    where uid = "${uid}";`,
    function (err, rows) {
      if (err) {
        res.statusCode = 403;
        res.setHeader("Content-Type", "application/json");
        res.json({ sucess: false, msg: err.message });
      } else {
        res.statusCode = 200;
        res.setHeader("Content-Type", "application/json");
        res.json({ sucess: true, msg: "Sucess" });
      }
    }
  );
};

exports.password = (req, res) => {
  const { oldpassword, newpassword, uid } = req.body;
  //for updating password
  database.get(
    `SELECT password FROM user WHERE uid = "${uid}";`,
    function (err, rows) {
      if (err) {
        res.statusCode = 403;
        res.setHeader("Content-Type", "application/json");
        res.json({ sucess: false, msg: err.message });
      } else {
        const pwd_check = bcrypt.compareSync(oldpassword, rows.password);
        if (pwd_check) {
          const hashpw = bcrypt.hashSync(newpassword, saltRounds);
          database.all(
            `UPDATE user SET 
                    password="${hashpw}" 
                    where uid = "${uid}";`,
            function (err, rows) {
              if (err) {
                res.statusCode = 403;
                res.setHeader("Content-Type", "application/json");
                res.json({ sucess: false, msg: err.message });
              } else {
                res.statusCode = 200;
                res.setHeader("Content-Type", "application/json");
                res.json({ sucess: true, msg: "Sucess" });
              }
            }
          );
        } else {
          res.statusCode = 403;
          res.setHeader("Content-Type", "application/json");
          res.json({ sucess: false, msg: "Old password didn't matched." });
        }
      }
    }
  );
};

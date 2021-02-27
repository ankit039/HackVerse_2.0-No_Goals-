const sqlite3 = require("sqlite3").verbose();
const database = new sqlite3.Database("db.db");

exports.getuserbyskills = (tokendata, req, res) => {
  const { username } = req.body;
  //skill form for logged user
  database.all(
    `SELECT uid,username,fullName,emailId,image,skills,connect,accept,reject FROM user where userName <> "${username}";`,
    function (err, rows) {
      if (err) {
        res.statusCode = 403;
        res.setHeader("Content-Type", "application/json");
        res.json({ sucess: false, msg: err.message });
      } else {
        //skill form for logged user
        res.statusCode = 200;
        res.setHeader("Content-Type", "application/json");
        res.json({ sucess: true, msg: "scucess", rows: rows });
      }
    }
  );
};

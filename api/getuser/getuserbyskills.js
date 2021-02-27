const sqlite3 = require("sqlite3").verbose();
const database = new sqlite3.Database("db.db");

exports.getuserbyskills=(tokendata,req,res)=>{
    const {username}=req.body;
    database.get(
        `SELECT skills FROM user WHERE username = "${username}";`,
        function (err, rows) {
          if (err) {
            res.statusCode = 403;
            res.setHeader("Content-Type", "application/json");
            res.json([{ sucess: false, msg: err.message }]);
          }
          else{
              //skill form for logged user
              console.log(rows.skills.split(/,/).join(",")[0])
              database.get(
                `SELECT * FROM user WHERE skills in (${rows.skills.split(/,/)});`,
                function (err, rows) {
                  if (err) {
                    res.statusCode = 403;
                    res.setHeader("Content-Type", "application/json");
                    res.json([{ sucess: false, msg: err.message }]);
                  }
                  else{
                      //skill form for logged user
                      console.log(rows.username);
                  }
                })
          }
        })
}
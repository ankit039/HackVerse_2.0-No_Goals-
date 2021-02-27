const express = require("express");
const sqlite3 = require("sqlite3").verbose();

//jwt token
// /const auth = require("./auth_jwt");

const app = express();
var cors = require('cors')
 
app.use(cors())

const database = new sqlite3.Database("./db.db");
const port = process.env.PORT || 4000;

const createContactTable = () => {
  const user = `
        CREATE TABLE IF NOT EXISTS user (
        uid INTEGER PRIMARY KEY,
        userName VARCHAR(255) UNIQUE,
        fullName VARCHAR(255),
        emailId VARCHAR(255) UNIQUE,
        password VARCHAR(255),
        skills VARCHAR(255),
        connect VARCHAR(255),
        reject VARCHAR(255),
        accept VARCHAR(255)
        )`;
  database.run(user);
//[react,c,c++,node,mongo,express,java]
//[0,1,2,3,4,5,6]

// let usertab = [
//     ["ankit039","Ankit R","itsrankit@gmail.com","$2b$10$JGOti0UsZNbQ3aU/jxiWyexVZizMHd/9u6roDrB7VtvMGg6wjdX2a","0,1,2,3","","",""]
//   ];
  
//   let q1 =
//     "insert into user (username, fullName, emailId, password, skills, connect, accept , reject) " +
//     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
  
//   let s1 = database.prepare(q1);
  
//   for (var i = 0; i < usertab.length; i++) {
//     s1.run(usertab[i], function (err) {
//       if (err) throw err;
//     });
//   }
  
//   s1.finalize();

};
createContactTable();

database.all(
    `SELECT * FROM user`,
    function (err, rows) {
      if (err) {
        reject(err);
      } else {
          console.log(rows);
      }
    }
  );

const apiRouter = require("./api/apiRoute");
app.use("/api", cors(), apiRouter);

console.log(`server listening on port: ${port}`);
app.listen(port);
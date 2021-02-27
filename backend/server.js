const express = require("express");
const sqlite3 = require("sqlite3").verbose();
var bodyParser = require("body-parser");

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
        image TEXT,
        skills VARCHAR(255),
        connect VARCHAR(255),
        reject VARCHAR(255),
        accept VARCHAR(255)
        )`;
  database.run(user);
//[react,c,c++,node,mongo,express,java]
//[0,1,2,3,4,5,6]

// let usertab = [
//     ["a","a r","a@gmail.com","$2b$10$JGOti0UsZNbQ3aU/jxiWyexVZizMHd/9u6roDrB7VtvMGg6wjdX2a","","0,1,2,3","","",""],
//     ["b","b r","b@gmail.com","$2b$10$JGOti0UsZNbQ3aU/jxiWyexVZizMHd/9u6roDrB7VtvMGg6wjdX2a","","5,7,1,6,9","","",""],
//     ["c","c r","c@gmail.com","$2b$10$JGOti0UsZNbQ3aU/jxiWyexVZizMHd/9u6roDrB7VtvMGg6wjdX2a","","7,1,2,6,4","","",""],
//     ["d","d r","d@gmail.com","$2b$10$JGOti0UsZNbQ3aU/jxiWyexVZizMHd/9u6roDrB7VtvMGg6wjdX2a","","0,1,2,3,4,5","","",""],
//     ["e","e r","e@gmail.com","$2b$10$JGOti0UsZNbQ3aU/jxiWyexVZizMHd/9u6roDrB7VtvMGg6wjdX2a","","0,1,2,3,4,5,6,7,8,9","","",""],
//     ["f","f r","f@gmail.com","$2b$10$JGOti0UsZNbQ3aU/jxiWyexVZizMHd/9u6roDrB7VtvMGg6wjdX2a","","8,9,1,5","","",""],
//     ["g","g r","g@gmail.com","$2b$10$JGOti0UsZNbQ3aU/jxiWyexVZizMHd/9u6roDrB7VtvMGg6wjdX2a","","0","","",""]
//   ];
  
//   let q1 =
//     "insert into user (username, fullName, emailId, password, image, skills, connect, accept , reject) " +
//     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
  
//   let s1 = database.prepare(q1);
  
//   for (var i = 0; i < usertab.length; i++) {
//     s1.run(usertab[i], function (err) {
//       if (err) throw err;
//     });
//   }
  
//   s1.finalize();

};
createContactTable();

// database.all(
//     `SELECT * FROM user`,
//     function (err, rows) {
//       if (err) {
//         reject(err);
//       } else {
//           console.log(rows);
//       }
//     }
//   );

const apiRouter = require("./api/apiRoute");

app.use(bodyParser({limit: '10mb'}));

app.use("/api", cors(), apiRouter);

console.log(`server listening on port: ${port}`);
app.listen(port);
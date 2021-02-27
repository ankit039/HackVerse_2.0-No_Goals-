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
//     ["ankit039","Ankit R","itsrankit@gmail.com","$2b$10$JGOti0UsZNbQ3aU/jxiWyexVZizMHd/9u6roDrB7VtvMGg6wjdX2a","data:image/png;base64,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ZmjXif3zv1rVYzL0L1inBpQQ4MoLtZgITiz9aExSCpyJc88qrTx7+6JAy1fANKpaMz98qqah9ymOWROnfvcfr/vLrJdI+WF52t3O9hqc2S1zl63LepRYPslbrqyZ8t5jBRfOMaPTxqUdenh9SkgcAaw5Tg8otSqjQuJXzn2o7y47BeGqVavYmnMYFp1wrPh0VXvvg3ed2q5t5w7EywOToxIABPoHLByfpe3qzqu9CPT2N2KZ69TAX+695+F1gf4BFs2JEG56N0OjsmnMWw82qFqtbVQoED42fs6b9yxzs3yg78T5guU5HxyvKhyo5zoqYHq3VCjcef9rWRPSma07G07tBnCg4PjsL57TGvRMfVGAAEMDozbOWz45pfcbXsxmc+6JA28c3XqqqoiCMjnWZEcFFBALRVuzV/715hngCq7tBrBp37eLdvyDUjP6WrB4XNaKGYuiwyKtmflFv352/IftF4+2d6tYyYMPRELR19kvz715GjiEB7sBfJC75enc9/VGI+sAdxdLHxg1/enMe5mPWDNotdojZ07tLcs/VHOusL7CYGLP+gNBkNxn630vZ6ROtG6FG/ixG8DOn/cu+HqNxqCzW3qzkydnD8+YPWGqrO+d9a7urvzis79UXahTt5a1111sq+3R65Rd7UZz77evi4QiX3dP5pTK7soAJkYP37LwFdsHYDiDN7sB/Hrx/D1bXilvroE9Uyjg6+45JWZkekTKpISRiTHxHpZzblsopR2qTo2up7j2UmFT5c81RSWNVUX1FXYXlEvcVqTf/9TMhbaX1LmET7sBaLSav29b/1H+D3qzqf8hzwoi/YJDvRVx8kBKKfMNd2YBLqkbleqOiraGgWYxASHkruHpr895JMYJT49cOzzbzXCmpHDF7o0/XjyJAcp8MAEImZk44bkp900exf93210Xdv//gaOTeBcMLrs5xWU3p7js5hSX3ZzisptTXHZzistuTnHZzSkuuznFZTenuOzmFJfdnOKym1NcdnOKy25OcdnNKS67OcVlN6e47OYUl92c8n8ly698uAGZzgAAAABJRU5ErkJggg==","0,1,2,3","","",""]
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
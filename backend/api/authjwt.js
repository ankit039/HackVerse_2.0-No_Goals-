var jwt = require("jsonwebtoken");
require("dotenv").config();

exports.getToken = (logindatax) =>
  new Promise((resolve, reject) => {
    const logindata = logindatax;
    console.log(logindata + " <-login/signup");
    if (logindata) {
      resolve(
        jwt.sign({ logindata: logindata }, process.env.secretKey, {
          expiresIn: "1h",
        })
      );
    } else {
      reject(false);
    }
  });

exports.verifyToken = (token) => {
  const fun1 = () => {
    const parse_token = token;
    const fun2 = jwt.verify(
      parse_token,
      process.env.secretKey,
      function (err, decoded) {
        if (err) {
          return false;
        } else {
          return decoded.logindata;
        }
      }
    );
    return fun2;
  };
  const last = fun1();
  return last;
}
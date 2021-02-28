const express = require("express");
var bodyParser = require("body-parser");
const auth = require("./authjwt");

const regsiter = require("../api/register/registerroute");
const login = require("../api/login/loginroute");
const getuserbyskills = require("../api/getuser/getuserbyskills");
const update = require("../api/login/updateroute");

const apiRouter = express.Router();
apiRouter.use(bodyParser.json());

apiRouter.post("/login", function (req, res, next) {
  login.login(req, res, next);
});

apiRouter.post("/register", function (req, res, next) {
  regsiter.regsiter(req, res, next);
});

apiRouter.post("/getuserbyskills", function (req, res, next) {
  const tokendata = auth.verifyToken(req.body.token, req, res);
  tokendata != false
    ? getuserbyskills.getuserbyskills(tokendata, req, res, next)
    : "";
});

apiRouter.post("/getuserbyuid", function (req, res, next) {
  const tokendata = auth.verifyToken(req.body.token, req, res);
  tokendata != false
    ? getuserbyskills.getuserbyuid(req, res, next)
    : "";
});


apiRouter.post("/update", function (req, res, next) {
  const tokendata = auth.verifyToken(req.body.token, req, res);
  tokendata != false ? update.feild(req, res, next) : "";
});

apiRouter.post("/updatepassword", function (req, res, next) {
  const tokendata = auth.verifyToken(req.body.token, req, res);
  tokendata != false ? update.password(req, res, next) : "";
});

module.exports = apiRouter;

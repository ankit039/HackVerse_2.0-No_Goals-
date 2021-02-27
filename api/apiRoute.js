const express = require("express");
var bodyParser = require("body-parser");
const auth = require("./authjwt");

const regsiter = require("../api/register/registerroute");
const login = require("../api/login/loginroute");
const getuserbyskills = require("../api/getuser/getuserbyskills");
const update = require("../api/login/updateroute");

const apiRouter = express.Router();
apiRouter.use(bodyParser.json());


apiRouter.post("/login",function (req, res, next) {
  login.login(req, res, next);
});

apiRouter.post("/register",function (req, res, next) {
  regsiter.regsiter(req, res, next);
});

apiRouter.post("/getuserbyskills",function (req, res, next) {
  const tokendata = auth.verifyToken(req.body.token,req,res);
  tokendata!=false?getuserbyskills.getuserbyskills(tokendata, req, res, next):"";
});

apiRouter.post("/updatepassword",function (req, res, next) {
  const tokendata = auth.verifyToken(req.body.token,req,res);
  tokendata!=false?update.password(tokendata, req, res, next):"";
});

apiRouter.post("/updateskills",function (req, res, next) {
  const tokendata = auth.verifyToken(req.body.token,req,res);
  tokendata!=false?update.skills(tokendata, req, res, next):"";
});

module.exports = apiRouter;

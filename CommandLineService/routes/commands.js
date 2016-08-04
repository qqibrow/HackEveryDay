/**
 * Created by lniu on 9/18/15.
 */
var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/:command', function(req, res, next) {
    res.render('index', { title: req.params.command + req.query.id + req.query.name});
});


module.exports = router;

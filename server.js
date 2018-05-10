var http = require('http');
var url = require('url');
var express = require('express');
var app = express();
var path    = require("path");

app.use(express.static(__dirname))
app.get('*', function(req, res){
    //res.sendFile(path.join(__dirname+'/main.html?body='+req.body));
    // var url = require('url');
    // var url_parts = url.parse(req.url, true);
    // var query = url_parts.query;
    
    res.redirect('main.html?body='+req.get("json"))
});

app.listen(8080);
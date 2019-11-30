const http = require('http');
const express = require('express');
const net = require('net');
const path = require('path');

const hostname = '127.0.0.1';
const app = express();
const port = 3000;

/* Login page*/
app.get('/', (req, res) => res.sendFile(path.join(__dirname + '/pages/login.html')));

/* Search page*/
app.get('/search', (req, res) => res.sendFile(path.join(__dirname + '/pages/search.html')));


app.listen(port, () => console.log(`Server running at http://${hostname}:${port}/`));

const IFS = net.connect(1010, 'localhost');

var jsonObject = JSON.stringify({
    message: "testing json",
    m2: "message2"
});

IFS.write(jsonObject);
IFS.end();
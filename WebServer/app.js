const http = require('http');
const express = require('express');
const net = require('net');

const hostname = '127.0.0.1';
const app = express();
const port = 3000;

// const server = http.createServer((req, res) => {
//   res.statusCode = 200;
//   res.setHeader('Content-Type', 'text/plain');
//   res.end('Hello World\n');
// });

// server.listen(port, hostname, () => {
//   console.log(`Server running at http://${hostname}:${port}/`);
// });

app.get('/', (req, res) => res.send('Hello World!'));
app.listen(port, () => console.log(`Server running at http://${hostname}:${port}/`));

const IFS = net.connect(1010, 'localhost');

var jsonObject = JSON.stringify({
    message: "testing json",
    m2: "message2"
});

IFS.write(jsonObject);
IFS.end();
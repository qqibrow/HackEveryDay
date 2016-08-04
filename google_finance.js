var fs = require('fs');
var readline = require('readline');
var util = require('util');
var request = require('request');
var sleep = require('sleep');

var rd = readline.createInterface({
    input: fs.createReadStream('./test'),
    output: process.stdout,
    terminal: false
});




var getData = function(line) {
    var symbolWithExch = line.split(":");
    var symbol = symbolWithExch[0];
    var exchange = symbolWithExch[1];
    /*
    if(exchange != "NYQ") {
        console.log(line + " is not from NYQ");
        return;
    }
    exchange = "NASD";
    */
    var url = util.format("https://www.google.com/finance/getprices?q=%s&i=60&p=10d&f=d,c,v,o,h,l&df=cpct&auto=1&ts=1438781400&ei=83PCVYmmKsvriwKAs5uoDg"
        ,symbol);
    console.log(url);
    sleep.usleep(250000);
    request(url, function(error, response, body) {
        if (!error && response.statusCode == 200) {
            fs.writeFile("./dataSleep/" + line, body, function(err) {
                if(err) {
                    return console.log(err);
                }
                console.log("The file was saved! " + line);
            });
        } else {
            console.log("error with" + url);
        }
    });

};
//getData("YHOO:NYfQ");
rd.on('line', getData,
    'close', function() {
        console.log('goodbye!');
        process.exit(0);
    });

var request = require('request');
var url = 'http://www.kbb.com/bmw/3-series/2012-bmw-3-series/328i-sedan-4d/?condition=excellent&vehicleid=374152&intent=buy-used&category=sedan&mileage=50600&pricetype=private-party';
request(url, function(error, response, body){
    if (!error && response.statusCode == 200) {
            var regexToExtractPrice = new RegExp("", "g");
            var testRegex = new RegExp("values");
var result = /(?:\"values\": )({(?:\s|.)*?)(?:,\s+\"timAmount\")/.exec(body);
    console.log(result[1]);
    }

})

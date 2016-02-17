var request = require('request');
var async = require('async');


var getRandomYear = function(callback) {
    callback(null, 2013);
}

/*
    Get vehicle brand and categories json file.
 */
var getBrandCategory = function(year, callback) {
    console.log(year);
    // TODO Check year is valid.

    var res =  {
        brand: "toyota",
        category: "corolla"
    }
    callback(null, res);
}

/*
    Construct Url like:
    http://www.kbb.com/bmw/3-series/2012-bmw-3-series/categories/?intent=buy-used
*/
var constructYearMakeModelUrl = function(yearMakeModelJson, callback) {
    console.log(yearMakeModelJson.brand);
    var randomUrl = "http://www.kbb.com/bmw/3-series/2012-bmw-3-series/categories/?intent=buy-used";
    callback(null, randomUrl);
}

/*
    grep "mod-category-inner" from  "http://www.kbb.com/bmw/3-series/2012-bmw-3-series/categories/?intent=buy-used" page.
 */

var grepBodyStylesFromUrl = function(yearMakeModeUrl, callback) {
    var results = [
        'http://www.kbb.com/bmw/3-series/2015-bmw-3-series/styles/?intent=buy-used&bodystyle=sedan',
        'http://www.kbb.com/bmw/3-series/2015-bmw-3-series/styles/?intent=buy-used&bodystyle=wagon'
    ];
    callback(null, results);
}

/*
    grep "style-name section-title” to get all <StyleName, vechileID> pair.
 */
var grepModeNameVehicleIDPair = function(url, callback) {
    // do the grep thing.
    var results = [
        'url1',
        'url2'
    ];
    callback(null, url);
}

var setMillage = function (mileage, url, callback) {
    var urlWithMileage = url + "&mileage=" + mileage;
    callback(urlWithMileage);
}




async.waterfall([
    getRandomYear,
    getBrandCategory,
    constructYearMakeModelUrl
        ], function(err, result) {
            console.log(result);
    }
)

/*
var url = 'http://www.kbb.com/bmw/3-series/2012-bmw-3-series/328i-sedan-4d/?condition=excellent&vehicleid=374152&intent=buy-used&category=sedan&mileage=50600&pricetype=private-party';
request(url, function(error, response, body){
    if (!error && response.statusCode == 200) {
            // Now it works. the problem is I must escape literal backslash in the '\s'
            var regexToExtractPrice = new RegExp('(?:"values": )({(?:\\s|.)*?)(?:,\\s+"timAmount")', "g");
            var matchResult = regexToExtractPrice.exec(body);
            if(matchResult == null) {
                console.log("Cannot find a match in html.");
            }
            var jsonResult = JSON.parse(matchResult[1]);
            console.log(jsonResult);

            // This works as well. Notice that the literal backslash doesn't need to be escaped in this case.
            /*
            var result = /(?:\"values\": )({(?:\s|.)*?)(?:,\s+\"timAmount\")/.exec(body);
            console.log(result[1]);

    }

});
*/



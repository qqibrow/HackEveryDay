/**
 * Created by lniu on 9/1/15.
 */
var request = require('request');
request('http://www.avaloncommunities.com/california/sunnyvale-apartments/avalon-silicon-valley/floor-plans', function (error, response, body) {
    if (!error && response.statusCode == 200) {
        console.log(body); // Show the HTML for the Google homepage.
    }
})

/**
 * Created by lniu on 9/1/15.
 */
var page = require('webpage').create();
function getElementByXpath(path) {
    return document.evaluate(path, document, null, XPathResult.ANY_TYPE, null);
}
var url = 'http://www.avaloncommunities.com/california/sunnyvale-apartments/avalon-silicon-valley/floor-plans';
var xpath = '//*[@id=\"bedrooms-1\"]/div[1]/div[1]/div/div/div[2]/div/table/tbody/tr[1]/td[4]';
page.open(url, function () {
    // Checks for bottom div and scrolls down from time to time
    var seq = 1;
    window.setInterval(function() {
        // Checks if there is a div with class=".has-more-items"
        // (not sure if this is the best way of doing it)
        //console.log(page.content);
         var count = getElementByXpath(xpath);
          var thisNode = count.iterateNext();
         console.log(thisNode.textContent);
        // var count = getElementByXpath("//html/body");
        if(count === null) { // Didn't find
            console.log("scoll to the bottom of the page");
            console.log("scroll top now is " + window.document.body.scrollTop);
            page.render("snapshot" + seq + ".png");
            page.evaluate(function() {
                // Scrolls to the bottom of page
                window.document.body.scrollTop += 200;
            });
        }
        else { // Found
            console.log("found the match");
            phantom.exit();
        }
    }, 2000); // Number of milliseconds to wait between scrolls

});

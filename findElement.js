/**
 * Created by lniu on 9/1/15.
 */

function getElementByXpath(path) {
    return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
}

page.open('http://www.avaloncommunities.com/california/sunnyvale-apartments/avalon-silicon-valley/floor-plans', function () {

    // Checks for bottom div and scrolls down from time to time
    window.setInterval(function() {
        // Checks if there is a div with class=".has-more-items"
        // (not sure if this is the best way of doing it)
        var count = getElementByXpath("//html/body/form[4]/section[16]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/table[1]/tbody[2]/tr[1]/td[4]/span[1]");
        if(count === null) { // Didn't find
            page.evaluate(function() {
                // Scrolls to the bottom of page
                window.document.body.scrollTop = document.body.scrollHeight;
            });
        }
        else { // Found
            console.log("found the match");
            phantom.exit();
        }
    }, 500); // Number of milliseconds to wait between scrolls

});

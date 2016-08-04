var page = require('webpage').create();
var url = 'http://www.avaloncommunities.com/california/sunnyvale-apartments/avalon-silicon-valley/floor-plans';

function getText() {
            var xpath = '//*[@id=\"bedrooms-1\"]/div[1]/div[1]/div/div/div[2]/div/table/tbody';
            // var xpath = '//*[@id=\"bedrooms-1\"]/div[1]/div[1]/div/div/div[2]/div/table/tbody/tr[1]/td[4]/span';
            var count = document.evaluate(xpath, document, null, XPathResult.STRING_TYPE, null);
            return count.stringValue;

}

page.open(url, function (status) {
    page.injectJs("http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js");
    window.setTimeout(function() {
      var content = page.content;
      //console.log(content);
      var res = page.evaluate(getText);
      console.log(res);
      phantom.exit();
    }, 3000);
});

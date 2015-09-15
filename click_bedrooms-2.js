var casper = require('casper').create();

function getText() {
            //var xpath = '//*[@id=\"bedrooms-1\"]/div[1]/div[1]/div/div/div[2]/div/table/tbody';
            var xpath = '//*[@id=\"bedrooms-1\"]/div[1]/div[1]/div/div/div[2]/div/table/tbody/tr[1]/td[4]/span';
            var count = document.evaluate(xpath, document, null, XPathResult.STRING_TYPE, null);
            return count.stringValue;

}

var url = 'http://www.avaloncommunities.com/california/sunnyvale-apartments/avalon-silicon-valley/floor-plans';
casper.start(url);

casper.wait(2000, function(){
        this.capture("test.png");
        var text = this.evaluate(getText);
        this.echo(text);
});

casper.run();

var casper = require('casper').create();
var url = 'http://www.avaloncommunities.com/california/sunnyvale-apartments/avalon-silicon-valley/floor-plans';
casper.userAgent('Mozilla/5.0 (Macintosh; Intel Mac OS X)');
casper.start(url);
casper.wait(2000);
casper.viewport(1024, 768, function() {
    this.echo("view point changed to 1024 * 768");
});

casper.then(function() {
    var cssPathOfBedrooms2Tag = '#floor-plans > div > div:nth-child(1) > div > ul > li:nth-child(2) > a';
    this.click(cssPathOfBedrooms2Tag);
});

casper.wait(2000, function() {
    this.capture("shouldShowBedrooms2.png");
});
casper.run();

import re
from scrapy.spiders import CrawlSpider, Rule
from scrapy.contrib.linkextractors.sgml import SgmlLinkExtractor

class KbbPriceSpider(CrawlSpider):
    name = "kbb price"
    allowed_domains = ['kbb.com']
    start_urls = ['http://www.kbb.com/bmw/3-series/2015/320i-sedan-4d/?vehicleid=402899&intent=buy-used&category=sedan&mileage=19387&condition=excellent&pricetype=private-party']
    #start_urls = ['http://www.kbb.com/bmw/3-series/2015/']
    #rules = (
         #Rule(LinkExtractor(allow=('bmw'))),
    #)

    rules = [Rule(SgmlLinkExtractor(allow=()), callback='parse_item', follow=True)]

    def parse_item(self,response):
        self.log('A response from %s just arrived!' % response.url)

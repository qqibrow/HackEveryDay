import re
from scrapy.spiders import CrawlSpider, Rule
from scrapy.linkextractors import LinkExtractor
#from scrapy.contrib.linkextractors.sgml import SgmlLinkExtractor

class KbbPriceSpider(CrawlSpider):
    name = "kbb price"
    allowed_domains = ['kbb.com']
    #start_urls = ['http://www.kbb.com/bmw/3-series/2015/320i-sedan-4d/?vehicleid=402899&intent=buy-used&category=sedan&mileage=19387&condition=excellent&pricetype=private-party']
    #start_urls = ['http://www.kbb.com/bmw/3-series/2015/']
    start_urls = ['http://www.kbb.com/bmw/3-series/2012/categories/?intent=buy-used']
    #rules = (
         #Rule(LinkExtractor(allow=('bmw'))),
    #)

    rules = [
             Rule(LinkExtractor(deny=('cars-for-sale')), follow=False),
             Rule(LinkExtractor(allow=('bodystyle')), callback='parse_item', follow=True),
             Rule(LinkExtractor(allow=('vehicleid')), callback='parse_item', follow=True),
             Rule(LinkExtractor(allow=('bmw')), callback='parse_item', follow=True),
             Rule(LinkExtractor(allow=('valuetype')), callback='parse_item', follow=True),
             Rule(LinkExtractor(allow=('pricetype')), callback='parse_item', follow=False),
             Rule(LinkExtractor(allow=('pricetype')), callback='parse_item', follow=False)]

    def parse_item(self, response):
        p = re.compile(ur'(?:\"values\": )({(?:\s|.)*?)(?:,\s+\"timAmount\")')
        matched = p.search(response.body)
        if matched:
          yield {
              'url': response.url,
              'prices': matched.group(1)
          }

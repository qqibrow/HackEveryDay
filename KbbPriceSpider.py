import scrapy
import re

class KbbPriceSpider(scrapy.Spider):
    name = "kbb price"
    start_urls = ['http://www.kbb.com/bmw/3-series/2015/320i-sedan-4d/?vehicleid=402899&intent=buy-used&category=sedan&mileage=19387&condition=excellent&pricetype=private-party']

    def parse(self, response):
        p = re.compile(ur'(?:\"values\": )({(?:\s|.)*?)(?:,\s+\"timAmount\")')
        matched = p.search(response.body)
        if matched:
          yield {
              'url': response.url,
              'prices': matched.group(1)
          }

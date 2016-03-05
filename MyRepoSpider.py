import scrapy

class MyRepoSpider(scrapy.Spider):
    name = "qqibrow's repo"
    start_urls = ['https://github.com/qqibrow?tab=repositories']

    def parse(self, response):
        for sel in response.css('.repo-list-name a::text'):
            repoTitle = sel.extract().lstrip()
            yield {
                'title': repoTitle
            }

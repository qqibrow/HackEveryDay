var page = require('webpage').create();
var url = 'http://www.avaloncommunities.com/california/sunnyvale-apartments/avalon-silicon-valley/floor-plans';

page.open url, (status) ->
      setTimeout(( ->
        console.log("scroll top is " + window.document.body.scrollTop);
        page.evaluate(->
          pos = 0
          scroll = ->
            pos += 250
            window.document.body.scrollTop = pos
            setTimeout(scroll, 100)

          scroll()
        )

        setTimeout((->
          page.render('bild.png')
          phantom.exit()
        ), 5000)
      ), 1000)

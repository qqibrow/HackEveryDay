$(document).ready(function(){
// track every click
document.body.addEventListener('click', function (event) {
    var my_selector_generator = new CssSelectorGenerator;
      // get reference to the element user clicked on
      var element = event.target;
        // get unique CSS selector for that element
        var selector = my_selector_generator.getSelector(element);
          // do whatever you need to do with that selector
          console.log('selector', selector);
});
});

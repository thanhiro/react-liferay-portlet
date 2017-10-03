function unactivateLinks(els) {
  els.forEach(function (els) {
    els.classList.remove('active');
  });
}

/**
 * Add SPA history pushState event handlers to navi links
 */
AUI().use('event', function (A) {
  A.on('domready', function () {
    var itms = document.querySelectorAll('.lfr-nav-item');

    itms.forEach(function (ni) {
      var link = ni.querySelector('a');

      link.addEventListener('click', function (e) {
        var re = /(.*)\/ui\/(\w*)/i;
        var m = String.prototype.match
          .call(link.getAttribute('href'), re);
        unactivateLinks(itms);
        ni.classList.add('active');
        window.appHistory.push('/' + (m[2] || ''));
        e.preventDefault();
      });
    });
  });
});

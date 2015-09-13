"use strict";

(function() {
  'use strict';

  angular
    .module('angularProject')
    .controller('MainController', ['$location', MainController]);

  /** @ngInject */
  function MainController($location) {
    var vm = this;

    vm.submit = function() {
      $location.path('/results').search({zip: vm.zipcode});
    }
  }
})();

(function() {
  'use strict';

  angular
    .module('angularProject')
    .config(routeConfig);

  function routeConfig($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'app/main/main.html',
        controller: 'MainController',
        controllerAs: 'main'
      })
      .when('/results', {
        templateUrl: 'app/results/results.html',
        controller: 'ResultsController',
        controllerAs: 'results'
      })
      .otherwise({
        redirectTo: '/'
      });
  }

})();

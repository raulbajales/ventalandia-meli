'use strict';


// Declare app level module which depends on filters, and services
angular.module('vldApp', ['vldApp.filters', 'vldApp.services', 'vldApp.directives']).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/news', {templateUrl: '/partials/news.html', controller: NewsController});
    $routeProvider.when('/customers', {templateUrl: '/partials/customers.html', controller: CustomersController});
    $routeProvider.when('/products', {templateUrl: '/partials/products.html', controller: ProductsController});
    $routeProvider.otherwise({redirectTo: '/news'});
  }]);

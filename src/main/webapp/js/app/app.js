'use strict';

angular.module('vldApp', ['ngCookies', 'vldApp.filters', 'vldApp.services', 'vldApp.directives']).
  config(function($routeProvider) {
    $routeProvider.when('/news', {templateUrl: '/partials/news.html', controller: NewsController});
    $routeProvider.when('/customers', {templateUrl: '/partials/customers.html', controller: CustomersController});
    $routeProvider.when('/products', {templateUrl: '/partials/products.html', controller: ProductsController});
    $routeProvider.otherwise({redirectTo: '/news'});
  });
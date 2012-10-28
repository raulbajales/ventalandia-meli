'use strict';

angular.module('vldApp', ['ngCookies', 'vldApp.filters', 'vldApp.services', 'vldApp.directives']).
  config(function($routeProvider) {
    $routeProvider.when('/news', {templateUrl: '/partials/news.html', controller: ventalandia.controller.NewsController});
    $routeProvider.when('/customers', {templateUrl: '/partials/customers.html', controller: ventalandia.controller.CustomersController});
    $routeProvider.when('/products', {templateUrl: '/partials/products.html', controller: ventalandia.controller.ProductsController});
    $routeProvider.otherwise({redirectTo: '/news'});
  }).factory('SharedModel', function () {
    var model = {};
    model.set = function(key, value){
        model[key] = value;
    };
    model.get = function(key){
        return model[key];
    };
    return model;
  });
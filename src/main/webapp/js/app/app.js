ventalandia.settings = {
  USE_MOCKS = true;
}

angular.module('vldApp', ['ngCookies', 'vldApp.filters', 'vldApp.services', 'vldApp.directives']).
  config(function($routeProvider) {
    $routeProvider.when('/news', {templateUrl: '/partials/news.html', controller: ventalandia.controller.NewsController});
    $routeProvider.when('/customers', {templateUrl: '/partials/customers.html', controller: ventalandia.controller.CustomersController});
    $routeProvider.when('/products', {templateUrl: '/partials/products.html', controller: ventalandia.controller.ProductsController});
    $routeProvider.otherwise({redirectTo: '/news'});
  }).run(function($rootScope) {
    $rootScope.$on('broadcast', function(event, message) {
        if (message && message.id && message.data) {
            $rootScope.$broadcast(message.id, message.data);    
        } else {
            console.log("[WARN] - Unable to broadcast message for: " + JSON.stringify(message));
        }
    });    
  });
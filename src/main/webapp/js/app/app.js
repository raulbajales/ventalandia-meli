/* -------------------------------------------------- */
//
//  Namespaces
//
/* -------------------------------------------------- */

var ventalandia = {};

ventalandia.ui = {};
ventalandia.ui.newsfeed = {};
ventalandia.model = {};
ventalandia.controller = {};
ventalandia.service = {};

ventalandia.test = {};
ventalandia.test.mocks = {};

/* -------------------------------------------------- */
//
//  App settings
//
/* -------------------------------------------------- */

ventalandia.settings = {
  NEWSFEED_PULL_INTERVAL_IN_MILLIS: 1000 * 60 * 3,
  USE_MOCKS: false
}

/* -------------------------------------------------- */
//
//  The application
//
/* -------------------------------------------------- */

var vldApp = angular.module('vldApp', ['vldApp.filters', 'vldApp.services', 'vldApp.directives', 'angular-underscore', 'ng', 'ngCookies']);

vldApp.config(function($routeProvider, $provide) {
  /*
    Handle URL routes
  */
  $routeProvider
      .when('/news', {
        templateUrl: '/partials/news.html', 
        controller: ventalandia.controller.NewsController
      })
      .when('/customers', {
        templateUrl: '/partials/customers.html', 
        controller: ventalandia.controller.CustomersController
      })
      .when('/products', {
        templateUrl: '/partials/products.html', 
        controller: ventalandia.controller.ProductsController
      })
      .otherwise({
        redirectTo: '/news'
      });
  /*
    Enable mocks
  */
  if (ventalandia.settings.USE_MOCKS) {
    $provide.decorator('$httpBackend', angular.mock.e2e.$httpBackendDecorator);
  }
});

vldApp.provider({
    /*
      General Exception handler
    */
    $exceptionHandler: function() {
        var handler = function(exception, cause) {
            console.log("exception: " + exception);
            console.log("cause: " + cause);
            ventalandia.ui.alert("Ha ocurrido un error!", 
              "Ha ocurrido un error, es necesario que inicie su sesión nuevamente. Si el error vuelve a ocurrir, por favor escríbanos a <a href='mailto:info@ventalandia.com'>info@ventalandia.com</a>, disculpe las molestias!", 
              "Ok", 
              function() {
                ventalandia.ui.logout();
              });
        };

        this.$get = function() {
            return handler;
        };
    }
});

vldApp.run(function($rootScope, $httpBackend) {
  /*
    Event dispatching for controllers, how to use it:
      Dispatch event:
        $scope.$emit('broadcast', {id: "{theMessageId}", data: {theDataObject}});
      Handle event:
        $scope.$on('{theMessageId}', function(event, theDataObject) {...});    
  */
  $rootScope.$on('broadcast', function(event, message) {
      if (message && message.id && message.data) {
          $rootScope.$broadcast(message.id, message.data);    
      } else {
          console.log("[WARN] - Unable to broadcast message for: " + JSON.stringify(message));
      }
  }); 
  /*
    Enable mocks
  */
  if (ventalandia.settings.USE_MOCKS) {
    ventalandia.test.mocks.configureBackend($httpBackend);
  }
});

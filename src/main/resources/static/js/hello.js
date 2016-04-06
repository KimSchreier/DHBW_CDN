angular.module('hello', [ 'ngRoute' ])
  .config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home'
	}).when('/login', {
		templateUrl : 'login.html',
		controller : 'navigation'
    }).when('/shop', {
        templateUrl : 'shop.html',
        controller : 'home'
    }).when('/cart', {
        templateUrl : 'cart.html',
        controller : 'home'
    }).when('/purches', {
        templateUrl : 'purches.html',
        controller : 'home'
	}).otherwise('/');

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

  })
  .controller('home', function($rootScope, $scope, $http) {
      $scope.getProducts = function () {
          $http.get('/products').success(function(data) {
              $scope.products = data;
          });
      };
      $scope.addItem = function (id) {
          $http.get('/addToCart?id='+id+'user={{user.id}}').success(function(data) {
              $rootScope.cart = data;
          });
      };
      $scope.removeItem = function (id) {
          $http.get('/removeFromCart?id='+id+'user={{user.id}}').success(function(data) {
              $rootScope.cart = data;
          });
      };
  })
.controller('navigation',

  function($rootScope, $scope, $http, $location) {

  $scope.logout = function() {
	    $rootScope.authenticated = false;
        $rootScope.user = {};
	}

  $scope.credentials = {};
  $scope.login = function() {
      $http({
          method: 'POST',
          url: '/userLogin',
          headers: {
              'Content-Type': 'application/json',
              'Accept': 'application/json'
          },
          data: $scope.credentials
      }).then(function successCallback(response) {
          $location.path("/");
          $scope.error = false;
          $rootScope.user = response.data;
          $rootScope.authenticated = true;
      }, function errorCallback(response) {
          $location.path("/login");
          $scope.error = true;
      });
  };
});
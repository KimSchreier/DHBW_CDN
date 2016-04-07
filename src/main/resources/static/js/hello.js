angular.module('hello', [ 'ngRoute' , 'ngCookies'])
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
    }).when('/purch', {
        templateUrl : 'purchase.html',
        controller : 'home'
    }).when('/register', {
        templateUrl : 'register.html',
        controller : 'navigation'
	}).otherwise('/');

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

  })
  .controller('home', function($rootScope, $scope, $http) {
      $scope.getProducts = function () {
          $http.get('/products').success(function(data) {
              $scope.products = data;
          });
      };

      $rootScope.totalPrice=0;

      $scope.getPurches = function () {
          $http.get('/purches?user='+$rootScope.user.id).success(function(data) {
              $rootScope.purches = data;
          });
      };
      $scope.getCart = function () {
          $http.get('/getCart?user='+$rootScope.user.id).success(function(data) {
              $rootScope.cart = data;
              data.forEach(function(entry) {
                  $rootScope.totalPrice += entry.price;
              });
          });
      };
      $scope.addItem = function (id) {
          $http.get('/addToCart?id='+id+'&user='+$rootScope.user.id).success(function(data) {
              $rootScope.cart = data;
          });
      };
      $scope.newPurche = function () {
          $rootScope.cart = [];
          $rootScope.totalPrice = 0;
          $http.get('/placePurches?user='+$rootScope.user.id).success(function(data) {
              $rootScope.purches = data;
          });
      };
      $scope.removeItem = function (id) {
          $http.get('/removeFromCart?id='+id+'&user='+$rootScope.user.id).success(function(data) {
              $rootScope.cart = data;
              $rootScope.totalPrice =0;
              data.forEach(function(entry) {
                  $rootScope.totalPrice += entry.price;
              });
          });
      };
  })
.controller('navigation',

  function($rootScope, $scope, $http, $location, $cookies) {

  $scope.logout = function() {
	    $rootScope.authenticated = false;
        $rootScope.user = {};
        $cookies.userId = "";
	};

  $scope.credentials = {};
      
      $scope.getUser = function () {
          var userId = $cookies.userId;
          $http.get('/user?id='+userId).success(function(data) {
              $rootScope.user = data;
              $rootScope.authenticated = true;
          });
      };

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
          $cookies.userId = response.data.id;
      }, function errorCallback(response) {
          $location.path("/login");
          $scope.error = true;
      });
  };

      $scope.register = function() {
          $http({
              method: 'POST',
              url: '/users',
              headers: {
                  'Content-Type': 'application/json',
                  'Accept': 'application/json'
              },
              data: $scope.reg
          }).then(function successCallback(response) {
              $location.path("/login");
              $scope.error = false;
          }, function errorCallback(response) {
              $location.path("/register");
              $scope.error = true;
          });
      };

});
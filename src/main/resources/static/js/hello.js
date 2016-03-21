angular.module('hello', [ 'ngRoute' ])
  .config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home'
	}).when('/login', {
		templateUrl : 'login.html',
		controller : 'navigation'
	}).otherwise('/');

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

  })
  .controller('home', function($scope, $http) {
    $http.get('/resource/').success(function(data) {
      $scope.greeting = data;
    })
  })
.controller('navigation',

  function($rootScope, $scope, $http, $location) {

  $scope.logout = function() {
	    $rootScope.authenticated = false;
        $cookies.remove('Authorization');
	}

  $scope.credentials = {};
  $scope.login = function() {
      credentials.grant_type='password';
      credentials.scope='read write';
      credentials.client_secret='123456';
      credentials.client_id='clientapp';
      $http({
          method: 'POST',
          url: '/user',
          headers: {
              'Content-Type': 'application/json',
              'Accept': 'application/json'
          },
          data: credentials
      }).then(function successCallback(response) {
          $location.path("/");
          $scope.error = false;
          $cookies.setAttribute('Authorization',response.data.token_type+' '+response.data.access_token);
          $rootScope.authenticated = true;
      }, function errorCallback(response) {
          $location.path("/login");
          $scope.error = true;
      });
  };
});
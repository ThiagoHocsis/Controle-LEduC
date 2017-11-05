angular.module('ControleLeduc')
// Creating the Angular Controller
.controller('HomeController', function($http, $scope, AuthService) {
	$scope.usuario = AuthService.usuario;
});

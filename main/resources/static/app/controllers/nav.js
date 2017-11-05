angular.module('ControleLeduc')
// Creating the Angular Controller
.controller('NavController', function($http, $scope, AuthService, $state, $rootScope) {
	$scope.$on('LoginSuccessful', function() {
		$scope.usuario = AuthService.usuario;
	});
	$scope.$on('LogoutSuccessful', function() {
		$scope.usuario = null;
	});
	$scope.logout = function() {
		AuthService.usuario = null;
		$rootScope.$broadcast('LogoutSuccessful');
		$state.go('login');
	};
});

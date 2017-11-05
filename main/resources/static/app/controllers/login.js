angular.module('ControleLeduc')

.controller('LoginController', function($http, $scope, $state, AuthService, $rootScope) {
	
	$scope.login = function() {
		
		$http({
			url : 'autenticar',
			method : "POST",
			params : {
				username : $scope.username,
				password: $scope.password
			}
		}).success(function(response) {
			$scope.password = null;
			
			if (response.token) {
				$scope.message = '';
				
				$http.defaults.headers.common['Authorization'] = 'Bearer ' + response.token;

				
				AuthService.usuario = response.usuario;
				$rootScope.$broadcast('LoginSuccessful');
				
				$state.go('home');
			} else {
				
				$scope.message = 'Falha na autenticação !';
			}
		}).error(function(error) {
			
			$scope.message = 'Falha na autenticação !';
		});
	};
});

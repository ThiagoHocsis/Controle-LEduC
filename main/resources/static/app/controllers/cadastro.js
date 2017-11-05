angular.module('ControleLeduc')
// Creating the Angular Controller
.controller('CadastroController', function($http, $scope, AuthService) {
	$scope.submit = function() {
		$http.post('cadastrar', $scope.usuario).success(function(res) {
			$scope.usuario = null;
			$scope.confirmarSenha = null;
			$scope.cadastro.$setPristine();
			$scope.message = "Cadastro efetuado com sucesso !";
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
});

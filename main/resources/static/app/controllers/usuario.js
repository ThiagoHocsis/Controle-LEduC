angular.module('ControleLeduc')
// Creating the Angular Controller
.controller('UsuarioController', function($http, $scope, AuthService) {
	var editar = false;
	$scope.textoBotao = 'Criar';
	var iniciar = function() {
		$http.get('api/usuarios').success(function(response) {
			$scope.usuarios = response;
			
			$scope.formularioUsuario.$setPristine();
			$scope.message='';
			$scope.usuario = null;
			$scope.textoBotao = 'Criar';
			
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
	$scope.iniciarEditar = function(usuario) {
		editar = true;
		$scope.usuario = usuario;
		$scope.message='';
		$scope.textoBotao = 'Atualizar';
	};
	$scope.iniciarAdicionarUsuario = function() {
		editar = false;
		$scope.usuario = null;
		$scope.formularioUsuario.$setPristine();
		$scope.message='';
		$scope.textoBotao = 'Criar';
	};
	$scope.deletarUsuario = function(usuario) {
		$http.delete('api/usuarios/'+usuario.id).success(function(response) {
			$scope.deleteMessage ="Usuario excluido com sucesso!";
			iniciar();
		}).error(function(error) {
			$scope.deleteMessage = error.message;
		});
	};
	var editarUsuario = function(){
		$http.put('api/usuarios', $scope.usuario).success(function(response) {
			$scope.usuario = null;
			$scope.confirmarSenha = null;
			$scope.formularioUsuario.$setPristine();
			$scope.message = "Edição realizada com sucesso!";
			iniciar();
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
	var AdicionarUsuario = function(){
		$http.post('api/usuarios', $scope.usuario).success(function(response) {
			$scope.usuario = null;
			$scope.confirmarSenha = null;
			$scope.formularioUsuario.$setPristine();
			$scope.message = "Usuario criado com sucesso!";
			iniciar();
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
	$scope.submit = function() {
		if(editar){
			editarUsuario();
		}else{
			AdicionarUsuario();	
		}
	};
	iniciar();

});

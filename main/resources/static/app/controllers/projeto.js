angular.module('ControleLeduc')
.controller('ProjetoController', function($http, $scope, AuthService) {
	var editar = false;
	$scope.textoBotao = 'Criar';
	var iniciar = function() {
		$http.get('api/projetos').success(function(response) {
			$scope.projetos = response;
			$scope.formularioProjeto.$setPristine();
			$scope.message='';
			$scope.projeto = null;
			$scope.textoBotao = 'Criar';
			
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
	$scope.iniciarEditar = function(projeto) {
		editar = true;
		$scope.projeto = projeto;
		$scope.message='';
		$scope.textoBotao = 'Atualizar';
	};
	$scope.iniciarAdicionarProjeto = function() {
		editar = false;
		$scope.projeto = null;
		$scope.formularioProjeto.$setPristine();
		$scope.message='';
		$scope.textoBotao = 'Criar';
	};
	$scope.deletarProjeto = function(projeto) {
		$http.delete('api/projetos/'+projeto.id).success(function(response) {
			$scope.deleteMessage ="Projeto excluido com sucesso!";
			iniciar();
		}).error(function(error) {
			$scope.deleteMessage = error.message;
		});
	};
	var editarProjeto = function(){
		$http.put('api/projetos', $scope.projeto).success(function(response) {
			$scope.projeto = null;
			$scope.formularioProjeto.$setPristine();
			$scope.message = "Edição realizada com sucesso!";
			iniciar();
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
	var AdicionarProjeto = function(){
		$http.post('api/projetos', $scope.projeto).success(function(response) {
			$scope.projeto = null;
			$scope.formularioProjeto.$setPristine();
			$scope.message = "Projeto criado com sucesso!";
			iniciar();
		}).error(function(error) {
			$scope.message = "Erro ao inserir projeto";
		});
	};
	$scope.submit = function() {
		if(editar){
			editarProjeto();
		}else{
			AdicionarProjeto();	
		}
	};
	iniciar();

});

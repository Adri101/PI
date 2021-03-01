angular.module('wikilocApp')
.controller('registerCtrl', ['usersFactory','$routeParams','$location',
	function(usersFactory,$routeParams,$location){
var registerViewModel = this;
registerViewModel.user={};
registerViewModel.functions = {
where : function(route){
		return $location.path() == route;
	},
	createUser : function () {
		usersFactory.postUser(registerViewModel.user)
			.then(function(response){
			console.log("User create");
			window.location.replace('https://localhost:8443/WikiLocDAO');
		}, function(response){
			console.log("Error create user");
		})
	}
}
}]);
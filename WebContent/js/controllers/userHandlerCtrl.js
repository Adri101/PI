angular.module('wikilocApp')
.controller('userHandlerCtrl', ['usersFactory','$routeParams','$location',
			function(usersFactory,$routeParams,$location){
	var userHandlerViewModel = this;
    userHandlerViewModel.user={};
    userHandlerViewModel.functions = {
		where : function(route){
   			return $location.path() == route;
   		},
       	readUser : function() {
    		usersFactory.getUser()
    				.then(function(response){
    					console.log("Reading user with id: ", id," Response: ", response);
    					userHandlerViewModel.user = response;  
    				}, function(response){
    					console.log("Error reading user");
    				})
    		},
		readUserLoaded : function() {
    		usersFactory.getUser()
    				.then(function(response){
    					userHandlerViewModel.user.email = response.email;
    					userHandlerViewModel.user.username = response.username;
    					userHandlerViewModel.user.password = response.password;
    					userHandlerViewModel.user = response;
    					console.log("Reading user with id: ", id," Response: ", response);
    					
    				}, function(response){
    					console.log("Error reading user");
    				})
    		},
		updateUser : function() {
			usersFactory.putUser(userHandlerViewModel.user)
				.then(function(response){
					console.log("Updating user with id:",userHandlerViewModel.user.id," Response:", response);
					window.location.replace('https://localhost:8443/WikiLocDAO');
    			}, function(response){
    				console.log("Error updating user");
    			})
		},	
		deleteUser : function() {
			usersFactory.deleteUser()
				.then(function(response){
					console.log("Deleting user with id:",id," Response:", response);
					window.location.replace('https://localhost:8443/WikiLocDAO');
				}, function(response){
					console.log("Error deleting user");
				})
		},
		userHandlerSwitcher : function(){
			if (userHandlerViewModel.functions.where('/editCuenta')){
				console.log($location.path());
				userHandlerViewModel.functions.updateUser();
			}
			else if (userHandlerViewModel.functions.where('/deleteCuenta')){
				console.log($location.path());
				userHandlerViewModel.functions.deleteUser();
			}else {
			console.log($location.path());
			}
			$location.path('/');
		}
    }
	if ($routeParams.ID==undefined) userHandlerViewModel.functions.readUserLoaded();
	else userHandlerViewModel.functions.readUser($routeParams.ID);
}])
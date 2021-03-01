angular.module('wikilocApp')
.controller('routeHandlerCtrl', ['routesFactory','usersFactory','$routeParams','$location',
			function(routesFactory,usersFactory,$routeParams,$location){
	var routeHandlerViewModel = this;
	routeHandlerViewModel.route={};
	routeHandlerViewModel.functions = {
   		where : function(route){
   			return $location.path() == route;
   		},
   		readRoutesUser : function(){
   			usersFactory.getUser()
			.then(function(response){
				routeHandlerViewModel.route.name= response.name;
				routeHandlerViewModel.route.email= response.email;
				console.log("Reading user with id: ",response.id," Response: ", response);
			}, function(response){
				console.log("Error reading user data");
			})
   		},
   		readRoute : function(id) {
			routesFactory.getRoute(id)
				.then(function(response){
					console.log("Reading route with id: ", id," Response: ", response);
					routeHandlerViewModel.route = response;
				}, function(response){
					console.log("Error reading route");
					$location.path('/');
				})
		},
   		createRoute : function() {
	        routesFactory.postRoute(routeHandlerViewModel.route)
				.then(function(response){
					console.log("Creating route. Response:", response);
    			}, function(response){
    				console.log("Error creating the route");
    			})
		},
		updateRoute : function() {
    		routesFactory.putRoute(routeHandlerViewModel.route)
				.then(function(response){
	    			console.log("Updating routes...: ", response);
	    			 routeHandlerViewModel.routes = response;
	    		}, function(response){
	    			console.log("Error updating routes");
	    		})
		},
		deleteRoute : function(id) {
    		routesFactory.deleteRoute(id)
				.then(function(response){
					console.log("Deleting route with id:",id," Response:", response);
	    		}, function(response){
	    			console.log("Error deleting routes");
	    		})
		},
   		routeHandlerSwitcher : function(){
			if (routeHandlerViewModel.functions.where('/uploadRoute')){
				console.log($location.path());
				routeHandlerViewModel.functions.createRoute();
			} 
			else if (routeHandlerViewModel.functions.where('/editRoute/'+ routeHandlerViewModel.route.id)){
				console.log($location.path());
			routeHandlerViewModel.functions.updateRoute();
			
			}else if (routeHandlerViewModel.functions.where('/deleteRoute/'+ routeHandlerViewModel.route.id)){
				console.log($location.path());
				routeHandlerViewModel.functions.deleteRoute(routeHandlerViewModel.route.id);
				
				}
			else
					console.log($location.path());
					$location.path('/');
   		}
	}
	if ($routeParams.ID==undefined) routeHandlerViewModel.functions.readRoutesUser();
   	else routeHandlerViewModel.functions.readRoute($routeParams.ID);
}]);
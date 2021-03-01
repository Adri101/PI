angular.module('wikilocApp')
.controller('listCtrl', ['routesFactory','$routeParams','$location',function(routesFactory,$routeParams,$location){
	var listViewModel = this;
	listViewModel.routes=[];
	listViewModel.myRoutes=[];
	listViewModel.searched=[];
	listViewModel.functions = {
		where : function(route){
   			return $location.path() == route;
   		},
   		readRoutes : function() {
    		routesFactory.getRoutes() 
				.then(function(response){
					listViewModel.routes = response;
					console.log("Reading all the routes: ", response);
	    		}, function(response){
	    			console.log("Error reading routes");
	    			$location.path('/');
	    		})
		},
		myRoutes : function() {
			routesFactory.ownRoutes()
			.then(function(response){
				console.log("Reading all the routes: ", response);
				listViewModel.myRoutes = response;
			}, function(response){
				console.log("Error reading routes");
			})
		},
		searchRoutes : function() {
			routesFactory.search(listViewModel.searched)
			.then(function(response){
				console.log("Reading all the routes: ", response);
				listViewModel.routes = response;
			}, function(response){
				console.log("Error reading routes");
			})
		},
		sortRoutes : function() {
			routesFactory.sortRoutes()
			.then(function(response) {
						console.log("Reading routes: ",response);
						listViewModel.routes = response;
					}, function(response) {
						console.log("Error reading ");
					})
		},
		filterRoutes : function() {
			routesFactory.filterRoutes()
			.then(function(response) {
						console.log("Reading routes: ",response);
						listViewModel.routes = response;
					}, function(response) {
						console.log("Error reading ");
					})
		},
		availableRoutes : function() {
			routesFactory.availableRoutes()
			.then(function(response) {
						console.log("Filtering routes: ",response);
						listViewModel.routes = response;
					}, function(response) {
						console.log("Error reading ");
					})
		},
		addKudos : function(id) {
			routesFactory.addKudos(id)
			.then(function(response){
				console.log("Adding kudos to the route  with id:",id," Response:", response);
				listViewModel.functions.readRoutes();
			}, function(response){
				console.log("Error addding kudos");
			})
		},
		lessKudos : function(id) {
			routesFactory.lessKudos(id)
			.then(function(response){
				console.log("Substracting kudos to the route  with id:",id," Response:", response);
				listViewModel.functions.readRoutes();
			}, function(response){
				console.log("Error substracting kudos");
			})
		},
		stateRoute : function(id) {
			routesFactory.changeState(id)
			.then(function(response){
				console.log("Changing the state of the route with id:",id," Response:", response);
				listViewModel.functions.myRoutes();
			}, function(response){
				console.log("Error substracting kudos");
			})
		},

		
		routeHandlerSwitcher : function(){
			if (listViewModel.functions.where('/')){
				console.log($location.path());
				listViewModel.functions.readRoutes();
			} else if (listViewModel.functions.where('/myRoutes')){
				console.log($location.path());
				listViewModel.functions.myRoutes();
			} else if (listViewModel.functions.where('/sortRoutes')){
				console.log($location.path());
				listViewModel.functions.sortRoutes();
			}else if (listViewModel.functions.where('/filterRoutes')){
				console.log($location.path());
				listViewModel.functions.filterRoutes();
			} else if (listViewModel.functions.where('/availableRoutes')){
				console.log($location.path());
				listViewModel.functions.availableRoutes();
			}
		}
	}
	listViewModel.functions.routeHandlerSwitcher();
	
}]);
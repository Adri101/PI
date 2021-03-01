angular.module('wikilocApp', ['ngRoute'])
.config(function($routeProvider){
	$routeProvider
	.when("/", {
		controller: "listCtrl",
		controllerAs: "listVM",
		templateUrl: "listRoutesTemplate.html",
		resolve: {
			// produce 500 miliseconds (0,5 seconds) of delay that should be enough to allow the server
			//does any requested update before reading the orders.
			// Extracted from script.js used as example on https://docs.angularjs.org/api/ngRoute/service/$route
			delay: function($q, $timeout) {
			var delay = $q.defer();
			$timeout(delay.resolve, 500);
			return delay.promise;
			}
		}
	})
	.when("/editCuenta", {
    	controller: "userHandlerCtrl",
		controllerAs: "userHandlerVM",
		templateUrl: "userHandlerTemplate.html"
    })
    .when("/deleteCuenta", {
    	controller: "userHandlerCtrl",
		controllerAs: "userHandlerVM",
		templateUrl: "userHandlerTemplate.html"
    })
    .when("/uploadRoute", {
    	controller: "routeHandlerCtrl",
		controllerAs: "routeHandlerVM",
		templateUrl: "routeHandlerTemplate.html"
    })
	.when("/editRoute/:ID", {
		controller: "routeHandlerCtrl",
		controllerAs: "routeHandlerVM",
		templateUrl: "routeHandlerTemplate.html"
    })
      .when("/deleteRoute/:ID", {
		controller: "routeHandlerCtrl",
		controllerAs: "routeHandlerVM",
		templateUrl: "routeHandlerTemplate.html"
    })
    .when("/myRoutes", {
    	controller: "listCtrl",
		controllerAs: "listVM",
		templateUrl: "myRoutes.html"
    })
    .when("/sortRoutes", {
    	controller: "listCtrl",
		controllerAs: "listVM",
		templateUrl: "listRoutesTemplate.html"
    })
    .when("/filterRoutes", {
    	controller: "listCtrl",
		controllerAs: "listVM",
		templateUrl: "listRoutesTemplate.html"
    })
    .when("/availableRoutes", {
    	controller: "listCtrl",
		controllerAs: "listVM",
		templateUrl: "listRoutesTemplate.html"
    })
    .when("/searchRoutes", {
    	controller: "listCtrl",
		controllerAs: "listVM",
		templateUrl: "searchedRoutes.html"
    })
})
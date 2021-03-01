angular.module('wikilocApp')
.factory("routesFactory", ['$http',function($http){
	var url = 'https://localhost:8443/WikiLocDAO/rest/routes/';
    var routesInterface = {
    	getRoutes: function(){
    		return $http.get(url)
    			.then(function(response){
    				return response.data;
    			});
    	},
    	getRoute: function(id){
    		var urlid = url + id;
            return $http.get(urlid)
            	.then(function(response){
            		return response.data;
         		});
    	},
    	   postRoute:  function(route){
    			return $http.post(url,route)
    	        	.then(function(response){
    	        		return response.status;
    	 			});
    	},
    	ownRoutes: function(route){
    		var urlid = url + 'myRoutes';
            return $http.get(urlid)
            	.then(function(response){
            		return response.data;
            		});
    	}, 
    	putRoute : function(route){
    		var urlid = url+route.id;
            return $http.put(urlid,route)
            	.then(function(response){
      				 return response.status;
  				});                   
    	},
    	deleteRoute : function(id){
        	var urlid = url+id;
            return $http.delete(urlid)
            	.then(function(response){
            		return response.status;
            	});
    	},
    	addKudos : function(id){
        	var urlid = url + 'addKudos/' + id;
            return $http.put(urlid)
            	.then(function(response){
            		return response.status;
            	});
    	},
    	lessKudos : function(id){
        	var urlid = url + 'lessKudos/' + id;
            return $http.put(urlid)
            	.then(function(response){
            		return response.status;
            	});
    	},
    	changeState : function(id){
        	var urlid = url + 'state/' + id;
            return $http.put(urlid)
            	.then(function(response){
            		return response.status;
            	});
    	},
    	sortRoutes : function(){
        	var urlid = url + 'sortRoutes';
            return $http.get(urlid)
            	.then(function(response){
            		return response.data;
            	});
    	},
    	filterRoutes : function(){
        	var urlid = url + 'filterRoutes';
            return $http.get(urlid)
            	.then(function(response){
            		return response.data;
            	});
    	},
    	search: function(nombre){
    		var urlid = url + 'searchRoutes/' + nombre;
    		console.log("URL:",urlid);
            return $http.get(urlid)
            	.then(function(response){
            		return response.data;
         		});
    	},
    	availableRoutes : function(){
        	var urlid = url + 'availableRoutes';
            return $http.get(urlid)
            	.then(function(response){
            		return response.data;
            	});
    	}
    	
  }
 
    return routesInterface;
}])
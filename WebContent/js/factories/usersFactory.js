angular.module('wikilocApp')
.factory('usersFactory',['$http', function($http){
	var url = 'https://localhost:8443/WikiLocDAO/rest/users/';
    var usersInterface = {
        postUser : function(user){
    		url = url ;
            return $http.post(url, user)
          		.then(function(response){
          			return response.data;
          		});
    	},		
    	getUser : function() {
			url = url;
			return $http.get(url).then(function(response) {
				return response.data;
			});
		},
		putUser : function(user) {
			return $http.put(url, user).then(function(response) {
				return response.status;
			});
		},
		deleteUser : function(){
            return $http.delete(url)
            	.then(function(response){
            		return response.status;
            	});
        }		
    }
    return usersInterface;
}])
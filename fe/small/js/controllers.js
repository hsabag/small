var controllers = angular.module('F1FeederApp.controllers', []);

  controllers.controller('registerCompanyController', function($scope, registerCompanyAPI) {
	$scope.alert = function(){
		
		var result = registerCompanyAPI.register($scope.company);
		alert("registered");
	}
  });
  
  
  controllers.controller('fetchCompanyController', function($scope, fetchCompanyAPI) {
	$scope.search = function(){
		$scope.company = fetchCompanyAPI.get({name:$scope.name});
		alert(angular.toJson($scope.company));
	}
	
  });
  
  controllers.controller('listCompanyController', function($scope, listCompanyAPI) {
	$scope.search = function(){
		$scope.companies = listCompanyAPI.get();
	}
	
  });
  

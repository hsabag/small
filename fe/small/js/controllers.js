var controllers = angular.module('F1FeederApp.controllers', []);

  controllers.controller('registerCompanyController', function($scope, $window, companyAPI) {
	$scope.alert = function(){
		
		var success = function (response) {
		  $window.location.href= "#listComp";
		};
		var error = function (response) {
		  $scope.error = response;
		};
		var result = companyAPI.register({},$scope.company, success, error);
		
	}
  });
  
  
  controllers.controller('fetchCompanyController', function($scope, companyAPI) {
	$scope.search = function(){
		$scope.company = companyAPI.get({name:$scope.name});
		alert(angular.toJson($scope.company));
	}
	
  });
  
  controllers.controller('listCompanyController', function($scope, companyAPI) {
	  $scope.searchFilter = function (company) {
        var re = new RegExp($scope.nameFilter, 'i');
        return !$scope.nameFilter || re.test(company.name) || re.test(company.manager.firstName);
		};
		$scope.delete = function (name) {
			alert("delete:" + angular.toJson(name));
		};
	  companyAPI.list({},function (response) {
		  $scope.companies = response;
    });
	
  });
  

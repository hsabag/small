var smallBisServices = angular.module('F1FeederApp.services', ['ngResource']);


smallBisServices.factory('companyAPI', ['$resource',
  function($resource, $scope){
    return $resource('http://localhost:8080/company', {}, {
	  register: {method:'POST'},
	  fetch: {method:'GET',url:'http://localhost:8080/company/:name'},
	  list: {method:'GET', url:'http://localhost:8080/companies', isArray:true},
	  delete: {method:'DELETE',url:'http://localhost:8080/company/:name'}
    });
  }]);
  
  smallBisServices.factory('fetchCompanyAPI', ['$resource',
  function($resource, $scope){
    return $resource('http://localhost:8080/company/:name', {}, {
	  fetch: {method:'GET'}
    });
  }]);
  
  smallBisServices.factory('listCompanyAPI', ['$resource',
  function($resource, $scope){
    return $resource('http://localhost:8080/companies', {}, {
	  fetch: {method:'GET'}
    });
  }]);
  
  
 
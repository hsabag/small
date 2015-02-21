var smallBisServices = angular.module('F1FeederApp.services', ['ngResource']);


smallBisServices.factory('registerCompanyAPI', ['$resource',
  function($resource, $scope){
    return $resource('http://localhost:8080/company', {}, {
	  register: {method:'POST'}
    });
  }]);
  
  smallBisServices.factory('fetchCompanyAPI', ['$resource',
  function($resource, $scope){
    return $resource('http://localhost:8080/company/:name', {}, {
	  fetch: {method:'GET'}
    });
  }]);
  
  smallBisServices.factory('listCompanyController', ['$resource',
  function($resource, $scope){
    return $resource('http://localhost:8080/company/:name', {}, {
	  fetch: {method:'GET'}
    });
  }]);
  
  
 
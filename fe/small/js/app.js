var app = angular.module('F1FeederApp', [
  'F1FeederApp.services',
  'F1FeederApp.controllers',
  'ngRoute',
  'ngResource'
]);
app.config(['$routeProvider', function($routeProvider) {
  $routeProvider.
	when("/regComp", {templateUrl: "pages/registerCompany.html", controller: "registerCompanyController"}).
	when("/fetchComp", {templateUrl: "pages/fetchCompany.html", controller: "fetchCompanyController"}).
	when("/listComp", {templateUrl: "pages/listCompany.html", controller: "listCompanyController"}).
	otherwise({redirectTo: '/listComp'});
}]);
app.config(function($httpProvider){
	$httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
});
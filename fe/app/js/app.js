angular.module('F1FeederApp', [
  'F1FeederApp.services',
  'F1FeederApp.controllers',
  'ngRoute'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.
	when("/drivers", {templateUrl: "custom/drivers.html", controller: "driversController"}).
	when("/driver/:id", {templateUrl: "custom/driver.html", controller: "driverController"}).
	otherwise({redirectTo: '/drivers'});
}]);
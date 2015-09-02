/**
 * bootstraps angular onto the window.document node
 */
define([
        'angular'
        ], function (angular) {

	angular.module('myApp', [])
	.controller('MyController', ['$scope', function ($scope) {
		$scope.greetMe = 'World';
	}]);

	angular.element(document).ready(function() {
		angular.bootstrap(document, ['myApp']);
	});

});
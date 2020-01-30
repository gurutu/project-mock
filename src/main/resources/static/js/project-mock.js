var app=angular.module('projectmock', ['ngMaterial', 'ngMessages','ngRoute','ui.router'])
.controller('projectMockCtrl', function($scope,$rootScope) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
    $rootScope.url="http://localhost:8080";
});

app.config(function($stateProvider,$locationProvider,$urlRouterProvider) {
    var helloState = {
        name: 'hello',
        url: '/',
        templateUrl: 'api-dashboard.html',
        controller: 'apiDashboardController'
    }

    var apiDetailState = {
        name: 'api-detail',
        url: '/api-detail',
        templateUrl: 'api-details.html',
        controller:'apiDetailsController',
        params: {
        	data: null
        }
    }
    
    var requestResponseState= {
            name: 'requestResponseState',
            url: '/requestResponseState',
            templateUrl: 'request-response-template.html',
           
        }

    var changeApiState = {
        name: 'change-api-detail',
        url: '/change-api-detail',
        templateUrl: 'change-api-details.html',
        controller:'changeApiDetailsController',
        	params: {
            	data: null
            }
    }

    $urlRouterProvider.otherwise('/');
    $stateProvider.state(helloState);
    $stateProvider.state(apiDetailState);
    $stateProvider.state(changeApiState);
    $stateProvider.state(requestResponseState);
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });

});
app.controller('apidashboardCtrl', function($scope) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
});


app.directive("requestResponseTemplate", function() {
    return {
    	templateUrl: 'request-response-template.html'//'request-response-template.html'
    };
});

 app.directive("testareaTemplate", function() {
     return {
         //controller: 'apidashboardCtrl',
    	 scope: {
    	      customerInfo: '=data'
    	    },
         bindToController: false,
         template: '<md-input-container class="md-block" flex-gt-sm>'+
             '<label>Request</label>'+
             '<textarea  class="md-whiteframe-3dp" style="background-color: white;" value={{JSON.stringify(JSON.parse(customerInfo), null, 2)}} rows="10" cols="150" md-no-autogrow md-select-on-focus'+ 
             '></textarea>'+
         '</md-input-container>'
     };
 });
//
// app.directive("apiDetailsTemplate", function() {
//     return {
//         templateUrl: 'api-details.html'
//     };
// });
//
// app.directive("changeApiDetailsTemplate", function() {
//     return {
//         templateUrl: 'change-api-details.html'
//     };
// });



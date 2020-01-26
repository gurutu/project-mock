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
        controller:'apiDetailsController'
    }

    var changeApiState = {
        name: 'change-api-detail',
        url: '/change-api-detail',
        templateUrl: 'change-api-details.html',
        controller:'changeApiDetailsController'
    }

    $urlRouterProvider.otherwise('/');
    $stateProvider.state(helloState);
    $stateProvider.state(apiDetailState);
    $stateProvider.state(changeApiState);
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
        templateUrl: 'login.html'
    };
});

// app.directive("apiDashboardTemplate", function() {
//     return {
//         //controller: 'apidashboardCtrl',
//         controller: function () {},
//         controllerAs: '$ctrl',
//         bindToController: false,
//         templateUrl: 'api-dashboard.html'
//     };
// });
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



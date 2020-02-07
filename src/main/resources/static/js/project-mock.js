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


app.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('default')
    .primaryPalette('blue')
    .accentPalette('orange')
    //.dark();
    .backgroundPalette('blue-grey');
//	$mdThemingProvider.theme('default')
//    .dark();
	// $mdThemingProvider.disableTheming();
//	 $mdThemingProvider.theme('altTheme')
//	    .primaryPalette('purple') 
	//$mdThemingProvider.generateThemesOnDemand(true);

	  //themes are still defined in config, but the css is not generated
//	  $mdThemingProvider.theme('altTheme')
//	    .primaryPalette('blue')
//	    .accentPalette('green');
	 // Enable browser color
//    $mdThemingProvider.enableBrowserColor({
//      theme: 'default', // Default is 'default'
//      palette: 'accent', // Default is 'primary', any basic material palette and extended palettes are available
//      hue: '200' // Default is '800'
//    });
//	  $mdThemingProvider.definePalette('amazingPaletteName', {
//		    '50': 'ffebee',
//		    '100': 'ffcdd2',
//		    '200': 'ef9a9a',
//		    '300': 'e57373',
//		    '400': 'ef5350',
//		    '500': 'f44336',
//		    '600': 'e53935',
//		    '700': 'd32f2f',
//		    '800': 'c62828',
//		    '900': 'b71c1c',
//		    'A100': 'ff8a80',
//		    'A200': 'ff5252',
//		    'A400': 'ff1744',
//		    'A700': 'd50000',
//		    'contrastDefaultColor': 'light',    // whether, by default, text (contrast)
//		                                        // on this palette should be dark or light
//
//		    'contrastDarkColors': ['50', '100', //hues which contrast should be 'dark' by default
//		     '200', '300', '400', 'A100'],
//		    'contrastLightColors': undefined    // could also specify this if default was 'dark'
//		  });
//
//		  $mdThemingProvider.theme('default')
//		    .primaryPalette('amazingPaletteName')
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



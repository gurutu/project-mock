var app=angular.module('projectmock');
app.controller('apiDashboardController',function($scope,$timeout, $q,$state) {
    $scope.message = 'Hello Welcome To Home Page';
     $scope.selectedItem="";

     $scope.goToApiDetails=function(param){
         $state.go('api-detail');
     }


    // ******************************
    // Internal methods
    // ******************************

    /**
     * Search for states... use $timeout to simulate
     * remote dataservice call.
     */
    $scope.querySearch =function(query) {
        var results = query ? $scope.loadAll().filter($scope.createFilterFor(query)) : $scope.loadAll();
        var deferred = $q.defer();
        $timeout(function () { deferred.resolve(results); }, Math.random() * 1000, false);
        return deferred.promise;
    }

    /**
     * Build `states` list of key/value pairs
     */
    $scope.loadAll=function () {
        var allStates = 'Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware,\
              Florida, Georgia, Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana,\
              Maine, Maryland, Massachusetts, Michigan, Minnesota, Mississippi, Missouri, Montana,\
              Nebraska, Nevada, New Hampshire, New Jersey, New Mexico, New York, North Carolina,\
              North Dakota, Ohio, Oklahoma, Oregon, Pennsylvania, Rhode Island, South Carolina,\
              South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia,\
              Wisconsin, Wyoming';

        return allStates.split(/, +/g).map(function (state) {
            return {
                value: state.toLowerCase(),
                display: state
            };
        });
    }

    /**
     * Create filter function for a query string
     */
    $scope.createFilterFor=function(query){
        var lowercaseQuery = query.toLowerCase();

        return function filterFn(state) {
            return (state.value.indexOf(lowercaseQuery) === 0);
        };

    }

});

app.controller('apiDetailsController',function($scope,$state) {
    $scope.message = 'Hello Welcome To Home Page';

    $scope.goToChangeApiDetails=function () {
        $state.go('change-api-detail');
    }

});

app.controller('changeApiDetailsController',function($scope) {
    $scope.message = 'Hello Welcome To Home Page';



});
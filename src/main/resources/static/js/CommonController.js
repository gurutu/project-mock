var app=angular.module('projectmock');
app.controller('apiDashboardController',function($scope,$timeout, $q,$state,CommonService,StoreService) {
    $scope.message = 'Hello Welcome To Home Page';
     $scope.selectedItem="";
     $scope.searchRelativeData=StoreService.getSearchData()?JSON.parse(StoreService.getSearchData()).reverse():[];

     $scope.goToApiDetails=function(){
    	var object={"object":this.item}
    	StoreService.pushSearchData(this.item);
         $state.go('api-detail',{data:object});
     }
     $scope.clickedRelative=function(method,path){
    	 $state.go('api-detail',{data:{"value":method,"display":path}});
     }
     
     $scope.selectedAutoSearch=function(method,uriPath){
    	 //CommonService.searchbyUriandmethod
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
     	var data={"searchQuery":query};
     	var searData=[];
     	var config={"Content-Type":"application/json"};
         CommonService.getData(data,config).then(function (response) {
         	response.data.forEach(function(item, index, array) {
         		  searData.push({value: item.method,display: item.urlPath
                })
         	})
           return searData;
         });
         
         var deferred = $q.defer();
         $timeout(function () { deferred.resolve(searData); }, Math.random() * 1000, false);
         return deferred.promise;
//    	var resData={
//            value: "",
//            display: ""
//        }

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

app.controller('apiDetailsController',function($scope,$state,CommonService) {
    $scope.message = 'Hello Welcome To Home Page';
    $scope.publishbutton=false;
    $scope.method="";
    $scope.urlPath="";
    $scope.requestData;
    $scope.publishData=function(method,urlPath,request,response){
    	var data={
    			  "urlPath":urlPath,
    			  "method":method,
    			  "request":request,
    			  "response":response
    	}
    	var config={"Content-Type":"application/json"};
    	CommonService.saveDate(data,config).then(function(response){
    		console.log(response.data);
    	});
    }
    $scope.formatJsonResponse=function(res){
    	try{
    		$scope.responseModal=JSON.stringify(JSON.parse(res), null, 2);
    	}catch(err){}	
    }
    
    $scope.convertInJson=function(data){
    	return JSON.stringify(JSON.parse(data), null, 2);
    }
    $scope.formatJson=function(request){
    	try{
    		$scope.requestModal=JSON.stringify(JSON.parse(request), null, 2);
    	}catch(err){}
    	
    }

    $scope.showListBottomSheet=function(param){
    	  $scope.publishbutton=param;
    }
    
    $scope.goToChangeApiDetails=function () {
        $state.go('change-api-detail');
    }

    $scope.init=function(){
    	
    	 $scope.method=$state.params.data.value;
        $scope.urlPath=$state.params.data.display;
    	CommonService.searchbyUriandmethod($state.params.data).then(function (response) {
    		$scope.requestData=response.data;
    	});
    }
    $scope.init();
});

app.controller('changeApiDetailsController',function($scope) {
    $scope.message = 'Hello Welcome To Home Page';



});
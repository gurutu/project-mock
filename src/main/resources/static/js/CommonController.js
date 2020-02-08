var app=angular.module('projectmock');
app.controller('apiDashboardController',function($scope,$timeout, $q,$state,CommonService,StoreService,ToastService,UtilService,$rootScope) {
    $scope.message = 'Hello Welcome To Home Page';
    //ToastService.simpleToast("API Request & Response has been  Deleted");
    $rootScope.hostName=window.location.origin;
    $rootScope.requestPlaceholder=JSON.stringify({
    	   "message": "Put some JSON in the text box.",
    	   "error": null,
    	   "year": 2020,
    	   "ios": false
    	}, null, 2);
    $rootScope.responsePlaceholder=JSON.stringify({
 	   "message": "Put some JSON in the text box.",
 	   "error": null,
 	   "year": 2020,
 	   "ios": false
 	}, null, 2);
     $scope.selectedItem="";
     $scope.searchRelativeData=StoreService.getSearchData()?JSON.parse(StoreService.getSearchData()).reverse():[];

     $scope.checkMethod=(method)=>{
    	return  UtilService.checkMethod(method);
     }
     $scope.goToApiDetails=function(){
    	var object={"object":this.item}
    	StoreService.pushSearchData(this.item);
         $state.go('api-detail',{data:{"value":this.item.value,"display":this.item.display}});
     }
     $scope.clickedRelative=function(method,path){
    	 $state.go('api-detail',{data:{"value":method,"display":path}});
     }
     
     $scope.selectedAutoSearch=function(method,uriPath){
    	 //CommonService.searchbyUriandmethod
     }
     
     $scope.searchByUrlAndMethod=function(method,url){
    	 var data={"display":url,"value":method};
    	 var config={"Content-Type":"application/json"};
    	 CommonService.searchbyUriandmethod(data,config).then(function(response){
    		 if(response.data.length>0){
     			ToastService.simpleToast("Api EndPoint Already Exits.");
     		}else{
     			ToastService.simpleToast("Api EndPoint is not Exits. Please create EndPoint.");
     		}
    	 });
    	 
     }
     
     $scope.publishData=function(method,urlPath,request,response){
    	 if(request!=undefined||response!=undefined){
     	var data={
     			  "urlPath":urlPath,
     			  "method":method,
     			  "request":request,
     			  "response":response
     	}
     	var config={"Content-Type":"application/json"};
     	CommonService.saveDate(data,config).then(function(response){
     		console.log(response.data);
     		ToastService.simpleToast("Your API has been created Successfull.");
     	});
    	 }
     }

     $scope.createNewApi=function(param){
    	 $scope.createNew=param;
     }
     
     $scope.formatJsonResponse=function(res){
     	try{
     		$scope.responseModal=JSON.stringify(JSON.parse(res), null, 2);
     	}catch(err){console.log(err);}	
     }
     $scope.formatJsonRequest=function(request){
     	try{
     		$scope.requestModal=JSON.stringify(JSON.parse(request), null, 2);
     	}catch(err){console.log(err);}
     	
     }

    // ******************************
    // Internal methods
    // ******************************

    /**
     * Search for states... use $timeout to simulate
     * remote dataservice call.
     */
    $scope.querySearch =function(query) {
        // var results = query ? $scope.loadAll().filter($scope.createFilterFor(query)) : $scope.loadAll();
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

app.controller('apiDetailsController',function($scope,$state,CommonService,$rootScope,StoreService,ToastService,UtilService) {
    $scope.message = 'Hello Welcome To Home Page';
    if($state.params.data==null){
    	$state.go('hello');
    }
    $scope.checkMethod=(method)=>{
    	return  UtilService.checkMethod(method);
     }
    
    $scope.publishbutton=false;
    $scope.method="";
    $scope.urlPath="";
    $scope.requestData;
    $scope.publishData=function(method,urlPath,request,response){
    	if(request!=undefined||response!=undefined){
    	var data={
    			  "urlPath":urlPath,
    			  "method":method,
    			  "request":request,
    			  "response":response
    	}
    	var config={"Content-Type":"application/json"};
    	CommonService.saveDate(data,config).then(function(response){
    		console.log(response.data);
    		 ToastService.simpleToast("API Request & Response has been  Created SuccessFull");
    	});
    	}
    }
    $scope.formatJsonResponse=function(res){
    	try{
    		$scope.responseModal=JSON.stringify(JSON.parse(res), null, 2);
    	}catch(err){console.log(err);}	
    }
    
    $scope.deleteById=function(id){
    	var data={ "id":id};
  	var config={"Content-Type":"application/json"};
  	CommonService.deleteById(data,config).then(function(response){
  		console.log(response.data);
  		ToastService.simpleToast("API Request & Response has been  Deleted");
  		 $scope.init();
  	});
    }
    
    $scope.deleteByUrlAndMethod=function(url,method){
    	var data={ "url":url,"method":method};
  	var config={"Content-Type":"application/json"};
  	CommonService.deleteByUrlAndMethod(data,config).then(function(response){
  		console.log(response.data);
  		ToastService.simpleToast("API ENDPOINT Is Deleted");
  		$state.go('hello');
  	});
    }
    
    $scope.convertInJson=function(data){
    	return JSON.stringify(JSON.parse(data), null,2);
    }
    $scope.formatJson=function(request){
    	try{
    		$scope.requestModal=JSON.stringify(JSON.parse(request), null, 2);
    	}catch(err){console.log(err);}
    	
    }

    $scope.showListBottomSheet=function(param){
    	  $scope.publishbutton=param;
    }
    
    $scope.goToChangeApiDetails=function (request,response) {
    	
        $state.go('change-api-detail',{data:{"urlPath":$scope.urlPath,"method":$scope.method,
        	"request":request,"response":response}});
    }

    $scope.init=function(){
    	
    	 $scope.method=$state.params.data.value;
        $scope.urlPath=$state.params.data.display;
      
        var config={"Content-Type":"application/json"};
    	CommonService.searchbyUriandmethod($state.params.data,config).then(function (response) {
    		$scope.requestData=response.data;
    		if(response.data.length==0){
    			ToastService.simpleToast("API ENDPOINT has been Deleted.");
    			StoreService.deleteData($state.params.data);
    			$state.go('hello');
    		}
    	});
    }
    $scope.init();
});

app.controller('changeApiDetailsController',function($scope,$state,CommonService,UtilService,ToastService) {
    $scope.message = 'Hello Welcome To Home Page';
    if($state.params.data==null){
    	$state.go('hello');
    }
    $scope.checkMethod=(method)=>{
    	return  UtilService.checkMethod(method);
     }
    $scope.convertInJson=function(data){
    	return JSON.stringify(JSON.parse(data), null, 2);
    }
    $scope.method=$state.params.data.method;
    $scope.urlPath=$state.params.data.urlPath;
    $scope.request=$scope.convertInJson($state.params.data.request);
    $scope.response=$scope.convertInJson($state.params.data.response);
    $scope.publishData=function(method,urlPath,request,response){
    	if(request!=undefined||response!=undefined){
    	var data={
    			  "urlPath":urlPath,
    			  "method":method,
    			  "request":request,
    			  "response":response
    	}
    	
    	if(request=="null")delete data.request;
    	
    	var config={"Content-Type":"application/json"};
    	CommonService.saveDate(data,config).then(function(response){
    		console.log(response.data);
    		ToastService.simpleToast("You Data has been Published.")
    	});
    	}
    }
    $scope.formatJsonResponse=function(res){
    	try{
    		$scope.response=JSON.stringify(JSON.parse(res), null, 2);
    	}catch(err){console.log(err);}	
    }
   
   
    $scope.formatJsonRequest=function(request){
    	try{
    		$scope.request=JSON.stringify(JSON.parse(request), null, 2);
    	}catch(err){console.log(err);}
    	
    }

    $scope.init=function(){
    	
    }
    $scope.init();

});
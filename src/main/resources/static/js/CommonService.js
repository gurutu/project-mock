var app=angular.module('projectmock');
app.service('CommonService',function ($http,$rootScope) {
    this.getData=(data,config)=>{
        return $http.post("project-mock/search",data,config);
    }
    
    this.searchbyUriandmethod=(data,config)=>{
        return $http.post("project-mock/searchbyUriandmethod",data,config);project-mock/save-data
    }
    
    this.saveDate=(data,config)=>{
        return $http.post("project-mock/save-data",data,config);
    }
    this.deleteById=(data,config)=>{
    	  return $http.post("project-mock/deletebyid",data,config);
    }
    this.deleteByUrlAndMethod=(data,config)=>{
    	return $http.post("project-mock/deletebyUrlAndMethod",data,config);
    }
});

app.service('ToastService',function($mdToast){
	this.simpleToast=(message)=>{
		var last = {
			      bottom: false,
			      top: true,
			      left: false,
			      right: true
			    };
		var toastPosition = angular.extend({}, last);
		 $mdToast.show(
			        $mdToast.simple()
			        .textContent(message)
			        .position(Object.keys(last)
			      	      .filter(function(pos) {
			      	        return toastPosition[pos];
			      	      }).join(' '))
			        .hideDelay(3000))
			      .then(function() {
			        console.log('Toast dismissed.');
			      }).catch(function() {
			        console.log('Toast failed or was forced to close early by another toast.');
			      });
	}
	
	
});
app.service('UtilService',function () {
	this.checkMethod=(method)=>{
		if(method==='GET'||method==='DELETE'||method==='HEAD'||method==='OPTION'||method==='TRACE'){
			return true;
		}else{
			return false;
		}
	}
});




app.service('StoreService',function ($http,$rootScope) {
	this.getSearchData=()=>{
		return localStorage.getItem('searchData');
	}
	this.pushSearchData=(data)=>{
		var existing=localStorage.getItem('searchData');
		var visited = new Set();
		var flag=false;
		visited = existing ? JSON.parse(existing) : [];
		visited.forEach(element =>{ if(JSON.stringify(element)===JSON.stringify(data)){ flag=true; }});
		if(!flag)visited.push(data);
		
	  localStorage.setItem('searchData', JSON.stringify(visited));
	  return ;
	}
	this.deleteData=(data)=>{
		var existing=localStorage.getItem('searchData');
		var visited = new Set();
		var index=0;
		visited = existing ? JSON.parse(existing) : [];
		for(var i=0;i<visited.length;i++){
			if(data.value===visited[i].value&&data.display===visited[i].display){
				visited.splice(i, 1);
				break;
			}
		}
		 localStorage.setItem('searchData', JSON.stringify(visited));
	}
});
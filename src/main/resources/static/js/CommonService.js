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
});
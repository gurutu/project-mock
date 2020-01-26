var app=angular.module('projectmock');
app.service('CommonService',function ($http,$rootScope) {
    this.getData=(data,config)=>{
        return $http.post("project-mock/search",data,config);
    }
})
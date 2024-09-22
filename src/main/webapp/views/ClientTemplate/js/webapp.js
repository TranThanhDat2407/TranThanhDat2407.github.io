angular
.module("myApp", ["ngRoute"])
  .config(function ($routeProvider) {
	
	})
  .controller("myCtrl", function ($scope) {
	$http.get('https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json').then(function(response){
	     $scope.test = response.data;
	    console.log(response.data);
	   });
    
   });

    
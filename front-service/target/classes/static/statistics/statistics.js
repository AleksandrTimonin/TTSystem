angular.module('ttsystem-front').controller('statisticsController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/api/v1/';

$scope.isAllowed = function(elem){
              var result = $localStorage.allowance.roles.includes(elem);
              console.log(result);
              return result ;

        }
});
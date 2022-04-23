angular.module('ttsystem-front').controller('personalController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/api/v1/';

        $scope.loadPersonalInfo = function () {

                    $http({
                        url: contextPath + 'orders/getPersonal',
                        method: 'GET'

                    }).then(function (response) {
                        $scope.UserDto = response.data;
                    });
                };
        $scope.isAllowed = function(elem){
              var result = $localStorage.allowance.roles.includes(elem);
              console.log(result);
              return result ;

        }





    $scope.loadPersonalInfo();




});
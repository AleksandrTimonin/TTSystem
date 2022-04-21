angular.module('ttsystem-front').controller('personalController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/api/v1/';
 $scope.loadOrders = function (pageIndex = 1) {
            $http({
                url: contextPath + 'orders/management',
                method: 'GET',
                params: {
                    p: pageIndex,
                    old_date: $scope.filter ? $scope.filter.oldDate : null,
                    new_date: $scope.filter ? $scope.filter.newDate : null
                }
            }).then(function (response) {
                $scope.OrdersPage = response.data;
                $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.OrdersPage.totalPages);
            });
        };
        $scope.loadPersonalInfo = function () {
                $scope.User = $localStorage.ttsystemUser;
                    $http({
                        url: contextPath + 'orders/getPersonal',
                        method: 'GET'

                    }).then(function (response) {
                        $scope.UserDto = response.data;
                    });
                };
       $scope.loadUsersInfo = function () {
                 $http({
                  url: contextPath + 'orders/getUserInfo',
                   method: 'GET' ,
                    params: {

                        users : $scope.users

                    }

                    }).then(function (response) {
                     $scope.UserDto = response.data;
                    });

        };

        $scope.generatePagesIndexes = function (startPage, endPage) {
            let arr = [];
                for (let i = startPage; i < endPage + 1; i++) {
                    arr.push(i);
                }
            return arr;
        }

    $scope.isAllowed = function(elem){
        var result = $localStorage.allowance.roles.includes(elem);
        console.log(result);
        return result ;

    }
    if($scope.isAllowed('EMPLOYEE')){
        $scope.loadOrders();
    }
    $scope.loadPersonalInfo();
    console.log($localStorage.ttsystemUser);



});
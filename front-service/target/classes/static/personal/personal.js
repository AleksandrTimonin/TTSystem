angular.module('ttsystem-front').controller('personalController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';
 $scope.loadOrders = function (pageIndex = 1) {
            $http({
                url: contextPath + 'api/v1/orders',
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

        $scope.generatePagesIndexes = function (startPage, endPage) {
            let arr = [];
                for (let i = startPage; i < endPage + 1; i++) {
                    arr.push(i);
                }
            return arr;
        }

    $scope.loadOrders();
});
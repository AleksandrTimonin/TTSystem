angular.module('ttsystem-front').controller('ordersController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:5555/core/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'api/v1/orders')
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    }

    $scope.goToDetails = function (orderId) {
        $location.path('/details/' + orderId);
    }
    $scope.isActual = function(status){
        return status=='CREATED';
    }

    $scope.loadOrders();
});
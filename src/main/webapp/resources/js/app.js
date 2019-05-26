var partslistApp = angular.module("partslistApp", []);

partslistApp.controller("partsController", function ($scope, $http, $location) {
    var baseUrl = $location.absUrl() + "/parts";
    var currentPage = 1;
    var itemsPerPage = 10;
    var commonParams = function (page, size) {
        return "page=" + page + "&size=" + size;
    };
    $scope.selectedPart = {};
    $scope.partsList = {};
    $scope.filterParam = "";
    $scope.searchText = "";
    var paramsForPaginate = function () {
        return "required=" + $scope.filterParam + "&search=" +
        $scope.searchText + "&" + commonParams(currentPage, itemsPerPage);
    };

    updateContent();

    var numberOfPages = function(callback) {
        $http.get(baseUrl + "?required=" + $scope.filterParam + "&search=" +
            $scope.searchText).then(function success(response) {
                if (callback && typeof callback === "function") {
                    callback(Math.ceil(response.data.parts.length / itemsPerPage));
                }
            });
    };

    $scope.prevPage = function() {
        if (currentPage > 1) {
            currentPage--;
        }
        $http.get(baseUrl + "?" + paramsForPaginate()).then(function success(response) {
            $scope.partsList = response.data;
        });
    };

    $scope.nextPage = function() {
        numberOfPages(function (allSize) {
            if (currentPage < allSize) {
                currentPage++;
            }
            $http.get(baseUrl + "?" + paramsForPaginate()).then(function success(response) {
                $scope.partsList = response.data;
            });
        });
    };

    $scope.searchForName = function () {
        currentPage = 1;
        $http.get(baseUrl + "?" + paramsForPaginate()).then(function success(response) {
            $scope.partsList = response.data;
        })
    };

    $scope.clearSearchText = function () {
        $scope.searchText = "";
        updateContent();
    };

    $scope.filter = function (required) {
        $scope.filterParam = required;
        currentPage = 1;
        $http.get(baseUrl + "?" + paramsForPaginate()).then(function success(response) {
            $scope.partsList = response.data;
        })
    };

    //edit part
    $scope.getTemplate = function (part) {
        if (part.id === $scope.selectedPart.id) {
            return "edit";
        } else {
            return "display";
        }
    };

    $scope.editPart = function (part) {
        $scope.selectedPart = angular.copy(part);
    };

    $scope.savePart = function (part) {
        $http.put(baseUrl + "/" + part.id, $scope.selectedPart).then(function success() {
            updateContent();
        });
        $scope.reset();
    };

    $scope.reset = function () {
        $scope.selectedPart = {};
    };
    //end edit part

    $scope.addPart = function (addForm) {
        if (addForm.$valid) {
            $http.post(baseUrl, $scope.newPart).then(function success() {
                updateContent();
            })
        }
    };

    $scope.deletePart = function (part) {
        $http.delete(baseUrl + "/" + part.id).then(function success() {
            updateContent();
        })
    };

     function updateContent() {
         $http.get(baseUrl + "?" + paramsForPaginate()).then(function success(response) {
             $scope.partsList = response.data;
         });
     }
});
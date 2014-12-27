angular.module('showExtract', ['mm.foundation']).controller('myController', function ($scope) {
  $scope.dynamicTooltip = "Hello, World!";
  $scope.runID = "ubuntu-2014-12-15 21:09:52.072"
  $scope.dynamicTooltipText = "dynamic";
  $scope.htmlTooltip = "I've been made <b>bold</b>!";
});
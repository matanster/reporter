angular.module('plunker', ['mm.foundation']).controller('TooltipDemoCtrl', function ($scope) {
  $scope.dynamicTooltip = "Hello, World!";
  $scope.dynamicTooltipText = "dynamic";
  $scope.htmlTooltip = "I've been made <b>bold</b>!";
});
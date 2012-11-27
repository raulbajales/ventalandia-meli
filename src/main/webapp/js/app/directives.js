'use strict';

/* Directives */

/*
angular.module('vldApp.directives', []).
	directive('fallbackImages', function() {
	    return {
	        scope: {
	            fallbackImages: '='
	        },
	        link: function(scope, element, attrs) {

	            var loadElement = angular.element(document.createElement('img'));

	            scope.$watch('image()', function(newImage, oldImage) {
	                if (newImage) {
	                    loadElement.attr('src', newImage);
	                }
	            });

	            loadElement.bind('error', function() {
	                scope.$apply(function() {
	                    scope.imageFailed(loadElement.attr('src'));
	                });
	            });

	            loadElement.bind('load', function() {
	                element.attr('src', loadElement.attr('src'));
	            });

	        },
	        controller: function($scope) {

	            $scope.badImages = [];

	            $scope.imageFailed = function(image) {
	                $scope.badImages.push(image);
	            };

	            $scope.image = function() {
	                var potentialNextImage = $scope.fallbackImages.filter(function(image) {
	                    return $scope.badImages.indexOf(image) === -1;
	                });

	                if (potentialNextImage.length > 0) {
	                    return potentialNextImage[0];
	                }
	            };
	        }
	    };
	});
*/
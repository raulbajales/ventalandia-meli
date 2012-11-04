'use strict';

angular.module('vldApp.services', [])
       .factory('SharedModel', function () {
			var model = {};
			model.set = function(key, value){
			    model[key] = value;
			};
			model.get = function(key){
			    return model[key];
			};
			return model;
		});

/* -------------------------------------------------- */
//
//  Registering services for IoC
//
/* -------------------------------------------------- */

angular.module('vldApp.services', [])
       .factory('UserService', ventalandia.service.UserService)
       .factory('NewsService', ventalandia.service.NewsService);


/* -------------------------------------------------- */
//
//  The Sevices
//
/* -------------------------------------------------- */

ventalandia.service.UserService = function($cookies, $http) {
	var UserService = ventalandia.service.UserService;

	/**
	 * @param {function(ventalandia.model.MiniProfile)} onSuccess
	 */
	UserService.getMyProfile = function(onSuccess) {
		$http({method: "GET", url: "/api/users/me", 
			   headers: {"x-vtd-token": $cookies["vtd_token"]}
		    }).success(function(data, status, headers, config) {
		    	onSuccess(ventalandia.model.MiniProfile.fromObject(data));
			}).error(function(data, status, headers, config) {
				console.log("[ERROR] - Unable to get profile data");
				console.log(JSON.stringify(data)); // invoke a general ui error handler
			});		
	}
}

ventalandia.service.NewsService = function($cookies, $http) {
	var NewsService = ventalandia.service.NewsService;

	/**
	 * @param {function(ventalandia.model.Newsfeed)} onSuccess
	 */
	NewsService.getMyNewsfeed = function(onSuccess) {
		$http({method: "GET", url: "/api/news", 
			   headers: {"x-vtd-token": $cookies["vtd_token"]}
		    }).success(function(data, status, headers, config) {
				onSuccess(ventalandia.model.Newsfeed.fromObject(data));
			}).error(function(data, status, headers, config) {
				console.log("[ERROR] - Unable to get newsfeed");
				console.log(JSON.stringify(data)); // invoke a general ui error handler
			});
	}

	/**
	 * @param {function(ventalandia.model.Newsfeed)} onSuccess
	 */
	NewsService.getNewsDetails = function(newsId, onSuccess) {
		$http({method: "GET", url: "/api/news/" + newsId, 
			   headers: {"x-vtd-token": $cookies["vtd_token"]}
		    }).success(function(data, status, headers, config) {
		    	onSuccess(ventalandia.model.NewsDetails.fromObject(data));
			}).error(function(data, status, headers, config) {
				console.log("[ERROR] - Unable to get newsfeed details for id " + entry.id);
				console.log(JSON.stringify(data)); // invoke a general ui error handler
			});
	}


}
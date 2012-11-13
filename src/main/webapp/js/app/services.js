/* -------------------------------------------------- */
//
//  UserService
//
/* -------------------------------------------------- */

ventalandia.service.UserService = function($cookies, $http) {

  return {

	/**
	 * @param {function(ventalandia.model.MiniProfile)} onSuccess
	 */
    getMyProfile: function(onSuccess) {
		$http({method: "GET", url: "/api/users/me", 
			   headers: {"x-vtd-token": $cookies["vtd_token"]}
		    }).success(function(data, status, headers, config) {
		    	onSuccess(ventalandia.model.MiniProfile.fromObject(data));
			}).error(function(data, status, headers, config) {
				throw "[ERROR] - Unable to get profile data\n" + JSON.stringify(data);
			});		
	}
  }
}

/* -------------------------------------------------- */
//
//  NewsService
//
/* -------------------------------------------------- */

ventalandia.service.NewsService = function($cookies, $http) {

	return {

		/**
		 * @param {function(ventalandia.model.Newsfeed)} onSuccess
		 */
		getMyNewsfeed: function(onSuccess) {
			$http({method: "GET", url: "/api/news", 
				   headers: {"x-vtd-token": $cookies["vtd_token"]}
			    }).success(function(data, status, headers, config) {
					onSuccess(ventalandia.model.Newsfeed.fromObject(data));
				}).error(function(data, status, headers, config) {
					throw "[ERROR] - Unable to get newsfeed\n" + JSON.stringify(data);
				});
		},

		/**
		 * @param {string} newsId
		 * @param {function(ventalandia.model.Newsfeed)} onSuccess
		 */
		getNewsDetails: function(newsId, onSuccess) {
			$http({method: "GET", url: "/api/news/" + newsId, 
				   headers: {"x-vtd-token": $cookies["vtd_token"]}
			    }).success(function(data, status, headers, config) {
			    	onSuccess(ventalandia.model.NewsDetails.fromObject(data));
				}).error(function(data, status, headers, config) {
					throw "[ERROR] - Unable to get newsfeed details for id " + entry.id + "\n" + JSON.stringify(data);
				});
		},

		/**
		 * @param {function(ventalandia.model.Summary)} onSuccess
		 */
		getMyNewsSummary: function(onSuccess) {
			$http({method: "GET", url: "/api/news/summary", 
				   headers: {"x-vtd-token": $cookies["vtd_token"]}
			    }).success(function(data, status, headers, config) {
			    	onSuccess(ventalandia.model.Summary.fromObject(data));
				}).error(function(data, status, headers, config) {
					throw "[ERROR] - Unable to get news summary\n" + JSON.stringify(data);
				});
		},

		/**
		 * @param {string} newsId
		 * @param {string} answer
		 * @param {function()} onSuccess
		 */
		sendAnswer: function(newsId, answer, onSuccess) {
			var theBody = {"question_id": newsId, "text": answer};
			$http({method: "POST", url: "/api/answers/", 
				   headers: {"x-vtd-token": $cookies["vtd_token"],
				             "Content-Type": "application/json"},
				   data: theBody
			    }).success(function(data, status, headers, config) {
			    	onSuccess();
			    	console.log("Just sent answer: " + JSON.stringify(theBody));
				}).error(function(data, status, headers, config) {
					throw "[ERROR] - Unable to send answer\n" + JSON.stringify(data);
				});			
		},

		/**
		 * @param {string} aDate Date in UTC string format (like "2012-10-10T13:10:52.067")
		 * @param {function(Array.<ventalandia.model.Newsfeed.Entry>)} onSuccess
		 */
		getNewsfeedSince: function(aDate, onSuccess) {
			$http({method: "GET", url: "/api/news?since=" + encodeURIComponent(aDate), 
				   headers: {"x-vtd-token": $cookies["vtd_token"]}
			    }).success(function(data, status, headers, config) {
					var latest = ventalandia.model.Newsfeed.fromObject(data);
					onSuccess(latest.entries);
				}).error(function(data, status, headers, config) {
					throw "[ERROR] - Unable to get newsfeed\n" + JSON.stringify(data);
				});
		}
	}
}

/* -------------------------------------------------- */
//
//  Registering services for IoC
//
/* -------------------------------------------------- */

angular.module('vldApp.services', [])
       .factory('UserService', ventalandia.service.UserService)
       .factory('NewsService', ventalandia.service.NewsService);

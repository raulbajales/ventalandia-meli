/* -------------------------------------------------- */
//
//  Events
//
/* -------------------------------------------------- */

ventalandia.controller.event = {
	MINI_PROFILE_LOADED: "miniProfileLoaded",
	NEWS_DETAILS_REQUESTED: "newsDetailsRequested"
}

/* -------------------------------------------------- */
//
//  MiniProfileController
//
/* -------------------------------------------------- */

ventalandia.controller.MiniProfileController = function($scope, UserService) {
	var event = ventalandia.controller.event;
	
	UserService.getMyProfile(function(miniProfile) {
		$scope.miniProfile = miniProfile;
		$scope.reputationClass = ventalandia.ui.reputationClassFor($scope.miniProfile.sellerReputationLevel);
		$scope.$emit('broadcast', {
			id: event.MINI_PROFILE_LOADED, 
			data: $scope.miniProfile
		});
	});
}

/* -------------------------------------------------- */
//
//  NewsController
//
/* -------------------------------------------------- */

ventalandia.controller.NewsController = function($scope, $timeout, NewsService) {
	var event = ventalandia.controller.event;
	var settings = ventalandia.settings;

	NewsService.getMyNewsfeed(function(newsfeed) {
		console.log("newsfeed");
		console.log(newsfeed);
		$scope.newsfeed = newsfeed.hasEntries() ? newsfeed : null;			
	});

	$scope.showNewsDetails = function($event, entry) {
		ventalandia.ui.newsfeed.activate($event.currentTarget);
 		$scope.$emit('broadcast', {
 			id: event.NEWS_DETAILS_REQUESTED, 
 			data: entry
 		});
	}		

	$scope.updateNewsfeed = function() {
		$scope.newsfeed = angular.copy($scope.updatedNewsfeed);
		delete $scope.updatedNewsfeed;
		$scope.numNewsfeedUpdates = 0;
	}		

	/*
	  Start pulling for updates on newsfeed
	*/
    setInterval(function() {
        $scope.$apply(function() {
			var newestDate = $scope.newsfeed ? $scope.newsfeed.entries[0]["utcDate"] : (new Date()).toISO();
			NewsService.getNewsfeedSince(newestDate, function(entries) {
				$scope.numNewsfeedUpdates = entries && angular.isArray(entries) ? entries.length : 0;
				$scope.updatedNewsfeed = $scope.newsfeed.merge(entries);
			});
        });
    }, settings.NEWSFEED_PULL_INTERVAL_IN_MILLIS);
}

/* -------------------------------------------------- */
//
//  NewsDetailsController
//
/* -------------------------------------------------- */

ventalandia.controller.NewsDetailsController = function($scope, NewsService) {
	var event = ventalandia.controller.event;

	$scope.$on(event.NEWS_DETAILS_REQUESTED, function(event, entry) {
		$scope.newsEntry = entry;
		NewsService.getNewsDetails(entry.id, function(newsDetails) {
		    $scope.newsDetails = newsDetails;
	    });
	});

	$scope.$on(event.MINI_PROFILE_LOADED, function(event, miniProfile) {
		$scope.miniProfile = miniProfile;
	});

	$scope.sendAnswer = function(question) {
		if (!question.answer) return;
		NewsService.sendAnswer(question.id, question.answer, function() {
			question.answered = true;
			$scope.newsEntry.answered = true;
		});
	};	
}

/* -------------------------------------------------- */
//
//  TopbarController
//
/* -------------------------------------------------- */

ventalandia.controller.TopbarController = function($scope, NewsService) {
	NewsService.getMyNewsSummary(function(summary) {
		$scope.summary = summary;
	});

	$scope.confirmLogout = function() {
        ventalandia.ui.confirm("Salir", "Esta seguro de que desea salir de Ventalandia?", "Cancelar", "Ok", function() {
        	ventalandia.ui.logout();
        });
	}
}

/* -------------------------------------------------- */
//
//  CustomersController
//
/* -------------------------------------------------- */

ventalandia.controller.CustomersController = function() {}

/* -------------------------------------------------- */
//
//  ProductsController
//
/* -------------------------------------------------- */

ventalandia.controller.ProductsController = function() {}

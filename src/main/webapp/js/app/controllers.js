/* -------------------------------------------------- */
//
//  Events
//
/* -------------------------------------------------- */

ventalandia.controller.event = {
	MINI_PROFILE_LOADED: "miniProfileLoaded",
	NEWS_DETAILS_REQUESTED: "newsDetailsRequested",
	NEWSFEED_SEARCH_EXECUTED: "newsfeedSearchExecuted"
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
		if (!miniProfile.TOS) {
			ventalandia.ui.showTOS(function() {
			  UserService.acceptTOS();
			  document.location.reload();
			});
		}
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

	$scope.$on(event.NEWSFEED_SEARCH_EXECUTED, function(event, newsfeed) {
		$scope.newsfeed = newsfeed;
	});

	NewsService.getMyNewsfeed(function(newsfeed) {
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
        	var newsfeed = $scope.newsfeed;
			var newestDate = (newsfeed && newsfeed.entries) ? newsfeed.entries[0]["utcDate"] : (new Date()).toISO();

			// Add one minute to avoid getting the same result on updates (#102)
			var timeObject = (new Date(newestDate));
			timeObject.setTime(timeObject.getTime() + 1000 * 60);
			newestDate = timeObject.toISO();

			NewsService.getNewsfeedSince(newestDate, function(entries) {
				$scope.numNewsfeedUpdates = entries && angular.isArray(entries) ? entries.length : 0;
				$scope.updatedNewsfeed = newsfeed.merge(entries);
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
    var event = ventalandia.controller.event;
    
    $scope.searchTerms = "";

	NewsService.getMyNewsSummary(function(summary) {
		$scope.summary = summary;
	});

	$scope.confirmLogout = function() {
        ventalandia.ui.confirm("Salir", "Esta seguro de que desea salir de Ventalandia?", "Cancelar", "Ok", function() {
        	ventalandia.ui.logout();
        });
	}

	$scope.searchNewsfeed = function(terms) {
		NewsService.searchNewsfeed(terms, function(newsfeed) {
		    $scope.$emit('broadcast', {
				id: event.NEWSFEED_SEARCH_EXECUTED, 
				data: newsfeed.hasEntries() ? newsfeed : null
			});
	    })
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

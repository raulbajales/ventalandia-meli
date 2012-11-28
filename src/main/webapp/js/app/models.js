/* -------------------------------------------------- */
//
//  Mini Profile
//
/* -------------------------------------------------- */

/**
 * Models a user mini profile
 *
 * @param {string} meliId
 * @param {string} firstName
 * @param {string} lastName
 * @param {string} nickName
 * @param {string} thumbnailUrl
 * @param {string} sellerReputationLevel
 */
ventalandia.model.MiniProfile = function(meliId, firstName, lastName, nickName, thumbnailUrl, sellerReputationLevel) {
	this.meliId = meliId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.nickName = nickName;
	this.thumbnailUrl = thumbnailUrl;
	this.sellerReputationLevel = sellerReputationLevel;
}

/**
 * Builds a new MiniProfile instance from a JSON object.
 *
 * @param {object} obj
 * @return {ventalandia.model.MiniProfile}
 */
ventalandia.model.MiniProfile.fromObject = function(obj) {
	var defaultThumb = "img/icon_guest.jpeg"
	return new ventalandia.model.MiniProfile(
		obj.meliId, 
		obj.name, 
		obj.surname, 
		obj.nickname, 
		obj.thumbnailUrl || defaultThumb,
		obj.sellerReputationLevel);
}

/* -------------------------------------------------- */
//
//  Newsfeed
//
/* -------------------------------------------------- */

/**
 * Models a Newsfeed as a list of entries
 *
 * @param {Array.<ventalandia.model.Newsfeed.Entry>} entries
 */
ventalandia.model.Newsfeed = function(entries) {
	this.entries = entries;
}

/**
 * @return {boolean}
 */
ventalandia.model.Newsfeed.prototype.hasEntries = function() {
	return (!this.entries) || (this.entries.constructor.name == "Array" && this.entries.length > 0);
}

/**
 * @param {Array.<ventalandia.model.Newsfeed.Entry>|null|undefined} entries
 * @return {ventalandia.model.Newsfeed}
 */
ventalandia.model.Newsfeed.prototype.merge = function(entries) {
	var ids = entries ? _.map(entries, function(entry) {
		return entry.id;
	}) : [];
	var filtered = _.reject(angular.copy(this.entries), function(entry) {
		return _.indexOf(ids, entry.id) != -1;
	})
	var result = (entries || []).concat(filtered);
	return new ventalandia.model.Newsfeed(result);
}

/**
 * Base model for an entry
 *
 * @param {string} id 
 * @param {string} date
 * @param {string} type 
 * @param {object} raw 
 * @param {object} answered 
 */
ventalandia.model.Newsfeed.Entry = function(id, date, type, raw, answered) {
	this.id = id;
	this.utcDate = date;
	this.date = humaneDate(date);
	this.type = type;
	this.answered = answered;
	this._buildMetadata(raw);
}

/**
 * Builds metadata by entry type
 * 
 * @private
 *
 * @param {object} raw
 */
ventalandia.model.Newsfeed.Entry.prototype._buildMetadata = function(raw) {
	var that = this;
	var handlers = {
		"QUESTION": function(raw) {
			that.buyerId = raw.buyer.id,
			that.buyerNickName = raw.buyer.nickname,
			that.itemId = raw.item.id,
			that.itemTitle = raw.item.title
		}
	};
	handlers[this.type](raw);
}

/**
 * Builds a new Newsfeed instance from a JSON object.
 *
 * @param {Array} raw
 * @return {ventalandia.model.Newsfeed}
 */
ventalandia.model.Newsfeed.fromObject = function(raw) {
	var entries;
	if (!raw) {
		return new ventalandia.model.Newsfeed([]);
	};
	raw.forEach(function(e) {
		entries = entries || [];
		entries.push(new ventalandia.model.Newsfeed.Entry(e.id, e.date, e.type, e, e.answered));
	});
	return new ventalandia.model.Newsfeed(entries);
}

/* -------------------------------------------------- */
//
//  News Details
//
/* -------------------------------------------------- */

/**
 * Models details for a newsfeed entry
 *
 * @param {ventalandia.model.NewsDetails.Item} item
 * @param {ventalandia.model.NewsDetails.Buyer} buyer
 * @param {Array.<ventalandia.model.NewsDetails.Question>} questions
 */
ventalandia.model.NewsDetails = function(item, buyer, questions) {
	this.item = item;
	this.buyer = buyer;
	this.questions = questions;
}

/**
 * @param {string} title
 * @param {string} pictureUrl
 * @param {boolean} active
 */
ventalandia.model.NewsDetails.Item = function(title, pictureUrl, active) {
	this.title = title;
	this.pictureUrl = pictureUrl;
	this.active = active;
}

/**
 * @param {string} pictureUrl
 * @param {string} nickName
 */
ventalandia.model.NewsDetails.Buyer = function(pictureUrl, nickName) {
	var defaultThumb = "img/icon_customer.jpg"
	this.pictureUrl = pictureUrl || defaultThumb;
	this.nickName = nickName;
}

/**
 * @param {string} id
 * @param {string} text
 * @param {string} answer
 * @param {string} textDate
 * @param {string} answerDate
 */
ventalandia.model.NewsDetails.Question = function(id, text, answer, textDate, answerDate) {
	this.id = id;
	this.text = text;
	this.answer = answer;
	this.textDate = textDate;
	this.answerDate = answerDate;
	this.answered = (this.answer != null && this.answer != undefined)	
}

/**
 * Creates new NewsDetails entry from a JSON object
 *
 * @param {object} obj
 */
ventalandia.model.NewsDetails.fromObject = function(obj) {
	var item = new ventalandia.model.NewsDetails.Item(obj.item.title, obj.item.pictureUrl, obj.item.active);
	var buyer = new ventalandia.model.NewsDetails.Buyer(obj.buyer.pictureUrl, obj.buyer.nickname);
	var questions = [];
	obj.questions.forEach(function(e) {
		var id = e.id;
		var text = e.question.text;
		var textDate = humaneDate(e.question.date);
		var answer = e.answer ? e.answer.text : null;
		var answerDate = e.answer ? humaneDate(e.answer.date) : null;
		questions.push(new ventalandia.model.NewsDetails.Question(id, text, answer, textDate, answerDate));
	});
	return new ventalandia.model.NewsDetails(item, buyer, questions);
}

/* -------------------------------------------------- */
//
//  Summary
//
/* -------------------------------------------------- */

/**
 * Models summary of general changes
 *
 * @param {number} item
 */
ventalandia.model.Summary = function(newQuestions) {
	this.newQuestions = newQuestions;
}

/**
 * Creates new Summary instance from a JSON object
 *
 * @param {object} obj
 */
ventalandia.model.Summary.fromObject = function(obj) {
	return new ventalandia.model.Summary(obj.new_questions);
}



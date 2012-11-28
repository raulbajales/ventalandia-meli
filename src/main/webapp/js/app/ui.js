/**
 * Renders a "Confirm" dialog.
 *
 * @param {string|null} heading
 * @param {string} question
 * @param {string} cancelButtonTxt
 * @param {string} okButtonTxt
 * @param {string} callback
 */
ventalandia.ui.confirm = function(heading, question, cancelButtonTxt, okButtonTxt, callback) {
	var headingHTML = heading ? '<div class="modal-header">' +
            '<a class="close" data-dismiss="modal" >&times;</a>' +
            '<h3>' + heading +'</h3>' +
          '</div>' : '';
    var confirmModal =
      $('<div class="modal hide">' +    
          headingHTML +
          '<div class="modal-body">' +
            '<p>' + question + '</p>' +
          '</div>' +
          '<div class="modal-footer">' +
            '<a href="#" class="btn" data-dismiss="modal">' +
              cancelButtonTxt +
            '</a>' +
            '<a href="#" id="okButton" class="btn btn-inverse">' +
              okButtonTxt +
            '</a>' +
          '</div>' +
        '</div>');
    confirmModal.find('#okButton').click(function(event) {
      callback();
      confirmModal.modal('hide');
    });
    confirmModal.modal('show');    
};

/**
 * Renders an "Alert" dialog.
 *
 * @param {string|null} heading
 * @param {string} text
 * @param {string} okButtonTxt
 * @param {string} callback
 */
ventalandia.ui.alert = function(heading, text, okButtonTxt, callback) {
  var headingHTML = heading ? '<div class="modal-header">' +
            '<h3>' + heading +'</h3>' +
          '</div>' : '';
    var confirmModal =
      $('<div class="modal hide">' +    
          headingHTML +
          '<div class="modal-body">' +
            '<p>' + text + '</p>' +
          '</div>' +
          '<div class="modal-footer">' +
            '<a href="#" id="okButton" class="btn btn-inverse">' +
              okButtonTxt +
            '</a>' +
          '</div>' +
        '</div>');
    confirmModal.find('#okButton').click(function(event) {
      callback();
      confirmModal.modal('hide');
    });
    confirmModal.modal('show');    
};
/**
 * Maps a reputation level (as defined by our VentalandiaAPI)
 * to a css class name
 *
 * @param {number} sellerReputationLevel
 */
ventalandia.ui.reputationClassFor = function(sellerReputationLevel) {
  return {
    0: "grey",
    1: "red",
    2: "orange",
    3: "yellow",
    4: "lightgreen",
    5: "green"
  }[sellerReputationLevel];
}

/**
 * @param {*} element The DOM element for the newsfeed entry to activate.
 */
ventalandia.ui.newsfeed.activate = function(element) {
    $(".newsfeed .news").removeClass("active");
    $(element).addClass("active"); 
    $("#right").addClass("active"); 
}

/**
 * General UI initialization
 */
ventalandia.ui.initialize = function() {
  $(".generic-search").tooltip({title:"Puede buscar en todas sus Novedades. Escriba lo que busca y presione Enter.", placement: "bottom"});
  $(".reputation.badge").tooltip({title:"Este es su nivel actual de Reputacion en Mercadolibre.", placement: "right"});      
}

/**
 * Finishes current session
 */
ventalandia.ui.logout = function() {
  document.cookie = 'vtd_token=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
  document.location.href="/";
  document.location.reload();
}


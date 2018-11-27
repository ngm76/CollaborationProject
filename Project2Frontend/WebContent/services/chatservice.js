//This filter is used to display chat messages in reverse order ie from last index to 0 , latest message first
app.filter('reverse', function() {
	  return function(items) {
	    return items.slice().reverse();
	  };
	});

	app.directive('ngFocus', function() {
	  return function(scope, element, attrs) {
	    element.bind('click', function() {
	      $('.' + attrs.ngFocus)[0].focus();
	    });
	  };
	});

	app.factory('ChatService', function($rootScope) {
	  alert('app factory')
	  /*var base_url="http://localhost:8097/Project2Middleware";
	  service.SOCKET_URL=base_url+"/chat";
*/	    var socket = new SockJS('/Project2Middleware/chatmodule');
	    var stompClient = Stomp.over(socket);
	    stompClient.connect('', '', function(frame) {
	      $rootScope.$broadcast('sockConnected', frame);
	    });

	    return {
	      stompClient: stompClient
	    };
});
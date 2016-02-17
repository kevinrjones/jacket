$(function() {
	$(function() {
		$("[data-toggle = 'popover']").popover();
	});
	
	var nav_search = $('#nav_search'); 
	
	nav_search.popover({
		html: true,
		trigger: 'focus',
		container: 'body',
		placement: 'bottom',
		content: function() {
			return $('#nav_search_content').html();
		},
		title: function() {
			return $('#nav_search_title').html();
		}
	});
	
	var nav_add = $('#nav_add'); 
	
	nav_add.popover({
		html: true,
		trigger: 'focus',
		container: 'body',
		placement: 'bottom',
		content: function() {
			return $('#nav_add_content').html();
		},
		title: function() {
			return $('#nav_add_title').html();
		}
	});

})

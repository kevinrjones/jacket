$(function() {
//	$('[data-toggle="popover"]').popover({
//		container : 'body'
//	});

	$('#add-popover').popover({
		html : true,
		container: 'body',
		content : function() {
			return $("#add-popover-content").html();
		}
	});

})

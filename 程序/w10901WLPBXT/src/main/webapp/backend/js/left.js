$(function(){
	$('#navigation').accordion({
		header: '.head',
		event: 'click',
		heightStyle: "fill",
		animate: {
			easing: 'swing',
	        duration: 200,
	        down: {
		        easing: 'easeOutBounce',
		        duration: 1000
		    },
	    }
	});
	$(".mb-head-icon").addClass("mb");
	$(".mb-head-icon").addClass("fa");
	$(".mb-head-icon").addClass("fa-angle-double-right");
	$(".mb-role-icon").addClass("mb");
	$(".mb-role-icon").addClass("fa");
	$(".mb-role-icon").addClass("fa-users");
	$(".mb-sys-icon").addClass("mb");
	$(".mb-sys-icon").addClass("fa");
	$(".mb-sys-icon").addClass("fa-cogs");
	
	
	$(".mb-list-icon").addClass("mb");
	$(".mb-list-icon").addClass("fa");
	$(".mb-list-icon").addClass("fa-angle-right");
});

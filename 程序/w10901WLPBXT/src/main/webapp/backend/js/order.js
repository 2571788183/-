
$(document).ready(function(){
	
	
	//hide message_body after the first one
	$(".message_list .message_body:gt(0)").hide();
	
	//hide message li after the 5th
	$(".message_list li:gt(4)").hide();

	
	//toggle message_body
	$(".message_head").click(function(){
		$(this).next(".message_body").slideToggle(500);
		return false;
	});

	//collapse all messages
	$(".collpase_all_message").click(function(){
		$(".message_body").slideUp(500);
		return false;
	});

	//show all messages
	$(".show_all_message").click(function(){
		$(this).hide();
		$(".show_recent_only").show();
		$(".message_list li:gt(4)").slideDown();
		return false;
	});

	//show recent messages only
	$(".show_recent_only").click(function(){
		$(this).hide();
		$(".show_all_message").show();
		$(".message_list li:gt(4)").slideUp();
		return false;
	});

		
		
		 $("#plus,#box").css({"opacity":"0","top":"1%"});
		 $("#minus,#box").css({"opacity":"0","top":"1%"});
		
		
			//幻灯片元素与类“menu_body”段与类“menu_head”时点击
			$("#secondpane .menu_body:eq(0)").show();
			$("#secondpane p.menu_head").click(function(){
				$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp(500);
				$(this).siblings().removeClass("current");
			});	
			
			//清空
			$.ajax({
		        type: "POST",
		        url: 'OrderServlet',
		        cache: false,
		        async: true,
		        data:{
		      	 method:"fixXML",
		      	 order_id:$("#order_id").val()
		        },
		        success: function(data){
//生成订单order 和 groups
					
		        	$(".menu_list").append(data);
		        	$(".current").next("div.menu_body").slideDown(500);
		        	$("#secondpane p.menu_head").click(function(){
						$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp(500);
						$(this).siblings().removeClass("current");
					});
		        }
			 });					

});



function plus(menu_id,menu_name){
	$("#plus").text("");
	$("#plus").append("添加成功！ <span>"+menu_name+"</span>+1");
	$("#plus,#box").css({"opacity":"1","top":"35%"});
	$("#plus").delay(500).animate({top:'-=20px',opacity:'0'},"1000");
	$("#box").delay(500).animate({opacity:'0'},"1000",function(){
		$("#plus,#box").css({"opacity":"0","top":"1%"});
	});
	$.ajax({
        type: "POST",
        url: 'OrderServlet',
        cache: false,
        async: true,
        data:{
      	 method:"addSubOrder",
         member_id:$("#member_id").val(),
         menu_id:menu_id,
         order_id:$("#order_id").val(),
         groups_id:$(".current").attr("id")
        },
        success: function(data){
        	$(".current").next("div.menu_body").text("");
        	$(".current").next("div.menu_body").append(data);
        }
	 });
};
function minus(menu_id,menu_name){
	$.ajax({
        type: "POST",
        url: 'OrderServlet',
        cache: false,
        async: true,
        data:{
      	 method:"delSubOrder",
         member_id:$("#member_id").val(),
         menu_id:menu_id,
         order_id:$("#order_id").val(),
         groups_id:$(".current").attr("id")
        },
        success: function(data){
        	if(data=="删除失败"){
        		$("#minus").text("");
				$("#minus").append("删除失败！当前订单没有添加此菜品！");
        	}else{
        		$("#minus").text("");
				$("#minus").append("删除成功！<span>"+menu_name+"</span>-1");
        	$(".current").next("div.menu_body").text("");
        	$(".current").next("div.menu_body").append(data);
        	}
        }
	 });
	$("#minus,#box").css({"opacity":"1","top":"35%"});
	$("#minus").delay(500).animate({top:'-=20px',opacity:'0'},"1000");
	$("#box").delay(500).animate({opacity:'0'},"1000",function(){
	$("#minus,#box").css({"opacity":"0","top":"1%"});
	});

};

var order=1;
function add_order(){
	$.ajax({
        type: "POST",
        url: 'OrderServlet',
        cache: false,
        async: true,
        data:{
      	 method:"addGroups",
         member_id:$("#member_id").val(),
         order_id:$("#order_id").val()
        },
        success: function(data){
        	$(".current").next("div.menu_body").slideUp(500);
        	$(".current").removeClass("current");
        	$(".menu_list").append(data);
        	$(".current").addClass("current").next("div.menu_body").slideDown(500);
        	$("#secondpane p.menu_head").click(function(){
				$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp(500);
				$(this).siblings().removeClass("current");
			});
        }
     });	
	
	
};
function del_order(){
	$.ajax({
        type: "POST",
        url: 'OrderServlet',
        cache: false,
        async: true,
        data:{
      	 method:"delGroups",
      	 groups_id:$(".current").attr("id")
        },
        success: function(data){
        	$(".current").next("div.menu_body").remove();
        	$(".current").remove();
        	$("#secondpane p.menu_head").click(function(){
        		$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp(500);
        		$(this).siblings().removeClass("current");
        	});
        }
     });
	
};

function plus_betch(menu_id,menu_name){
	var num=prompt("请输入需要添加的  "+menu_name+"  的数量：","1");
	if(num===null){return;}
	else{
		
	$("#plus").text("");
	$("#plus").append("添加成功！ <span>"+menu_name+"</span>+"+num);
	$("#plus,#box").css({"opacity":"1","top":"35%"});
	$("#plus").delay(500).animate({top:'-=20px',opacity:'0'},"1000");
	$("#box").delay(500).animate({opacity:'0'},"1000",function(){
		$("#plus,#box").css({"opacity":"0","top":"1%"});
	});
	$.ajax({
        type: "POST",
        url: 'OrderServlet',
        cache: false,
        async: true,
        data:{
      	 method:"addSubOrderBetch",
         member_id:$("#member_id").val(),
         menu_id:menu_id,
         order_id:$("#order_id").val(),
         groups_id:$(".current").attr("id"),
         num:num
        },
        success: function(data){
        	$(".current").next("div.menu_body").text("");
        	$(".current").next("div.menu_body").append(data);
        }
	 });
	}
};
function minus_betch(menu_id,menu_name){
	var ret=confirm("确定要删除此订单所有的  "+menu_name+"  么?");
	if(ret){
		$.ajax({
	        type: "POST",
	        url: 'OrderServlet',
	        cache: false,
	        async: true,
	        data:{
	      	 method:"delSubOrderBetch",
	         member_id:$("#member_id").val(),
	         menu_id:menu_id,
	         order_id:$("#order_id").val(),
	         groups_id:$(".current").attr("id")
	        },
	        success: function(data){
	        	if(data=="删除失败"){
	        		$("#minus").text("");
					$("#minus").append("删除失败！当前订单没有添加此菜品！");
	        	}else{
	        		$("#minus").text("");
					$("#minus").append("删除成功！<span>"+menu_name+"</span>全部已删除！");
	        	$(".current").next("div.menu_body").text("");
	        	$(".current").next("div.menu_body").append(data);
	        	}
	        }
		 });
		$("#minus,#box").css({"opacity":"1","top":"35%"});
		$("#minus").delay(500).animate({top:'-=20px',opacity:'0'},"1000");
		$("#box").delay(500).animate({opacity:'0'},"1000",function(){
		$("#minus,#box").css({"opacity":"0","top":"1%"});
		});
	}
};

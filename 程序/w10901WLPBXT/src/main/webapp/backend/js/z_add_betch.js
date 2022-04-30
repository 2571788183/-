function add_betch(action,fileinput){

	(function ($) {
        $(".loading").ajaxStart(function(){
			$(this).css("display","inline-block");
		}).ajaxComplete(function(){
			$(this).css("display","none");
		});
		$.ajaxFileUpload({
			url:"upload.do",
			secureuri:false,
			fileElementId:fileinput.id,
			dataType:'json',
			success: function (data, status){
				var filenames=data.filenames;
				var contentTypes=data.contentTypes;
				for(var i=0;i<data.nums;i++){
					var ret = filenames[i]+contentTypes[i];
					if(ret!=null&&ret!=""){
						filename=filenames[i];
						contentType=contentTypes[i];
					var url="";
					if(contentType.indexOf("excel")>-1){
						$.ajax({
				          type: "POST",
				          url: action+'!addBetch',
				          cache: false,
				          async: false,
				          data:{
				           	 filename:filename
				          },
				          success: function(data){
				        	  window.location.href=action+".do";
				          }
				       });
					}else{
					}
					}
				}
			},
			error: function (data, status, e){
				alert(e);
			}
		});
	})(jQuery);
    
}

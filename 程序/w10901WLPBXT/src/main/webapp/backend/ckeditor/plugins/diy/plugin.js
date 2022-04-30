(function() {
	CKEDITOR.plugins.add( 'diy', {
	    init: function( editor ) {
	        editor.addCommand( 'alt1', {
	            exec: function( editor ) {
	            	var mySelection = editor.getSelection();
	        		var bookmarks = mySelection.createBookmarks();
	        		var range = mySelection.getRanges()[0];
	            	var documentFragment = range.extractContents();
	            	var selStr = documentFragment.getHtml();
	            	selStr = selStr.replace(/<(.*?)>/g,"");
	            	var node = new CKEDITOR.dom.element( 'h2' );
	            	var textnode=document.createTextNode(selStr);
	            	var text = new CKEDITOR.dom.text( textnode );
	            	text.appendTo(node);
	            	range.insertNode(node);
//	            	var allStr = editor.getData();
//	            	allStr = allStr.replace(/<(.*?)>(.*?)&nbsp;(.*?)<(.*?)>/g,"");
//	        		editor.setData(allStr);
	            	editor.getSelection().selectBookmarks(bookmarks);
	            }
	        });
	        editor.addCommand( 'alt2', {
	        	exec: function( editor ) {
	        		var mySelection = editor.getSelection();
	        		var bookmarks = mySelection.createBookmarks();
	        		var range = mySelection.getRanges()[0];
	        		var documentFragment = range.extractContents();
	        		var selStr = documentFragment.getHtml();
	        		selStr = selStr.replace(/<(.*?)>/g,"");
	        		var node = new CKEDITOR.dom.element( 'b' );
	        		var textnode=document.createTextNode(selStr);
	        		var text = new CKEDITOR.dom.text( textnode );
	        		text.appendTo(node);
	        		range.insertNode(node);
//	        		var allStr = editor.getData();
//	        		allStr = allStr.replace(/<(.*?)>(.*?)&nbsp;(.*?)<(.*?)>/g,"");
//	        		editor.setData(allStr);
	        		editor.getSelection().selectBookmarks(bookmarks);
	        	}
	        });
	        editor.addCommand( 'alt3', {
	        	exec: function( editor ) {
	        		var mySelection = editor.getSelection();
	        		var bookmarks = mySelection.createBookmarks();
	            	var range = mySelection.getRanges()[0];
	            	var documentFragment = range.extractContents();
	            	var selStr = documentFragment.getHtml();
	            	selStr = selStr.replace(/<(.*?)>/g,"");
	            	var node = new CKEDITOR.dom.element( 'p' );
	            	var textnode=document.createTextNode(selStr);
	            	var text = new CKEDITOR.dom.text( textnode );
	            	text.appendTo(node);
	            	range.insertNode(node);
//	            	var allStr = editor.getData();
//	            	allStr = allStr.replace(/<(.*?)>(.*?)&nbsp;(.*?)<(.*?)>/g,"");
//	            	allStr = allStr.replace(/<p>(.*?)&nbsp;(.*?)<\/p>/g,"");
//	            	allStr = allStr.replace(/<p>(.*?)((&nbsp;)*?)(.*?)<\/p>/g,"<p>$1$3<\/p>");
//	        		editor.setData(allStr);
	        		// Make DOM changes.
	        		editor.getSelection().selectBookmarks(bookmarks);
	        	}
	        });
	        editor.addCommand( 'alt4', {
	        	exec: function( editor ) {
	        		var allStr = editor.getData();
	        		allStr = allStr.replace(/(<p>&nbsp;<\/p>)/g,"");
	        		allStr = allStr.replace(/(<h2>&nbsp;<\/h2>)/g,"");
	        		allStr = allStr.replace(/(<b>&nbsp;<\/b>)/g,"");
	        		allStr = allStr.replace(/(<strong>&nbsp;<\/strong>)/g,"");
	        		allStr = allStr.replace(/&(.*?)nbsp;/g,"");
	        		editor.setData(allStr);
	        	}
	        });
	    }
	});
})();

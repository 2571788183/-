/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.uiColor = '#f8f8f8';
	config.skin = 'moono_blue';
	config.extraPlugins = 'codesnippet';
	config.uploadUrl = '/upload!paste';
//	config.filebrowserBrowseUrl = '/backend/upload.jsp';
//	config.filebrowserImageBrowseUrl = '/backend/upload.jsp';
	config.filebrowserUploadUrl = '/upload!paste';
	config.filebrowserImageUploadUrl = '/upload!paste';
//	config.removeDialogTabs = 'image:advanced;link:advanced';
	config.codeSnippet_theme = 'monokai_sublime';
	config.codeSnippet_languages = {  java: 'Java',html:'HTML',css:'CSS',javascript: 'JavaScript',sql:'SQL',json:'JSON',properties:'Properties' };
};


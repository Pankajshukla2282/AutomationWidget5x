package  com.sharethis.QAAutomation.XPaths;

import junit.framework.TestCase;

/*******************************************************************************
 * CommonXPath class contain XPaths of the elements.
 ******************************************************************************/
public class CommonXPath extends TestCase {
	
	// ************RE_SKINNED WIDGET Related XPATHS*********	
	
	public static String RESKINNED_WIDGET = "css=span[class*='st_sharethis'] span.stButton";
	public static String RESKINNED_EMAIL_WIDGET = "css=span.st_email span.stButton span.chicklets";
	public static String RESKINNED_WIDGET_WINDOW = "css=div#outercontainer div#main span#popular.popular";
	public static String RESKINNED_WIDGET_SEARCH_FIELD = "//div[@id='borderContainer']/div[@id='outercontainer']/div[@id='main']/div[@id='mainPage']/div[@id='chicklet_search']/input[@id='chicklet_search_field']";
	public static String RESKINNED_WIDGET_SIGNIN_LINK = "css=div#footer div#signIn span#signIn_text";
	public static String RESKINNED_WIDGET_SIGNOUT_LINK = "css=div#outercontainer div#footer div#footer_info a#signOutt";
	public static String RESKINNED_WIDGET_DONOTTRACK_LINK = "css=div#borderContainer div#outercontainer div#footer div#footer_p a#doNotTrack";
	public static String RESKINNED_WIDGET_SCROLLBAR = "css=div#main div#mainPage.pageContainer div#all_chicklets div#sts_slider";
	public static String RESKINNED_WIDGET_TWITTER = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_twitter_link.twitter";
	public static String RESKINNED_WIDGET_SEARCH_TWITTER = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_twitter_link.twitter span";
	public static String RESKINNED_WIDGET_FACEBOOK = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_facebook_link.facebook";
	public static String RESKINNED_WIDGET_SEARCH_FACEBOOK = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_facebook_link.facebook span";
	public static String RESKINNED_WIDGET_LINKEDIN = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_linkedin_link.linkedin";
	public static String RESKINNED_WIDGET_SEARCH_LINKEDIN = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_linkedin_link.linkedin span";
	public static String RESKINNED_WIDGET_PINTEREST = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_pinterest_link.pinterest";
	public static String RESKINNED_WIDGET_SEARCH_PINTEREST = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_pinterest_link.pinterest span";
	public static String RESKINNED_WIDGET_STUMBLEUPON = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_stumbleupon_link.stumbleupon";
	public static String RESKINNED_WIDGET_SEARCH_STUMBLEUPON = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_stumbleupon_link.stumbleupon span";
	public static String RESKINNED_WIDGET_CLOSE_BUTTON = "css=div#stwrapper.stwrapper div.stclose";
	public static String RESKINNED_WIDGET_EMAIL_LABEL = "css=div#main span#popular.popular a#header_email";
	
	public static String RESKINNED_WIDGET_REGISTRATION_BUTTON = "css=div#site-wrapperNew div div.outer div.leftDiv div.registerContent a.registerClass";
	public static String RESKINNED_WIDGET_REGISTRATION_BUTTON2 = "css=div#site-wrapper div#bd-wrapper.w945 div.outer div.leftDiv div.registerContent a.registerClass";
	
	public static String RESKINNED_WIDGET_FACEBOOK_SERVICE = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_facebook_link.facebook";
	public static String RESKINNED_WIDGET_TWITTER_SERVICE = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_twitter_link.twitter";
	public static String RESKINNED_WIDGET_LINKEDIN_SERVICE = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_linkedin_link.linkedin";
	public static String RESKINNED_WIDGET_EMAIL_SERVICE = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_email_link.email";
	public static String RESKINNED_WIDGET_PINTEREST_SERVICE = "css=div#main div#mainPage.pageContainer div#all_chicklets ul#top_chicklets li a#post_pinterest_link.pinterest";
	
	//***********REGISTRATION PAGE***********	
	public static String REGISTRATION_EMAIL_LABEL = "css=div#register-tip form#register_form input#username-clear1.inputBox";
	public static String REGISTRATION_PASSWORD_LABEL = "css=div#register-tip form#register_form input#password-clear1.inputBox";
	public static String REGISTRATION_CONFIRM_PASSWORD_LABEL = "css=div#register-tip form#register_form input#confirm-password-clear.inputBox";
	public static String REGISTRATION_EMAIL = "css=div#register-tip form#register_form input#username1.inputBox";
	public static String REGISTRATION_PASSWORD = "css=div#register-tip form#register_form input#password1.inputBox";
	public static String REGISTRATION_CONFIRM_PASSWORD = "css=div#register-tip form#register_form input#confirm-password.inputBox";
	public static String REGISTRATION_ALREADY_HAVE_AN_ACCOUNT_LINK = "css=div#site-wrapper div#bd-wrapper.w945 div#leftBar.register div.registerContent";
	public static String REGISTRATION_REGISTERED_USER_SIGNIN_LINK = "css=div#site-wrapper div#bd-wrapper.w945 div#leftBar.register div.registerContent a.registerClass";
	public static String CREATEACCOUNT_BUTTON = "//*[@id='RegisterBtn']";
	public static String TERMS_CHECKBOX = "terms";
	public static String LEFT_PANEL = "css=div#site-wrapper div#bd-wrapper.w945 div#leftBar.register";
	public static String EMAIL_ERROR_MSG_OLD = "//*[@id='usermsg']";
	public static String EMAIL_ERROR_MSG ="//*[@id='usermsg1' and contains(@style,'visibility: visible')]";
	public static String PASSWORD_ERROR_MSG = "css=div#bd-wrapper.w945 div#leftBar.register div#register-tip form#register_form div#pwdmsg.errormsg";
	public static String CONFIRM_PASSWORD_ERROR_MSG = "css=div#bd-wrapper.w945 div#leftBar.register div#register-tip form#register_form div#confirmpwdmsg.errormsg";
	public static String TERMS_ERROR_MSG_OLD = "//*[@id='termsmsg']";
	public static String TERMS_ERROR_MSG="//*[@id='termsmsg' and contains(@style,'visibility: visible')]";
	public static String CREATE_ONE_TODAY_LINK1="css=div#site-wrapperNew div div.outer div.leftDiv div.registerContent a.registerClass";
	
	public static String ACCOUNT_DASHBOARD = "css=div#body.container_1c div#account_content.container_2cwl div#bd-wrapper.center div h2";
	public static String I_AM_PUBLISHER_CHECKBOX = "isPublisher";
	
	//*****************SIGN IN PAGE********************	
	public static String SIGNIN_EMAIL_LABEL = "css=div#site-wrapperNew div div.outer div.leftDiv form#signin_form input#username-clear.inputBox";
	public static String SIGNIN_PASSWORD_LABEL = "css=div#site-wrapperNew div.outer div.leftDiv form#signin_form input#password-clear.inputBox";
	public static String SIGNIN_EMAIL = "css=div#site-wrapperNew div div.outer div.leftDiv form#signin_form input#username.inputBox";
	public static String SIGNIN_PASSWORD = "css=div#site-wrapperNew div.outer div.leftDiv form#signin_form input#password.inputBox";
	public static String SIGNIN_EMAIL_ERRORMSG = "css=div#site-wrapperNew div.outer div.leftDiv form#signin_form div#usermsg.errormsg";
	public static String SIGNIN_PASSWORD_ERRORMSG = "css=div#site-wrapperNew div.outer div.leftDiv form#signin_form div#pwdmsg.errormsg";
	public static String SIGNIN_FORGOT_PASSWORD_LINK = "css=div#site-wrapperNew div.outer div.leftDiv div.forgotPasswordDiv a.forgotlinkClass";
	public static String SIGNIN_BUTTON = "css=div.ctasSignin a#SignInBtn";
	public static String SIGNIN_BUTTON2 = "css=div#site-wrapper div#bd-wrapper.w945 div.outer div.leftDiv div.ctasNew a#SignInBtn";
	public static String SIGNIN_FACEBOOK_ICON ="css=div#site-wrapperNew div.outer div.leftDiv div#login-accounts a#signin_btn_facebook img";
	public static String SIGNIN_LINKEDIN_ICON ="css=div#site-wrapperNew div.outer div.leftDiv div#login-accounts a#signin_btn_linkedin img";
	public static String SIGNIN_TWITTER_ICON ="css=div#site-wrapperNew div.outer div.leftDiv div#login-accounts a#signin_btn_twitter img";
	public static String SIGNIN_YAHOO_ICON ="css=div#site-wrapperNew div.outer div.leftDiv div#login-accounts a#signin_btn_yahoo img";
	public static String SIGNIN_GOOGLE_ICON ="css=div#site-wrapperNew div.outer div.leftDiv div#login-accounts a#signin_btn_google img";
	public static String SIGNIN_ERROR_PANEL ="css=div#dialog.dialogBox";
	public static String SIGNIN_ERROR_PANEL_OK_BUTTON ="css=div#dialog.dialogBox div#dialogConfirm";
	public static String SIGNIN_FORGOT_PASSWORD_LINK_SEND_BUTTON ="css=div#body.container_1c div#account_content.container_2cwl div#bd-wrapper.center div.container_2cwl div#signin_form div.containerDiv div.leftDiv div.ctasNew a#sendBtn";
	public static String SIGNIN_FORGOT_PASSWORD_LINK_CANCEL_BUTTON ="css=div#body.container_1c div#account_content.container_2cwl div#bd-wrapper.center div.container_2cwl div#signin_form div.containerDiv div.rightDiv a.cancelLink";
	public static String SIGNIN_FORGOT_PASSWORD_PAGE_HEADER ="css=div#body.container_1c div#account_content.container_2cwl div#bd-wrapper.center div.container_2cwl div#signin_form h2.content-heading";
	
	//*****************FACEBOOK SIGN IN PAGE********************
	public static String SIGNIN_FACEBOOK_USERNAME ="css=div#content.fb_content div.login_form_container form#login_form div#loginform div.form_row input#email.inputtext";
	public static String SIGNIN_FACEBOOK_PASSWORD ="css=div#loginform div.form_row input#pass.inputpassword";
	public static String SIGNIN_FACEBOOK_SIGNIN_BUTTON ="css=div#content.fb_content div.login_form_container form#login_form div#dialog_buttons.dialog_buttons label#loginbutton.uiButton";
	public static String SIGNIN_FACEBOOK_ERROR_PANEL ="css=div#content.fb_content div.login_form_container form#login_form div.api_login_form_in_popup div#error.UIMessageBox";
	
	public static String FACEBOOK_USERNAME ="css=div#content.fb_content div.login_form_container form#login_form div#loginform div.form_row input#email.inputtext";
	public static String FACEBOOK_PASSWORD ="css=div#content.fb_content div.login_form_container form#login_form div#loginform div.form_row input#pass.inputpassword";
	public static String FACEBOOK_LOGIN_BUTTON ="css=div#content.fb_content div.login_form_container form#login_form div#dialog_buttons.dialog_buttons label#loginbutton.uiButton";
	public static String FACEBOOK_CANCEL_BUTTON ="//input[@value='Cancel']";
	public static String FACEBOOK_MESSAGE_BOX ="message";
	public static String FACEBOOK_SHARING_TITLE ="css=div.UIShareStage_ShareContent div.UIShareStage_Title";
	public static String FACEBOOK_SHARING_LINK ="share";
	public static String FACEBOOK_POST_CONTENT_AREA ="css=div.-cx-PRIVATE-uiImageBlock__content a.pam div.attachmentText";
	public static String FACEBOOK_POST_SHARED_TITLE ="css=div.attachmentText div.uiAttachmentTitle strong";
	public static String FACEBOOK_POST_TITLE = "css=div.clearfix div._42ef div a._52c6";
	public static String FACEBOOK_HOME_LINK = "css=ul#pageNav.clearfix li#navHome.navItem a.navLink";
	
	//*****************GOOGLE SIGN IN PAGE********************
	public static String SIGNIN_GOOGLE_USERNAME = "css=div.main div.card form#gaia_loginform input#Email";
    public static String SIGNIN_GOOGLE_PASSWORD = "css=div.main div.card form#gaia_loginform input#Passwd";
    public static String SIGNIN_GOOGLE_SIGNIN_BUTTON = "css=div.main div.card form#gaia_loginform input#signIn.rc-button";
    public static String SIGNIN_GOOGLE_TO_FIELD = "//*[@id=':op']";
    public static String SIGNIN_GOOGLE_SUBJECT_FIELD = "//*[@id=':p3']";
    //public static String SIGNIN_GOOGLE_MESSAGE_BODY = "//*[@id=':nx']";
    //public static String SIGNIN_GOOGLE_MESSAGE_BODY = "//div[@id=':o2']/iframe";
    public static String SIGNIN_GOOGLE_MESSAGE_BODY =  "//td[2]/div[2]/div/iframe";
    public static String SIGNIN_GOOGLE_SEND_BUTTON = "//*[@id=':pd']";
        
  //*****************TWITTER SIGN IN PAGE********************
    public static String SIGNIN_TWITTER_USERNAME = "css=div#bd div.auth form#oauth_form fieldset.sign-in div.row input#username_or_email.text";
    public static String SIGNIN_TWITTER_PASSWORD = "css=div#bd div.auth form#oauth_form fieldset.sign-in div.row input#password.password";
    public static String SIGNIN_TWITTER_SIGNIN_BUTTON_OLD= "css=div#bd div.auth form#oauth_form fieldset.buttons input#allow.submit";
    public static String SIGNIN_TWITTER_SIGNIN_BUTTON= "//div[@class='auth']/form[@id='oauth_form']/fieldset[@class='buttons']/input[@id='allow']";
    public static String SIGNIN_TWITTER_ERROR_PANEL ="css=div#bd div.error";
    public static String SIGNIN_TWITTER_SIGNIN_BUTTON1="css=input#allow.submit";
    
    public static String TWITTER_USERNAME ="css=div.ft fieldset.sign-in div.row input#username_or_email.text";
	public static String TWITTER_PASSWORD ="css=div.ft fieldset.sign-in div.row input#password.password";
	public static String TWITTER_SIGN_AND_TWEET_BUTTON ="css=div.ft fieldset.submit input.button";
	public static String TWITTER_TWEET_MSG_BOX ="css=textarea#status";
	public static String TWITTER_USER_LOGIN_HEADER ="css=div#header div.bar div#session h2.current-user a";
	public static String TWITTER_POST_SHARED_TITLE ="css=div.tweet div.content p.js-tweet-text";
	public static String TWITTER_SHARED_URL="css=div.tweet div.content p.js-tweet-text a.twitter-timeline-link";
	public static String TWITTER_SIGNIN_USERNAME="css=div#front-container.front-container div.front-card div.front-signin form.signin div.username input#signin-email.text-input";
	public static String TWITTER_SIGNIN_PASSWORD="css=div.front-card div.front-signin form.signin table.flex-table tbody tr td.flex-table-primary div.password input#signin-password.text-input";
	public static String TWITTER_SIGNIN_SIGNIN_BUTTON="css=td.flex-table-secondary button.submit";
    
  //*****************LINKEDIN SIGN IN PAGE********************
    public static String SIGNIN_LINKEDIN_USERNAME = "css=div.container div#body div.wrapper form.grant-access div.login ul li.email-input div.fieldgroup input#session_key-oauthAuthorizeForm";
    public static String SIGNIN_LINKEDIN_PASSWORD = "css=div.container div#body div.wrapper form.grant-access div.login ul li.password-input div.fieldgroup input#session_password-oauthAuthorizeForm";
    public static String SIGNIN_LINKEDIN_SIGNIN_BUTTON= "css=div.container div#body div.wrapper form.grant-access div.actions ul li input.allow";
    public static String SIGNIN_LINKEDIN_ERROR_PANEL1 ="css=div#frame-contents div#body div.wrapper div#global-error div.alert";
    public static String SIGNIN_LINKEDIN_ERROR_PANEL2 ="css=div#frame-contents div#body div.wrapper div#global-error div#yui-gen1.alert";
    public static String SIGNIN_LINKEDIN_SIGNIN_BUTTON1= "css=css= input.btn-primary";    
    
    public static String LINKEDIN_USERNAME = "css=div#main.signin form#login.ajax-form ul li div.fieldgroup input#session_key-login";
    public static String LINKEDIN_PASSWORD = "css=div#main.signin form#login.ajax-form ul li div.fieldgroup input#session_password-login";
    public static String LINKEDIN_SIGNIN_BUTTON= "css=div#main.signin form#login.ajax-form ul li.button input#btn-primary.btn-primary";
    public static String LINKEDIN_MESSAGE_BOX ="postText";
	public static String LINKEDIN_SHARING_LINK ="submitShare";
	public static String LINKEDIN_SHARING_CANCEL_BUTTON ="css=div#share-offsite form#share-dialog-form.share-dialog p.actions a.btn-secondary";
	public static String LINKEDIN_SUCCESS_ALERT ="css=div#share-offsite div.share-confirm div.alert";
	public static String LINKEDIN_CLOSE_WINDOW_BUTTON ="css=body#pagekey-share_offsite.en div#footer p a";
	public static String LINKEDIN_POST_SHARED_TITLE ="css=div.feed-content div.share-object p.share-title a";
	public static String LINKEDIN_SIGNIN_USERNAME="css=div.login form#login.ajax-form fieldset ul#control_gen_1 li div.fieldgroup input#session_key-login";
	public static String LINKEDIN_SIGNIN_PASSWORD="css=div.login form#login.ajax-form fieldset ul#control_gen_1 li div.fieldgroup input#session_password-login";
	public static String LINKEDIN_SIGNIN_SIGNIN_BUTTON="css=input.btn-secondary";
	public static String LINKEDIN_HOME_LINK="css=ul.nav li#nav-primary-home.tab a.tab-name span";
	public static String LINKEDIN_LINKEDIN_HOME_LINK="css=div#header.global-nav div.bottom-nav div.wrapper ul.nav li#nav-primary-home.tab ul.menu li a";
    
  //*****************YAHOO SIGN IN PAGE********************
    public static String SIGNIN_YAHOO_USERNAME = "css=div#yreglg div.top div.lgbx fieldset#fsLogin.clear form#login_form div#inputs.yui-skin-sam input#username";
    public static String SIGNIN_YAHOO_PASSWORD = "css=div#yreglg div.top div.lgbx fieldset#fsLogin.clear form#login_form div#inputs.yui-skin-sam input#passwd";
    public static String SIGNIN_YAHOO_SIGNIN_BUTTON = "//*[@id='.save']";
    public static String SIGNIN_YAHOO_ERROR_PANEL1 = "css=div#yregwp div#yregct.yregclb div#yreglg div.top div.badge div.yregbxi fieldset#fsLogin div.yregertxt";
    public static String SIGNIN_YAHOO_ERROR_PANEL2 = "css=div#yregwp div#yregct.yregclb div#yreglg div.top div.badge div.yregbxi fieldset#fsLogin div.yregertxt strong";
    public static String SIGNIN_YAHOO_AGREE_BUTTON="css=form div#btns input#agree";
    
    public static String SIGNIN_YAHOO_TO_FIELD="//*[@id='to-field']";
    public static String SIGNIN_YAHOO_SUBJECT_FIELD="//*[@id='subject-field']";
    //public static String SIGNIN_YAHOO_MESSAGE_BODY="//html/body/p[2]";
    public static String SIGNIN_YAHOO_MESSAGE_BODY="//div[6]/div[3]/div[4]/div[2]/div[6]/div/table/tbody/tr[2]/td/div/div/div[2]";
    //public static String SIGNIN_YAHOO_SEND_BUTTON="//div[6]/div[2]/div[2]/div/span[2]/a[text()='Send']";
    public static String SIGNIN_YAHOO_SEND_BUTTON="//div[6]/div[3]/div[4]/div[2]/div[6]/div/table/tbody/tr[3]/td/div[2]/span[1]";
    
    public static String SIGNIN_YAHOO_USERNAME_NEW = "css=form#login_form div#inputs.yui-skin-sam input#username";
    public static String SIGNIN_YAHOO_PASSWORD_NEW = "css=form#login_form div#inputs.yui-skin-sam input#passwd";
    
    //***************WIDGET EMAIL PAGE***********************
    public static String EMAIL_HEADER = "css=div#email_main div#emailcontainer div#heading_div.heading";
    public static String EMAIL_TO_FIELD ="css=div#email_main div#emailcontainer div#to_div.field div.input_holder textarea#txtYourAddr.text";
    public static String EMAIL_FROM_FIELD ="css=div#email_main div#emailcontainer div#from_div.field div.input_holder input#txtFromAddr.text";
    public static String EMAIL_MESSAGE_BOX ="css=div#email_main div#emailcontainer div#message_div.field div.input_holder textarea#txtMessage";
    public static String EMAIL_CANCEL_BUTTON ="css=div#email_main div#emailcontainer div#buttons_div.field div#btnShareCancel.button_holder";
    public static String EMAIL_SEND_BUTTON ="css=div#email_main div#emailcontainer div#buttons_div.field div#btnShareSend.button_holder";
    public static String EMAIL_RECENTS_CONTACT ="css=div#email_main div#emailcontainer div#recents";
    public static String EMAIL_IMPORT_CONTACT ="css=div#email_main div#emailcontainer div#import_div";
    public static String EMAIL_ERROR_PANEL ="css=div#main div#modalPage.pageContainer div#modal_box div#modal_text";
    public static String EMAIL_ERROR_PANEL_OK_BUTTON ="css=div#modal_box div#modal_ok div#modal_button.button_holder";
    public static String EMAIL_SUCCESS_MSG ="css=div#donePage.pageContainer div#successBox.againContainer div#msg_share_success.success";
    public static String FASTSHARE_SUCCESS_MSG ="css=div#main div#donePage.pageContainer div#fsDoneBox.againContainer div#msg_fs_done.success h5";
    
    public static String EMAIL_CAPTCHA ="css=div#recaptcha_area table#recaptcha_table.recaptchatable tbody tr td.recaptcha_r4_c4";
    public static String EMAIL_CAPTCHA_TEXT ="css=div#recaptcha_area table#recaptcha_table.recaptchatable tbody tr td.recaptcha_image_cell div#recaptcha_image img";
    public static String EMAIL_CAPTCHA_INSERT_FIELD ="css=div#recaptcha_area table#recaptcha_table.recaptchatable tbody tr td.recaptcha_r4_c1 div.recaptcha_input_area input#recaptcha_response_field";
    public static String EMAIL_CAPTCHA_SUBMIT_BUTTON ="css=div#main div#captchaPage.pageContainer div#captchaSubmit.button_holder";
    public static String EMAIL_CAPTCHA_CANCEL_BUTTON ="css=div#main div#captchaPage.pageContainer div#captchaCancel.button_holder";
    
    //***************WIDGET DONE SCREEN****************************
    public static String DONESCREEN_ENABLE_FASTSHARE_BUTTON ="css=div#donePage.pageContainer div#successBox.againContainer div.center div#btnFastShare.button_holder";
    public static String DONESCREEN_SHARETHIS_PLUGIN_BUTTON ="css=div#done_screen div#doneScreenContent div#relatedShares div#btnShareToolbar.button_holder-fcLayout";
    public static String DONESCREEN_SHARE_AGAIN_BUTTON ="css=div#donePage.pageContainer div#successBox.againContainer div.center div#btnShareAgain.button_holder";
   
    public static String GET_FREE_TOOLBAR_BUTTON_FIREFOX ="css=div#n-button a#ffDownloadButton.publishers";
    public static String GET_FREE_TOOLBAR_BUTTON_IE ="css=div#toolbar-footer.ie-footer div a.buttonNewCTABig";
    public static String GET_FREE_TOOLBAR_BUTTON_CHROME ="//*[@id='toolbar-footer']/div[1]/a/div[text()='ShareThis Bookmarklet']";
    
  //***************WIDGET FAST SHARE****************************
    public static String FASTSHARE_CHECKMARK_FACEBOOK ="css=span[class*='st_facebook_'] span.stButton img";
    public static String FASTSHARE_CHECKMARK_TWITTER ="css=span[class*='st_twitter_'] span.stButton img";
    public static String FASTSHARE_CHECKMARK_LINKEDIN ="css=span[class*='st_linkedin_'] span.stButton img";
    public static String FASTSHARE_WINDOW_FOOTER ="css=div#fs_mainBody.fsContainer div.fsFooter";
    public static String FASTSHARE_WINDOW_HEADER ="css=div h2#fsHeading";
    public static String FASTSHARE_WINDOW_TEXTBOX ="css=div#fs_mainBody.fsContainer textarea#st_shareMessage";
    public static String FASTSHARE_WINDOW_SIGNOUT_LINK ="css=div#fs_mainBody.fsContainer div.fsFooter a#fsSignOut";
    public static String FASTSHARE_WINDOW_SHARE_BUTTON ="css=div#fs_mainBody.fsContainer div.fsFooter a#st_shareButton.ctaButton";
    public static String FASTSHARE_WINDOW_CLOSE_BUTTON ="css=div#stOCWrapper.fsWrapper div div#stOCClose.close";
    public static String FASTSHARE_DONE_SCREEN ="css=div#fs_doneScreen p#fs_doneMsg";
    
    //*************OAUTH WIDGET******************************
    public static String OAUTH_WIDGET = "css=span[class*='st_sharethis'] span.stButton";
    public static String OAUTH_EMAIL_WIDGET = "css=span.st_email_large span.stButton span.stLarge";
    public static String OAUTH_WIDGET_WINDOW = "css=div#outerContainer>h2#welcomeMsg";
    public static String OAUTH_WIDGET_SIGNIN_LINK = "css=div#footer.sts-cb span#privacyLinks.sts-fr a#signIn";
    public static String OAUTH_WIDGET_MESSAGE = "css=div#mainBody div#preShareScreen div#extraInfo";
    public static String OAUTH_WIDGET_EMAIL_LABEL = "css=div#outerContainer>h2#welcomeMsg";
    public static String OAUTH_WIDGET_EMAIL_USERNAME = "css=h2#welcomeMsg p#welcomeName";
    public static String OAUTH_WIDGET_CLOSE_BUTTON ="css=div#stwrapper.stwrapper div.stCloseNew2";
    public static String OAUTH_WIDGET_SIGNOUT_LINK = "css=div#footer.sts-cb span#privacyLinks.sts-fr a#signOut";
    public static String OAUTH_WIDGET_SERVICES_IN_FOOTER="css=div#outerContainer div#serviceCTAs.sts-cb div#services:not(.sts-dn)";
    
    public static String OAUTH_WIDGET_EMAIL_BIGICON = "css=div#services a:nth-child(1) span.bigIcon";
    public static String OAUTH_WIDGET_FB_BIGICON = "css=div#services a:nth-child(2) span.bigIcon";
    public static String OAUTH_WIDGET_TWITTER_BIGICON ="css=div#services a:nth-child(3) span.bigIcon";
    public static String OAUTH_WIDGET_LINKEDIN_BIGICON ="css=div#services a:nth-child(4) span.bigIcon"; 
    public static String OAUTH_WIDGET_MORE_LINK ="css= div#serviceCTAs.sts-cb div#services a#moreLink.widgetIcons";
    public static String OAUTH_WIDGET_MESSAGE_BOX="css=div#outerContainer div#mainBody div#preShareScreen form#shareDetails textarea#shareMessage";
    
    public static String OAUTH_WIDGET_TITLE="css=div#mainBody div#preShareScreen div#articleDetails div.sts-fr h3#headline";
    public static String OAUTH_WIDGET_SNIPPET="css=div#preShareScreen div#articleDetails div.sts-fr p#snippet";
    public static String OAUTH_WIDGET_URL="css=div#preShareScreen div#articleDetails div.sts-fr a#url";
    public static String OAUTH_WIDGET_IMAGE_LINK="css=div#preShareScreen div#articleDetails a.imgLink";
    public static String OAUTH_WIDGET_OUTER_FRAME = "css=div#outerContainer";
    public static String OAUTH_WIDGET_OVERLAY="//*[@id='stOverlay' and contains(@style,'block')]";
    
    public static String OAUTH_WIDGET_MORE_SERVICES_FRAME="css=div#outerContainer div#chicklets:not(.sts-dn)";
    public static String OAUTH_WIDGET_BACK_TO_DEFAULT_VIEW_LINK="css=div#outerContainer h2#moreTitle a#lessLink.sts-fr";
    public static String OAUTH_DO_NOT_TRACK_LINK ="css=div#footer.sts-cb span#privacyLinks.sts-fr a:nth-child(3)";
    public static String OAUTH_POWERED_BY_SHARETHIS_LINK = "css=div#footer.sts-cb span#creditLine.sts-fl a#stLabel.widgetIcons";
    
    public static String OAUth_WIDGET_SIGN_IN_LINK="css=div#footer.sts-cb span#privacyLinks.sts-fr a#signIn";
    public static String OAUTH_WIDGET_EMAIL_GREEN_CHECKED_BUTTON="css=div#serviceCTAs.sts-cb>div#services>a.serviceDisplay.checked:nth-child(1)";
    public static String OAUTH_WIDGET_FACEBOOK_GREEN_CHECKED_BUTTON="css=div#serviceCTAs.sts-cb>div#services>a.serviceDisplay.checked:nth-child(2)";
    public static String OAUTH_WIDGET_TWITTER_GREEN_CHECKED_BUTTON="css=div#serviceCTAs.sts-cb>div#services>a.serviceDisplay.checked:nth-child(3)";
    public static String OAUTH_WIDGET_LINKEDIN_GREEN_CHECKED_BUTTON="css=div#serviceCTAs.sts-cb>div#services>a.serviceDisplay.checked:nth-child(4)";
    public static String OAUTH_WIDGET_NOT_YOU_LINK="css=div#outerContainer h2#welcomeMsg a#signOutTop";
    public static String OAUTH_WIDGET_POST_TO_FRIENDS_WALL="css=div#mainBody div#preShareScreen div#extraInfo span#friendsWall.sts-fr a#postFriendsLink";
    public static String OAUTH_WIDGET_FIRST_RECENT_CONTACT="css=div#mainBody div#preShareScreen form#shareDetails div#emailShareDetails div#recents span#recents_list a.recent_item:nth-child(1)";
    public static String OAUTH_WIDGET_SECOND_RECENT_CONTACT="css=div#mainBody div#preShareScreen form#shareDetails div#emailShareDetails div#recents span#recents_list a.recent_item:nth-child(2)";
    public static String OAUTH_WIDGET_THIRD_RECENT_CONTACT="css=div#mainBody div#preShareScreen form#shareDetails div#emailShareDetails div#recents span#recents_list a.recent_item:nth-child(3)";
    public static String OAUTH_WIDGET_SIGN_OUT_LINK="css=div#footer.sts-cb span#privacyLinks.sts-fr a#signOut:not(.sts-dn)";
    
    public static String COMMON_SIGN_OUT_LINK="css=a#signOut:not(.sts-dn)";
    public static String OAUTH_WIDGET_SHARE_BUTTON="css=a#shareButton.ctaButton";
    public static String OAUTH_WIDGET_DONE_SCREEN ="css=div#outerContainer div#mainBody div#doneScreen:not(.sts-dn)";
    public static String OAUTH_WIDGET_DONE_SCREEN_MESSAGE="css=div#doneScreen:not(.sts-dn) p#successMsg span#sharedMsg";
    public static String OAUTH_WIDGET_WORDPRESS_POST_LINK="css=div#outerContainer div#chicklets a#post_wordpress_link.wordpress";
    public static String OAUTH_WIDGET_FIRST_POST_LINK="css=div#chicklets a:nth-child(3)";
    public static String OAUTH_WIDGET_WORDPRESS_CANCEL_LINK ="css=div#outerContainer div#serviceCTAs.sts-cb a#cancelLink.sts-fr:not(.sts-dn)";
    public static String OAUTH_WIDGET_NON_TEXT_AREA="css=div#outerContainer div#serviceCTAs.sts-cb";
    public static String OAUTH_WIDGET_WIDGGET_ERROR_MESSAGE="css=div#outerContainer div#mainBody div#errorMsg:not(.sts-dn)";
    public static String OAUTH_WIDGET_SHARE_AGAIN_LINK="css=p#successMsg a#againLink.widgetIcons";
    public static String OAUTH_WIDGET_GET_SHARETHIS_CTA_BUTTON="css=div#toolbarUpsell div#btnShareToolbar.ctaButton";
    public static String OAUTH_WIDGET_TOOLBAR_UPSELL_IMAGE="css= div#toolbarUpsell span.toolbarImg";
    public static String OAUTH_WIDGET_TOOLBAR_UPSELL_DIV="css=div#doneScreen:not(.sts-dn) div#relatedShares div#toolbarUpsell";
    public static String OAUTH_WIDGET_SHARED_TO_TEXT="css=p#successMsg span#sharedServices";
    public static String OAUTH_WIDGET_EMAIL_SHARE_DETAILS_SECTION="css=form#shareDetails div#emailShareDetails:not(.sts-dn)";

    /* Email page of OAuth Widget */
    public static String OAUTH_WIDGET_EMAIL_TO_FIELD="css=form#shareDetails div#emailShareDetails:not(.sts-dn) div#toField textarea#txtYourAddr.text";
    public static String OAUTH_WIDGET_EMAIL_FROM_FIELD="css=div#emailShareDetails:not(.sts-dn) div#fromField input#txtFromAddr.text";
    public static String OAUTH_WIDGET_EMAIL_MESSAGE_FIELD="css=form#shareDetails:not(sts-dn) textarea#shareMessage";
    public static String OAUTH_WIDGET_EMAIL_ERROR_MESSAGE="css=div#outerContainer div#mainBody div#errorMsg";
    public static String OAUTH_WIDGET_RECENT_EMAIL="css=form#shareDetails div#emailShareDetails div#recents:not(.sts-dn) span#recents_list";
    public static String OAUTH_WIDGET_RECENT_EMAIL_ALL="css=form#shareDetails div#emailShareDetails div#recents span#recents_list a.recent_item";
    public static String OAUTH_WIDGET_RECENT_EMAIL_PART1="css=form#shareDetails div#emailShareDetails div#recents span#recents_list a.recent_item:nth-child(";
    public static String OAUTH_WIDGET_RECENT_EMAIL_PART2=")";
    public static String OAUTH_WIDGET_RECENT_EMAIL1="css=div#mainBody div#preShareScreen form#shareDetails div#emailShareDetails div#recents span#recents_list a.recent_item:nth-child(1)";
    public static String OAUTH_WIDGET_CAPTCHA_SCREEN="css=div#preShareScreen form#shareDetails div#emailShareDetails div#captcha:not(.sts-dn)";
    
    //*************NEW EMAIL UI******************************
    public static String EXTERNAL_EMAIL_GMAIL = "css=div#emailAuthentication.hideNewEmail div.leftSection div.externalEmailDiv a.externalEmailService span.gmail";
    public static String EXTERNAL_EMAIL_YAHOO = "css=div#emailAuthentication.hideNewEmail div.leftSection div.externalEmailDiv a.externalEmailService span.yahoo";
    public static String EXTERNAL_EMAIL_OUTLOOK = "css=div#emailAuthentication.hideNewEmail div.leftSection div.externalEmailDiv a.externalEmailService span.outlook";
    public static String EXTERNAL_EMAIL_NATIVE_EMAIL = "css=div#emailAuthentication.hideNewEmail div.leftSection div.externalEmailDiv a.externalEmailService span.mailto";
    public static String EMAIL_FACEBOOK_OAUTH = "css=div#emailAuthentication.hideNewEmail div.rightSection div#login-accounts a.oAuthService span.oFacebook";
    public static String EMAIL_LINKEDIN_OAUTH = "css=div#emailAuthentication.hideNewEmail div.rightSection div#login-accounts a.oAuthService span.oLinkedin";
    public static String EMAIL_TWITTER_OAUTH = "css=div#emailAuthentication.hideNewEmail div.rightSection div#login-accounts a.oAuthService span.oTwitter";
    public static String EMAIL_YAHOO_OAUTH = "css=div#emailAuthentication.hideNewEmail div.rightSection div#login-accounts a.oAuthService span.oYahoo";
    public static String EMAIL_GOOGLE_OAUTH = "css=div#emailAuthentication.hideNewEmail div.rightSection div#login-accounts a.oAuthService span.oGoogle";
    public static String EMAIL_GO_BACK_LINK = "css=a#goBackLink.goBackLink";
    public static String EMAIL_PAGE_INFO_LINK = "css=div#emailshareHeading.hideNewEmail h2.stBold a span._";
    
  //*************OUTLOOK SIGNIN PAGE******************************
    public static String SIGNIN_OUTLOOK_USERNAME = "css=div#mainTD.center div#signInTD.floatLeft div.floatLeft div#rightTD form div.section div#idDiv_PWD_UsernameTb.row div input#i0116.ltr_override";
    public static String SIGNIN_OUTLOOK_PASSWORD = "css=div#mainTD.center div#signInTD.floatLeft div.floatLeft div#rightTD form div.section div#idDiv_PWD_PasswordTb.row div input#i0118";
    public static String SIGNIN_OUTLOOK_SIGNIN_BUTTON = "css=div#signInTD.floatLeft div.floatLeft div#rightTD form div#idTd_PWD_SubmitCancelTbl.section input#idSIButton9.default";
    
    //public static String SIGNIN_OUTLOOK_TO_FIELD = "css=div#ComposeContentWrapper.sc div div#ComposeArea div#composeHeader.ComposeHeader div.Row div#toCP.t_cpv2 div.cp_inputContainer div.cp_inputArea span.cp_awe input.cp_primaryInput";
    public static String SIGNIN_OUTLOOK_TO_FIELD = "css=div.cp_inputContainer div.cp_inputArea.t_srbgc.t_sr_bordc.Focus span.cp_awe.cp_input textarea.cp_primaryInput.cp_anyInput.t_urtc";
    //public static String SIGNIN_OUTLOOK_SUBJECT_FIELD = "css=div#ComposeContentWrapper.sc div div#ComposeArea div#ComposeContent div#ComposeContentHeader div.Subject div.SubjectArea input#fSubject.t_subj";
    public static String SIGNIN_OUTLOOK_SUBJECT_FIELD = "css=div.Subject div.SubjectArea.TextLightI input#fSubject.t_subj.TextLightI.WatermarkedInput";
    //public static String SIGNIN_OUTLOOK_MESSAGE_BODY = "css=div#ComposeContentWrapper.sc div div#ComposeArea div#ComposeContent div#composeMessage.ComposeMessage div#ComposeRteEditor.RteEditor";
    public static String SIGNIN_OUTLOOK_MESSAGE_BODY = "css=div#ComposeArea div#ComposeContent div#composeMessage.ComposeMessage.slideOnResize.carouselSmooth div#ComposeRteEditor.RteEditor";
    //public static String SIGNIN_OUTLOOK_SEND_BUTTON = "css=div#HeaderContainer div#c_header.c_hb div#c_cb0.c_c ul.c_cc li a#SendMessage";
    public static String SIGNIN_OUTLOOK_SEND_BUTTON = "css=div.AppContainer div#m_wh div#c_header.c_hb.c_hncb div#c_cb0.c_c.t_hdbg.c_cd.c_c8.c_clt ul.c_cc li a#SendMessage";
    

}

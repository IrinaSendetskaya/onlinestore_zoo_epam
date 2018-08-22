
(function() {
	function async_load(u,id) {
		if (!gid(id)) {
			s="script", d=document,
			o = d.createElement(s);
			o.type = 'text/javascript';
                        o.id = id;
			o.async = true;
			o.src = u;
			// Creating scripts on page
			x = d.getElementsByTagName(s)[0];
			x.parentNode.insertBefore(o,x);
		}
	}
	function gid (id){
		return document.getElementById(id);
	}
	window.onload = function() {
	
	e = gid("s-twitter");
	e.setAttribute("data-lang", "ru"); 
	
	e = gid("s-facebook");
	e.setAttribute("data-layout", "button_count"); 
	e.setAttribute("data-send", "false"); 
	
	e = gid("s-google");
	e.setAttribute("data-size", "medium"); 
	
	
	async_load("//platform.twitter.com/widgets.js", "id-twitter");//twitter
	async_load("//connect.facebook.net/ru_RU/all.js#xfbml=1", "id-facebook");//facebook
	async_load("https://apis.google.com/js/plusone.js", "id-google");//google
	async_load("//vk.com/js/api/openapi.js", "id-vkontakte");//vkontakte
	};
	// Инициализация vkontakte
	window.vkAsyncInit = function(){
		VK.init({apiId: 3363525, onlyWidgets: true});
		VK.Widgets.Like("vk_like", {type: "button", height: 20});
	};
})();
/*! modernizr 3.2.0 (Custom Build) | MIT *
 * http://modernizr.com/download/?-adownload-ambientlight-applicationcache-atobbtoa-audio-backgroundsize-batteryapi-bgpositionshorthand-bgrepeatspace_bgrepeatround-bgsizecover-bloburls-borderimage-borderradius-boxshadow-canvas-canvastext-contenteditable-contextmenu-cookies-cors-cryptography-csstransforms3d-customevent-customprotocolhandler-dataview-emoji-eventlistener-eventsource-fileinput-filereader-flash-formattribute-formvalidation-fullscreen-gamepads-geolocation-getusermedia-hashchange-hiddenscroll-history-htmlimports-jpeg2000-jpegxr-json-localizednumber-lowbattery-matchmedia-proximity-requestautocomplete-sessionstorage-shapes-sizes-smil-speechsynthesis-srcset-subpixelfont-svgforeignobject-textshadow-urlparser-vibrate-videoautoplay-videopreload-webaudio-webgl-websocketsbinary-websqldatabase-webworkers-xhrresponsetype-xhrresponsetypearraybuffer-xhrresponsetypeblob-xhrresponsetypedocument-xhrresponsetypejson-xhrresponsetypetext-setclasses !*/
!function(e,A,t){function n(e,A){return typeof e===A}function o(){var e,A,t,o,i,r,a;for(var s in y)if(y.hasOwnProperty(s)){if(e=[],A=y[s],A.name&&(e.push(A.name.toLowerCase()),A.options&&A.options.aliases&&A.options.aliases.length))for(t=0;t<A.options.aliases.length;t++)e.push(A.options.aliases[t].toLowerCase());for(o=n(A.fn,"function")?A.fn():A.fn,i=0;i<e.length;i++)r=e[i],a=r.split("."),1===a.length?Modernizr[a[0]]=o:(!Modernizr[a[0]]||Modernizr[a[0]]instanceof Boolean||(Modernizr[a[0]]=new Boolean(Modernizr[a[0]])),Modernizr[a[0]][a[1]]=o),T.push((o?"":"no-")+a.join("-"))}}function i(e){var A=b.className,t=Modernizr._config.classPrefix||"";if(E&&(A=A.baseVal),Modernizr._config.enableJSClass){var n=new RegExp("(^|\\s)"+t+"no-js(\\s|$)");A=A.replace(n,"$1"+t+"js$2")}Modernizr._config.enableClasses&&(A+=" "+t+e.join(" "+t),E?b.className.baseVal=A:b.className=A)}function r(){return"function"!=typeof A.createElement?A.createElement(arguments[0]):E?A.createElementNS.call(A,"http://www.w3.org/2000/svg",arguments[0]):A.createElement.apply(A,arguments)}function a(){var e=A.body;return e||(e=r(E?"svg":"body"),e.fake=!0),e}function s(e){return e.replace(/([a-z])-([a-z])/g,function(e,A,t){return A+t.toUpperCase()}).replace(/^-/,"")}function d(e,A){if("object"==typeof e)for(var t in e)F(e,t)&&d(t,e[t]);else{e=e.toLowerCase();var n=e.split("."),o=Modernizr[n[0]];if(2==n.length&&(o=o[n[1]]),"undefined"!=typeof o)return Modernizr;A="function"==typeof A?A():A,1==n.length?Modernizr[n[0]]=A:(!Modernizr[n[0]]||Modernizr[n[0]]instanceof Boolean||(Modernizr[n[0]]=new Boolean(Modernizr[n[0]])),Modernizr[n[0]][n[1]]=A),i([(A&&0!=A?"":"no-")+n.join("-")]),Modernizr._trigger(e,A)}return Modernizr}function c(e,t,n,o){var i,s,d,c,l="modernizr",u=r("div"),p=a();if(parseInt(n,10))for(;n--;)d=r("div"),d.id=o?o[n]:l+(n+1),u.appendChild(d);return i=r("style"),i.type="text/css",i.id="s"+l,(p.fake?p:u).appendChild(i),p.appendChild(u),i.styleSheet?i.styleSheet.cssText=e:i.appendChild(A.createTextNode(e)),u.id=l,p.fake&&(p.style.background="",p.style.overflow="hidden",c=b.style.overflow,b.style.overflow="hidden",b.appendChild(p)),s=t(u,e),p.fake?(p.parentNode.removeChild(p),b.style.overflow=c,b.offsetHeight):u.parentNode.removeChild(u),!!s}function l(e,A){return!!~(""+e).indexOf(A)}function u(e,A){return function(){return e.apply(A,arguments)}}function p(e,A,t){var o;for(var i in e)if(e[i]in A)return t===!1?e[i]:(o=A[e[i]],n(o,"function")?u(o,t||A):o);return!1}function f(e){return e.replace(/([A-Z])/g,function(e,A){return"-"+A.toLowerCase()}).replace(/^ms-/,"-ms-")}function m(A,n){var o=A.length;if("CSS"in e&&"supports"in e.CSS){for(;o--;)if(e.CSS.supports(f(A[o]),n))return!0;return!1}if("CSSSupportsRule"in e){for(var i=[];o--;)i.push("("+f(A[o])+":"+n+")");return i=i.join(" or "),c("@supports ("+i+") { #modernizr { position: absolute; } }",function(e){return"absolute"==getComputedStyle(e,null).position})}return t}function h(e,A,o,i){function a(){c&&(delete I.style,delete I.modElem)}if(i=n(i,"undefined")?!1:i,!n(o,"undefined")){var d=m(e,o);if(!n(d,"undefined"))return d}for(var c,u,p,f,h,v=["modernizr","tspan"];!I.style;)c=!0,I.modElem=r(v.shift()),I.style=I.modElem.style;for(p=e.length,u=0;p>u;u++)if(f=e[u],h=I.style[f],l(f,"-")&&(f=s(f)),I.style[f]!==t){if(i||n(o,"undefined"))return a(),"pfx"==A?f:!0;try{I.style[f]=o}catch(g){}if(I.style[f]!=h)return a(),"pfx"==A?f:!0}return a(),!1}function v(e,A,t,o,i){var r=e.charAt(0).toUpperCase()+e.slice(1),a=(e+" "+V.join(r+" ")+r).split(" ");return n(A,"string")||n(A,"undefined")?h(a,A,o,i):(a=(e+" "+N.join(r+" ")+r).split(" "),p(a,A,t))}function g(e,A,n){return v(e,t,t,A,n)}var T=[],y=[],w={_version:"3.2.0",_config:{classPrefix:"",enableClasses:!0,enableJSClass:!0,usePrefixes:!0},_q:[],on:function(e,A){var t=this;setTimeout(function(){A(t[e])},0)},addTest:function(e,A,t){y.push({name:e,fn:A,options:t})},addAsyncTest:function(e){y.push({name:null,fn:e})}},Modernizr=function(){};Modernizr.prototype=w,Modernizr=new Modernizr,Modernizr.addTest("applicationcache","applicationCache"in e),Modernizr.addTest("cookies",function(){try{A.cookie="cookietest=1";var e=-1!=A.cookie.indexOf("cookietest=");return A.cookie="cookietest=1; expires=Thu, 01-Jan-1970 00:00:01 GMT",e}catch(t){return!1}}),Modernizr.addTest("cors","XMLHttpRequest"in e&&"withCredentials"in new XMLHttpRequest),Modernizr.addTest("customprotocolhandler",function(){if(!navigator.registerProtocolHandler)return!1;try{navigator.registerProtocolHandler("thisShouldFail")}catch(e){return e instanceof TypeError}return!1}),Modernizr.addTest("customevent","CustomEvent"in e&&"function"==typeof e.CustomEvent),Modernizr.addTest("dataview","undefined"!=typeof DataView&&"getFloat64"in DataView.prototype),Modernizr.addTest("eventlistener","addEventListener"in e),Modernizr.addTest("geolocation","geolocation"in navigator),Modernizr.addTest("history",function(){var A=navigator.userAgent;return-1===A.indexOf("Android 2.")&&-1===A.indexOf("Android 4.0")||-1===A.indexOf("Mobile Safari")||-1!==A.indexOf("Chrome")||-1!==A.indexOf("Windows Phone")?e.history&&"pushState"in e.history:!1}),Modernizr.addTest("webaudio",function(){var A="webkitAudioContext"in e,t="AudioContext"in e;return Modernizr._config.usePrefixes?A||t:t}),Modernizr.addTest("filereader",!!(e.File&&e.FileList&&e.FileReader)),Modernizr.addTest("eventsource","EventSource"in e),Modernizr.addTest("xhrresponsetype",function(){if("undefined"==typeof XMLHttpRequest)return!1;var e=new XMLHttpRequest;return e.open("get","/",!0),"response"in e}()),Modernizr.addTest("speechsynthesis","SpeechSynthesisUtterance"in e),Modernizr.addTest("sessionstorage",function(){var e="modernizr";try{return sessionStorage.setItem(e,e),sessionStorage.removeItem(e),!0}catch(A){return!1}}),Modernizr.addTest("websqldatabase","openDatabase"in e),Modernizr.addTest("urlparser",function(){var e;try{return e=new URL("http://modernizr.com/"),"http://modernizr.com/"===e.href}catch(A){return!1}}),Modernizr.addTest("websocketsbinary",function(){var A,t="https:"==location.protocol?"wss":"ws";if("WebSocket"in e){if(A="binaryType"in WebSocket.prototype)return A;try{return!!new WebSocket(t+"://.").binaryType}catch(n){}}return!1}),Modernizr.addTest("atobbtoa","atob"in e&&"btoa"in e,{aliases:["atob-btoa"]}),Modernizr.addTest("webworkers","Worker"in e);var b=A.documentElement;Modernizr.addTest("contextmenu","contextMenu"in b&&"HTMLMenuItemElement"in e);var E="svg"===b.nodeName.toLowerCase();Modernizr.addTest("audio",function(){var e=r("audio"),A=!1;try{(A=!!e.canPlayType)&&(A=new Boolean(A),A.ogg=e.canPlayType('audio/ogg; codecs="vorbis"').replace(/^no$/,""),A.mp3=e.canPlayType('audio/mpeg; codecs="mp3"').replace(/^no$/,""),A.opus=e.canPlayType('audio/ogg; codecs="opus"').replace(/^no$/,""),A.wav=e.canPlayType('audio/wav; codecs="1"').replace(/^no$/,""),A.m4a=(e.canPlayType("audio/x-m4a;")||e.canPlayType("audio/aac;")).replace(/^no$/,""))}catch(t){}return A}),Modernizr.addTest("canvas",function(){var e=r("canvas");return!(!e.getContext||!e.getContext("2d"))}),Modernizr.addTest("canvastext",function(){return Modernizr.canvas===!1?!1:"function"==typeof r("canvas").getContext("2d").fillText}),Modernizr.addTest("contenteditable",function(){if("contentEditable"in b){var e=r("div");return e.contentEditable=!0,"true"===e.contentEditable}}),Modernizr.addTest("emoji",function(){if(!Modernizr.canvastext)return!1;var A=e.devicePixelRatio||1,t=12*A,n=r("canvas"),o=n.getContext("2d");return o.fillStyle="#f00",o.textBaseline="top",o.font="32px Arial",o.fillText("🐨",0,0),0!==o.getImageData(t,t,1,1).data[0]}),Modernizr.addTest("webgl",function(){var A=r("canvas"),t="probablySupportsContext"in A?"probablySupportsContext":"supportsContext";return t in A?A[t]("webgl")||A[t]("experimental-webgl"):"WebGLRenderingContext"in e}),Modernizr.addTest("adownload",!e.externalHost&&"download"in r("a")),Modernizr.addTest("bgpositionshorthand",function(){var e=r("a"),A=e.style,t="right 10px bottom 10px";return A.cssText="background-position: "+t+";",A.backgroundPosition===t}),Modernizr.addTest("fileinput",function(){if(navigator.userAgent.match(/(Android (1.0|1.1|1.5|1.6|2.0|2.1))|(Windows Phone (OS 7|8.0))|(XBLWP)|(ZuneWP)|(w(eb)?OSBrowser)|(webOS)|(Kindle\/(1.0|2.0|2.5|3.0))/))return!1;var e=r("input");return e.type="file",!e.disabled}),Modernizr.addTest("formattribute",function(){var e,t=r("form"),n=r("input"),o=r("div"),i="formtest"+(new Date).getTime(),a=!1;t.id=i;try{n.setAttribute("form",i)}catch(s){A.createAttribute&&(e=A.createAttribute("form"),e.nodeValue=i,n.setAttributeNode(e))}return o.appendChild(t),o.appendChild(n),b.appendChild(o),a=t.elements&&1===t.elements.length&&n.form==t,o.parentNode.removeChild(o),a}),Modernizr.addTest("srcset","srcset"in r("img")),Modernizr.addTest("videopreload","preload"in r("video"));var x=function(){function e(e,A){var o;return e?(A&&"string"!=typeof A||(A=r(A||"div")),e="on"+e,o=e in A,!o&&n&&(A.setAttribute||(A=r("div")),A.setAttribute(e,""),o="function"==typeof A[e],A[e]!==t&&(A[e]=t),A.removeAttribute(e)),o):!1}var n=!("onblur"in A.documentElement);return e}();w.hasEvent=x,Modernizr.addTest("ambientlight",x("devicelight",e)),Modernizr.addTest("hashchange",function(){return x("hashchange",e)===!1?!1:A.documentMode===t||A.documentMode>7}),Modernizr.addTest("json","JSON"in e&&"parse"in JSON&&"stringify"in JSON);var C="CSS"in e&&"supports"in e.CSS,B="supportsCSS"in e;Modernizr.addTest("supports",C||B);var R=function(e){if("undefined"==typeof XMLHttpRequest)return!1;var A=new XMLHttpRequest;A.open("get","/",!0);try{A.responseType=e}catch(t){return!1}return"response"in A&&A.responseType==e};Modernizr.addTest("xhrresponsetypearraybuffer",R("arraybuffer")),Modernizr.addTest("xhrresponsetypeblob",R("blob")),Modernizr.addTest("xhrresponsetypedocument",R("document")),Modernizr.addTest("xhrresponsetypejson",R("json")),Modernizr.addTest("xhrresponsetypetext",R("text"));var k={}.toString;Modernizr.addTest("svgforeignobject",function(){return!!A.createElementNS&&/SVGForeignObject/.test(k.call(A.createElementNS("http://www.w3.org/2000/svg","foreignObject")))}),Modernizr.addTest("smil",function(){return!!A.createElementNS&&/SVGAnimate/.test(k.call(A.createElementNS("http://www.w3.org/2000/svg","animate")))}),Modernizr.addTest("video",function(){var e=r("video"),A=!1;try{(A=!!e.canPlayType)&&(A=new Boolean(A),A.ogg=e.canPlayType('video/ogg; codecs="theora"').replace(/^no$/,""),A.h264=e.canPlayType('video/mp4; codecs="avc1.42E01E"').replace(/^no$/,""),A.webm=e.canPlayType('video/webm; codecs="vp8, vorbis"').replace(/^no$/,""),A.vp9=e.canPlayType('video/webm; codecs="vp9"').replace(/^no$/,""),A.hls=e.canPlayType('application/x-mpegURL; codecs="avc1.42E01E"').replace(/^no$/,""))}catch(t){}return A});var F;!function(){var e={}.hasOwnProperty;F=n(e,"undefined")||n(e.call,"undefined")?function(e,A){return A in e&&n(e.constructor.prototype[A],"undefined")}:function(A,t){return e.call(A,t)}}(),w._l={},w.on=function(e,A){this._l[e]||(this._l[e]=[]),this._l[e].push(A),Modernizr.hasOwnProperty(e)&&setTimeout(function(){Modernizr._trigger(e,Modernizr[e])},0)},w._trigger=function(e,A){if(this._l[e]){var t=this._l[e];setTimeout(function(){var e,n;for(e=0;e<t.length;e++)(n=t[e])(A)},0),delete this._l[e]}},Modernizr._q.push(function(){w.addTest=d}),Modernizr.addAsyncTest(function(){var t,n,o=function(e){b.contains(e)||b.appendChild(e)},i=function(e){e.fake&&e.parentNode&&e.parentNode.removeChild(e)},s=function(e,A){var t=!!e;if(t&&(t=new Boolean(t),t.blocked="blocked"===e),d("flash",function(){return t}),A&&f.contains(A)){for(;A.parentNode!==f;)A=A.parentNode;f.removeChild(A)}};try{n="ActiveXObject"in e&&"Pan"in new e.ActiveXObject("ShockwaveFlash.ShockwaveFlash")}catch(c){}if(t=!("plugins"in navigator&&"Shockwave Flash"in navigator.plugins||n),t||E)s(!1);else{var l,u,p=r("embed"),f=a();if(p.type="application/x-shockwave-flash",f.appendChild(p),!("Pan"in p||n))return o(f),s("blocked",p),void i(f);l=function(){return o(f),b.contains(f)?(b.contains(p)?(u=p.style.cssText,""!==u?s("blocked",p):s(!0,p)):s("blocked"),void i(f)):(f=A.body||f,p=r("embed"),p.type="application/x-shockwave-flash",f.appendChild(p),setTimeout(l,1e3))},setTimeout(l,10)}}),d("htmlimports","import"in r("link")),Modernizr.addAsyncTest(function(){function A(){clearTimeout(t),e.removeEventListener("deviceproximity",A),d("proximity",!0)}var t,n=300;"ondeviceproximity"in e&&"onuserproximity"in e?(e.addEventListener("deviceproximity",A),t=setTimeout(function(){e.removeEventListener("deviceproximity",A),d("proximity",!1)},n)):d("proximity",!1)}),Modernizr.addAsyncTest(function(){var e=new Image;e.onload=e.onerror=function(){d("jpeg2000",1==e.width)},e.src="data:image/jp2;base64,/0//UQAyAAAAAAABAAAAAgAAAAAAAAAAAAAABAAAAAQAAAAAAAAAAAAEBwEBBwEBBwEBBwEB/1IADAAAAAEAAAQEAAH/XAAEQED/ZAAlAAFDcmVhdGVkIGJ5IE9wZW5KUEVHIHZlcnNpb24gMi4wLjD/kAAKAAAAAABYAAH/UwAJAQAABAQAAf9dAAUBQED/UwAJAgAABAQAAf9dAAUCQED/UwAJAwAABAQAAf9dAAUDQED/k8+kEAGvz6QQAa/PpBABr994EAk//9k="}),Modernizr.addAsyncTest(function(){var e=new Image;e.onload=e.onerror=function(){d("jpegxr",1==e.width,{aliases:["jpeg-xr"]})},e.src="data:image/vnd.ms-photo;base64,SUm8AQgAAAAFAAG8AQAQAAAASgAAAIC8BAABAAAAAQAAAIG8BAABAAAAAQAAAMC8BAABAAAAWgAAAMG8BAABAAAAHwAAAAAAAAAkw91vA07+S7GFPXd2jckNV01QSE9UTwAZAYBxAAAAABP/gAAEb/8AAQAAAQAAAA=="}),Modernizr.addAsyncTest(function(){var e,A,t,n=r("img"),o="sizes"in n;!o&&"srcset"in n?(A="data:image/gif;base64,R0lGODlhAgABAPAAAP///wAAACH5BAAAAAAALAAAAAACAAEAAAICBAoAOw==",e="data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==",t=function(){d("sizes",2==n.width)},n.onload=t,n.onerror=t,n.setAttribute("sizes","9px"),n.srcset=e+" 1w,"+A+" 8w",n.src=e):d("sizes",o)}),Modernizr.addAsyncTest(function(){function e(t){clearTimeout(A),n.removeEventListener("playing",e,!1),d("videoautoplay",t&&"playing"===t.type||0!==n.currentTime),n.parentNode.removeChild(n)}var A,t=300,n=r("video"),o=n.style;if(!(Modernizr.video&&"autoplay"in n))return void d("videoautoplay",!1);o.position="absolute",o.height=0,o.width=0;try{if(Modernizr.video.ogg)n.src="data:video/ogg;base64,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";else{if(!Modernizr.video.h264)return void d("videoautoplay",!1);n.src="data:video/mp4;base64,AAAAHGZ0eXBtcDQyAAAAAG1wNDJpc29tYXZjMQAAAz5tb292AAAAbG12aGQAAAAAzaNacc2jWnEAAV+QAAFfkAABAAABAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACAAAAGGlvZHMAAAAAEICAgAcAT////3//AAACQ3RyYWsAAABcdGtoZAAAAAHNo1pxzaNacQAAAAEAAAAAAAFfkAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAAAAEAAAAAAEAAAABAAAAAAAd9tZGlhAAAAIG1kaGQAAAAAzaNacc2jWnEAAV+QAAFfkFXEAAAAAAAhaGRscgAAAAAAAAAAdmlkZQAAAAAAAAAAAAAAAAAAAAGWbWluZgAAABR2bWhkAAAAAQAAAAAAAAAAAAAAJGRpbmYAAAAcZHJlZgAAAAAAAAABAAAADHVybCAAAAABAAABVnN0YmwAAACpc3RzZAAAAAAAAAABAAAAmWF2YzEAAAAAAAAAAQAAAAAAAAAAAAAAAAAAAAAAEAAQAEgAAABIAAAAAAAAAAEOSlZUL0FWQyBDb2RpbmcAAAAAAAAAAAAAAAAAAAAAAAAY//8AAAAxYXZjQwH0AAr/4QAZZ/QACq609NQYBBkAAAMAAQAAAwAKjxImoAEABWjOAa8gAAAAEmNvbHJuY2xjAAYAAQAGAAAAGHN0dHMAAAAAAAAAAQAAAAUAAEZQAAAAKHN0c3oAAAAAAAAAAAAAAAUAAAIqAAAACAAAAAgAAAAIAAAACAAAAChzdHNjAAAAAAAAAAIAAAABAAAABAAAAAEAAAACAAAAAQAAAAEAAAAYc3RjbwAAAAAAAAACAAADYgAABaQAAAAUc3RzcwAAAAAAAAABAAAAAQAAABFzZHRwAAAAAAREREREAAAAb3VkdGEAAABnbWV0YQAAAAAAAAAhaGRscgAAAAAAAAAAbWRpcgAAAAAAAAAAAAAAAAAAAAA6aWxzdAAAADKpdG9vAAAAKmRhdGEAAAABAAAAAEhhbmRCcmFrZSAwLjkuOCAyMDEyMDcxODAwAAACUm1kYXQAAAHkBgX/4NxF6b3m2Ui3lizYINkj7u94MjY0IC0gY29yZSAxMjAgLSBILjI2NC9NUEVHLTQgQVZDIGNvZGVjIC0gQ29weWxlZnQgMjAwMy0yMDExIC0gaHR0cDovL3d3dy52aWRlb2xhbi5vcmcveDI2NC5odG1sIC0gb3B0aW9uczogY2FiYWM9MCByZWY9MSBkZWJsb2NrPTE6MDowIGFuYWx5c2U9MHgxOjAgbWU9ZXNhIHN1Ym1lPTkgcHN5PTAgbWl4ZWRfcmVmPTAgbWVfcmFuZ2U9NCBjaHJvbWFfbWU9MSB0cmVsbGlzPTAgOHg4ZGN0PTAgY3FtPTAgZGVhZHpvbmU9MjEsMTEgZmFzdF9wc2tpcD0wIGNocm9tYV9xcF9vZmZzZXQ9MCB0aHJlYWRzPTYgc2xpY2VkX3RocmVhZHM9MCBucj0wIGRlY2ltYXRlPTEgaW50ZXJsYWNlZD0wIGJsdXJheV9jb21wYXQ9MCBjb25zdHJhaW5lZF9pbnRyYT0wIGJmcmFtZXM9MCB3ZWlnaHRwPTAga2V5aW50PTUwIGtleWludF9taW49NSBzY2VuZWN1dD00MCBpbnRyYV9yZWZyZXNoPTAgcmM9Y3FwIG1idHJlZT0wIHFwPTAAgAAAAD5liISscR8A+E4ACAACFoAAITAAAgsAAPgYCoKgoC+L4vi+KAvi+L4YfAEAACMzgABF9AAEUGUgABDJiXnf4AAAAARBmiKUAAAABEGaQpQAAAAEQZpilAAAAARBmoKU"}}catch(i){return void d("videoautoplay",!1)}n.setAttribute("autoplay",""),n.style.cssText="display:none",b.appendChild(n),setTimeout(function(){n.addEventListener("playing",e,!1),A=setTimeout(e,t)},0)});var Q=w.testStyles=c;Modernizr.addTest("hiddenscroll",function(){return Q("#modernizr {width:100px;height:100px;overflow:scroll}",function(e){return e.offsetWidth===e.clientWidth})}),Q("#modernizr{position: absolute; top: -10em; visibility:hidden; font: normal 10px arial;}#subpixel{float: left; font-size: 33.3333%;}",function(A){var t=A.firstChild;t.innerHTML="This is a text written in Arial",Modernizr.addTest("subpixelfont",e.getComputedStyle?"44px"!==e.getComputedStyle(t,null).getPropertyValue("width"):!1)},1,["subpixel"]),Modernizr.addTest("formvalidation",function(){var A=r("form");if(!("checkValidity"in A&&"addEventListener"in A))return!1;if("reportValidity"in A)return!0;var t,n=!1;return Modernizr.formvalidationapi=!0,A.addEventListener("submit",function(A){(!e.opera||e.operamini)&&A.preventDefault(),A.stopPropagation()},!1),A.innerHTML='<input name="modTest" required><button></button>',Q("#modernizr form{position:absolute;top:-99999em}",function(e){e.appendChild(A),t=A.getElementsByTagName("input")[0],t.addEventListener("invalid",function(e){n=!0,e.preventDefault(),e.stopPropagation()},!1),Modernizr.formvalidationmessage=!!t.validationMessage,A.getElementsByTagName("button")[0].click()}),n});var S=r("input"),U="search tel url email datetime date month week time datetime-local number range color".split(" "),G={};Modernizr.inputtypes=function(e){for(var n,o,i,r=e.length,a="1)",s=0;r>s;s++)S.setAttribute("type",n=e[s]),i="text"!==S.type&&"style"in S,i&&(S.value=a,S.style.cssText="position:absolute;visibility:hidden;",/^range$/.test(n)&&S.style.WebkitAppearance!==t?(b.appendChild(S),o=A.defaultView,i=o.getComputedStyle&&"textfield"!==o.getComputedStyle(S,null).WebkitAppearance&&0!==S.offsetHeight,b.removeChild(S)):/^(search|tel)$/.test(n)||(i=/^(url|email)$/.test(n)?S.checkValidity&&S.checkValidity()===!1:S.value!=a)),G[e[s]]=!!i;return G}(U),Modernizr.addTest("localizednumber",function(){if(!Modernizr.inputtypes.number)return!1;if(!Modernizr.formvalidation)return!1;var e,t=r("div"),n=a(),o=function(){return b.insertBefore(n,b.firstElementChild||b.firstChild)}();t.innerHTML='<input type="number" value="1.0" step="0.1"/>';var i=t.childNodes[0];o.appendChild(t),i.focus();try{A.execCommand("InsertText",!1,"1,1")}catch(s){}return e="number"===i.type&&1.1===i.valueAsNumber&&i.checkValidity(),o.removeChild(t),n.fake&&o.parentNode.removeChild(o),e});var P="Moz O ms Webkit",V=w._config.usePrefixes?P.split(" "):[];w._cssomPrefixes=V;var M=function(A){var n,o=prefixes.length,i=e.CSSRule;if("undefined"==typeof i)return t;if(!A)return!1;if(A=A.replace(/^@/,""),n=A.replace(/-/g,"_").toUpperCase()+"_RULE",n in i)return"@"+A;for(var r=0;o>r;r++){var a=prefixes[r],s=a.toUpperCase()+"_"+n;if(s in i)return"@-"+a.toLowerCase()+"-"+A}return!1};w.atRule=M;var N=w._config.usePrefixes?P.toLowerCase().split(" "):[];w._domPrefixes=N;var Y={elem:r("modernizr")};Modernizr._q.push(function(){delete Y.elem});var I={style:Y.elem.style};Modernizr._q.unshift(function(){delete I.style});var H=w.testProp=function(e,A,n){return h([e],t,A,n)};Modernizr.addTest("textshadow",H("textShadow","1px 1px")),w.testAllProps=v;var D=w.prefixed=function(e,A,t){return 0===e.indexOf("@")?M(e):(-1!=e.indexOf("-")&&(e=s(e)),A?v(e,A,t):v(e,"pfx"))};Modernizr.addTest("batteryapi",!!D("battery",navigator),{aliases:["battery-api"]});var Z=D("crypto",e);Modernizr.addTest("crypto",!!D("subtle",Z)),Modernizr.addTest("fullscreen",!(!D("exitFullscreen",A,!1)&&!D("cancelFullScreen",A,!1))),Modernizr.addTest("gamepads",!!D("getGamepads",navigator)),Modernizr.addTest("vibrate",!!D("vibrate",navigator)),Modernizr.addTest("lowbattery",function(){var e=.2,A=D("battery",navigator);return!!(A&&!A.charging&&A.level<=e)}),Modernizr.addTest("requestautocomplete",!!D("requestAutocomplete",r("form")));var L=D("URL",e,!1);L=L&&e[L],Modernizr.addTest("bloburls",L&&"revokeObjectURL"in L&&"createObjectURL"in L),Modernizr.addTest("getusermedia",!!D("getUserMedia",navigator)),Modernizr.addTest("matchmedia",!!D("matchMedia",e)),w.testAllProps=g,Modernizr.addTest("bgrepeatround",g("backgroundRepeat","round")),Modernizr.addTest("bgrepeatspace",g("backgroundRepeat","space")),Modernizr.addTest("backgroundsize",g("backgroundSize","100%",!0)),Modernizr.addTest("bgsizecover",g("backgroundSize","cover")),Modernizr.addTest("borderimage",g("borderImage","url() 1",!0)),Modernizr.addTest("borderradius",g("borderRadius","0px",!0)),Modernizr.addTest("boxshadow",g("boxShadow","1px 1px",!0)),Modernizr.addTest("shapes",g("shapeOutside","content-box",!0)),Modernizr.addTest("csstransforms3d",function(){var e=!!g("perspective","1px",!0),A=Modernizr._config.usePrefixes;if(e&&(!A||"webkitPerspective"in b.style)){var t,n="#modernizr{width:0;height:0}";Modernizr.supports?t="@supports (perspective: 1px)":(t="@media (transform-3d)",A&&(t+=",(-webkit-transform-3d)")),t+="{#modernizr{width:7px;height:18px;margin:0;padding:0;border:0}}",Q(n+t,function(A){e=7===A.offsetWidth&&18===A.offsetHeight})}return e}),o(),i(T),delete w.addTest,delete w.addAsyncTest;for(var W=0;W<Modernizr._q.length;W++)Modernizr._q[W]();e.Modernizr=Modernizr}(window,document);
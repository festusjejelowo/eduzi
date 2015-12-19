<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>WebsSockets</title>
    <link rel="stylesheet" href="/style.css">
    <link rel="icon" type="image/x-icon" href="/images/favicon.ico">
</head>
<body>
<div class="chat">
  <header>
    <div class="menu-icon"><span class="entypo-menu"></span></div>
    <h1>Chats</h1>
    <span></span>
  </header>
  <div class="new" id="chatControls">
    <p><span class="entypo-feather"></span> New Message</p>
    <input id="message" placeholder="Type your message">
    <button id="send" class="btn btn-default">Send</button>
  </div>
  <section class="messages">
    <div class="message">
      <img src="http://www.placecage.com/70/70"/>
      <h2>Almighty Cage</h2>
      <p>I am Nicolas, don't you know? I'm fuckin' famous...</p>
      <p class="time"><span class="entypo-clock"></span> 5 mins ago</p>
    </div>
    <div class="message">
      <img src="http://fillmurray.com/70/70"/>
      <h2>The Murray</h2>
      <p>Quiet there Cage! I'm even more famous than you...</p>
      <p class="time"><span class="entypo-clock"></span> 3 mins ago</p>
    </div>
    <div class="message">
      <img src="http://placeape.com/70/70"/>
      <h2>Professor Ape</h2>
      <p>You two act like monkeys and shit...</p>
      <p class="time"><span class="entypo-clock"></span> just now</p>
    </div>
  </section>
  <footer><p>end of messages</p></footer>
</div>  
<script type="text/javascript" src="/scripts/jquery-1.9.1.min.js"></script>
<script src="/scripts/chat.js"></script>
</body>
</html>


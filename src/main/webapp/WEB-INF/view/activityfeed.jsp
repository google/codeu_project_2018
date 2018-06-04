<!DOCTYPE html>
<html>
<head>
  <title>ActivityFeed</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>
  <nav>
    <a id="navTitle" href="/">CodeU Chat App</a>
    <a href="/conversations">Conversations</a>
    <% if(request.getSession().getAttribute("user") != null) { %>
      <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
    <% } else { %>
      <a href="/login">Login</a>
    <% } %>
    <a href="/activityfeed">ActivityFeed</a>
    <a href="/about.jsp">About</a>
  </nav>
  <div id="activityfeed">
    <h2 style="color:blue"> WELCOME TO THE <strong> ACTIVITY FEED PAGE </strong> (in progress) </h2>
  </div>
</body>
</html>

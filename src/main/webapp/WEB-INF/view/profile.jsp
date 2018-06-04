<%--
  Copyright 2017 Google Inc.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--%>
<%@ page import="codeu.model.data.User" %>
<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="codeu.model.data.Message" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%@ page import="codeu.model.store.basic.MessageStore" %>
<%@ page import="codeu.model.data.StyleText" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.time.ZonedDateTime" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.time.ZoneOffset" %>
<%
User user = (User) request.getAttribute("user");
List<Message> messagesByUser = (List<Message>) request.getAttribute("messagesByUser");
%>

<!DOCTYPE html>
<html>
<head>
  <title>My Profile</title>
  <link rel="stylesheet" href="/css/main.css">

  <style>
    #chat {
      background-color: white;
      height: 500px;
      width: 750px;
      overflow-y: scroll
    }
  </style>

  <script>
    // scroll the chat div to the bottom
    function scrollChat() {
      var chatDiv = document.getElementById('chat');
      chatDiv.scrollTop = chatDiv.scrollHeight;
    };
  </script>
</head>
<body>

  <nav>
    <a id="navTitle" href="/">CodeByter's Chat App</a>
    <a href="/conversations">Conversations</a>
    <% if(request.getSession().getAttribute("user") != null){ %>
      <a>Hello <%=request.getSession().getAttribute("user")%>!</a>
      <a href="/users/<%=request.getSession().getAttribute("user")%>">My Profile</a>
      <a href="/logout.jsp">Logout</a>
    <% } else { %>
      <a href="/login">Login</a>
    <% } %>
    <a href="/about.jsp">About</a>
  </nav>

  <div id="container">

    <% if (request.getAttribute("error") != null) { %>
      <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <% if (request.getSession().getAttribute("user") != null) { %>
      <h1><%=request.getSession().getAttribute("user")%>'s Profile Page</h1>
      <hr/>
      <strong>About <%=request.getSession().getAttribute("user")%></strong><br>
      <p><%=StyleText.style(user.getAboutMe())%></p>
      <form action="/users/<%=request.getSession().getAttribute("user") %>" method="POST">

        <div class="form-group">
          <label class="form-control-label">Edit Your About Me (Only you can see this):</label>
          <textarea rows="5" cols="120" name="About Me"></textarea>
        </div>

        <button type="submit">submit</button>
      </form>

      <hr/>

      <h1><%=request.getSession().getAttribute("user")%>'s Sent Messages</h1>

      <div id="chat">
        <ul>
          <% for (Message message : messagesByUser) {
            Instant time = message.getCreationTime();
            String creation = DateTimeFormatter.RFC_1123_DATE_TIME.withZone(ZoneOffset.UTC).format(time);
          %>
            <li><strong><%= creation %>:</strong> <%= StyleText.style(message.getContent()) %></li>
          <% } %>
        </ul>
      </div>

      <hr/>
    <% } %>
  </div>
</body>
</html>

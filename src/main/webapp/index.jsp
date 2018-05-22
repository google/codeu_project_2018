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
<!DOCTYPE html>
<html>
<head>
  <title>CodeByter's Chat App</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>

  <nav>
    <a id="navTitle" href="/">CodeByter's Chat App</a>
    <a href="/conversations">Conversations</a>
    <% if(request.getSession().getAttribute("user") != null){ %>
      <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
      <a href="/users/<%= request.getSession().getAttribute("user") %>">My Profile</a>
      <a href="/logout.jsp">Logout</a>
    <% } else{ %>
      <a href="/login">Login</a>
    <% } %>
    <a href="/about.jsp">About</a>
  </nav>

  <div id="container">
    <div
      style="width:75%; margin-left:auto; margin-right:auto; margin-top: 50px;">

      <h1>CodeByter's Chat App</h1>
      <h2>Welcome!</h2>
      <p>
        Through our site, you will be able to connect with the world through online chat!
      </p>
      <ul>
        <% if(request.getSession().getAttribute("user") != null){ %>
          <li>Go to the <a href="/users/<%= request.getSession().getAttribute("user") %>"
          >My profile</a> page to view your profile.</li>
        <% } else{ %>
          <li><a href="/login">Login</a> to get started.</li>
        <% } %>
          <li>Go to the <a href="/conversations">conversations</a> page to
            create or join a conversation.</li>
          <li>View the <a href="/about.jsp">about</a> page to learn more about the
            project.</li>

      </ul>
    </div>
  </div>
</body>
</html>

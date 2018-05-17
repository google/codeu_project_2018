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
  <title>My Profile</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>

  <nav>
    <a id="navTitle" href="/">CodeByter's Chat App</a>
    <a href="/conversations">Conversations</a>
    <% if(request.getSession().getAttribute("user") != null){ %>
      <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
      <a href="/MyProfile">My Profile</a>
    <% } else{ %>
      <a href="/login">Login</a>
    <% } %>
    <a href="/about.jsp">About</a>
  </nav>

  <div id="container">

    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <% if(request.getSession().getAttribute("user") != null){ %>
      <h1><%= request.getSession().getAttribute("user") %>'s Profile Page</h1>
      <form action="/MyProfile" method="POST">
          <div class="form-group">
            <li><strong>About <%= request.getSession().getAttribute("user") %>:</strong></li>
            <label class="form-control-label">Edit your about me:</label>
          <input type="text" name="aboutMe">
        </div>

        <button type="submit">submit</button>
      </form>

      <hr/>
    <% } %>

    <h1><%= request.getSession().getAttribute("user") %>'s Conversations</h1>

    
    <hr/>
  </div>
</body>
</html>


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
    <% } else{ %>
      <a href="/login">Login</a>
    <% } %>
    <a href="/about.jsp">About</a>
  </nav>

  <div id="container">
    <div
      style="width:75%; margin-left:auto; margin-right:auto; margin-top: 50px;">

      <h1>Administration</h1>
      <p>
        This is the administration page of the CodeU Chat App. Only the administrators of the site can view stats. 
      </p>

      <h2><strong>Site Statistics</strong></h2>
      <ul>
        <li><strong>Total Users:</strong></li>
        <li><strong>Total Conversations:</strong></li>
        <li><strong>Total Messages:</strong></li>
        <li><strong>Most Active User:</strong></li>
        <li><strong>Newest User:</strong></li>
        <li><strong>Wordiest User:</strong></li>
      </ul>
      
    </div>
  </div>
</body>
</html>

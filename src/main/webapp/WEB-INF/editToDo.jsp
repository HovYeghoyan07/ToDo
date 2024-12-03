<%@ page import="am.itspace.todo.model.ToDo" %><%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 03.12.2024
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit</title>
  <link href="/css/bootstrap.min.css" rel="stylesheet">
  <script src="/js/bootstrap.bundle.min.js" ></script>
</head>
<body>

<span>
    <%
      if (session.getAttribute("msg") != null) { %>
        <h3><%=session.getAttribute("msg")%></h3>
   <%
       session.removeAttribute("msg");
     }
   %>
</span>



<section class="vh-100" style="background-color: #9A616D;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col col-xl-10">
        <div class="card" style="border-radius: 1rem;">
          <div class="row g-0">
            <div class="col-md-6 col-lg-5 d-none d-md-block">
              <img src="/img/img.png"
                   alt="login form" class="img-fluid" style="border-radius: 1rem 0 0 1rem; height: 100%" />
            </div>
            <div class="col-md-6 col-lg-7 d-flex align-items-center">
              <div class="card-body p-4 p-lg-5 text-black">

                <form action="/editToDo" method="post" enctype="multipart/form-data">
                  <%ToDo toDo = (ToDo) request.getAttribute("toDo");%>
                  <input type="hidden" name="id" value="<%=toDo.getId()%>">
                  <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Edit</h5>
                  <div data-mdb-input-init class="form-outline mb-4">
                    <input type="text" id="name" class="form-control form-control-lg" name="name"/>
                    <label class="form-label" for="name">Title</label>
                  </div>


                  <div class="pt-1 mb-4">
                    <button data-mdb-button-init data-mdb-ripple-init class="btn btn-dark btn-lg btn-block" type="submit">Edit</button>
                  </div>

                  <p class="mb-5 pb-lg-2" style="color: #393f81;">have an account? <a href="/login"
                                                                                      style="color: #393f81;">Login here</a></p>
                </form>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>


</body>
</html>
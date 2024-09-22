<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client Sign In</title>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/views/ClientTemplate/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/views/ClientTemplate/css/bootstrap.min.css">
    <link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

  <link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
  <!-- or -->
  <link rel="stylesheet"
    href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/views/ClientTemplate/css/rating.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/views/ClientTemplate/css/profile.css">
    <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/angular.min.js"></script>
        <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 		<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
</head>
<body>
       <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.lang}" scope="request"/>

 <%@include file="/views/ClientTemplate/layout/nav.jsp"%>
	<fmt:setBundle basename="forgot_password" scope="request"/>
	<!-- content -->
 	
   <!-- CONTENT -->
    <div class="container p-5 border border-1 mt-5 mb-5">
      <div class="row ">
        <div class="col-6 border-end">
          <h2><fmt:message key="fg.title"/></h2>
          <p><fmt:message key="fg.subTitle"/></p>
          <form action="<%=request.getContextPath()%>/client/forgot_password" method="post">
           
            <!-- Email input -->
            <div data-mdb-input-init class="form-outline mb-4">
              <label class="form-label fw-bold" for="form1Example1"><fmt:message key="fg.email"/></label>
              <input type="email" name=email class="form-control" />
              <c:if test="${not empty errorMessage}">
             <div class="text-danger mt-1" >
        		${errorMessage}
            </div>
            </c:if>
            </div>
            <!-- 2 column grid layout for inline styling -->
     		
            <!-- Submit button -->
            <button data-mdb-ripple-init type="submit" class="btn btn-success btn-block"><fmt:message key="fg.sendButton"/></button>
          </form>
        </div>

        <div class="col-6">
          <h2><fmt:message key="fg.SignInTitle"/></h2>
          <p><fmt:message key="fg.SignInSubTitle"/></p>
          <button class="btn btn-success"><fmt:message key="fg.SignInButton"/></button>
        </div>
      </div>
    </div>

    <!-- CONTENT -->

	<!-- content -->

   <%@include file="/views/ClientTemplate/layout/footer.jsp"%>
    

   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/angular-route.min.js"></script>
   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/webapp.js"></script>
   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/bootstrap.bundle.min.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client Sign up</title>

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
		<fmt:setBundle basename="signup" scope="request"/>
	<!-- content -->
 	
    <!-- SLIDER -->

<!-- CONTENT -->
<div class="container p-5 border border-1 mt-5 mb-5">
  <div class="row">
    <div class="col-6 border-end">
      <h2><fmt:message key="signup.title"/></h2>
      <p><fmt:message key="signup.subTitle"/></p>
      <form action="signup" method="post" novalidate>
        <!-- Email input -->
        <div data-mdb-input-init class="form-outline mb-4">
        
          <label class="form-label fw-bold"><fmt:message key="signup.name"/></label>
		  <input type="text" class="form-control" name="name" />
		 <c:if test="${not empty ErrorName}">
             <div class="text-danger mt-1" >
        		${ErrorName}
            </div>
            </c:if>
        	 
          <label class="form-label fw-bold mt-3"><fmt:message key="signup.phone"/></label>
          <input type="text" class="form-control" name="phone" />
            
          <c:if test="${not empty ErrorPhone}">
             <div class="text-danger mt-1" >
        		${ErrorPhone}
            </div>
            </c:if>
        </div>
         
        <!-- Email input -->
        <div data-mdb-input-init class="form-outline mb-4">
          <label class="form-label fw-bold"><fmt:message key="signup.email"/></label>
          <input type="email" class="form-control" name="email" />
          
            <c:if test="${not empty ErrorEmail}">
             <div class="text-danger mt-1" >
        		${ErrorEmail}
            </div>
            </c:if>
        </div>

        <!-- Password input -->
        <div data-mdb-input-init class="form-outline mb-4">
          <label class="form-label fw-bold"><fmt:message key="signup.password"/></label>
          <input type="password" class="form-control" name="password" />
         
           <c:if test="${not empty ErrorPassword}">
             <div class="text-danger mt-1" >
        		${ErrorPassword}
            </div>
            </c:if>
        </div>

        <!-- Password input -->
        <div data-mdb-input-init class="form-outline mb-4">
          <label class="form-label fw-bold"><fmt:message key="signup.repassword"/></label>
          <input type="password" class="form-control" name="repassword" />
         
            <c:if test="${not empty ErrorRePassword}">
             <div class="text-danger mt-1" >
        		${ErrorRePassword}
            </div>
            </c:if>
        </div>

    <!--     <div class="row mb-4">
          <div class="col-3 d-flex">
            Checkbox
            <div class="form-check">
              <input
                class="form-check-input"
                type="checkbox"
                value=""
                id="showPassword"
                ng-model="showPassword"
                checked
              />
              <label class="form-check-label"> Hiện mật khẩu </label>
            </div>
          </div>
        </div> -->

        <!-- Submit button -->
        <button data-mdb-ripple-init type="submit" class="btn btn-block btn-success">
          <fmt:message key="signup.SignUpButton"/>
        </button>
      </form>
    </div>

    <div class="col-6">
      <h2><fmt:message key="signup.SignInTitle"/></h2>
      <p>
        <fmt:message key="signup.SignInSubTitle"/>
      </p>
      <a href="<%=request.getContextPath()%>/client/signin">
        <button class="btn btn-success"> <fmt:message key="signup.SignInButton"/></button>
      </a>
    </div>
  </div>
</div>

<!-- CONTENT -->


  <!-- CONTENT -->
	<!-- content -->

   <%@include file="/views/ClientTemplate/layout/footer.jsp"%>
    

   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/angular-route.min.js"></script>
   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/webapp.js"></script>
   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/bootstrap.bundle.min.js"></script>
</body>
</html>
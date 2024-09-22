<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
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

<fmt:setLocale value="${sessionScope.lang}" scope="request"/>
 <%@include file="/views/ClientTemplate/layout/nav.jsp"%>
	<fmt:setBundle basename="OTP" scope="request"/>
	<!-- content -->
 	
   <!-- CONTENT -->
    <div class="container p-5 border border-1 mt-5 mb-5">
      <div class="row ">
        <div class="col-6 border-end">
          <h2><fmt:message key="otp.title"/></h2>
          <p><fmt:message key="otp.subTitle"/></p>
          <form action="<%=request.getContextPath()%>/client/OTP" method="post">
           
            <!-- Email input -->
            <div data-mdb-input-init class="form-outline mb-4">
              <label class="form-label fw-bold" for="form1Example1"><fmt:message key="otp.otp_input"/></label>
              <input type="text" name="OTP" class="form-control" />
              <c:if test="${not empty errorMessage}">
             <div class="text-danger mt-1" >
        		${errorMessage}
            </div>
             <button type="submit" formaction="<%=request.getContextPath()%>/client/OTP/reOTP" class="btn btn-success btn-block"><fmt:message key="otp.reButton"/></button>
            
            </c:if>
            </div>
            <!-- 2 column grid layout for inline styling -->
     		  <div class="d-flex justify-content-between align-items-center mb-4">
                        <span><fmt:message key="otp.5min"/></span>
                    </div>
            <!-- Submit button -->
            <button data-mdb-ripple-init type="submit" class="btn btn-success btn-block"><fmt:message key="otp.sendButton"/></button>
          </form>
        </div>

        <div class="col-6">
          <h2><fmt:message key="otp.SignInTitle"/></h2>
          <p><fmt:message key="otp.SignInSubTitle"/></p>
          <button class="btn btn-success"><fmt:message key="otp.SignInButton"/></button>
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
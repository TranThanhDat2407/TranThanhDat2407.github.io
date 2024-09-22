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
	<fmt:setBundle basename="signin" scope="request"/>
	<!-- content -->
 	
    <!-- SLIDER -->

	
	   <!-- NAVBAR -->
   <nav aria-label="breadcrumb" style="margin-left:80px; margin-top: 15px;
   margin-bottom: 50px;">
     <ol class="breadcrumb">
       <li class="breadcrumb-item"><a href="#" style="color:green; text-decoration: none;"><fmt:message key="signin.breadcrumHome"/></a></li>
       <li class="breadcrumb-item"><fmt:message key="signin.breadcrumSignIn"/></li>
     </ol>
   </nav>

   <!-- CONTENT -->
   <div class="container p-5 border border-1 mt-5 mb-5">
     <div class="row">
       <div class="col-6 border-end">
         <h2><fmt:message key="signin.title"/></h2>
         <p><fmt:message key="signin.subTitle"/></p>
         <form action="signin" method="post" enctype="multipart/form-data" novalidate="novalidate">
         
           <div data-mdb-input-init class="form-outline mb-4">
           

             <label class="form-label fw-bold" for="form1Example1"><fmt:message key="signin.emailTitle"/></label>
             <input type="email" id="form1Example1" name="email" 
             class="form-control"/>
              
           </div>
         
           <div data-mdb-input-init class="form-outline mb-4">
             <label class="form-label fw-bold" for="form1Example2"><fmt:message key="signin.passwordTitle"/></label>
             <input type="password" 
              class="form-control" 
             name="password"  
              />
      		
           </div>
           
            <div data-mdb-input-init class="form-outline mb-4">
             <label class="form-label fw-bold" for="form1Example2"><fmt:message key="signin.QRCODE"/></label>
             <input type="file" 
              class="form-control" 
             name="QR"  
              />
      	   <c:if test="${not empty errorMessage}">
             <div class="text-danger mt-1" >
        		${errorMessage}
            </div>
            </c:if>
           </div>
        

          <div class="row mb-4">
             <!-- <div class="col-3 d-flex">
               <div class="form-check">
                 <input class="form-check-input" type="checkbox" value="" id="form1Example3" value="" id="showPassword" ng-model="showPassword" checked/>
                 <label class="form-check-label" for="form1Example3"> Hiện mật khẩu </label>
               </div>
             </div> --> 
         
             <div class="col-3">
               <a href="<%=request.getContextPath()%>/client/forgot_password" class="text-success" style="text-decoration: none; "><fmt:message key="signin.ForgotPassword"/></a>
             </div>
           </div>
         
           <!-- Submit button -->
           <button data-mdb-ripple-init type="submit" class="btn btn-success btn-block" ><fmt:message key="signin.SignInButton"/></button>
         </form>
       </div>

       <div class="col-6">
         <h2><fmt:message key="signin.SignUpTitle"/></h2>
         <p><fmt:message key="signin.SignUpSubTitle"/></p>
         <a href="#!account/register"><button class="btn btn-success"><fmt:message key="signin.SignUpButton"/></button></a>
       </div>
     </div>
   </div>

	<!-- content -->

   <%@include file="/views/ClientTemplate/layout/footer.jsp"%>
    

   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/angular-route.min.js"></script>
   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/webapp.js"></script>
   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/bootstrap.bundle.min.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Purple Admin</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/views/AdminTemplate/assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/views/AdminTemplate/assets/vendors/ti-icons/css/themify-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/views/AdminTemplate/assets/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/views/AdminTemplate/assets/vendors/font-awesome/css/font-awesome.min.css">
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/views/AdminTemplate/assets/css/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/views/AdminTemplate/assets/images/favicon.png" />
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
  </head>
  <body>
    <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth">
          <div class="row flex-grow">
            <div class="col-lg-4 mx-auto">
              <div class="auth-form-light text-left p-5">
                <div class="brand-logo">
                <%--   <img src="<%=request.getContextPath()%>/views/AdminTemplate/assets/images/logo.svg"> --%>
                <h1 class="text-success">BRAND</h1>
                </div>
                <h4>Hello! let's get started</h4>
                <h6 class="font-weight-light">Sign in to continue.</h6>
                <form class="pt-3" action="login" method="post">
                  <div class="form-group">
                    <input type="email" class="form-control form-control-lg" name="email">
                  </div>
                  <div class="form-group">
                    <input type="password" class="form-control form-control-lg" name="password">
                  </div>
                   <c:if test="${not empty errorMessage}">
        				<div class="text-danger">${errorMessage}</div>
    			   </c:if>
                  <div class="mt-3 d-grid gap-2">
                    <button class="btn btn-block btn-gradient-success btn-lg font-weight-medium auth-form-btn" 
                   type=submit>SIGN IN</button>
                  </div>
                  <div class="text-center mt-4 font-weight-light"> Don't have an account? <a href="register.html" class="text-success">Create</a>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <!-- content-wrapper ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <!-- plugins:js -->
    <script src="../../assets/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="<%=request.getContextPath()%>/views/AdminTemplate/assets/js/off-canvas.js"></script>
    <script src="<%=request.getContextPath()%>/views/AdminTemplate/assets/js/misc.js"></script>
    <script src="<%=request.getContextPath()%>/views/AdminTemplate/assets/js/settings.js"></script>
    <script src="<%=request.getContextPath()%>/views/AdminTemplate/assets/js/todolist.js"></script>
    <script src="<%=request.getContextPath()%>/views/AdminTemplate/assets/js/jquery.cookie.js"></script>
    <!-- endinject -->
  </body>
</html>
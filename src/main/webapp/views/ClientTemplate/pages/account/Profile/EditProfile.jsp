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
    <style>
 .infor {
  /* border-top: 10px solid #f0f0f0; */
  margin-left: 500px;
  border-radius: 5px;
  margin-bottom: 200px;
  width: 570px;
  height: 400px !important; /* Điều chỉnh chiều cao theo nhu cầu của bạn */
  padding: 50px;
}
.infor_name {
  margin-left: 500px;
  border-radius: 5px;
  margin-bottom: 200px;
  width: 570px;
  height: 400px !important; /* Điều chỉnh chiều cao theo nhu cầu của bạn */
  padding: 50px;
}
.title_decor {
  height: 50px;
}

.title_decor_phone {
  margin-left: 100px;
  font-size: 20px;
  font-weight: bold;
  font-style: italic;
  margin-top: 15px;
}
.title_decor label {
  margin-left: 140px;
  font-size: 20px;
  font-weight: bold;
  font-style: italic;
  margin-top: 15px;
}
.body_decor {
  width: 500px;
  height: 500px;
  /* background-color: red */
}
.body_decor label {
  font-weight: bolder;
}
.form_decor {
  padding-bottom: 20px;
}

.inforTab_header {
  margin-top: 30px;
  margin-left: 40px;
  margin-bottom: 20px;
}
.inforUser {
  width: 965px;
  height: 300px;
  margin-top: 50px;
  border: 3px solid #f0f0f0;
  border-radius: 20px;
  margin-left: 42px;
}

.detail_infor {
  padding: 40px 40px;
  height: 100%;
}

.lable {
  font-weight: 500;
  font-size: 25px;
}
.text {
  line-height: 2.7;
  word-wrap: break-word;
  overflow-wrap: break-word;
}
.detail_infor a {
  line-height: 2.7;
  color: #198754;
  font-weight: bold;
}
.detail_infor input {
  width: 500px;
}

    </style>
    	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
 <fmt:setLocale value="${sessionScope.lang}" scope="request"/>
</head>
<body>


 <%@include file="/views/ClientTemplate/layout/nav.jsp"%>
		<fmt:setBundle basename="editprofile" scope="request"/>
	<!-- content -->
 	
    <!-- SLIDER -->
<div class="container">
        <nav aria-label="breadcrumb" style="margin-left:80px; margin-top: 15px;
        margin-bottom: 50px;">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#" style="color:green; text-decoration: none;"><fmt:message key="editprofile.breadcrumHome"/></a></li>
                <li class="breadcrumb-item"><a href="#" style="color:green; text-decoration: none;"><fmt:message key="editprofile.breadcrumProfile"/></a></li>
            </ol>
        </nav>
    
    </div>
    
    <div class="container mt-4" style="  width: 1300px;height: 700px;margin-left: 140px; ">
        <div class="row" style="margin-top: 10px ; margin-left: 20px">
            <div class="col-12" style="margin-left:80px; width: 250px;">
                <div class="user_Avata" >
                    <div class=" row">
                    <div class="col-12 " style="margin-top: 20px; margin-left: 10px;">
                        <div class="row">
                            <div class="col-6">
                                <div class="icon_user">
                                    <i class="fa-regular fa-user"></i>
                                </div>
                            </div>
                            <div class="col-6 br_up">
    
                            </div>
                        </div>
    
                    </div>
                    <div class="col-12"
        border-top: 3px solid #f0f0f0;
                        style="width: 251px;height: 120px; ; margin-top: -50px;z-index: -999; margin-left: 10px;">
                        <div class="name_User" style="z-index: 999;"> <label for="">${user.email}</label></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" style="width: 1200px; ;margin-top: -200px; height: 600px;"> 
        <div class="col-3" style=" width: 250px;margin-top: 200px; height: 287px;margin-left: 4px;
            border-bottom: 3px solid #f0f0f0; border-left: 3px solid #f0f0f0; border-right:3px solid #f0f0f0;">
            <ul class="nav flex-column nav-pills set_btnMenu" id="myTab" role="tablist"
                style="   margin-left: -12px;width:249px;height: 300px;">
       
                    </li> 
                    <li class="nav-item" style="border-top: 3px solid #f0f0f0;width: 248px;">
                        <a href="<%=request.getContextPath()%>/client/profile"> 
                    <button class="decor_btn_menu " 
                        type="button" ><i class="fa-solid fa-user"  style="font-size: 20px; margin-left: -37px; margin-right: 10px;" ></i>
                        <fmt:message key="editprofile.menu1"/></button>
                        </a>
                    </li>
                <li class="nav-item"  >
                <a href="<%=request.getContextPath()%>/client/profile/order"> 
                    <button class=" decor_btn_menu " id="home-tab" 
                        type="button"
                        ><i class="fa-solid fa-clock-rotate-left"
                            style="font-size: 20px; margin-right: 10px;"></i> <fmt:message key="editprofile.menu2"/></button>
                            </a>
                </li>
                <li class="nav-item" >
                    <a href="<%=request.getContextPath()%>/client/profile/address"> 
                        <button class="decor_btn_menu  "
                        type="button"><i
                            class="fa-solid fa-location-dot"
                            style="font-size: 20px;margin-left: -5px; margin-right: 10px;"></i> <fmt:message key="editprofile.menu3"/></button>

                    </a>
                   
                </li>
                <li class="nav-item" >
                 <a href="<%=request.getContextPath()%>/client/profile/change_password">
                    <button class="decor_btn_menu" 
                        type="button" ><i
                            class="fa-solid fa-lock" style="font-size: 20px; margin-left: -37px; margin-right: 10px;"></i>
                        <fmt:message key="editprofile.menu4"/> </button>
                        </a>
                </li>
            </ul>
        </div>
        <div class="col-9"  >
            <div class="tab-content tab_history"
                style="border: 3px solid #f0f0f0; width: 1046px; height:600px; ">
                <div class="tab-pane fade show active"  
                    tabindex="0">
                    <div class="row">
                        <div class="col-12 tab_Mini1">
                          <strong><fmt:message key="editprofile.title"/></strong>
                        </div>
                        <div class="col-12 tab_Mini2 ">
                          <form action="edit" method="post" class="edit_address mt-5" style="padding: 20px 40px">
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label"><fmt:message key="editprofile.name"/></label>
                                <input type="text" class="form-control" name="name" value="${user.name}">
                                             <c:if test="${not empty ErrorName}">
             <div class="text-danger mt-1" >
        		${ErrorName}
            </div>
            </c:if>
                              </div>
                              <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label"><fmt:message key="editprofile.phone"/></label>
                                <input type="text" class="form-control" name="phone" value="${user.phone}" placeholder="Nhập Số Điện Thoại của bạn">
                                             <c:if test="${not empty ErrorPhone}">
             <div class="text-danger mt-1" >
        		${ErrorPhone}
            </div>
            </c:if>
                              </div>
                              
                            <button
                              type="submit"
                              class="btn btn-primary"
                              style="
                                width: 110px;
                                height: 42px;
                                background-color: #198754;
                                border: none;
                                font-size: 16px;
                              "
                            >
                              <fmt:message key="editprofile.btnChange"/>
                            </button>
                          </form>
                        </div>
                      </div>
                </div>          
             </div>
            </div>
        </div>
    </div>
    </div>
    </div>

   <%@include file="/views/ClientTemplate/layout/footer.jsp"%>
    

   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/bootstrap.bundle.min.js"></script>
</body>
</html>
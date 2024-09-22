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
	<fmt:setBundle basename="list_address" scope="request"/>
	<!-- content -->
 	
    <div class="container">
    <nav aria-label="breadcrumb" style="margin-left:80px; margin-top: 15px;
    margin-bottom: 50px;">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="#" style="color:green; text-decoration: none;"><fmt:message key="la.breadcrumHome"/></a></li>
            <li class="breadcrumb-item"><a href="#" style="color:green; text-decoration: none;"><fmt:message key="la.breadcrumProfile"/></a></li>
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
                <fmt:message key="la.menu1"/></button>     
                </li>
                </a>
                <a href="<%=request.getContextPath()%>/client/profile/order">
            <li class="nav-item">
                <button class=" decor_btn_menu "
                    type="button"
                    ><i class="fa-solid fa-clock-rotate-left"
                        style="font-size: 20px; margin-right: 10px;"></i>   <fmt:message key="la.menu2"/></button>
            </li>
            	</a>
            <li class="nav-item" >
                <button class="decor_btn_menu active "
                    type="button"><i
                        class="fa-solid fa-location-dot"
                        style="font-size: 20px;margin-left: -5px; margin-right: 10px;"></i>   <fmt:message key="la.menu3"/></button>
            </li>
            <li class="nav-item" >
             <a href="<%=request.getContextPath()%>/client/profile/change_password">
                <button class="decor_btn_menu" 
                    type="button" ><i
                        class="fa-solid fa-lock" style="font-size: 20px; margin-left: -37px; margin-right: 10px;"></i>
                      <fmt:message key="la.menu4"/> </button>
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
                        <strong>  <fmt:message key="la.title"/></strong>
                    </div>
                    <div class="col-12 tab_Mini2">
                        <table class="table " style="margin-top: 20px; ">
                            <thead>
                                <tr
                                    style="width: 200px; height: 80px;justify-content: center;font-size: 13px; font-weight: bold; ">
                                    <th scope="col" style="width: 130px; "><label for=""
                                            style="justify-content: center;margin-bottom: 20px;"><fmt:message key="la.no"/></label></th>
                                    <th scope="col" style="width: 400px;  "><label for=""
                                            style="justify-content: center;margin-bottom: 20px; "><fmt:message key="la.address"/></label></th>
                                 
                                    <th scope="col" style="width: 100px;"><label for=""
                                            style="justify-content: center;margin-bottom: 20px;"><fmt:message key="la.detail"/></label></th>
                                </tr>
                            </thead>
                            <tbody>
										<c:forEach items="${AddressDefault}" var="item">
											<tr
												style="width: 200px; height: 90px; font-size: 13px; font-weight: bold;">
												<th><label for="" style="margin-top: 30px;">1</label></th>
												<td><label for=""
													style="margin-top: 30px;; text-align: left;">
														${item[1]}, ${item[2]}, ${item[3]}</label></td>
												
												
												<td>
												<a href="<%=request.getContextPath()%>/client/profile/address/edit?id=${item[0]}"><button
															class="btn btn-outline-success" style="width: 170px;"><fmt:message key="la.edit"/></button></a>
													
													<button class="btn btn-success mt-2" style="width: 170px;">
														<i class="fa-solid fa-check"></i> <fmt:message key="la.default"/>
													</button></td>
												
											</tr>
										</c:forEach>

										<c:set var="index" value="2"></c:set>
										<c:forEach items="${listAddress}" var="item">
									
                                <tr style="width: 200px; height: 90px; font-size: 13px; font-weight: bold;">
                                    <th><label for="" style="margin-top: 30px;">${index}</label></th>
                                    <td><label for="" style="margin-top: 30px; ; text-align: left;">
                                    ${item[1]},  ${item[2]},  ${item[3]}</label>
                                    </td>
                                    <td>
                                 
                                     
                                        <a href="<%=request.getContextPath()%>/client/profile/address/edit?id=${item[0]}"><button
															class="btn btn-outline-success" style="width: 170px;"><fmt:message key="la.edit"/></button></a>
                                       <a href="<%=request.getContextPath()%>/client/profile/address/set_default?id=${item[0]}"><button
															class="btn btn-outline-success mt-2" style="width: 170px;"><fmt:message key="la.setDefault"/>
															</button></a>
                                        </td>
                                    
                                </tr>
                                <c:set var="index" value="${index + 1}" />
									  </c:forEach>
                            </tbody>
                        </table>
                        
                    </div>
                     <a href="<%=request.getContextPath()%>/client/profile/address/create" 
                                     ><button class="btn btn-success ms-5 mt-4"><fmt:message key="la.newAddress"/></button></a>
                </div>
            </div>          
         </div>
        </div>
    </div>
</div>
</div>
</div>
	<!-- content -->

   <%@include file="/views/ClientTemplate/layout/footer.jsp"%>
    

   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/bootstrap.bundle.min.js"></script>
</body>
</html>
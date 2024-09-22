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
</head>
<body>
<fmt:setLocale value="${sessionScope.lang}" scope="request"/>

 <%@include file="/views/ClientTemplate/layout/nav.jsp"%>
	<fmt:setBundle basename="edit_address" scope="request"/>
	<!-- content -->
 	
  <div class="container">
        <nav aria-label="breadcrumb" style="margin-left:80px; margin-top: 15px;
        margin-bottom: 50px;">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#" style="color:green; text-decoration: none;"><fmt:message key="ea.breadcrumHome"/></a></li>
                <li class="breadcrumb-item"><a href="#" style="color:green; text-decoration: none;"><fmt:message key="ea.breadcrumProfile"/></a></li>
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
                     <a href="<%=request.getContextPath()%>/client/profile">   <button class="decor_btn_menu "
                        type="button" ><i class="fa-solid fa-user"  style="font-size: 20px; margin-left: -37px; margin-right: 10px;" ></i>
                        <fmt:message key="ea.menu1"/></button></a> 
                    </li>
                <li class="nav-item"  >
                <a href="<%=request.getContextPath()%>/client/profile/order"> 
                    <button class=" decor_btn_menu " id="home-tab" 
                        type="button"
                        ><i class="fa-solid fa-clock-rotate-left"
                            style="font-size: 20px; margin-right: 10px;"></i><fmt:message key="ea.menu2"/></button>
                            </a>
                </li>
                <li class="nav-item" >
                <a href="<%=request.getContextPath()%>/client/profile/address"> 
                    <button class="decor_btn_menu active "
                        type="button"><i
                            class="fa-solid fa-location-dot"
                            style="font-size: 20px;margin-left: -5px; margin-right: 10px;"></i><fmt:message key="ea.menu3"/></button>
                            </a>
                </li>
                <li class="nav-item" >
               <a href="<%=request.getContextPath()%>/client/profile/change_password">
                    <button class="decor_btn_menu" 
                        type="button" ><i
                            class="fa-solid fa-lock" style="font-size: 20px; margin-left: -37px; margin-right: 10px;"></i>
                        <fmt:message key="ea.menu4"/></button>
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
                          <strong><fmt:message key="ea.title"/></strong>
                        </div>
                        <div class="col-12 tab_Mini2">
                          <form class="edit_address" method="post" style="padding: 20px 40px">
                            <div class="mb-3">
                              <label for="province" class="form-label"><fmt:message key="ea.city"/></label>
                             <!--  <select class="form-control" id="province" value="01">
                                <option value="">Chọn tỉnh/thành phố</option>
                                 <option value="Thành phố Hồ Chí Minh">Thành phố Hồ Chí Minh</option>
                              </select> -->
                              <input type="text"  name="city" class="form-control"value="${address.city}" >
                            </div>
                            <div class="mb-3">
                              <label for="district" class="form-label"><fmt:message key="ea.ward"/></label>
                           <%--    <select class="form-control" id="district">
                                 <option value="${address.ward}">${address.ward}</option>
                              </select> --%>
                              <input type="text" name="ward" class="form-control"value="${address.ward}">
                            </div>
                            <div class="mb-3">
                              <label for="ward" class="form-label"><fmt:message key="ea.street"/></label>
                            <%--   <select class="form-control" id="ward">
                                < <option value="${address.street}">${address.street}</option>
                              </select> --%>
                               <input type="text" name="street" class="form-control"value="${address.street}">
                            </div>
                      <%--       <div class="mb-3">
                              <label for="address" class="form-label">Chi Tiết Địa chỉ</label>
                              <input class="form-control" id="address" value="${address.city}, ${address.ward}, ${address.street}"/>
                            </div> --%>
                            
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
                              <fmt:message key="ea.btnEdit"/>
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
	<!-- content -->

   <%@include file="/views/ClientTemplate/layout/footer.jsp"%>
    

   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/bootstrap.bundle.min.js"></script>
   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/RenderCities.js"></script>
<!--    <script type="text/javascript">
   // Fetch the data from the URL
   fetch(
     "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json"
   )
     .then((response) => response.json())
     .then((data) => {
       // Get the reference to the select elements
       const provinceSelect = document.getElementById("province");
       const districtSelect = document.getElementById("district");
       const wardSelect = document.getElementById("ward");

       // Populate the province select
       data.forEach((province) => {
         const option = document.createElement("option");
         option.value = province.Name;
         option.text = province.Name;
         provinceSelect.add(option);
       });

       // Add event listener to province select
       provinceSelect.addEventListener("change", () => {
         // Clear the district and ward selects
         districtSelect.innerHTML = "<option value=''>Chọn quận</option>";
         wardSelect.innerHTML = "<option value=''>Chọn phường</option>";

         // Find the selected province
         const selectedProvince = data.find(
           (p) => p.Id === provinceSelect.value
         );

         // Populate the district select
         selectedProvince.Districts.forEach((district) => {
           const option = document.createElement("option");
           option.value = district.Name;
           option.text = district.Name;
           districtSelect.add(option);
         });
       });

       // Add event listener to district select
       districtSelect.addEventListener("change", () => {
         // Clear the ward select
         wardSelect.innerHTML = "<option value=''>Chọn phường</option>";

         // Find the selected province and district
         const selectedProvince = data.find(
           (p) => p.Id === provinceSelect.value
         );
         const selectedDistrict = selectedProvince.Districts.find(
           (d) => d.Id === districtSelect.value
         );

         // Populate the ward select
         selectedDistrict.Wards.forEach((ward) => {
           const option = document.createElement("option");
           option.value = ward.Name;
           option.text = ward.Name;
           wardSelect.add(option);
         });
       });
     })
     .catch((error) => console.error(error));
   </script> -->
</body>
</html>
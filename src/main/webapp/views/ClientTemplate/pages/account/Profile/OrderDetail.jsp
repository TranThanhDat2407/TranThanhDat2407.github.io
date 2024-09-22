<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client Sign In</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/ClientTemplate/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/ClientTemplate/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
<!-- or -->
<link rel="stylesheet"
	href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/ClientTemplate/css/rating.css">
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/views/ClientTemplate/css/profile.css"> --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/ClientTemplate/css/profile2.css">
<script
	src="<%=request.getContextPath()%>/views/ClientTemplate/js/angular.min.js"></script>
<style>
.infor {
	/* border-top: 10px solid #f0f0f0; */
	margin-left: 500px;
	border-radius: 5px;
	margin-bottom: 200px;
	width: 570px;
	height: 400px !important;
	/* Điều chỉnh chiều cao theo nhu cầu của bạn */
	padding: 50px;
}

.infor_name {
	margin-left: 500px;
	border-radius: 5px;
	margin-bottom: 200px;
	width: 570px;
	height: 400px !important;
	/* Điều chỉnh chiều cao theo nhu cầu của bạn */
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
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
 <fmt:setLocale value="${sessionScope.lang}" scope="request"/>
</head>
<body>


	<%@include file="/views/ClientTemplate/layout/nav.jsp"%>
	<fmt:setBundle basename="order_detail" scope="request"/>
	<div class="container">
		<nav aria-label="breadcrumb"
			style="margin-left: 80px; margin-top: 15px; margin-bottom: 50px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#"
					style="color: green; text-decoration: none;"><fmt:message key="od.breadcrumHome"/></a></li>
				<li class="breadcrumb-item"><a href="#"
					style="color: green; text-decoration: none;"><fmt:message key="od.breadcrumProfile"/></a></li>
			</ol>
		</nav>



	</div>

	<div class="container mt-4"
		style="width: 1300px; height: 800px; margin-left: 140px;">
		<div class="row" style="margin-top: 10px; margin-left: 20px">
			<div class="col-12" style="margin-left: 80px; width: 250px;">
				<div class="user_Avata">
					<div class=" row">
						<div class="col-12 " style="margin-top: 20px; margin-left: 10px;">
							<div class="row">
								<div class="col-6">
									<div class="icon_user">
										<i class="fa-regular fa-user"></i>
									</div>
								</div>
								<div class="col-6 br_up"></div>
							</div>

						</div>
						<div class="col-12" border-top: 3px solid
							#f0f0f0;
                    style="width: 251px; height: 120px;; margin-top: -50px; z-index: -999; margin-left: 10px;">
							<div class="name_User" style="z-index: 999;">
								<label for="">${user.email}</label>
							</div>
						</div>
					</div>

				</div>
			</div>
			<!-- <div class="col-10 form_profile">
            <div class="titel_profile">
                <strong class="profile_email_titel">Hồ Sơ Của <a href="">manhntps37466@fpt.edu.vn</a>   </strong>
                <p>Quản lý thông tin hồ sơ để bảo mật tài khoản</p>
            </div>
            </div> -->
			<!-- background-color: red -->
		</div>
		<div class="row"
			style="width: 1200px;; margin-top: -200px; height: 600px;">

			<div class="col-3"
				style="width: 250px; margin-top: 200px; height: 287px; margin-left: 4px; border-bottom: 3px solid #f0f0f0; border-left: 3px solid #f0f0f0; border-right: 3px solid #f0f0f0;">
				<ul class="nav flex-column nav-pills set_btnMenu" id="myTab"
					role="tablist"
					style="margin-left: -12px; width: 249px; height: 300px;">
					<!-- <li class="nav-item" role="presentation" ">
                    <button  style="width: 200px; height: 100px;"  id="disabled-tab" data-bs-toggle="tab" data-bs-target="#disabled-tab-pane"
                        type="button" role="tab" aria-controls="disabled-tab-pane" aria-selected="false" disabled>
                    </button>
                </li> -->
					<li class="nav-item" role="presentation"
						style="border-top: 3px solid #f0f0f0; width: 248px;"><a
						href="<%=request.getContextPath()%>/client/profile">
							<button class="decor_btn_menu " type="button">
								<i class="fa-solid fa-user"
									style="font-size: 20px; margin-left: -37px; margin-right: 10px;"></i>
								<fmt:message key="od.menu1"/>
							</button>
					</a></li>
					<li class="nav-item" role="presentation"><a
						href="<%=request.getContextPath()%>/client/profile/order">
							<button class=" decor_btn_menu active" type="button">
								<i class="fa-solid fa-clock-rotate-left"
									style="font-size: 20px; margin-right: 10px;"></i><fmt:message key="od.menu2"/>
							</button>
					</a></li>
					<li class="nav-item" role="presentation"><a
						href="<%=request.getContextPath()%>/client/profile/address">
							<button class="decor_btn_menu " type="button" role="tab"
								aria-controls="profile-tab-pane" aria-selected="false">
								<i class="fa-solid fa-location-dot"
									style="font-size: 20px; margin-left: -5px; margin-right: 10px;"></i>
								<fmt:message key="od.menu3"/>
							</button>
					</a></li>
					<li class="nav-item" role="presentation"><a
						href="<%=request.getContextPath()%>/client/profile/change_password">
							<button class="decor_btn_menu" type="button">
								<i class="fa-solid fa-lock"
									style="font-size: 20px; margin-left: -37px; margin-right: 10px;"></i>
								<fmt:message key="od.menu4"/>
							</button>
					</a></li>



				</ul>
			</div>


			<!-- Tab Lịch Sử Mua Hàng -->
			<div class="col-9">
				<div class="tab-content tab_history" id="myTabContent">
					<div class="tab-pane fade show active" id="home-tab-pane"
						role="tabpanel" aria-labelledby="home-tab" tabindex="0">
						<div class="row">
							<div class="col-12 tab_Mini1">
								<strong><fmt:message key="od.title"/></strong>
							</div>
							<div class="col-12 tab_Mini2">
								<div class="row">
									<div class="col-12"
										style="background-color: #ffffff; width: 900px; height: 618px;">
										<div class="row ">
											<div class="col-12 decor_detailBill_adress">
												<label for=""><i
													class="fa-solid fa-cart-flatbed-suitcase"
													style="margin-right: 5px;"></i><fmt:message key="od.title"/></label>
												<p>
													<i class="fa-solid fa-caret-right"
														style="color: black; font-size: 10px; margin-right: 4px;"></i>
													${nameUser}
												</p>
												<p>
													<i class="fa-solid fa-caret-right"
														style="color: black; font-size: 10px; margin-right: 4px;"></i>
													${phone}
												</p>
												<p>
													<i class="fa-solid fa-caret-right"
														style="color: black; font-size: 10px; margin-right: 4px;"></i>
													${shipping_address}
												</p>
											</div>
											<div class="col-12 decor_detailBill_product">
												<label for="" style="margin-left: 5px;"><i
													class="fa-solid fa-apple-whole" style="margin-right: 5px;"></i></i><fmt:message key="od.detail"/></label>
												<div class="row">

													<div class="col-12 decor_detailBill_items">

															<c:forEach var="o" items="${listOrderDetail}">
	
	
																<div class="row item_detail">
																	<div class="col-3" style="width: 120px;">
																		<img src="${pageContext.request.contextPath}/views/imgs/products/${o.thumbnail}" alt=""
																			style="width: 100px; height: 100px; overflow: auto; margin-top: 5px; margin-left: 20px;">
																	</div>
																	<div class="col-9"
																		style="margin-left: 10px; width: 230px;">
																		<div class="row">
																			<div class="col-12 name_items">
																				<label for=""></label> <strong
																					style="margin-right: 3px;">${o.name} </strong> <label
																					for="" style="font-size: 10px;">(Loại 1)</label></label>
																			</div>
	
																			<div class="col-12 quantity_item">
																				<p>
																					<fmt:message key="od.qty"/>: <label for="">${o.qty} </label> <label for=""
																						style="margin-left: 50px; color: #198754; margin-left: 210px; font-style: italic;"><fmt:formatNumber type = "number" 
         										pattern="#,###"   value = "${o.price}" /></label>
																					<label for=""
																						style="color: #198754; font-style: italic">VNĐ</label>
	
																					<label for=""
																						style="margin-left: 50px; color: red; margin-left: 200px; font-size: 20px; font-style: italic">
																						<fmt:formatNumber type = "number" 
         										pattern="#,###"   value = "${o.total}" /></label>
																					<label for="" style="color: red; font-style: italic">VNĐ</label>
																				</p>
																			</div>
																		</div>
	
	
																	</div>
																</div>
	
															</c:forEach>





													</div>


												</div>

											</div>
											<div class="col-12 total_items">
												<div class="row" style="margin-top: 15px;">
													<div class="col-12">
														<label for="" style="margin-left: 5px;"><strong
															style="font-size: 15px; font-weight: bold; margin-top: 5px;"><i
																class="fa-solid fa-receipt"></i> <fmt:message key="od.total"/></strong> <label
															for=""
															style="margin-left: 70px; font-size: 12px; font-weight: bold; color: #198754;"><label
																for="" style="margin-right: 10px;">SL : </label><label
																for="" style="font-size: 15px;">
																<fmt:formatNumber type = "number" 
         										pattern="#,###"   value = "${qtyAll}" /></label></label> <label for=""></label>
															<label for=""
															style="font-size: 20px; font-weight: bold; color: red; margin-left: 440px; font-family: Verdana, Geneva, Tahoma, sans-serif;"><label
																for="">
																<fmt:formatNumber type = "number" 
         										pattern="#,###"   value = "${totalAll}" /></label><label for="">VNĐ</label></label> </label>
													</div>
												</div>

											</div>

											<div class="col-12 decor_detailBill_adress"
												style="margin-top: 12px; height: 60px;">
												<label for=""><i
													class="fa-solid fa-money-check-dollar"
													style="margin-right: 6px;"></i></i><fmt:message key="od.method"/> </label>


												<p>
													<i class="fa-solid fa-caret-right"
														style="color: black; font-size: 10px; margin-right: 4px;"></i>${payment_type_name }
												</p>

											</div>
										</div>
									</div>


								</div>


							</div>
						</div>

					</div>
					<!--             
            <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample"
                aria-labelledby="offcanvasExampleLabel">
                <div class="offcanvas-header">
                    <h5 class="offcanvas-title" id="offcanvasExampleLabel">
                        <label for=""
                            style="font-size: 20px; font-weight: bold; font-style: italic; margin-top: 40px;margin-left: 50px; ">
                            THÔNG TIN ĐƠN HÀNG </label>

                    </h5>
                </div>
                <div class="offcanvas-body">
                    <div class="row">
                        <div class="col-12" style="background-color: #ffffff; width: 400px;height: 670px;">
                            <div class="row ">
                                <div class="col-12 decor_detailBill_adress">
                                    <label for=""><i class="fa-solid fa-cart-flatbed-suitcase"
                                            style="margin-right: 5px;"></i>Thông tin giao hàng</label>
                                    <p><i class="fa-solid fa-caret-right" style="color: black; font-size: 10px; margin-right: 4px;"></i>Nguyễn Thanh Mạnh</p>
                                    <p><i class="fa-solid fa-caret-right" style="color: black; font-size: 10px; margin-right: 4px;"></i>0116697129</p>
                                    <p><i class="fa-solid fa-caret-right" style="color: black; font-size: 10px; margin-right: 4px;"></i>QTSC 9 Building, Đ. Tô
                                        Ký, Tân Chánh Hiệp, Quận 12, Hồ Chí Minh</p>
                                </div>
                                <div class="col-12 decor_detailBill_product">
                                    <label for=""><i class="fa-solid fa-apple-whole"
                                            style="margin-right: 5px;"></i></i>Thông tin sản phẩm</label>
                                            <div class="row">
                                                <div class="col-12 decor_detailBill_items">
                                                    <div class="row item_detail" >
                                                        <div class="col-3">
                                                            <img src="/imgs/products/apple1.png" alt="" style=" width: 100px; height: 100px; overflow: auto; margin-top: 5px;">
                                                        </div>
                                                        <div class="col-9" style="margin-left: 10px; width: 230px;">
                                                            <div class="row">
                                                                <div class="col-12 name_items">
                                                                   <label for=""></label> <strong style="margin-right: 3px;">Táo Vàng </strong> <label for="" style="font-size: 10px;">(Loại 1)</label></label>
                                                                </div>
                                                                
                                                                 <div class="col-12 quantity_item">
                                                                    <p>Số Lượng : <label for="">3</label> <label for="" style="margin-left: 50px;color: red;margin-left: 40px;">10.000</label> <label for="" style=" color: red;">đ</label></p>
                                                                 </div>
                                                            </div>
                                                            
                                                        </div>
                                                    </div>
                                                    <div class="row item_detail" >
                                                        <div class="col-3">
                                                            <img src="/imgs/products/apple2.png" alt="" style=" width: 100px; height: 100px; overflow: auto; margin-top: 5px;">
                                                        </div>
                                                        <div class="col-9" style="margin-left: 10px; width: 230px;">
                                                            <div class="row">
                                                                <div class="col-12 name_items">
                                                                   <label for=""></label> <strong style="margin-right: 3px;">Táo Vàng </strong> <label for="" style="font-size: 10px;">(Loại 1)</label></label>
                                                                </div>
                                                                
                                                                 <div class="col-12 quantity_item">
                                                                    <p>Số Lượng : <label for="">3</label> <label for="" style="margin-left: 50px;color: red;margin-left: 40px;">10.000.000</label> <label for="" style=" color: red;">đ</label></p>
                                                                 </div>
                                                            </div>
                                                            
                                                        </div>
                                                    </div>
                                                    <div class="row item_detail" >
                                                        <div class="col-3">
                                                            <img src="/imgs/products/banana1.png" alt="" style=" width: 100px; height: 100px; overflow: auto; margin-top: 5px;">
                                                        </div>
                                                        <div class="col-9" style="margin-left: 10px; width: 230px;">
                                                            <div class="row">
                                                                <div class="col-12 name_items">
                                                                   <label for=""></label> <strong style="margin-right: 3px;">Táo Vàng </strong> <label for="" style="font-size: 10px;">(Loại 1)</label></label>
                                                                </div>
                                                                
                                                                 <div class="col-12 quantity_item">
                                                                    <p>Số Lượng : <label for="">3</label> <label for="" style="margin-left: 50px;color: red;margin-left: 40px;">10.000.000</label> <label for="" style=" color: red;">đ</label></p>
                                                                 </div>
                                                            </div>
                                                            
                                                        </div>
                                                    </div>         
                                                    <div class="row item_detail" >
                                                        <div class="col-3">
                                                            <img src="/imgs/products/banana2.png" alt="" style=" width: 100px; height: 100px; overflow: auto; margin-top: 5px;">
                                                        </div>
                                                        <div class="col-9" style="margin-left: 10px; width: 230px;">
                                                            <div class="row">
                                                                <div class="col-12 name_items">
                                                                   <label for=""></label> <strong style="margin-right: 3px;">Táo Vàng </strong> <label for="" style="font-size: 10px;">(Loại 1)</label></label>
                                                                </div>
                                                                
                                                                 <div class="col-12 quantity_item">
                                                                    <p>Số Lượng : <label for="">3</label> <label for="" style="margin-left: 50px;color: red;margin-left: 40px;">10.000.000</label> <label for="" style=" color: red;">đ</label></p>
                                                                 </div>
                                                            </div>
                                                            
                                                        </div>
                                                    </div>                                            
                                                </div>
                                            </div>
                                            
                                </div>
                                <div class="col-12 total_items" >
                                    <div class="row" style="margin-top: 15px;" >
                                        <div class="col-12">
                                            <label for=""><strong style="font-size: 13px; font-weight: bold;margin-top: 5px;    "><i class="fa-solid fa-receipt"></i> Thành Tiền</strong> 
                                                <label for="" style="margin-left: 60px;font-size: 12px; font-weight: bold; color: #198754;"><label for="" style="margin-right: 10px;">SL : </label><label for="" style="font-size: 15px;">10</label></label>
                                            <label for=""></label> 
                                                
                                            <label for="" style="font-size: 20px;font-weight: bold;color: red;margin-left: 30px;font-family: Verdana, Geneva, Tahoma, sans-serif;"><label for="">200,000</label><label for="">đ</label></label>
                                        </label>
                                        </div>
                                    </div>

                                </div>

                                <div class="col-12 decor_detailBill_adress" style="margin-top: 12px; height: 60px;">
                                    <label for=""><i class="fa-solid fa-money-check-dollar" style="margin-right: 6px;"></i></i>Phương thức thanh toán </label>


                                    <p><i class="fa-solid fa-caret-right" style="color: black; font-size: 10px; margin-right: 4px;"></i>Ví Momo</p>
                                    
                                </div>
                            </div>
                        </div>


                    </div>
                 
                </div>
            </div>
            <div class="offcanvas offcanvas-bottom ofCan_address" tabindex="-1" id="offcanvasBottom" aria-labelledby="offcanvasBottomLabel" >
                <div class="offcanvas-header " style="width: auto;height: auto; ">
                  <h5 class="offcanvas-title title_decor" id="offcanvasBottomLabel"><label for="" >Chỉnh Sửa Địa Chỉ</label></h5>
                </div>
                <div class="offcanvas-body small body_decor"  >
                    <form>
                        <div class="mb-3 form_decor">
                          <label for="exampleInputEmail1" class="form-label">SỐ NHÀ - TÊN ĐƯỜNG </label>
                          <input   class="form-control" id="" >
                        </div>
                        <div class="mb-3 form_decor">
                          <label for="exampleInputPassword1" class="form-label">PHƯỜNG - XÃ (QUẬN)</label>
                          <input   class="form-control" id=" ">
                        </div>
                        <div class="mb-3 form_decor">
                            <label for="exampleInputPassword1" class="form-label">THÀNH PHỐ - HUYỆN</label>
                            <input   class="form-control" id=" ">
                          </div>
                        <div class="mb-3 form-check form_decor">
                          <input type="checkbox" class="form-check-input" id="exampleCheck1">
                          <label class="form-check-label" for="exampleCheck1" style="color: #198754;">Bạn có chắc với thông tin trên ?</label>
                        </div>
                        <button type="submit btn_address" class="btn btn-primary" style="width: 110px; height: 42px;background-color: #198754;border: none;font-size: 16px;">CHỈNH SỬA</button>
                      </form>
                </div>
              </div>
 -->


					<!-- 
              <div class="offcanvas offcanvas-bottom infor" tabindex="-1" id="offcanvasBottom_infor" aria-labelledby="offcanvasBottomLabel_infor" >
                <div class="offcanvas-header " style="width: auto;height: auto; ">
                  <h5 class="offcanvas-title title_decor_phone" id="offcanvasBottomLabel_infor"><label for="" >Chỉnh Sửa Số Điện Thoại</label></h5>
                </div>
                <div class="offcanvas-body small body_decor"  >
                    <form>
                        <div class="mb-3  ">
                          <label for="exampleInputEmail1" class="form-label">Số Điện Thoại</label>
                          <input   class="form-control" id="" >
                        </div>
                        <div class="mb-3 form-check  ">
                          <input type="checkbox" class="form-check-input" id="exampleCheck1">
                          <label class="form-check-label" for="exampleCheck1" style="color: #198754;">Bạn có chắc với thông tin trên ?</label>
                        </div>
                        <button type="submit btn_address" class="btn btn-primary" style="width: 110px; height: 42px;background-color: #198754;border: none;font-size: 16px;">CHỈNH SỬA</button>
                      </form>
                </div>
              </div> -->

					<!-- 
              <div class="offcanvas offcanvas-bottom infor_name" tabindex="-1" id="offcanvasBottom_infor_name" aria-labelledby="offcanvasBottomLabel_infor_name" >
                <div class="offcanvas-header " style="width: auto;height: auto; ">
                  <h5 class="offcanvas-title title_decor_phone" id="offcanvasBottomLabel_infor"><label for="" >Chỉnh Sửa Họ và Tên</label></h5>
                </div>
                <div class="offcanvas-body small body_decor"  >
                    <form>
                        <div class="mb-3  ">
                          <label for="exampleInputEmail1" class="form-label">Họ và Tên</label>
                          <input   class="form-control" id="" >
                        </div>
                        <div class="mb-3 form-check  ">
                          <input type="checkbox" class="form-check-input" id="exampleCheck1">
                          <label class="form-check-label" for="exampleCheck1" style="color: #198754;">Bạn có chắc với thông tin trên ?</label>
                        </div>
                        <button type="submit btn_address" class="btn btn-primary" style="width: 110px; height: 42px;background-color: #198754;border: none;font-size: 16px;">CHỈNH SỬA</button>
                      </form>
                </div>
              </div> -->
				</div>
			</div>
		</div>
	</div>
	</div>

	</div>
	<%@include file="/views/ClientTemplate/layout/footer.jsp"%>


	<script
		src="<%=request.getContextPath()%>/views/ClientTemplate/js/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/views/ClientTemplate/js/RenderCities.js"></script>

</body>
</html>
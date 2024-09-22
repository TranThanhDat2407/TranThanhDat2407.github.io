<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Brand Admin</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/AdminTemplate/assets/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/AdminTemplate/assets/vendors/ti-icons/css/themify-icons.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/AdminTemplate/assets/vendors/css/vendor.bundle.base.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/AdminTemplate/assets/vendors/font-awesome/css/font-awesome.min.css">
<!-- endinject -->
<!-- Plugin css for this page -->
<!-- End Plugin css for this page -->
<!-- inject:css -->
<!-- endinject -->
<!-- Layout styles -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/AdminTemplate/assets/css/style.css">
<!-- End layout styles -->
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/views/AdminTemplate/assets/images/favicon.png" />
<style>
.form-check .form-check-label input[type=radio]+.input-helper:before {
	border: solid #1bcfb4 !important;
}
</style>
</head>
<body>
	<div class="container-scroller">

		<!-- partial:partials/_navbar.html -->
		<nav
			class="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
			<div
				class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-start">
				<a class="navbar-brand text-success fs-2 fw-bold"
					href="<%=request.getContextPath()%>/admin">BRAND</a>

			</div>
			<div class="navbar-menu-wrapper d-flex align-items-stretch">

				<ul class="navbar-nav navbar-nav-right">
					<li class="nav-item nav-profile dropdown"><a
						class="nav-link dropdown-toggle" id="profileDropdown" href="#"
						data-bs-toggle="dropdown" aria-expanded="false">
							<div class="nav-profile-text">
								<p class="mb-1 text-black">${adminName}</p>
							</div>
					</a>
						<div class="dropdown-menu navbar-dropdown"
							aria-labelledby="profileDropdown">
							<a class="dropdown-item"
								href="<%=request.getContextPath()%>/admin/logout"> <i
								class="mdi mdi-logout me-2 text-success"></i> Signout
							</a>
						</div></li>
					<li class="nav-item d-none d-lg-block full-screen-link"><a
						class="nav-link"> <i class="mdi mdi-fullscreen"
							id="fullscreen-button"></i>
					</a></li>
				</ul>

			</div>
		</nav>
		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<!-- partial:partials/_sidebar.html -->
			<nav class="sidebar sidebar-offcanvas" id="sidebar">
				<ul class="nav">

					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/admin"> <span
							class="menu-title ">Dashboard</span> <i
							class="mdi mdi-home menu-icon"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/admin/user"> <span
							class="menu-title ">Users</span> <i
							class="fa fa-user-circle menu-icon "></i>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/admin/category"> <span
							class="menu-title">Category</span> <i
							class="fa fa-list menu-icon"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/admin/products"> <span
							class="menu-title">Products</span> <i
							class="fa fa-apple menu-icon"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/admin/orders"> <span
							class="menu-title">Orders</span> <i
							class="fa fa-dropbox menu-icon"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> <span
							class="menu-title text-success">Statistics</span> <i
							class="fa fa-signal menu-icon text-success"></i>
					</a></li>
				</ul>
			</nav>
			<!-- partial -->
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="page-header">
						<h3 class="page-title">
							<span class="page-title-icon bg-gradient-success text-white me-2">
								<i class="fa fa-signal"></i>
							</span> Statistics
						</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item active" aria-current="page"><span></span>Overview
									<i
									class="mdi mdi-alert-circle-outline icon-sm text-success align-middle"></i>
								</li>
							</ul>
						</nav>
					</div>

					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item" role="presentation">
							<button class="nav-link active text-success" id="home-tab"
								data-bs-toggle="tab" data-bs-target="#home" type="button"
								role="tab" aria-controls="home" aria-selected="true">Total
								Revenue</button>
						</li>
						<li class="nav-item">
							<button class="nav-link text-success" id="profile-tab"
								data-bs-toggle="tab" data-bs-target="#profile" type="button"
								role="tab" aria-controls="profile" aria-selected="false">Best
								Sell Product</button>
						</li>
						<li class="nav-item">
							<button class="nav-link text-success" id="Address-tab"
								data-bs-toggle="tab" data-bs-target="#Address" type="button"
								role="tab" aria-controls="Address" aria-selected="false">Orders</button>
						</li>
						<li class="nav-item">
							<button class="nav-link text-success" id="AddressTable-tab"
								data-bs-toggle="tab" data-bs-target="#AddressTable"
								type="button" role="tab" aria-controls="AddressTable"
								aria-selected="false">Users</button>
						</li>
					</ul>
					<div class="tab-content" id="myTabContent">
					
					<div class="tab-content" id="myTabContent">
						<!--Renueve Form -->
						<div class="tab-pane fade show active" id="home" role="tabpanel"
							aria-labelledby="home-tab">

							<div class="col-md-12 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">Total Revenue</h4>
										<p class="card-description">Please fill all the
											information.</p>
										<form class="forms-sample"
											action="<%=request.getContextPath()%>/admin/static/fillTotalRevenue"
											method="get">
											<div class="row">
												<div class="form-group col-5">
													<label for="exampleFormControlSelect2">Month</label> <select
														name="mounth_totalRevenue" class="form-select"
														id="exampleFormControlSelect2">
														<option value="0"></option>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
														<option value="5">5</option>
														<option value="6">6</option>
														<option value="7">7</option>
														<option value="8">8</option>
														<option value="9">9</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
													</select>
												</div>

												<div class="form-group col-5">
													<label for="exampleFormControlSelect2">Year</label> <select
														name="year_totalRevenue" class="form-select"
														id="exampleFormControlSelect2">
														<option value="0"></option>
														<option value="2020">2020</option>
														<option value="2021">2021</option>
														<option value="2022">2022</option>
														<option value="2023">2023</option>
														<option value="2024">2024</option>
													</select>
												</div>

												<div class="form-group col-2" style="margin-top: 20px;">
													<label for="exampleFormControlSelect2"> <a
														href="<%=request.getContextPath()%>/admin/static/fillTotalRevenue">
															<div class="form-group col-2" style="margin-top: 20px;">
																<button type="submit" class="btn"
																	style="background: #32d1bb; color: white;">Info</button>
															</div>
													</a></label>
												</div>
											</div>
										</form>
										<div class="row">
											<div class="col-md-12 grid-margin stretch-card">
												<table class="table table-striped">
													<thead>
														<tr>
															<th>Date</th>
															<th>Total Reneuve</th>
														</tr>
													</thead>
													<tbody>
														<c:choose>
															<c:when test="${not empty listRequest}">
																<c:forEach var="o" items="${listRequest}">
																	<tr>
																		<td><c:out value="${o[0]}" /></td>
																		<td><c:out value="${o[1]}" /></td>
																	</tr>
																</c:forEach>


															</c:when>
															<c:otherwise>
																<tr>
																	<td colspan="2" style="color: red; margin-left: 200px;">Thông
																		tin trống hoặc bạn chưa chọn Tháng hoặc Năm</td>

																</tr>
															</c:otherwise>
														</c:choose>
													</tbody>
												</table>
											</div>

										</div>

									</div>
								</div>
							</div>
						</div>
						<!--Renueve Form -->

						<!--Top Sell Product Table -->
						<div class="tab-pane fade" id="profile" role="tabpanel"
							aria-labelledby="profile-tab">
							<div class="col-lg-12 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">Top Sell Products</h4>

										<table class="table table-striped">
											<thead>
												<tr>
													<th>ID</th>
													<th>Product IMG</th>
													<th>Product Name</th>
													<th>Total Sold Quantity</th>
													<th>Total Revenue</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="p" items="${listTopProduct}">
													<tr>
														<td>${p.id}</td>
														<td class="py-1"><img
															src="${pageContext.request.contextPath}/views/imgs/products/${p.thumbnail}"
															alt="image" /></td>
														<td>${p.name}</td>
														<td>${p.totalSoldQuantity}</td>
														<td>${p.totalRevenue}VND</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<!-- User Table -->


						<!-- Address-form -->
						<div class="tab-pane fade" id="Address" role="tabpanel"
							aria-labelledby="Address">
							<div class="col-md-12 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">Orders</h4>
										<form class="forms-sample" action="<%=request.getContextPath()%>/admin/static/OrdersCount" method="get">
											<div class="row">
												<div class="form-group col-5">
													<label for="exampleFormControlSelect2">Month</label> <select
														name="month_OrdersCount" class="form-select" id="exampleFormControlSelect2">
														<option value="0"></option>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
														<option value="5">5</option>
														<option value="6">6</option>
														<option value="7">7</option>
														<option value="8">8</option>
														<option value="9">9</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
													</select>
												</div>

												<div class="form-group col-5">
													<label for="exampleFormControlSelect2">Year</label> <select
													name="year_OrdersCount"	class="form-select" id="exampleFormControlSelect2">
														<option value="0"></option>
														<option value="2020">2020</option>
														<option value="2021">2021</option>
														<option value="2022">2022</option>
														<option value="2023">2023</option>
														<option value="2024">2024</option>
													</select>
												</div>
												<div class="form-group col-2" style="margin-top: 20px;">
													<a href="<%=request.getContextPath()%>/admin/static/OrdersCount"><label for="exampleFormControlSelect2"><button
															type="submit" class="btn "
															style="background: #32d1bb; color: white;">Info</button></label></a>
												</div>
											</div>
										</form>
									</div>

									<div class="col-lg-12 grid-margin stretch-card">
										<div class="card">
											<div class="card-body">

												<table class="table table-striped">
													<thead>
														<tr>
															<th>Date</th>
															<th>Total Orders</th>
														</tr>
													</thead>
													<tbody>
													<c:choose>
															<c:when test="${not empty listOrderCount}">
																<c:forEach var="o" items="${listOrderCount}">
																	<tr>
																		<td><c:out value="${o[0]}" /></td>
																		<td><c:out value="${o[1]}" /></td>
																	</tr>
																</c:forEach>


															</c:when>
															<c:otherwise>
																<tr>
																	<td colspan="2" style="color: red; margin-left: 200px;">Thông
																		tin trống hoặc bạn chưa chọn Tháng hoặc Năm</td>

																</tr>
															</c:otherwise>
														</c:choose>
													</tbody>
												</table>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>



						<!-- Address-Form -->

						<!-- Address Table -->
						<div class="tab-pane fade" id="AddressTable" role="tabpanel"
							aria-labelledby="AddressTable-tab">
							<div class="col-md-12 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">Users</h4>
										<form class="forms-sample" action="<%=request.getContextPath()%>/admin/static/UsersCount"  method="get">
											<div class="row">
												<div class="form-group col-5">
													<label for="exampleFormControlSelect2">Month</label> <select
												name="month_UsersCount"		class="form-select" id="exampleFormControlSelect2">
														<option value="0"></option>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
														<option value="5">5</option>
														<option value="6">6</option>
														<option value="7">7</option>
														<option value="8">8</option>
														<option value="9">9</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
													</select>
												</div>

												<div class="form-group col-5">
													<label for="exampleFormControlSelect2">Year</label> <select
													 name="year_UsersCount"		class="form-select" id="exampleFormControlSelect2">
														<option value="0"></option>
														<option value="2020">2020</option>
														<option value="2021">2021</option>
														<option value="2022">2022</option>
														<option value="2023">2023</option>
														<option value="2024">2024</option>
													</select>
												</div>

												<div class="form-group col-2" style="margin-top: 20px;">
													<label for="exampleFormControlSelect2">
													<a href="<%=request.getContextPath()%>/admin/static/UsersCount"><button
															type="submit" class="btn "
															style="background: #32d1bb; color: white;">Info</button></a></label>
												</div>
											</div>
										</form>
									</div>

									<div class="row">
										<div class="col-lg-12 grid-margin stretch-card">
											<div class="card">
												<div class="card-body">
													</p>
													<table class="table table-striped">
														<thead>
															<tr>
																<th>Date</th>
																<th>Total Users</th>
															</tr>
														</thead>
														<tbody>
															<c:choose>
															<c:when test="${not empty listUsersCount}">
																<c:forEach var="o" items="${listUsersCount}">
																	<tr>
																		<td><c:out value="${o[0]}" /></td>
																		<td><c:out value="${o[1]}" /></td>
																	</tr>
																</c:forEach>


															</c:when>
															<c:otherwise>
																<tr>
																	<td colspan="2" style="color: red; margin-left: 200px;">Thông
																		tin trống hoặc bạn chưa chọn Tháng hoặc Năm</td>

																</tr>
															</c:otherwise>
														</c:choose>
														</tbody>
													</table>
												</div>
											</div>
										</div>

									</div>
									<!-- Address Table -->
								</div>
							</div>
						</div>
					</div>





				</div>
				<!-- content-wrapper ends -->
				<!-- partial:../../partials/_footer.html -->
				<footer class="footer">
					<div
						class="d-sm-flex justify-content-center justify-content-sm-between">
						<span
							class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright
							Â© 2023 <a href="https://www.bootstrapdash.com/" target="_blank">BootstrapDash</a>.
							All rights reserved.
						</span> <span
							class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted
							& made with <i class="mdi mdi-heart text-danger"></i>
						</span>
					</div>
				</footer>
				<!-- partial -->
			</div>
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>


	<!-- container-scroller -->
	<!-- plugins:js -->
	<script
		src="<%=request.getContextPath()%>/views/AdminTemplate/assets/vendors/js/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page -->
	<!-- End plugin js for this page -->
	<!-- inject:js -->
	<script
		src="<%=request.getContextPath()%>/views/AdminTemplate/assets/js/off-canvas.js"></script>
	<script
		src="<%=request.getContextPath()%>/views/AdminTemplate/assets/js/misc.js"></script>
	<script
		src="<%=request.getContextPath()%>/views/AdminTemplate/assets/js/settings.js"></script>
	<script
		src="<%=request.getContextPath()%>/views/AdminTemplate/assets/js/todolist.js"></script>
	<script
		src="<%=request.getContextPath()%>/views/AdminTemplate/assets/js/jquery.cookie.js"></script>
	<!-- endinject -->
</body>
</html>
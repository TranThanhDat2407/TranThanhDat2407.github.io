<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BRAND ADMIN</title>
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/AdminTemplate/assets/vendors/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/AdminTemplate/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<!-- endinject -->
<!-- Layout styles -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/AdminTemplate/assets/css/style.css">
<!-- End layout styles -->
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/views/AdminTemplate/assets/images/favicon.png" />
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
</head>
<body>
	<div class="container-scroller">

		<!-- partial:partials/_navbar.html -->
		<nav
			class="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
			<div
				class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-start">
				<a class="navbar-brand text-success fs-2 fw-bold" href="#">BRAND</a>

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
							<a class="dropdown-item" href="<%=request.getContextPath()%>/admin/logout"> <i
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

					<li class="nav-item"><a class="nav-link" href="#"> <span
							class="menu-title text-success">Dashboard</span> <i
							class="mdi mdi-home menu-icon text-success fw-bold"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/admin/user"> <span
							class="menu-title">Users</span> <i
							class="fa fa-user-circle menu-icon"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/admin/category">
							<span class="menu-title">Category</span> <i
							class="fa fa-list menu-icon"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/admin/products">
							<span class="menu-title">Products</span> <i
							class="fa fa-apple menu-icon"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/admin/orders"> <span class="menu-title">Orders</span>
							<i class="fa fa-dropbox menu-icon"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/admin/static"> <span class="menu-title">Statistics</span>
							<i class="fa fa-signal menu-icon"></i>
					</a></li>
				</ul>
			</nav>
			<!-- partial -->
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="page-header">
						<h3 class="page-title">
							<span class="page-title-icon bg-gradient-success text-white me-2">
								<i class="mdi mdi-home"></i>
							</span> Dashboard
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
					<div class="row">
						<div class="col-md-4 stretch-card grid-margin">
							<div class="card bg-gradient-danger card-img-holder text-white">
								<div class="card-body">
									<img
										src="<%=request.getContextPath()%>/views/AdminTemplate/assets/images/dashboard/circle.svg"
										class="card-img-absolute" alt="circle-image" />
									<h4 class="font-weight-normal mb-3">
										Monthly Sales <i class="mdi mdi-chart-line mdi-24px float-end"></i>
									</h4>
									<h2 class="mb-5"><fmt:formatNumber type = "number" 
         										pattern="#,###"   value = "${totalRevenue}" /> VNĐ</h2>

								</div>
							</div>
						</div>
						<div class="col-md-4 stretch-card grid-margin">
							<div class="card bg-gradient-info card-img-holder text-white">
								<div class="card-body">
									<img
										src="<%=request.getContextPath()%>/views/AdminTemplate/assets/images/dashboard/circle.svg"
										class="card-img-absolute" alt="circle-image" />
									<h4 class="font-weight-normal mb-3">
										Monthly Orders <i
											class="mdi mdi-bookmark-outline mdi-24px float-end"></i>
									</h4>
									<h2 class="mb-5">${countOrders}</h2>
								</div>
							</div>
						</div>
						<div class="col-md-4 stretch-card grid-margin">
							<div class="card bg-gradient-success card-img-holder text-white">
								<div class="card-body">
									<img
										src="<%=request.getContextPath()%>/views/AdminTemplate/assets/images/dashboard/circle.svg"
										class="card-img-absolute" alt="circle-image" />
									<h4 class="font-weight-normal mb-3">
										Monthly Users <i class="mdi mdi-diamond mdi-24px float-end"></i>
									</h4>
									<h2 class="mb-5">${countUsers}</h2>

								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Top 5 Best Sell Products</h4>
									</p>
									<table class="table table-striped">
										<thead>
											<tr>
												<th>No.</th>
												<th>Product ID</th>
												<th>Product IMG</th>
												<th>Product Name</th>
												<th>Product Price</th>
												<th>Total Sold Quantity</th>
												<th>Total Revenue</th>
											</tr>
										</thead>
										<tbody>
										<c:set var="index" value="1"></c:set>
										<c:forEach items="${top5Proc}" var="product">
										<c:if test="${index <=5}">
											<tr>
												<td>${index}</td>
												<td>${product.id}</td>
												<td class="py-1"><img
													src="<%=request.getContextPath()%>/views/imgs/products/${product.thumbnail}"
													alt="image" /></td>
												<td>${product.name}</td>
												<td><fmt:formatNumber type = "number" 
         										pattern="#,###"   value = "${product.price}" /> VNĐ / kg</td>
												<td>${product.totalSoldQuantity} kg</td>
												<td><fmt:formatNumber type = "number" 
         										pattern="#,###"   value = "${product.totalRevenue}" /> VNĐ</td>
											</tr>
											
											   <c:set var="index" value="${index + 1}" />
										</c:if>
									  </c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>

					</div>
				</div>
				<!-- content-wrapper ends -->
				<!-- partial:partials/_footer.html -->
				<footer class="footer">
					<div
						class="d-sm-flex justify-content-center justify-content-sm-between">
						<span
							class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright
							© 2023 <a href="https://www.bootstrapdash.com/" target="_blank">BootstrapDash</a>.
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
	<script
		src="<%=request.getContextPath()%>/views/assets/vendors/chart.js/chart.umd.js"></script>
	<script
		src="<%=request.getContextPath()%>/views/assets/vendors/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
	<!-- End plugin js for this page -->
	<!-- inject:js -->
	<script
		src="<%=request.getContextPath()%>/views/assets/js/off-canvas.js"></script>
	<script src="<%=request.getContextPath()%>/views/assets/js/misc.js"></script>
	<script src="<%=request.getContextPath()%>/views/assets/js/settings.js"></script>
	<script src="<%=request.getContextPath()%>/views/assets/js/todolist.js"></script>
	<script
		src="<%=request.getContextPath()%>/views/assets/js/jquery.cookie.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page -->
	<script
		src="<%=request.getContextPath()%>/views/assets/js/dashboard.js"></script>
	<!-- End custom js for this page -->
</body>
</html>
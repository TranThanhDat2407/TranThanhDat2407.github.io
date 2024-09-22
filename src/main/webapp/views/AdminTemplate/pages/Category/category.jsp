<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BRAND Admin</title>
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
					<li class="nav-item"><a class="nav-link" href="#"> <span
							class="menu-title text-success">Category</span> <i
							class="fa fa-list menu-icon text-success"></i>
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
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/admin/static"> <span
							class="menu-title">Statistics</span> <i
							class="fa fa-signal menu-icon"></i>
					</a></li>
				</ul>
			</nav>
			<!-- partial -->
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="page-header">
						<h3 class="page-title">
							<span class="page-title-icon bg-gradient-success text-white me-2">
								<i class="fa fa-list"></i>
							</span> Category
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
								role="tab" aria-controls="home" aria-selected="true">Category
								Form</button>
						</li>
						<li class="nav-item">
							<button class="nav-link text-success" id="profile-tab"
								data-bs-toggle="tab" data-bs-target="#profile" type="button"
								role="tab" aria-controls="profile" aria-selected="false">Category
								Table</button>
						</li>
					</ul>
					<div class="tab-content" id="myTabContent">
						<!--users Form -->
						<div class="tab-pane fade show active" id="home" role="tabpanel"
							aria-labelledby="home-tab">
							<div class="col-md-12 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">Category Form</h4>
										<p class="card-description">Please fill all the
											information.</p>
										<form class="forms-sample" action="admin/category"
											method="post">
											<div class="form-group row">
												<label for="exampleInputUsername2"
													class="col-sm-3 col-form-label">ID</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" name="id"
														value="${categoryEdit.id}" disabled>
												</div>
											</div>

											<div class="form-group row">
												<label for="exampleInputUsername2"
													class="col-sm-3 col-form-label">Parent Category</label>
												<div class="col-sm-9">
													
													<select class="form-select" id="parentCategory"
														name="parentCategory">
														
														<option value="" ${ categoryEdit.parentCategory.id == '' ? 'selected' : ''}></option>
														<c:forEach items="${listCategory}" var="category">
															<c:if test="${category.id != categoryEdit.id}">
																<option value="${category.id}"
																	${category.id == categoryEdit.parentCategory.id ? 'selected' : ''}>
																	 ${category.id} - ${category.name}</option>
															</c:if>
														</c:forEach>
													</select>

												</div>
											</div>
											<div class="form-group row">
												<label for="exampleInputEmail2"
													class="col-sm-3 col-form-label">Name</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" name="name"
														value="${param.name != null ? param.name :categoryEdit.name}">
													<c:if test="${not empty errorName}">
														<small class="text-danger">${errorName}</small>
													</c:if>
												</div>
											</div>



											<button type="submit"
												formaction="<%=request.getContextPath()%>/admin/category/create"
												class="btn btn-gradient-success me-2">Create</button>
											<button type="submit"
												formaction="<%=request.getContextPath()%>/admin/category/update?id=${categoryEdit.id}"
												class="btn btn-gradient-success me-2">Update</button>
											<button
												formaction="<%=request.getContextPath()%>/admin/category"
												class="btn btn-light">Cancel</button>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!--users Form -->

						<!--users Table -->
						<div class="tab-pane fade" id="profile" role="tabpanel"
							aria-labelledby="profile-tab">
							<div class="col-lg-12 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">Category Table</h4>
										<form>
											<div class="form-group">
												<div class="input-group">

													<input type="text" class="form-control"
														placeholder="Category" aria-label="Recipient's username"
														name="findname" aria-describedby="basic-addon2"
														value="${findname}">
													<div class="input-group-append">
														<button
															formaction="<%=request.getContextPath()%>/admin/category/search?findname=${findName}"
															class="btn btn-sm btn-gradient-success py-3"
															type="submit">Search</button>
													</div>
												</div>
										</form>
									</div>
									</p>
									<table class="table table-striped">
										<thead>
											<tr>
												<th>No.</th>
												<th>ID</th>
												<th>Parent ID</th>
												<th>Name</th>
												<th class="col-span-2">Function</th>
											</tr>
										</thead>
										<tbody>
											<c:set var="index" value="1"></c:set>
											<c:forEach items="${listCategory}" var="category">
												<tr>
													<!-- ID -->
													<td class="py-1">${index}</td>
													<td class="py-1">${category.id}</td>
													<!-- Parent ID -->
													<td>${category.parentCategoryID}</td>
													<!-- Name -->
													<td>${category.name}</td>

													<td><a class="text-decoration-none"
														href="<%=request.getContextPath()%>/admin/category/edit?id=${category.id}">

															<button type="submit"
																class="btn btn-gradient-success me-2">Edit</button>
													</a> <a class="text-decoration-none"
														href="<%=request.getContextPath()%>/admin/category/delete?id=${category.id}">
															<button type="submit"
																class="btn btn-gradient-success me-2">Delete</button>
													</a></td>
												</tr>
												<c:set var="index" value="${index + 1}" />
											</c:forEach>

										</tbody>
									</table>
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
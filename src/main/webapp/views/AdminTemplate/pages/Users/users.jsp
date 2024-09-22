<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
</head>
<body>
	<div class="container-fuild">

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
		<div class="container-fluid page-body-wrapper" style="width: 101% !important">
			<!-- partial:partials/_sidebar.html -->
			<nav class="sidebar sidebar-offcanvas" id="sidebar">
				<ul class="nav">

					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/admin"> <span class="menu-title ">Dashboard</span>
							<i class="mdi mdi-home menu-icon"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> <span
							class="menu-title text-success">Users</span> <i
							class="fa fa-user-circle menu-icon text-success"></i>
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
								<i class="mdi mdi-home"></i>
							</span> Users
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
								role="tab" aria-controls="home" aria-selected="true">Users
								Form</button>
						</li>
						<li class="nav-item">
							<button class="nav-link text-success" id="profile-tab"
								data-bs-toggle="tab" data-bs-target="#profile" type="button"
								role="tab" aria-controls="profile" aria-selected="false">Users
								Table</button>
						</li>
						<li class="nav-item">
							<button class="nav-link text-success" id="Address-tab"
								data-bs-toggle="tab" data-bs-target="#Address" type="button"
								role="tab" aria-controls="Address" aria-selected="false">Address
								Form</button>
						</li>
						<li class="nav-item">
							<button class="nav-link text-success" id="AddressTable-tab"
								data-bs-toggle="tab" data-bs-target="#AddressTable"
								type="button" role="tab" aria-controls="AddressTable"
								aria-selected="false">Address Table</button>
						</li>
					</ul>
					<div class="tab-content" id="myTabContent">
						<!--users Form -->
						<div class="tab-pane fade show active" id="home" role="tabpanel"
							aria-labelledby="home-tab">
							<div class="col-md-12 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
							
										<h4 class="card-title">Users Form</h4>
										<p class="card-description">Please fill all the
											information.</p>
										<form class="forms-sample" class="form" action="admin/user" method="post">
											<div class="form-group row">
												<label for="exampleInputUsername2"
													class="col-sm-3 col-form-label">ID</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" name="id" value="${user.id}"
														disabled>
																								 <c:if test="${not empty ErrorID}">
             <div class="text-danger mt-1" >
        		${ErrorID}
            </div>
            </c:if>
												</div>
											</div>

											<div class="form-group row">
												<label for="exampleInputUsername2"
													class="col-sm-3 col-form-label">Name</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" name="name" value="${user.name}"
														placeholder="Username">
														 <c:if test="${not empty ErrorName}">
             <div class="text-danger mt-1" >
        		${ErrorName}
            </div>
            </c:if>
												</div>
											</div>
											<div class="form-group row">
												<label for="exampleInputEmail2"
													class="col-sm-3 col-form-label">Email</label>
												<div class="col-sm-9">
													<input type="email" class="form-control" name="email" value="${user.email}"
														placeholder="Email">
														 <c:if test="${not empty ErrorEmail}">
             <div class="text-danger mt-1" >
        		${ErrorEmail}
            </div>
            </c:if>
												</div>
											</div>
											<div class="form-group row">
												<label for="exampleInputMobile"
													class="col-sm-3 col-form-label">Phone</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" name="phone" value="${user.phone}"
														placeholder="Mobile number">
														 <c:if test="${not empty ErrorPhone}">
             <div class="text-danger mt-1" >
        		${ErrorPhone}
            </div>
            </c:if>
												</div>
											</div>
											<div class="form-group row">
												<label for="exampleInputPassword2"
													class="col-sm-3 col-form-label">Password</label>
												<div class="col-sm-9">
													<input type="password" class="form-control" name="password" value="${user.password}"
														placeholder="Password">
														 <c:if test="${not empty ErrorPassword}">
             <div class="text-danger mt-1" >
        		${ErrorPassword}
            </div>
            </c:if>
												</div>
											</div>
										<!-- 	<div class="form-group row">
												<label for="exampleInputConfirmPassword2"
													class="col-sm-3 col-form-label">Re Password</label>
												<div class="col-sm-9">
													<input type="password" class="form-control"
														name="re-password" placeholder="Password">
												</div>
											</div> -->
											<div class="form-group row">
												<label for="exampleInputConfirmPassword2"
													class="col-sm-3 col-form-label">Role</label>
												<div class="col-md-6">
													<div class="form-group row">
	
														<div class="col-sm-4">
															<div class="form-check form-check-success">
																<label class="form-check-label "> <input
																	type="radio" class="form-check-input" name="role"
																	value="true" ${user.role ? 'checked' :''}> Admin
																</label>
															</div>
														</div>
														<div class="col-sm-5">
															<div class="form-check form-check-success">
																<label class="form-check-label"> <input
																	type="radio" class="form-check-input" name="role"
																	value="false" ${user.role ? '' :'checked'}> User
																</label>
															</div>
														</div>
													</div>
												</div>
											</div>
											<button formaction="<%=request.getContextPath()%>/admin/user/create"
											type="submit" class="btn btn-gradient-success me-2">Create</button>
											<button formaction="<%=request.getContextPath()%>/admin/user/update?id=${user.id}"
											type="submit" class="btn btn-gradient-success me-2">Update</button>
											
											<button class="btn btn-light" formaction="<%=request.getContextPath()%>/admin/user">Cancel</button>
											
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
									<form>
										<h4 class="card-title">Users Table</h4>
										<div class="form-group">
											<div class="input-group">
												<input type="text" class="form-control"
													placeholder="Username" 
												name="findname" value="${findname}">
												<div class="input-group-append" >
													<button class="btn btn-sm btn-gradient-success py-3" 
													 formaction="<%=request.getContextPath()%>/admin/user/search?findname=${findname}"
													 type="submit" >Search</button>
												</div>
											</div>
										</div>
										</form>
										
										<table class="table table-striped ">
											<thead>
												<tr>
													<th>No.</th>
													<th>ID</th>
													<th>Name</th>
													<th>Phone</th>
													<th>Email</th>
													<th>Role</th>
													<th>Create at</th>
													<th>QR CODE</th>
													<th>Function</th>
												</tr>
											</thead>
											<tbody>
											
													<c:set var="index" value="1"></c:set>
													<c:forEach items="${listUser}" var="u">
														<tr>
													<!-- ID -->
													<td class="py-1">${index}</td>
													<td class="py-1">${u.id}</td>
												
													<!-- Name -->
													<td>${u.name}</td>
													<!-- Phone -->
													<td>${u.phone}</td>
													<!-- Email -->
													<td>${u.email}</td>
													<!-- Role -->
													<td>${u.role? 'Admin':'User'}</td>
													<!-- Create At -->
													<td><fmt:formatDate value="${u.create_at}" pattern="dd/MM/yyyy"/></td>
														<td class="py-1"><img
													src="<%=request.getContextPath()%>/views/QRCode/Users/${u.QR}"
													alt="image" /></td>
													<td >
													<a class="text-decoration-none" href="<%=request.getContextPath()%>/admin/user/edit?id=${u.id}">
														<button type="submit"
													     class="btn btn-gradient-success me-2 " >Edit</button>
													</a>
													<a class="text-decoration-none" href="<%=request.getContextPath()%>/admin/user/delete?id=${u.id}">
														<button type="submit"
															class="btn btn-gradient-success me-2 ">Delete</button>
													</a>
													</td>
												</tr>
													<c:set var="index" value="${index + 1}" />
									        </c:forEach>

											</tbody>
										</table>
										<div class="d-flex mt-3">
										<a class="text-decoration-none" href="<%=request.getContextPath()%>/admin/user/export_excel">
														<button type="submit"
															class="btn btn-gradient-success me-2 ">Export Excel</button>
										</a>
										<form action="user" method="post" enctype="multipart/form-data" class="d-flex">
										<input type="submit" class="btn btn-gradient-success me-2" 
										formaction="user/import_excel" name="importUser" value="Import Users"/>
									
										<h3>Import file: <input  name="userExcelFile" type="file"/> (.xlsx)</h3>
										
										</form>
													</div>
									</div>
								</div>
							</div>
						
						</div>
						<!-- User Table -->


						<!-- Address-form -->
						<div class="tab-pane fade show" id="Address" role="tabpanel"
							aria-labelledby="Address">
							<div class="col-md-12 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">Address Form</h4>
										<p class="card-description">Please fill all the
											information.</p>
										<form class="forms-sample" action="admin/user/address" method="post">
											<div class="form-group row">
												<label for="exampleInputUsername2"
													class="col-sm-3 col-form-label">ID</label>
												<div class="col-sm-9">
													<input type="text" class="form-control"
														 name="addressIDal" value="${ua.getAddress().getId()}" disabled>
												</div>
											</div>

											<div class="form-group row">
												<label for="exampleInputUsername2"
													class="col-sm-3 col-form-label">User</label>
												<div class="col-sm-9">
												<%-- 	<input type="text" value="${ua.getUser().getId()}"
													class="form-control"
														id="exampleInputUsername2" disabled> --%>
														<select class="form-select" name="userIDal">
														
														<c:forEach items="${listUser}" var="ul">
														
													   <option value="${ul.id}" ${ul.id == ua.getUser().getId() ? 'selected' : ''}>User ID: ${ul.id}, User Name: ${ul.name}</option>

														</c:forEach>
														</select>
												</div>
											</div>
											<div class="form-group row">
												<label for="exampleInputEmail2"
													class="col-sm-3 col-form-label">City</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" name="cityal"
													value="${ua.getAddress().getCity()}"
														>
												</div>
											</div>
											<div class="form-group row">
												<label for="exampleInputMobile"
													class="col-sm-3 col-form-label">Ward</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" name="wardal"
													value="${ua.getAddress().getWard()}"
														id="exampleInputMobile" placeholder="Mobile number">
												</div>
											</div>
											<div class="form-group row">
												<label for="exampleInputPassword2"
													class="col-sm-3 col-form-label">Street</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" name="streetal"
													value="${ua.getAddress().getStreet()}"
														id="exampleInputPassword2" placeholder="Password">
												</div>
											</div>

											<div class="form-group row">
												<label for="exampleInputConfirmPassword2"
													class="col-sm-3 col-form-label">Is_Default</label>
												<div class="col-md-6">
													<div class="form-group row">

														<div class="col-sm-4">
															<div class="form-check form-check-success">
																<label class="form-check-label "> <input
																	type="radio" class="form-check-input"
																	name="isDefaultal" id="membershipRadios1" value="false"
																	 ${!ua.isDefault() ? 'checked':''}
																	> True
																</label>
															</div>
														</div>
														<div class="col-sm-5">
															<div class="form-check form-check-success">
																<label class="form-check-label"> <input
																	type="radio" class="form-check-input"
																	name="isDefaultal" id="membershipRadios2" value="true"
																	${ua.isDefault() ? 'checked':''}
																	value="option2"> False
																</label>
															</div>
														</div>
													</div>
												</div>
											</div>
											<button formaction="<%=request.getContextPath()%>/admin/user/address/
											create"
											
											type="submit" class="btn btn-gradient-success me-2">Create</button>
											<button formaction="<%=request.getContextPath()%>/admin/user/address/
											update?addressID=${ua.getAddress().getId()}
											
											"
											type="submit" class="btn btn-gradient-success me-2">Update</button>
											<button class="btn btn-light">Cancel</button>
										</form>
									</div>
								</div>
							</div>

						</div>
						<!-- Address-Form -->

						<!-- Address Table -->
						<div class="tab-pane fade" id="AddressTable" role="tabpanel"
							aria-labelledby="AddressTable-tab">
							<div class="col-lg-12 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">Address Table</h4>
										<div class="form-group">
										<form>
											<div class="input-group">
												<input type="text" class="form-control"
													name="findnamea">
												<div class="input-group-append">
													<button formaction="<%=request.getContextPath()%>/admin/user/address/search?findnamea=${findnamea}"
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
													<th>ID</th>
													<th>User ID</th>
													<th>User Name</th>
													<th>City</th>
													<th>Ward</th>
													<th>Street</th>
													<th>Is_Default</th>
													<th class="col-span-2">Function</th>
												</tr>
											</thead>
											<tbody>
											<c:set var="index" value="1"></c:set>
													<c:forEach items="${listAddress}" var="a">
												<tr>

														<!-- ID -->
														<td class="py-1">${index}</td>
														<!-- User ID -->
														<td>${a.getId().getUser().getId()}</td>
														
														<td>${a.getId().getUser().getName()}</td>
														<!-- City -->
														<td>${a.getAddress().getCity()}</td>
														<!-- Ward -->
														<td>${a.getAddress().getWard()}</td>
														<!-- Street -->
														<td>${a.getAddress().getStreet()}</td>
														<!-- Is_Default -->
														<td>${!a.isDefault()}</td>
														<td>
															<a type="submit"
															href="<%=request.getContextPath()%>/admin/user/address/edit?id=${a.id}"
																class="btn btn-gradient-success me-2">Edit</a>
															<a type="submit"
															href="<%=request.getContextPath()%>/admin/user/address/delete?id=${a.id}"
																class="btn btn-gradient-success me-2">Delete</a>
														</td>
													</tr>
												<c:set var="index" value="${index + 1}" />
									</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<!-- Address Table -->

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
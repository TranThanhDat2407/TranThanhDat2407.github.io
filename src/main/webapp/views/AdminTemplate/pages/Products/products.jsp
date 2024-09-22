1<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
/* .description-cell {
        word-wrap: break-word; /* Ngắt dòng khi từ quá dài */
white-space


:

 

pre-line


; /* Duy trì các ký tự xuống dòng trong văn bản */
max-width


:

 

100px

 

!
important


; /* Điều chỉnh chiều rộng tối đa của ô mô tả */
}
*
/
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
							class="menu-title">Dashboard</span> <i
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
					<li class="nav-item"><a class="nav-link" href="#"> <span
							class="menu-title text-success">Products</span> <i
							class="fa fa-apple menu-icon text-success"></i>
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
								<i class="fa fa-apple"></i>
							</span> Products
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
								role="tab" aria-controls="home" aria-selected="true">Product
								Form</button>
						</li>
						<li class="nav-item">
							<button class="nav-link text-success" id="profile-tab"
								data-bs-toggle="tab" data-bs-target="#profile" type="button"
								role="tab" aria-controls="profile" aria-selected="false">Product
								Table</button>
						</li>
						<li class="nav-item">
							<button class="nav-link text-success" id="Address-tab"
								data-bs-toggle="tab" data-bs-target="#Address" type="button"
								role="tab" aria-controls="Address" aria-selected="false">Gallery
								Form</button>
						</li>
						<li class="nav-item">
							<button class="nav-link text-success" id="AddressTable-tab"
								data-bs-toggle="tab" data-bs-target="#AddressTable"
								type="button" role="tab" aria-controls="AddressTable"
								aria-selected="false">Gallery Table</button>
						</li>
						<li class="nav-item">
							<button class="nav-link text-success" id="Stock-tab"
								data-bs-toggle="tab" data-bs-target="#Stock" type="button"
								role="tab" aria-controls="Stock" aria-selected="false">Stock
								Form</button>
						</li>
						<li class="nav-item">
							<button class="nav-link text-success" id="StockTable-tab"
								data-bs-toggle="tab" data-bs-target="#StockTable" type="button"
								role="tab" aria-controls="StockTable" aria-selected="false">Stock
								Table</button>
						</li>
					</ul>
					<div class="tab-content" id="myTabContent">
						<!--Product Form -->
						<div class="tab-pane fade show active" id="home" role="tabpanel"
							aria-labelledby="home-tab">
							<div class="col-md-12 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">Product Form</h4>
										<p class="card-description">Please fill all the
											information.</p>
										<form class="forms-sample" action="admin/product"
											method="post">

											<div class="form-group row">

												<label for="exampleInputUsername2"
													class="col-sm-3 col-form-label">ID</label>
												<div class="col-sm-9">

													<input type="text" class="form-control" name="id"
														value="${productEdit.id}" disabled>
												</div>
											</div>

											<div class="form-group row">
												<label for="exampleInputUsername2"
													class="col-sm-3 col-form-label">Category ID</label>
												<div class="col-sm-9">
													<select class="form-select" id="category" name="category">
														
															<c:forEach var="category" items="${listCategory}">
																<option value="${category.id}"
																	<c:if test="${productEdit.category.id == category.id}">selected</c:if>>${category.id} - ${category.name}
																</option>
															</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group row">
												<label for="exampleInputEmail2"
													class="col-sm-3 col-form-label">Product Name</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" name="name"
														value="${param.name != null ? param.name : productEdit.name}">
													<c:if test="${not empty errorName}">
														  <small class="text-danger">${errorName}</small>
													</c:if>
												</div>
											</div>
											<div class="form-group row">
												<label for="exampleInputMobile"
													class="col-sm-3 col-form-label">Description</label>
												<div class="col-sm-9">
													<textarea class="form-control" rows="4" name="description">${param.description != null ? param.description : productEdit.description}</textarea>
													<c:if test="${not empty errorDescription}">
														<div class="text-danger">${errorDescription}</div>
													</c:if>
												</div>
											</div>



											<button type="submit"
												formaction="<%=request.getContextPath()%>/admin/products/create"
												class="btn btn-gradient-success me-2">Create</button>
											<button type="submit"
												formaction="<%=request.getContextPath()%>/admin/products/update?id=${productEdit.id}"
												class="btn btn-gradient-success me-2">Update</button>

											<button type="submit"
												formaction="<%=request.getContextPath()%>/admin/products"
												class="btn btn-light">Cancel</button>
										</form>

									</div>
								</div>
							</div>
						</div>
						<!--Product Form -->

						<!--Product Table -->
						<div class="tab-pane fade" id="profile" role="tabpanel"
							aria-labelledby="profile-tab">
							<div class="col-lg-12 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">Product Table</h4>
										<form>
											<div class="form-group">
												<div class="input-group">
													<input type="text" class="form-control"
														placeholder="Username" aria-label="Recipient's username"
														aria-describedby="basic-addon2" name="findname">
													<div class="input-group-append">
														<button
															formaction="<%=request.getContextPath()%>/admin/products/search?findname=${findName}"
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
												<th>Category ID</th>
												<th>Name</th>
												<th>Description</th>
												<!-- <th>Create at</th>
													<th>Update at</th> -->
												<th class="col-span-2">Function</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<c:set var="index" value="1"></c:set>
												<c:forEach items="${listProduct}" var="product">
													<!-- ID -->
													<td class="py-1">${index}</td>
													<td class="py-1">${product.id}</td>

													<!-- Category ID -->
													<td>${product.category.id}</td>

													<!-- Name -->
													<td>${product.name}</td>

													<!-- Description -->
													<td>${product.description}</td>

													<td><a class="text-decoration-none"
														href="<%=request.getContextPath()%>/admin/products/edit?id=${product.id}">
															<button type="submit"
																class="btn btn-gradient-success me-2">Edit</button>
													</a> <a class="text-decoration-none"
														href="<%=request.getContextPath()%>/admin/products/delete?id=${product.id}">
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
					<!-- Product Table -->


					<!-- Gallery-form -->
					<div class="tab-pane fade show" id="Address" role="tabpanel"
						aria-labelledby="Address">
						<div class="col-md-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Gallery Form</h4>
									<p class="card-description">Please fill all the
										information.</p>
									<form class="forms-sample" action="admin/product/gallery" method="post"
										enctype="multipart/form-data">
										<div class="form-group row">
											<label for="exampleInputUsername2"
												class="col-sm-3 col-form-label">ID</label>
											<div class="col-sm-9">
												<input type="text" class="form-control"
													id="exampleInputUsername2" name="id"
													value="${galleryEdit.id}" disabled>
											</div>
										</div>
										<div class="form-group row">
											<label for="exampleInputUsername2"
												class="col-sm-3 col-form-label">Product ID</label>
											<div class="col-sm-9">
												<select class="form-select" id="product" name="product">
													<c:forEach items="${listProduct}" var="pl">

														<option value="${pl.id}"
															${pl.id == galleryEdit.getProduct().getId()? 'selected' : ''}>Product
															ID: ${pl.id}, Product Name: ${pl.name}</option>

													</c:forEach>

												</select>
											</div>
										</div>
										<div class="form-group row">
											<label for="exampleInputEmail2"
												class="col-sm-3 col-form-label">Thumbnail</label>
											<div class="col-sm-9">
												<input type="file" class="form-control"
													value="${gallery.thumbnail}" name="thumbnail">
												<%-- 	<c:if test="${not empty errorThubnail}">
														<div class="text-danger">${errorThubnail}</div>
													</c:if> --%>
											</div>
										</div>
										<button type="submit"
											formaction="<%=request.getContextPath()%>/admin/products/gallery/create"
											class="btn btn-gradient-success me-2">Create</button>
										<button type="submit"
											formaction="<%=request.getContextPath()%>/admin/products/gallery/update?id=${galleryEdit.id}"
											class="btn btn-gradient-success me-2">Update</button>

										<button type="submit"
											formaction="<%=request.getContextPath()%>/admin/products/gallery"
											class="btn btn-light">Cancel</button>
									</form>
								</div>
							</div>
						</div>

					</div>
					<!-- Gallery-Form -->

					<!-- Gallery Table -->
					<div class="tab-pane fade" id="AddressTable" role="tabpanel"
						aria-labelledby="AddressTable-tab">
						<div class="col-lg-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">Gallery Table</h4>
									<div class="form-group">
										<form>
											<div class="form-group">
												<div class="input-group">
													<input type="text" class="form-control"
														placeholder="Id" aria-label="Recipient's username"
														aria-describedby="basic-addon2" name="id">
													<div class="input-group-append">
														<button
															formaction="<%=request.getContextPath()%>/admin/products/gallery/search?id=${id}"
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
												<th>NO.</th>
												<th>ID</th>
												<th>Product ID</th>
												<th>Thumbnail</th>
												<th class="col-span-2">Function</th>
											</tr>
										</thead>
										<tbody>
												<c:set var="index" value="1"></c:set>
												<c:forEach items="${listGallery}" var="gallery">
												<tr>
													<!-- NO. -->
													<td class="py-1">${index}</td>
													<!-- ID -->
													<td class="py-1">${gallery.id}</td>
													<!-- Product ID -->
													<td>${gallery.product.id}</td>
													<!-- Thumbnail -->
													<td><img
														src="${pageContext.request.contextPath}/views/imgs/products/${gallery.thumbnail}"
														 class="card-img-center"
														alt="${gallery.thumbnail}"
														style="height: 70px !important; width: 70px !important;">
													</td>
													<!-- Function Buttons -->
													<td><a class="text-decoration-none"
														href="${pageContext.request.contextPath}/admin/products/gallery/edit?id=${gallery.id}">
															<button type="submit"
																class="btn btn-gradient-success me-2">Edit</button>
													</a> <a class="text-decoration-none"
														href="${pageContext.request.contextPath}/admin/products/gallery/delete?id=${gallery.id}">
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
					<!-- Gallery Table -->

		</div>
				<!-- Product_Item-form -->
						<div class="tab-pane fade show" id="Stock" role="tabpanel"
							aria-labelledby="Stock">
							<div class="col-md-12 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">Stock Form</h4>
										<p class="card-description">Please fill all the
											information.</p>
										<form class="forms-sample" action="admin/products/productItem" method="post"
										enctype="multipart/form-data">
											<div class="form-group row">
												<label for="exampleInputUsername2"
													class="col-sm-3 col-form-label">ID</label>
												<div class="col-sm-9">
													<input type="text" class="form-control"
														id="exampleInputUsername2" name="id" value="${piEdit.id}" disabled>
												</div>
											</div>
											<div class="form-group row">
												<label for="exampleInputUsername2"
													class="col-sm-3 col-form-label">Product ID</label>
												<div class="col-sm-9">
												<select class="form-select" id="product" name="product">
													<c:forEach var="prod" items="${listProduct}">
														<option value="${prod.id}"
															<c:if test="${piEdit.product.id == prod.id}">selected</c:if>>
															${prod.id} - ${prod.name}</option>
													</c:forEach>
												</select>
											</div>
											</div>
											
											<div class="form-group row">
												<label for="exampleInputEmail2"
													class="col-sm-3 col-form-label">Quantity In Stock</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" name="qty_in_stock" value="${piEdit.qtyInStock}">
												</div>
											</div>
											<div class="form-group row">
												<label for="exampleInputEmail2"
													class="col-sm-3 col-form-label">Price</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" name="price" value="${piEdit.price}">
												</div>
											</div>
											<div class="form-group row">
												<label for="exampleInputEmail2"
													class="col-sm-3 col-form-label">Original_Price</label>
												<div class="col-sm-9">
													<input type="text" class="form-control"
														name="original_price" value="${piEdit.originalPrice}">
												</div>
											</div>
											<button type="submit" 
											formaction="<%=request.getContextPath()%>/admin/products/productItem/updateOrInsert"
											class="btn btn-gradient-success me-2">Save</button>
											<button type="submit"
											formaction="<%=request.getContextPath()%>/admin/products/productItem"
											class="btn btn-light">Cancel</button>
										</form>
									</div>
								</div>
							</div>

						</div>
						<!-- Stock-Form -->

						<!-- Stock Table -->
						<div class="tab-pane fade" id="StockTable" role="tabpanel"
							aria-labelledby="StockTable-tab">
							<div class="col-lg-12 grid-margin stretch-card">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">Stock Table</h4>
										<!-- <div class="form-group">
											<div class="input-group">
												<input type="text" class="form-control"
													placeholder="Product ID" aria-label="Recipient's username"
													aria-describedby="basic-addon2">
												<div class="input-group-append">
													<button class="btn btn-sm btn-gradient-success py-3"
														type="button">Search</button>
												</div>
											</div>
										</div> -->
										<table class="table table-striped">
											<thead>
												<tr>
													<th>ID</th>
													<th>Product ID</th>
													<th>Product Name</th>
													<th>Quantity In Stock</th>
													<th>Price</th>
													<th>Original Price</th>
													<th>Create At</th>
													<th>Update At</th>
													<th class="col-span-2">Function</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach items="${listProductItem}" var="pi">
												<tr>
													<td class="py-1">${pi.id}</td>
													<td>${pi.product.id}</td>
													<td>${pi.product.name}</td>
													<td>${pi.qtyInStock}</td>
													<td>${pi.price}</td>
													<td>${pi.originalPrice}</td>
													<td><fmt:formatDate value="${pi.createAt}" pattern="dd/MM/yyyy"/></td>
													<td><fmt:formatDate value="${pi.updateAt}" pattern="dd/MM/yyyy"/></td>

													<td><a class="text-decoration-none"
														href="${pageContext.request.contextPath}/admin/products/productItem/edit?id=${pi.id}">
															<button type="submit"
																class="btn btn-gradient-success me-2">Edit</button>
													</a> <a class="text-decoration-none"
														href="${pageContext.request.contextPath}/admin/products/productItem/delete?id=${pi.id}">
															<button type="submit"
																class="btn btn-gradient-success me-2">Delete</button>
													</a></td>
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					<!-- 	Stock Table -->
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
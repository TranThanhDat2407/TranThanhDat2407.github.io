<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client Product</title>

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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/ClientTemplate/css/profile.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/views/ClientTemplate/css/style.css">
<script
	src="<%=request.getContextPath()%>/views/ClientTemplate/js/angular.min.js"></script>
</head>
<body ng-app="myApp" ng-controller="myCtrl">


	<%@include file="/views/ClientTemplate/layout/nav.jsp"%>

	<!-- content -->

	<!-- SLIDER -->


	<!-- NAVBAR -->
	<!-- NAVBAR -->
	<div class="container">
		<nav aria-label="breadcrumb"
			style="margin-top: 15px; margin-bottom: 50px">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#"
					style="color: green; text-decoration: none">Trang Chủ</a></li>
				<li class="breadcrumb-item"><a href="#"
					style="color: green; text-decoration: none">Giỏ Hàng</a></li>
				<li class="breadcrumb-item">Thanh Toán</li>
			</ol>
		</nav>

		<h2>Thanh Toán</h2>
		<!-- CONTENT -->
		<div class="container p-5 border border-1 mt-4 mb-5">
			<div class="row">
				<div class="col-6 border-end">
					<div data-mdb-input-init class="form-outline mb-4">
						<label class="form-label fw-bold" for="form1Example2">Địa
							chỉ giao hàng </label> <select class="form-select pe-3 mb-2 form-control"
							ng-model="tp" ng-options="tp.Name for tp in test"
							name="address_id">
							<c:forEach items="${listAddress}" var="address">
								<option value="${address[0]}">${address[0]},
									${address[1]}, ${address[2]}, ${address[3]}</option>
							</c:forEach>
						</select>
						<!-- Button trigger modal -->
						<button type="button" class="btn btn-success btn-block"
							data-bs-toggle="modal" data-bs-target="#addressModal">Thêm
							Địa chỉ giao hàng</button>

						<!-- Modal -->
						<div class="modal fade" id="addressModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h1 class="modal-title fs-5" id="exampleModalLabel">Thêm
											địa chỉ giao hàng</h1>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<form
										action="<%=request.getContextPath()%>/client/profile/address/createAddress"
										method="post">
										<div class="modal-body">
											<div class="mb-3">
												<label class="form-label">Tỉnh, Thành phố</label> <input
													type="text" name="city" class="form-control">
											</div>
											<div class="mb-3">
												<label class="form-label">Quận, Huyện</label> <input
													type="text" name="ward" class="form-control">
											</div>
											<div class="mb-3">
												<label class="form-label">Phường, Đường</label> <input
													type="text" name="street" class="form-control">
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Hủy</button>
											<button type="submit" class="btn btn-success">Thêm</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<div data-mdb-input-init class="form-outline mb-4">
						<label class="form-label fw-bold" for="form1Example2">Hình
							Thức Thanh Toán</label> <select class="form-select mb-4"
							aria-label="Default select example" ng-model="paymentMethod"
							name="upm_id">
							<c:forEach items="${listUpm}" var="upm">
								<option value="${upm.id}">${upm.id},
									${upm.paymentType.name}, ${upm.provider}</option>
							</c:forEach>
						</select>
						<!-- Button trigger modal -->
						<button type="button" class="btn btn-success btn-block"
							data-bs-toggle="modal" data-bs-target="#upmModal">Thêm
							phương thức thanh toán</button>

						<!-- Modal -->
						<div class="modal fade" id="upmModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h1 class="modal-title fs-5" id="exampleModalLabel">Thêm
											phương thức thanh toán</h1>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<form
										action="<%=request.getContextPath()%>/client/payment/createUPM"
										method="post">
										<div class="modal-body">
											<div class="mb-3">
												<label class="form-label">Kiểu thanh toán</label> <select
													class="form-select" aria-label="Default select example"
													name="paymentType">
													<c:forEach items="${listPT}" var="pt">
														<option value="${pt.id}">Id: ${pt.id} - Name:
															${pt.name}</option>
													</c:forEach>
												</select>
											</div>
											<div class="mb-3">
												<label class="form-label">Mã số thẻ</label> <input
													type="text" name="CardNumber" class="form-control">
											</div>
											<div class="mb-3">
												<label class="form-label">Tên chủ thẻ</label> <input
													type="text" name="CardHoderName" class="form-control">
											</div>
											<div class="mb-3">
												<label class="form-label">Ngày hết hạn</label> <input
													type="date" name="ExpiryDate" class="form-control">
											</div>
											<div class="mb-3">
												<label class="form-label">Nhà cung cấp</label> <input
													type="text" name="Provider" class="form-control">
											</div>

										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Hủy</button>
											<button type="submit" class="btn btn-success">Thêm</button>
										</div>
									</form>
								</div>
							</div>
						</div>

					</div>



					<br>
					<button type="button" data-bs-toggle="modal"
						data-bs-target="#thanhToan" class="btn btn-success btn-block mt-5">Thanh
						Toán</button>

					<div class="modal fade" id="thanhToan" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5" id="exampleModalLabel">Thêm
										phương thức thanh toán</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<form
									action="<%=request.getContextPath()%>/client/payment/createOrder"
									method="post">
									<div class="modal-body">
										<label class="form-label fw-bold" for="form1Example2">Địa
											chỉ giao hàng </label> <select
											class="form-select pe-3 mb-2 form-control" ng-model="tp"
											ng-options="tp.Name for tp in test" name="address_id">
											<c:forEach items="${listAddress}" var="address">
												<option value="${address[0]}">${address[0]},
													${address[1]}, ${address[2]}, ${address[3]}</option>
											</c:forEach>
										</select> <br> <label class="form-label fw-bold"
											for="form1Example2">Hình Thức Thanh Toán</label> <select
											class="form-select mb-4" aria-label="Default select example"
											ng-model="paymentMethod" name="upm_id">
											<c:forEach items="${listUpm}" var="upm">
												<option value="${upm.id}">${upm.id},
													${upm.paymentType.name}, ${upm.provider}</option>
											</c:forEach>
										</select>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Hủy</button>
										<button type="submit" class="btn btn-success">Xác nhận thanh toán</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					</form>
				</div>

				<div class="col-6">
					<div class="sticky">
						<h2>Đơn Hàng</h2>
						<hr />
						<c:set var="TongTien" value="0"></c:set>
						<c:forEach items="${listCart}" var="cart">
							<div class="d-flex justify-content-between">
								<p class="fw-bold">${cart.productItem.product.name}X
									${cart.qty}</p>
								<p>${cart.productItem.price}VNĐ</p>
							</div>
							<c:set var="TongTien"
								value="${TongTien + (cart.productItem.price * cart.qty)}" />
						</c:forEach>
						<hr />
						<div class="d-flex justify-content-between">
							<p class="fw-bold">Tổng Cộng:</p>
							<div>

								<p class="fw-bold">
									<c:out value="${TongTien}" />
									VNĐ
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>




	<!-- CONTENT -->
	<!-- content -->

	<%@include file="/views/ClientTemplate/layout/footer.jsp"%>


	<script
		src="<%=request.getContextPath()%>/views/ClientTemplate/js/angular-route.min.js"></script>
	<%-- 	<script src="<%=request.getContextPath()%>/views/ClientTemplate/js/webapp.js"></script>   --%>
	<script
		src="<%=request.getContextPath()%>/views/ClientTemplate/js/bootstrap.bundle.min.js"></script>
</body>
</html>
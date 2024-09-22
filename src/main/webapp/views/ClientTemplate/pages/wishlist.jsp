<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client Wishlist</title>

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
<script
	src="<%=request.getContextPath()%>/views/ClientTemplate/js/angular.min.js"></script>
</head>
<body>
<fmt:setLocale value="${sessionScope.lang}" scope="request"/>

	<%@include file="/views/ClientTemplate/layout/nav.jsp"%>
	<fmt:setBundle basename="wishlist" scope="request"/>
	<!-- content -->

	<!-- SLIDER -->


	<!-- NAVBAR -->
	<div class="container">


		<nav aria-label="breadcrumb"
			style="margin-top: 15px; margin-bottom: 50px;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#"
					style="color: green; text-decoration: none;"><fmt:message key="wishlist.breadcrumHome"/></a></li>
				<li class="breadcrumb-item"><fmt:message key="wishlist.breadcrumWishlist"/></li>
			</ol>
		</nav>

		<h1 class="mt-3"><fmt:message key="wishlist.breadcrumWishlist"/></h1>
		<!-- CONTENT -->
		<div class="container p-5 border border-1 mt-5 mb-5">
			<div class="row">
				<p class="fs-5" style="font-weight: bold;">${countWishList} ${productSize}</p>

				<c:choose>
					<c:when test="${not empty listProductFromWishList }">
						<c:forEach var="product" items="${listProductFromWishList}">
							<div class="col-12 p-3 border-bottom mb-3">
								<div class="row">
									<div class="col-lg-2 col-sm-12">
										<a href="<%=request.getContextPath()%>/client/product_detail"
											style="color: inherit;" class="text-decoration-none"> <img
											src="${pageContext.request.contextPath}/views/imgs/products/${product[2]}"
											alt=""
											style="width: 211px !important; height: 211px !important;"
											class="bg-light">
										</a>
									</div>
									<div class="col-lg-8 col-sm-12 ms-4 mt-2">
										<a href="<%=request.getContextPath()%>/client/product_detail"
											style="color: inherit;" class="text-decoration-none">
											<h3>${product[1]}</h3>
											<div class="mb-0 d-flex">
												<p><fmt:message key="wishlist.stock"/> : </p>
												<p class="text-success fw-bold">${product[4]} <fmt:message key="wishlist.inStock"/>.</p>
											</div>
											<p class="fw-bold fs-3">${product[3]}&nbsp;VNĐ</p>
										</a>
										<form
											action="<%=request.getContextPath()%>/client/wishlist/delete?product_item_id_delete=${product[0]}"
											method="post">
											<button type="submit" class="btn btn-danger btn"
												style="width: 150px;">
												<i class='bx bx-heart'></i>
											</button>
										</form>
									</div>
								</div>
							</div>
						</c:forEach>

					</c:when>
					<c:otherwise>
						<div class="col-12 p-3 border-bottom mb-3">
						
						
						<h5 style="color: #198754; font-weight: bold ; font-style: italic;">${thongbao}<i class="fa-solid fa-seedling fa-bounce" style="color: #198754;"></i> </h5>
						</div>
					
					
					
					</c:otherwise>

				</c:choose>



				<!-- <div class="col-12 p-3 border-bottom mb-3">
          <div class="row">
            <div class="col-lg-2 col-sm-12">
              <img src="./imgs/products/avocado1.png" alt="" style="width: 211px !important; height: 211px !important;" class="bg-light">
            </div>
            <div class="col-lg-8 col-sm-12 ms-4 mt-2">
              <h3>Bơ</h3>
              <div  class="mb-0 d-flex">
              <p>Tình trạng:&nbsp;</p><p class="text-success fw-bold"> còn hàng</p>
              </div>
              <p class=" mb-0  fs-5 text-decoration-line-through">60.000 VNĐ</p>
              <p class=" fs-3 mb-0 fw-bold text-danger">59.999 VNĐ</p>
              <button type="button" class="btn btn-danger btn" style="width: 150px;"><i class='bx bx-heart'></i></button>
            </div>
          </div>
        </div> -->


			</div>
		</div>
	</div>

	<!-- content -->

	<%@include file="/views/ClientTemplate/layout/footer.jsp"%>


	<script
		src="<%=request.getContextPath()%>/views/ClientTemplate/js/angular-route.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/views/ClientTemplate/js/webapp.js"></script>
	<script
		src="<%=request.getContextPath()%>/views/ClientTemplate/js/bootstrap.bundle.min.js"></script>
</body>
</html>
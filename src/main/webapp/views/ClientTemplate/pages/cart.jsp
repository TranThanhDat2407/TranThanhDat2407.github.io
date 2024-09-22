<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.lang}" scope="request"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client Cart</title>

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
</head>
<body>


 <%@include file="/views/ClientTemplate/layout/nav.jsp"%>
	<fmt:setBundle basename="cart" scope="request"/>
	<!-- content -->
 	
    <!-- SLIDER -->

	
	<div class="container">
  <nav aria-label="breadcrumb" style="margin-top: 15px;
margin-bottom: 50px;">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="#"
          style="color:green; text-decoration: none;"><fmt:message key="cart.breadcrumHome"/></a></li>
      <li class="breadcrumb-item"><fmt:message key="cart.breadcrumCart"/></li>
    </ol>
  </nav>

  <h1 class="mt-3 mb-3"><fmt:message key="cart.title"/></h1>
  <!-- CONTENT -->
  <div class="container p-3">

    <div class="row">
      <div class="col-lg-7 col-sm-12">

        <div class="container">
        <c:set var="TongTien" value="0"></c:set>
        <c:set var="tongSP" value="1"></c:set>
        <c:forEach items="${listCart}" var="cart">
          <div class="product-cart mb-3 border-bottom">
            <div class="row pb-5">
              <div class="col-3 me-4">
                <img src="<%=request.getContextPath()%>/views/imgs/products/${cart.productItem.product.galleries[0].thumbnail}" alt
                  style="width: 215px !important; height: 215px !important;"
                  class="bg-light">
              </div>
              <div class="col-8 ms-4">
                <div class="d-flex justify-content-between">
                  <h3>${cart.productItem.product.name}</h3>
                  <form action="${pageContext.request.contextPath}/client/cart/remove" method="post" style="display: inline;">
    				<input type="hidden" name="cart_id" value="${cart.id}">
    				<button type="submit" class='bx bx-x fs-1 border border-0 bg-body'></button>
				  </form>
                </div>

                <div class="d-flex">
                  <p class="fw-bold fs-3">${cart.productItem.price}</p>
                  <p class="fw-bold fs-3">&nbsp;VNĐ</p>
                </div>

                <p class="fw-bold pt-4"><fmt:message key="cart.qty"/></p>

                <div class="d-flex justify-content-between">

                  <div class="d-flex">
                    <form action="${pageContext.request.contextPath}/client/cart/updateQuantity" method="post" style="display: inline;">
    					<input type="hidden" name="cartId" value="${cart.id}">
    						<select name="newQuantity" class="form-select pe-3 mb-2" style="width: 130px;" onchange="this.form.submit()">
        						<c:forEach begin="1" end="5" var="i">
            						<option value="${i}" ${cart.qty == i ? 'selected' : ''}>${i}</option>
        						</c:forEach>
        						<c:if test="${cart.qty > 5}">
        							<option value="${cart.qty}" selected>${cart.qty}</option>
    							</c:if>
    						</select>
					</form>
                  </div>

                  <div class="d-flex mt-4 me-3">
                    <h5 class="text-uppercase fw-bold"
                      style="margin-bottom: 0 !important;"><fmt:message key="cart.total1"/>: &nbsp;</h5>
                    <div class="d-flex">
                      <h5
                        class="text-uppercase fw-bold text-danger">${cart.productItem.price * cart.qty}</h5>
                      <h5
                        class="text-uppercase fw-bold text-danger">&nbsp;VNĐ</h5>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <c:set var="TongTien" value="${TongTien + (cart.productItem.price * cart.qty)}"/>
          <c:set var="TongSP" value="${TongSP + 1}"/>
		</c:forEach>
          <!-- <div class="product-cart mb-3">
        <div class="row pb-5">
          <div class="col-lg-3 col-sm-12 me-4">
            <img src="./imgs/products/apple2.png" alt
              style="width: 215px !important; height: 215px !important;"
              class="bg-light">
          </div>
          <div class="col-8 ms-4 ">
              <div class="d-flex justify-content-between">
                <h3>Táo</h3>
                <i class='bx bx-x fs-1'></i>
              </div>
              <div class="d-flex">
                <p class=" fs-5 text-decoration-line-through mb-0">60.000</p>
                <p class=" fs-5 text-decoration-line-through mb-0">&nbsp;VNĐ</p>
              </div>

              <p class="text-danger fw-semibold mb-0">Sale</p>
              <div class="d-flex mb-1">
                <h4
                  class="text-uppercase fw-bold text-danger">59.999</h5>
                <h4
                  class="text-uppercase fw-bold text-danger">&nbsp;VNĐ</h5>
              </div>

              <p class="fw-bold mb-1">Số Lượng</p>

              <div class="d-flex justify-content-between">
                <div class="d-flex">
                <select class="form-select pe-3"
                  style="width: 130px;">
                  <option value="1" selected>1</option>
                  <option value="2" >2</option>
                  <option value="3">3</option>
                  <option value="4">4</option>
                  <option value="5">5</option>
                </select>
                 <p class="mb-0 mt-4">&nbsp;/kg</p> 
              </div>
                <div class="d-flex mt-4 me-3">
                  <h5 class="text-uppercase fw-bold"
                    style="margin-bottom: 0 !important;">Tổng: &nbsp;</h5>
                  <div class="d-flex">
                    <h5
                      class="text-uppercase fw-bold text-danger">59.999</h5>
                    <h5
                      class="text-uppercase fw-bold text-danger">&nbsp;VNĐ</h5>
                  </div>
                </div>
              </div>
          </div>
        </div>
      </div> -->

        </div>
      </div>
      <div class="col-lg-4 col-sm-12 ">
        <div class="container">

          <div class="container border border-1 border-black mb-3 "
            style="padding: 28px 20px;">
            <div class="row">
              <div class="d-flex">
                <h5 class="text-uppercase fw-bold"><fmt:message key="cart.total2"/>|&nbsp;</h5>
                <h5
                  class="text-uppercase text-dark fw-bold"><c:out value = "${TongSP}"/></h5>
                <h5 class="text-uppercase text-dark fw-bold">&nbsp;<fmt:message key="cart.product"/></h5>

              </div>


              <div class="d-flex justify-content-between pt-3 mt-5">
                <h6 class="text-uppercase fw-bold fs-3"><fmt:message key="cart.total3"/>: </h6>
                <div class="d-flex mt-1">
                  <h6 class="text-uppercase fw-bold fs-4"><c:out value = "${TongTien}"/></h6>
                  <h6 class="text-uppercase fw-bold">&nbsp;VNĐ</h6>
                </div>
              </div>
            </div>
          </div>

          <div class="row mt-2 mb-2">
            <div class="col-10">
              <p class="fw-medium" style="font-size: 15px"><fmt:message key="cart.freeship"/></p>
            </div>
            <div class="col-2 p-3">
              <i class='text-body-tertiary bx bxs-info-circle fs-3 mt-1 '
                data-bs-toggle="tooltip" data-bs-placement="left"
                data-bs-custom-class="custom-tooltip"
                data-bs-title="Các sản phẩm bạn chọn sẽ không được đặt trước trong giỏ hàng của bạn. Các sản phẩm bạn chọn sẽ được đặt trước trong vòng 30 phút sau khi bạn đã nhấn nút &quot;Thanh toán&quot;."></i>
            </div>
          </div>

          <a href="<%=request.getContextPath()%>/client/payment" style="text-decoration: none;">
            <div class="d-grid mb-2">
              <button class="btn btn-lg btn-danger text-uppercase fw-bold"><fmt:message key="cart.buttonCheckOut"/></button>
            </div></a>
            <a href="#!category" style="text-decoration: none;">
          <div class="d-grid">
            <button
              class="btn btn-lg btn-outline-dark  text-uppercase fw-bold"><fmt:message key="cart.buttonProducts"/></button>
          </div>
          </a>

        </div>

      </div>

    </div>

  </div>
</div>

    
	<!-- content -->

   <%@include file="/views/ClientTemplate/layout/footer.jsp"%>
    

   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/angular-route.min.js"></script>
   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/webapp.js"></script>
   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/bootstrap.bundle.min.js"></script>
</body>
</html>
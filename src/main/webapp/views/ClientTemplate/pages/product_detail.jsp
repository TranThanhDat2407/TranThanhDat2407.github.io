<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.lang}" scope="request"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client Product</title>

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
	<fmt:setBundle basename="product_detail" scope="request"/>
	<!-- content -->
 	
    <!-- SLIDER -->

	
	<!-- NAVBAR -->

<nav aria-label="breadcrumb" style="margin-left:80px; margin-top: 15px;
    margin-bottom: 50px;">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="#" style="color:green; text-decoration: none;"><fmt:message key="product_detail.breadcrumHome"/></a></li>
    <li class="breadcrumb-item"><a href="#" style="color:green; text-decoration: none;"><fmt:message key="product_detail.breadcrumProducts"/></a></li>
    <li class="breadcrumb-item"><a href="#" style="color:green; text-decoration: none;">${productDetails.categoryName}</a></li>
    <li class="breadcrumb-item"><a href="#" style="color:green; text-decoration: none;">${productDetails.productName}</a></li>

  </ol>
</nav>
<!-- CONTENT -->
<div class="container mb-5">

  <div class="row">
    <div class="col-lg-7 col-sm-12 carousel slide " id="carouselExampleIndicators">
      <div class="row">

        <div class="col-lg-3 col-sm-12 ">

          <div class="carousel-indicators d-flex" style="position: unset;">

         <div class="row mb-0 me-0">
         	<c:set var="index" value="0" />
         	<c:forEach items="${thumbnails}" var="thumnail">
                  <div class="col-6">
                  <img src="<%=request.getContextPath()%>/views/imgs/products/${thumnail}"
                    class="bg-light ${index == 0 ? 'active' : ''}"
                    style="padding: 5px !important; width: 50px !important; height: 50px !important;"
                    alt="..." data-bs-target="#carouselExampleIndicators"
                    data-bs-slide-to="${index}" ${index == 0 ? 'class="active" aria-current="true"' : ''}
                    aria-label="Slide ${index + 1}">
                    <c:set var="index" value="${index + 1}" />
                    </div>
             </c:forEach>
                    
                </div>

          </div>

        </div>
        <div class="col-lg-9 col-sm-12">
          <div class="carousel-inner bg-light">
          <c:forEach items="${thumbnails}" var="thumnail" varStatus="loop">
            <div class="carousel-item ${loop.index == 0 ? 'active' : ''}">
              <img src="<%=request.getContextPath()%>/views/imgs/products/${thumnail}" style="width: 519px !important; height: 519px !important;"
                alt="...">
            </div>
            </c:forEach>
            <div class="d-flex justify-content-between align-content-center">
              <button class="carousel-control-prev text-bg-dark align-items-center position-absolute"
                style="top: 250px !important; height: 40px; width: 40px;" type="button"
                data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
              </button>
              <button class="carousel-control-next text-bg-dark position-absolute"
                style="top: 250px !important; height: 40px; width: 40px;" type="button"
                data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
              </button>
            </div>
          </div>

        </div>
      </div>


    </div>



    <div class="col-lg-5 col-sm-12">
      <div class="container">
        <h1 style="font-size:45px;">${productDetails.productName}</h1>


        <div class="d-flex">
          <h1>${productDetails.price}&nbsp;VNĐ</h1>
        </div>

        <p class="mb-4" style="color:#7d7d7d; font-size:17px">${productDetails.categoryName}</p>
        <hr>
		<form action="${pageContext.request.contextPath}/client/product_detail/addToCart" method="POST">
        <p class="fw-bold fs-6 mb-3"><fmt:message key="product_detail.qty"/></p>
        <select class="form-select pe-3 mb-2" name="quantity" style="width: 130px;">
          <option value="1">1</option>
  		  <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
        </select>
        <div class="mb-0 d-flex">
          <p><fmt:message key="product_detail.stock"/>:&nbsp;</p>
          <c:if test="${productDetails.qtyInStock > 0}">
          	<p class="text-success fw-bold"><fmt:message key="product_detail.inStock"/>: ${productDetails.qtyInStock} kg</p>
          </c:if>
          <c:if test="${productDetails.qtyInStock <= 0}">
        	<p class="text-success fw-bold"><fmt:message key="product_detail.soldOut"/></p>
   			</c:if>
        </div>

        <div class="d-grid mb-2">
        
  				<input type="hidden" name="product_id" value="${productDetails.productId}">
  				<button type="submit" class="btn btn-lg btn-outline-dark text-uppercase fw-bold"><fmt:message key="product_detail.AddToCart"/></button>
		</form>
        </div>
        <div class="d-flex">

       <form action="<%=request.getContextPath()%>/client/wishlist/InsertWL_DProduct?WL_product_id=${productDetails.productId} "
						method="post">
						<button class="btn btn-lg btn-danger text-uppercase fw-bold me-3"
							type="submit"
							style="width: 250px !important; height: 60px !important;">
							<i class='bx bx-heart'></i>
						</button>
					</form>

          <button class="btn btn-lg btn-outline-dark text-uppercase fw-bold"
            style="width:  250px !important; height: 60px !important;"><fmt:message key="product_detail.Products"/></button>

        </div>
        <hr class="mb-1">
        <p class="fw-bold fs-6 mb-0"><fmt:message key="product_detail.share"/></p>
        <a><i class='bx bxl-facebook-square text-success' style="font-size: 50px;"></i></a>
        <a> <i class='bx bxl-whatsapp-square text-success' style="font-size: 50px;"></i></a>
      </div>
    </div>
    <!-- here -->
    <div class="col-12">
      <div class="container">
        <div class="row">
          <div class="col-12">
            <h2 class="titel1"><fmt:message key="product_detail.description"/></h2>
          </div>
          <div class="col-12 detail_pr">

            <strong> 🌱 <fmt:message key="product_detail.detail"/></strong><br>
            <p> ${productDetails.description}
            </p>
            <br><br>
          </div>
          <div class="col-12" style="height: 1700px;  border: 2px solid #e1e1e1; border-radius: 20px;">
            <div class="row">
              <div class="col-12 titel2">
                <strong><fmt:message key="product_detail.reviews"/> ${productDetails.productName}</strong>
              </div>

              <div class="col-6 box1">
                <div class="row">
                  <div class="col-12 rating_text ">
                    <p><label for="">4.9</label>/5</p>
                    <p class="icon_rating_text" style="margin-top: -20px;">
                    	<i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                    	<i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                    	<i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                    	<i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                    	<i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                    </p>
                  </div>
                </div>
              </div>
              <c:if test="${empty reviewCounts}">
    				<p>Chưa có thống kê đánh giá cho sản phẩm này.</p>
			  </c:if>
			  <c:if test="${not empty reviewCounts}">
              	<div class="col-6 box2">
                <div class="row" style="margin-left: 80px; margin-top: 20px;">
					
                  <div class="col-12" style="margin-bottom: 8px;">
                    <div class="row">
                      <div class="col-2">
                        <strong>5</strong> <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                      </div>
                      <div class="col-8 " style="margin-left: -60px;">
                        <div class="crossbar_rating_red">
                        </div>
                      </div>
                      <div class="col-2"
                        style="margin-left: -40px;width: 100px;font-size: 13px; font-weight: 500;margin-top: 3px;">
                        ${reviewCounts.five_star_count} <fmt:message key="product_detail.RValue"/>
                      </div>
                    </div>
                  </div>
                  <div class="col-12" style="margin-bottom: 8px;">
                    <div class="row">
                      <div class="col-2">
                        <strong>4</strong> <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                      </div>
                      <div class="col-8 " style="margin-left: -47px;margin-top: 10px;">
                        <div class="row">
                          <div class="col-10 colors_rating_red" style="width: 280px;">
                          </div>
                          <div class="col-2 colors_rating_white" style="width: 70px;">
                          </div>
                        </div>
                      </div>
                      <div class="col-2"
                        style="margin-left: -53px;width: 100px; font-size: 13px; font-weight: 500;margin-top: 3px;">
                        ${reviewCounts.four_star_count} <fmt:message key="product_detail.RValue"/>
                      </div>
                    </div>
                  </div>
                  <div class="col-12" style="margin-bottom: 8px;">
                    <div class="row">
                      <div class="col-2">
                        <strong>3</strong> <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                      </div>
                      <div class="col-8 " style="margin-left: -47px;margin-top: 10px;">
                        <div class="row">
                          <div class="col-10 colors_rating_red" style="width: 190px;">
                          </div>
                          <div class="col-2 colors_rating_white" style="width: 160px;">
                          </div>
                        </div>
                      </div>
                      <div class="col-2"
                        style="margin-left: -53px;width: 100px; font-size: 13px; font-weight: 500;margin-top: 3px;">
                        ${reviewCounts.three_star_count} <fmt:message key="product_detail.RValue"/>
                      </div>
                    </div>
                  </div>
                  <div class="col-12" style="margin-bottom: 8px;">
                    <div class="row">
                      <div class="col-2">
                        <strong>2</strong> <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                      </div>
                      <div class="col-8 " style="margin-left: -47px;margin-top: 10px;">
                        <div class="row">
                          <div class="col-10 colors_rating_red" style="width: 100px;">
                          </div>
                          <div class="col-2 colors_rating_white" style="width: 250px;">
                          </div>
                        </div>
                      </div>
                      <div class="col-2"
                        style="margin-left: -53px;width: 100px; font-size: 13px; font-weight: 500;margin-top: 3px;">
                        ${reviewCounts.two_star_count} <fmt:message key="product_detail.RValue"/>
                      </div>
                    </div>
                  </div>
                  <div class="col-12" style="margin-bottom: 8px;">
                    <div class="row">
                      <div class="col-2">
                        <strong>1</strong> <i class="fa-solid fa-star" style="color: #FFD43B;"></i>
                      </div>
                      <div class="col-8 " style="margin-left: -47px;margin-top: 10px;">
                        <div class="row">
                          <div class="col-10 colors_rating_red" style="width: 20px;">
                          </div>
                          <div class="col-2 colors_rating_white" style="width: 325px;">
                          </div>
                        </div>
                      </div>
                      <div class="col-2"
                        style="margin-left: -52px;width: 100px;font-size: 13px; font-weight: 500;margin-top: 3px;">
                        ${reviewCounts.one_star_count} <fmt:message key="product_detail.RValue"/>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              </c:if>
              <hr style="margin: 50px;width: 1180px;color: #7a7979; ">
              <div class="col-12 box3" style="text-align: center;">
              <form action="${pageContext.request.contextPath}/client/product_detail" method="post">
              	<input type="hidden" name="action" value="createReview">
        		<input type="hidden" name="product_id" value="${productDetails.id}">
                <h4><fmt:message key="product_detail.reviewsTitle"/></h4>
                <div class="rate">
                  <div id="full-stars-example">
                    <div class="form-group">
            			<label for="ratingValue"><fmt:message key="product_detail.RValue"/></label>
            				<select name="ratingValue" id="ratingValue" required>
                				<option value="5">5 Stars</option>
                				<option value="4">4 Stars</option>
                				<option value="3">3 Stars</option>
                				<option value="2">2 Stars</option>
                				<option value="1">1 Star</option>
            				</select>
        			</div>
           
                </div>
                </div>
                <div class="form-floating" style="margin: 30px 50px;">
                  <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" name="comment" style="height: 100px"></textarea>
                  <label for="floatingTextarea2"><fmt:message key="product_detail.RValue"/></label>
                </div>
                <button type="submit" class="btn btn-danger" style="border: none;"><fmt:message key="product_detail.btnReviews"/></button>
                </form>
              </body>
              
             
              </div>
              <hr style="margin: 50px;width: 1180px;color: #7a7979; ">
              <div class="col-12">
                <div class="col-12 titel2 " style="margin-top: -40px; margin-left: 55px;">
                  <strong><fmt:message key="product_detail.filter"/></strong><br>
                  <button style="width: 80px; background-color: red; color: white;"><fmt:message key="product_detail.filterAll"/></button> 
                  <button><fmt:message key="product_detail.filterPic"/></button>
                </div>
              </div>
              <div class="col-12 box4">
                <button style="  background-color: white; border: 1px solid #c2c0c0; ">5 <i class="fa-solid fa-star"
                    style="color: #FFD43B;"></i></button>
                <button style="  background-color: white; border: 1px solid #c2c0c0; ">4 <i class="fa-solid fa-star"
                    style="color: #FFD43B;"></i></button>
                <button style="  background-color: white;border: 1px solid #c2c0c0;">3 <i class="fa-solid fa-star"
                    style="color: #FFD43B;"></i></button>
                <button style="  background-color: white; border: 1px solid #c2c0c0; ">2 <i class="fa-solid fa-star"
                    style="color: #FFD43B;"></i></button>
                <button style="  background-color: white; border: 1px solid #c2c0c0; ">1 <i class="fa-solid fa-star"
                    style="color: #FFD43B;"></i></button>

              </div>
              <div class="col-12 comment_rating" style="margin-left: 60px;  margin-top: 20px;">
                <div class="row" style="margin-top: 20px; ">
				<c:forEach items="${reviews}" var="review">
                  <div class="col-12" style="margin-top: 20px;">
                    <div class="row" >
                      <div class="col-1">
                        <div class="avata">
                        </div>
                      </div>
                      <div class="col-11" style="margin-left: -50px;margin-top: -10px; font-weight: 500;">
                        <strong style="font-size: 15px;">${review.user.name}</strong>
                        <label for="" style="margin-left: 0px;font-size: 11px;"><i class="fa-regular fa-clock"> </i>
                          <fmt:formatDate value="${review.createAt}" pattern="dd/MM/yyyy"/></label>
                        <br><label for=" " style="color: #35a22a; "><i class="fa-regular fa-circle-check"></i> <fmt:message key="product_detail.alreadyBuy"/></label>
                        <br><label for="" style="color: #2c2c2c; border-right: 1px solid black; width: 130px;"> <label
                            for="" style="font-weight: bold; font-size: 12px;"><fmt:message key="product_detail.rating"/> :</label> <label for=""
                            style="font-size: 15px; font-weight: bold;">${review.ratingValue}</label> <i class="fa-solid fa-star"
                            style="color: #FFD43B;"></i></label>
                        <div class="text_comment" style="width: 500px;  margin-top: 30px;">
                          <label for="" style="margin-left: 10px; ">${review.comment}
                        </div>
                      </div>
                      <hr style=" width: 1150px;color: #7a7979;margin-top: 30px;  ">
                    </div>
                  </div>
                  </c:forEach>
                </div>
               
              </div>
              <div class="btn_comment_rating box3 " style="width: 1150px;;margin-left: 60px;text-align: center;">
                <hr style=" width: 1150px;color: #e8e3e3;margin-left: -12px;margin-bottom: 50px; ">
                <button style="margin-top: -50px;"><fmt:message key="product_detail.more"/></button>
              </div>


            </div>



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
    

   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/angular-route.min.js"></script>
   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/webapp.js"></script>
   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/bootstrap.bundle.min.js"></script>
</body>
</html>
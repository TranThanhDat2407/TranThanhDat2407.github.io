<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.lang}" scope="request"/>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Client Index</title>


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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/views/ClientTemplate/css/style.css">
    <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/angular.min.js"></script>
</head>


<body >


 <%@include file="layout/nav.jsp"%>
<fmt:setBundle basename="home" scope="request"/>
	<!-- content -->

 	
    <!-- SLIDER -->

<div id="carouselExampleIndicators" class="carousel slide"
  data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleIndicators"
      data-bs-slide-to="0" class="active" aria-current="true"
      aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators"
      data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators"
      data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner row">
    <div class="carousel-item active position-relative col-6"
      data-bs-interval="3000">
      <img
        src="<%=request.getContextPath()%>/views/imgs/banner/Green and White Organic Fruits and Vegetables YouTube Thumbnail.png"
        class="d-block w-100" alt="..." style="height: 630px;">
      <div class="carousel-caption position-absolute"
        style="top: 425px;">
        <h1 class="fw-bold"> <fmt:message key="home.BannerTitle1"/> </h1>
        <div class="description"><fmt:message key="home.BannerSubTitle1"/></div>
        <a href="#!category"><button type="button"
            class="btn btn-light mt-3"><fmt:message key="home.BannerButton1"/></button></a>
      </div>
    </div>
    <div class="carousel-item" data-bs-interval="3000">
      <img
        src="<%=request.getContextPath()%>/views/imgs/banner/Fruits Photo Montage Nourriture Facebook Couverture.png"
        class="d-block w-100" alt="..." style="height: 630px;">
      <div class="carousel-caption position-absolute" style="top: 425px;">
        <h1 class="fw-bold"><fmt:message key="home.BannerTitle2"/></h1>
        <div class="description"><fmt:message key="home.BannerSubTitle2"/></div>
        <a href="#!category"><button type="button"
            class="btn btn-light mt-3"><fmt:message key="home.BannerButton2"/></button></a>
      </div>
    </div>
    <div class="carousel-item" data-bs-interval="3000">
      <img
        src="<%=request.getContextPath()%>/views/imgs/banner/banner2.png"
        class="d-block w-100" alt="..." style="height: 630px;">
      <div class="carousel-caption position-absolute" style="top: 425px;">
        <h1 class="fw-bold">RAU SẠCH</h1>
        <div class="description">Mua lẹ đi</div>
        <a href="#!category"><button type="button" class="btn btn-light mt-3">MUA NGAY</button></a>
      </div>
    </div>

  </div>
  <button class="carousel-control-prev" type="button"
    data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button"
    data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

<!-- SLIDER -->

<!-- ================================= -->
<section>
  <div class="container py-4">
    <div class="row py-5">
      <div class="col-8 m-auto">
        <div class="row text-center">
          <div class="col-4 p-4">
            <img src="<%=request.getContextPath()%>/views/imgs/products/avocado1.png"  class="img-fluid">
            <h5 class="mt-4 text-success"><fmt:message key="home.secondTitle1"/> </h5>
          </div>
          <div class="col-4 p-4">
            <img src="<%=request.getContextPath()%>/views/imgs/products/avocado2.png"  class="img-fluid">
            <h5 class="mt-4 text-success"><fmt:message key="home.secondTitle2"/></h5>
          </div>
          <div class="col-4 p-4">
            <img src="<%=request.getContextPath()%>/views/imgs/products/apple2.png"  class="img-fluid">
            <h5 class="mt-4 text-success"><fmt:message key="home.secondTitle3"/></h5>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>



    
	<!-- content -->

    <%@include file="layout/footer.jsp"%>
    

   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/angular-route.min.js"></script>
   <%--  <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/webapp.js"></script> --%>
    <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/bootstrap.bundle.min.js"></script>
</body>
</html>
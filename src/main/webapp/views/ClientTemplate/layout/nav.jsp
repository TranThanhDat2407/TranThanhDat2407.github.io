<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.lang}" scope="request"/>
<fmt:setBundle basename="header" scope="request"/>
<title>Insert title here</title>
<style>
header {
      background-color: #f2f2f2;
      padding: 10px;
    }

    /* CSS for the language selector */
    .language-selector {

    }

    .language-selector button {
      background-color: transparent;
      border: none;
      color: #333;
      padding: 5px 10px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
      margin: 0 5px;
      cursor: pointer;
    }

    .language-selector button.active {
      font-weight: bold;
    }
  </style>

</head>
<body>

  <header>
   <div class="language-selector d-flex justify-content-end">
       <a href="?lang=vi"> <button class="me-1 ${lang == 'vi' ? 'fw-bold':''} "  > <fmt:message key="header.lang_vi"/> </button></a>
	   <a href="?lang=en"> <button  class="me-1  ${lang == 'en' ? 'fw-bold':''}"> <fmt:message key="header.lang_en"/> </button></a>
	   <a href="?lang=zh"> <button  class="me-3  ${lang == 'zh' ? 'fw-bold':''}"> <fmt:message key="header.lang_zh"/> </button></a>
 	</div>
</header>
  <!-- NAVBAR -->

  <nav class="navbar navbar-expand-lg border-bottom  bg-light sticky-top">
    <div class="container">
      <a class="navbar-brand fw-bold fs-4 text-success" href="<%=request.getContextPath()%>/client">BRAND</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false"
        aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <div class="tabs">
              <a class="nav-link fw-bold text-dark" href="<%=request.getContextPath()%>/client"><fmt:message key="header.home"/></a>
            </div>
          </li>
          <li class="nav-item">
            <div class="tabs">
              <a class="nav-link fw-bold text-dark" href="<%=request.getContextPath()%>/client/products"><fmt:message key="header.products"/></a>
            </div>
          </li>
          <li class="nav-item">
            <div class="tabs">
              <a class="nav-link fw-bold text-dark" href="<%=request.getContextPath()%>/client/contact"><fmt:message key="header.contact"/></a>
            </div>
          </li>
          <li class="nav-item">
            <div class="tabs">
              <a class="nav-link fw-bold text-dark" href="#!news"><fmt:message key="header.news"/></a>
            </div>
          </li>
        </ul>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link" href="#"><i
                  class='bx bx-search fs-4 fw-bold text-dark'></i></a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link" href="#" id="navbarDropdown" role="button"
                data-bs-toggle="dropdown" aria-expanded="false">
                <i class='bx bx-user fs-4 fw-bold text-dark'></i>
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li  ${accountID == '' ? '' : 'hidden'}>
                  <a class="dropdown-item" href="<%=request.getContextPath()%>/client/signin"><fmt:message key="header.login"/></a>
                </li>
               
                <li ${accountID == '' ? 'hidden' : ''}>
              
                  <a class="dropdown-item" href="<%=request.getContextPath()%>/client/logout"><fmt:message key="header.logout"/></a>
                
                </li>
                <li ${accountID == '' ? 'hidden' : ''}><hr class="dropdown-divider"></li>
                <li ${accountID == '' ? 'hidden' : ''}>
                  <a class="dropdown-item" ${accountID == '' ? 'hidden' : ''} href="<%=request.getContextPath()%>/client/profile"><fmt:message key="header.profile"/></a>
                </li>
                <li ${accountID == '' ? '' : 'hidden'}><hr class="dropdown-divider"></li>

                <li  ${accountID == '' ? '' : 'hidden'}>
                  <a class="dropdown-item" href="<%=request.getContextPath()%>/client/signup"><fmt:message key="header.regis"/></a>
                </li>
              </ul>
            </li>

            <li class="nav-item position-relative" >
              <a class="nav-link" href="<%=request.getContextPath()%>/client/wishlist"><i
                  class='bx bx-heart fs-4 fw-bold text-dark'></i>
                  
                  <span class="badge rounded-pill badge-notification bg-danger position-absolute  ${countWishList == 0 ? 'd-none': ''}" 
                   style="top:2px; left: 40px; ">${countWishList}</span></a>
            </li>
            <li class="nav-item position-relative">
              <a class="nav-link" href="<%=request.getContextPath()%>/client/cart"><i
                  class='bx bx-cart fs-4 fw-bold text-dark'></i>
                  <span class="badge rounded-pill badge-notification bg-danger position-absolute ${countQtyCart == 0 ? 'd-none': ''}" 
                   style="top:2px; left: 40px;">${countQtyCart}</span>
                </a>
                  
            </li>
          </ul>
          <!-- 
          <form class="d-flex" role="search">
            <input class=" me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn" type="submit">Search</button>
          </form> -->
        </div>
      </div>
    </div>
  </nav>

  <!-- <nav class="navbar navbar-expand-lg border-bottom sticky-top bg-light">
    <div class="container">
      <a class="navbar-brand fw-bold fs-4 text-success" href="#">BRAND</a>
      <div class="input-group me-auto mb-2 mb-lg-0">
        <span class="input-group-text"  id="basic-addon1"><i class='bx bx-search fs-3' ></i></span>
        <input type="text" class="form-control" >
      </div>
      <i class='bx bx-x fs-1' ></i>
      
  </nav> -->
  <!-- NAVBAR -->
  
</body>
</html>
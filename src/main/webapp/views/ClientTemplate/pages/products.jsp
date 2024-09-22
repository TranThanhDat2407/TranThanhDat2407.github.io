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
		<fmt:setBundle basename="products" scope="request"/>
	<!-- content -->
 	
    <!-- SLIDER -->

	
	<div class="container">
<nav aria-label="breadcrumb" style="margin-top: 15px;
margin-bottom: 50px;">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="#" style="color:green; text-decoration: none;"><fmt:message key="products.breadcrumHome"/></a></li>
    <li class="breadcrumb-item"><a href="#" style="color:green; text-decoration: none;"><fmt:message key="products.breadcrumProducts"/></a></li>
    <li class="breadcrumb-item active" aria-current="page"><fmt:message key="products.breadcrumAllProducts"/></li>
  </ol>
</nav>
<div class="container mb-5">

  

    <div class="row">
      <div class="col-8">
        
        <form action="" method="get">
       
          <input type="text" id="form1" class="form-control" placeholder="<fmt:message key="products.search"/>" aria-label="Search" name="keyword" value="${keyword}" />
         
           </div>
           <div class="col-2">
          <button class="btn btn-success form-control" type="submit"><fmt:message key="products.search"/></button>
          </div>
       
         
  		  </form>
        
             
     

      <div class="col-2 d-flex justify-content-end mb-2">
        <div class="dropdown">
         <input type="hidden" name="sortField" value="${sortField}">
  		  <input type="hidden" name="sortDirection" value="${sortDirection}">
          <button class="btn btn-success dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
            <i class='bx bx-sort'></i>&nbsp;&nbsp;<fmt:message key="products.orderBy"/>&nbsp;
          </button>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="?sortField=price&sortDirection=${sortField == 'price' && sortDirection == 'ASC' ? 'DESC' : 'ASC'}&page=${currentPage}&keyword=${keyword}">
    				<fmt:message key="products.orderPrice"/> ${sortField == 'price' && sortDirection == 'ASC' ? '▲' : '▼'}
  			</a></li>
  			<li><a class="dropdown-item" href="?sortField=name&sortDirection=${sortField == 'name' && sortDirection == 'ASC' ? 'DESC' : 'ASC'}&page=${currentPage}&keyword=${keyword}">
    				<fmt:message key="products.orderName"/> ${sortField == 'name' && sortDirection == 'ASC' ? '▲' : '▼'}
  			</a></li>
          </ul>
        </div>
      </div>
</form>
      <div class="col-3 ">

        <div class="accordion " id="accordionExample">
        <c:forEach items="${listParent}" var="cateParent">
        	<div class="accordion-item">
            <h2 class="accordion-header">
              <button class="accordion-button fw-bold" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne" ng-click="keyword = ''">
                ${cateParent.name}
              </button>
            </h2>
             <div id="collapse${cateParent.id}" class="accordion-collapse collapse show" 
             data-bs-parent="#accordionExample">
            <ul class="list-group list-group-flush">
                <c:forEach items="${cateParent.subCategories}" var="childCategory">
                    <a href="?categoryId=${childCategory.id}" 
                       class="list-group-item list-group-item-action">
                        ${childCategory.name}
                    </a>
                </c:forEach>
            </ul>
        </div>
          </div>
        </c:forEach>
          
          <!-- <div class="accordion-item">
            <h2 class="accordion-header">
              <button class="accordion-button collapsed fw-bold" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                Rau Củ
              </button>
            </h2>
            <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
              
              <ul class="list-group list-group-flush">
                <a href="#" class="list-group-item list-group-item-action">Táo</a>
                <a href="#" class="list-group-item list-group-item-action">Chuối</a>
                <a href="#" class="list-group-item list-group-item-action">Kiwi</a>
                <a href="#" class="list-group-item list-group-item-action">Nho</a>
                <a href="#" class="list-group-item list-group-item-action">Cherry</a>
                <a href="#" class="list-group-item list-group-item-action">Bơ</a>
                <a href="#" class="list-group-item list-group-item-action">Việt Quất</a>
              </ul>

            </div>
          </div>
          <div class="accordion-item">
            <h2 class="accordion-header">
              <button class="accordion-button collapsed fw-bold" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                Khác
              </button>
            </h2>
            <div id="collapseThree" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
             
              <ul class="list-group list-group-flush">
                <a href="#" class="list-group-item list-group-item-action">Táo</a>
                <a href="#" class="list-group-item list-group-item-action">Chuối</a>
                <a href="#" class="list-group-item list-group-item-action">Kiwi</a>
                <a href="#" class="list-group-item list-group-item-action">Nho</a>
                <a href="#" class="list-group-item list-group-item-action">Cherry</a>
                <a href="#" class="list-group-item list-group-item-action">Bơ</a>
                <a href="#" class="list-group-item list-group-item-action">Việt Quất</a>
              </ul>

            </div>
          </div> -->
        </div>
        
      </div>
      
      <div class="col-9">
        <div class="row">
        <c:forEach items="${productItemList}" var="ProdItem">
          <div class="col-12 col-sm-8 col-md-4 col-lg-3" ng-repeat="p in products | test:keyword:['name','price'] 
          | orderBy : prop  | limitTo:limit:start">
            <a href="<%=request.getContextPath()%>/client/product_detail?product_id=${ProdItem.product.id}" style="color: inherit;" class="text-decoration-none">
            <div class="card text-black">
              <div class="card-header bg-light">
              <img src="<%=request.getContextPath()%>/views/imgs/products/${ProdItem.product.galleries[0].thumbnail}"
                class="card-img-top img-fluid" alt="iPhone"   style="width: 189px !important; height: 207px !important;"/>
              </div>
              <div class="card-body">
                <div class="text-center mt-1">
                  <h4 class="card-title text-success">${ProdItem.product.name}</h4>
                  <h6 class="text-dark mb-1 pb-3">
                  	<fmt:formatNumber value="${ProdItem.price}"/> VNĐ /kg
                  
                  </h6>
                </div>
              
                <div class="d-flex flex-row">
                  <form action="<%=request.getContextPath()%>/client/wishlist/create?product_item_id=${ProdItem.id}" method="post">
                 <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-danger flex-fill me-1 " data-mdb-ripple-color="dark">
                    <i class='bx bx-heart'></i>
                  </button></form>
                  <form action="<%=request.getContextPath()%>/client/cart/createOrUpdate?product_id=${ProdItem.product.id}" method="post">
                  <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-success flex-fill ms-1" style="width: 140px;"><fmt:message key="products.buyNow"/></button>
                </form>
                </div>
                
              </div>
              
            </div>
          </a>
          </div>
		</c:forEach>
		</div>
		</div>
           <!-- <div class="col-md-12 col-lg-3 mb-5 mb-lg-0">
            <a href="./product_detail.html" style="color: inherit;" class="text-decoration-none">
            <div class="card text-black">
              <div class="card-header bg-light">
              <img src="./imgs/products/apple2.png"
                class="card-img-top img-fluid" alt="iPhone" />
              </div>
              <div class="card-body">
                <div class="text-center mt-1">
                  <h4 class="card-title text-success">Táo</h4>
                  <h6 class="text-dark mb-1 pb-3">60.000 VNĐ</h6>
                </div>
    
                <div class="d-flex flex-row">
                  <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-danger flex-fill me-1" data-mdb-ripple-color="dark">
                    <i class='bx bx-heart'></i>
                  </button>
                  <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-success flex-fill ms-1">Mua Ngay</button>
                </div>
              </div>
            </div>
          </a>
          </div>

          <div class="col-md-12 col-lg-3 mb-5 mb-lg-0">
            <a href="./product_detail.html" style="color: inherit;" class="text-decoration-none">
            <div class="card text-black">
              <div class="card-header bg-light">
              <img src="./imgs/products/apple2.png"
                class="card-img-top img-fluid" alt="iPhone" />
              </div>
              <div class="card-body">
                <div class="text-center mt-1">
                  <h4 class="card-title text-success">Táo</h4>
                  <h6 class="text-dark mb-1 pb-3">60.000 VNĐ</h6>
                </div>
    
                <div class="d-flex flex-row">
                  <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-danger flex-fill me-1" data-mdb-ripple-color="dark">
                    <i class='bx bx-heart'></i>
                  </button>
                  <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-success flex-fill ms-1">Mua Ngay</button>
                </div>
              </div>
            </div>
          </a>
          </div>

          <div class="col-md-12 col-lg-3 mb-5 mb-lg-0">
            <a href="./product_detail.html" style="color: inherit;" class="text-decoration-none">
            <div class="card text-black">
              <div class="card-header bg-light">
              <img src="./imgs/products/apple2.png"
                class="card-img-top img-fluid" alt="iPhone" />
              </div>
              <div class="card-body">
                <div class="text-center mt-1">
                  <h4 class="card-title text-success">Táo</h4>
                  <h6 class="text-dark mb-1 pb-3">60.000 VNĐ</h6>
                </div>
    
                <div class="d-flex flex-row">
                  <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-danger flex-fill me-1" data-mdb-ripple-color="dark">
                    <i class='bx bx-heart'></i>
                  </button>
                  <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-success flex-fill ms-1">Mua Ngay</button>
                </div>
              </div>
            </div>
          </a>
          </div>
          
          <div class="col-md-12 col-lg-3 mb-5 mb-lg-0">
            <a href="./product_detail.html" style="color: inherit;" class="text-decoration-none">
            <div class="card text-black">
              <div class="card-header bg-light">
              <img src="./imgs/products/apple2.png"
                class="card-img-top img-fluid" alt="iPhone" />
              </div>
              <div class="card-body">
                <div class="text-center mt-1">
                  <h4 class="card-title text-success">Táo</h4>
                  <h6 class="text-dark mb-1 pb-3">60.000 VNĐ</h6>
                </div>
    
                <div class="d-flex flex-row">
                  <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-danger flex-fill me-1" data-mdb-ripple-color="dark">
                    <i class='bx bx-heart'></i>
                  </button>
                  <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-success flex-fill ms-1">Mua Ngay</button>
                </div>
              </div>
            </div>
          </a>
          </div>

          <div class="col-md-12 col-lg-3 mb-5 mb-lg-0">
            <a href="./product_detail.html" style="color: inherit;" class="text-decoration-none">
            <div class="card text-black">
              <div class="card-header bg-light">
              <img src="./imgs/products/apple2.png"
                class="card-img-top img-fluid" alt="iPhone" />
              </div>
              <div class="card-body">
                <div class="text-center mt-1">
                  <h4 class="card-title text-success">Táo</h4>
                  <h6 class="text-dark mb-1 pb-3">60.000 VNĐ</h6>
                </div>
    
                <div class="d-flex flex-row">
                  <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-danger flex-fill me-1" data-mdb-ripple-color="dark">
                    <i class='bx bx-heart'></i>
                  </button>
                  <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-success flex-fill ms-1">Mua Ngay</button>
                </div>
              </div>
            </div>
          </a>
          </div>

          <div class="col-md-12 col-lg-3 mb-5 mb-lg-0">
            <a href="./product_detail.html" style="color: inherit;" class="text-decoration-none">
            <div class="card text-black">
              <div class="card-header bg-light">
              <img src="./imgs/products/apple2.png"
                class="card-img-top img-fluid" alt="iPhone" />
              </div>
              <div class="card-body">
                <div class="text-center mt-1">
                  <h4 class="card-title text-success">Táo</h4>
                  <h6 class="text-dark mb-1 pb-3">60.000 VNĐ</h6>
                </div>
    
                <div class="d-flex flex-row">
                  <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-danger flex-fill me-1" data-mdb-ripple-color="dark">
                    <i class='bx bx-heart'></i>
                  </button>
                  <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-success flex-fill ms-1">Mua Ngay</button>
                </div>
              </div>
            </div>
          </a>
          </div>

          <div class="col-md-12 col-lg-3 mb-5 mb-lg-0">
            <a href="./product_detail.html" style="color: inherit;" class="text-decoration-none">
            <div class="card text-black">
              <div class="card-header bg-light">
              <img src="./imgs/products/apple2.png"
                class="card-img-top img-fluid" alt="iPhone" />
              </div>
              <div class="card-body">
                <div class="text-center mt-1">
                  <h4 class="card-title text-success">Táo</h4>
                  <h6 class="text-dark mb-1 pb-3">60.000 VNĐ</h6>
                </div>
    
                <div class="d-flex flex-row">
                  <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-danger flex-fill me-1" data-mdb-ripple-color="dark">
                    <i class='bx bx-heart'></i>
                  </button>
                  <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-success flex-fill ms-1">Mua Ngay</button>
                </div>
              </div>
            </div>
          </a>
          </div> -->

        </div>
      </div>
    
    </div>
    <nav aria-label="Page navigation example" class="mt-4">
      <ul class="pagination justify-content-end ">
        <li class="page-item {{page == 1 ? 'disabled' : ''}}" >
            <c:if test="${currentPage == 1}">
  				<a class="page-link text-success fw-bold disabled" href="?&page=${currentPage - 1}"><fmt:message key="products.pre"/></a>
			</c:if>
			<c:if test="${currentPage != 1}">
  				<a class="page-link text-success fw-bold" href="?&page=${currentPage - 1}"><fmt:message key="products.pre"/></a>
			</c:if>
        </li>
        <c:forEach items="${pages}" var="pa">
        <li class="page-item">
        	<a class="page-link text-success fw-bold ${pa ==  currentPage ? 'active':''}"   href="?&page=${pa}">
        		${pa}
        	</a>
        </li>
       </c:forEach>
        <li class="page-item">
          <c:if test="${currentPage == totalPages}">
  				<a class="page-link text-success fw-bold disabled" href="?&page=${currentPage + 1}"><fmt:message key="products.next"/></a>
			</c:if>
			<c:if test="${currentPage != totalPages}">
  				<a class="page-link text-success fw-bold" href="?&page=${currentPage + 1}"><fmt:message key="products.next"/></a>
			</c:if>
        </li>
      </ul>
    </nav>
  </div>
</div>

    
	<!-- content -->

   <%@include file="/views/ClientTemplate/layout/footer.jsp"%>
    

   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/angular-route.min.js"></script>
   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/webapp.js"></script>
   <script src="<%=request.getContextPath()%>/views/ClientTemplate/js/bootstrap.bundle.min.js"></script>
</body>
</html>
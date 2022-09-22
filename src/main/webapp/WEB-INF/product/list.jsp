<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Product</title>
    <jsp:include page="/layout/css_head.jsp"></jsp:include>
    <style>
        thead i:hover {
            color: greenyellow;
            font-size: 20px;
            cursor: pointer;
            background:  #e0eefa;
        }
    </style>
</head>
<body>
<jsp:include page="/layout/header.jsp"></jsp:include>
<jsp:include page="/layout/left_bar.jsp"></jsp:include>


<div class="page-content">
    <div class="container-fluid">

        <!-- start page title -->
        <div class="row" style="justify-content: flex-end;">
            <div class="col-10">
                <div class="page-title-box d-flex align-items-center justify-content-between">
                    <h4 class="mb-0 font-size-18">Product List</h4>
                </div>
            </div>
        </div>
        <!-- end page title -->
        <div class="row" style="justify-content: flex-end;">
            <div class="col-10">
                <div class="page-title-box d-flex align-items-center justify-content-between">
                    <div class="page-title-right">
                        <ol class="breadcrumb m-0">
                            <li class="breadcrumb-item"><a href="javascript: void(0);">Product</a></li>
                            <li class="breadcrumb-item active">List Product</li>
                        </ol>
                    </div>

                </div>
            </div>
        </div>
        <div class="row" style="position: fixed;right: -47px;top: 84px; z-index: 999">
            <div class="col-12">
                <c:if test="${requestScope.error!=null}">
                    <div class="alert alert-icon alert-danger alert-dismissible fade show mb-0 col-10" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <strong>Errors!</strong> <br>
                        <li>${requestScope.error}</li>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="row" style="justify-content: flex-end;">
            <div class="col-lg-10">
                <div class="">
                    <div class="table-responsive">
 <div class="table-responsive gopBuuton"style="display: flex;
    flex-direction: row;

    justify-content: flex-start;
    flex-wrap: wrap;
 align-items: baseline;">
                        <button class="btn-primary" style="border: solid 2px;
                                border-radius: 5px; height: 35px;" >
                            <a href="<c:url value="/products?action=create"/>" style="color: white">
                                Create Product
                            </a>
                        </button>
     <form method="get" action="/products?action=search" class="app-search">
         <div class="app-search-box" style="margin-left: 50px;">
             <div class="input-group">
                 <input oninput="search(event)" style="width: 500px;border-radius: 20px" type="text" name="searchproduct" class="form-control" placeholder="Search..." value="">
                 <div class="input-group-append">
                     <button class="btn " type="submit">
                         <i class="fas fa-search text-dark"></i>
                     </button>
                 </div>
             </div>
         </div>
     </form>

                    </div>
                        <table class="table project-list-table table-nowrap table-centered table-borderless">
                            <thead >
                            <tr>
                                <th scope="col" style="text-align: center">ID</th>
                                <th scope="col" style="width:30px">Image</th>
                                <th scope="col" style="text-align: center">Name</th>
                                <th scope="col" style="text-align: center">Quantity</th>
                                <th scope="col" style="text-align: center">
                                    <a href="<c:url value="/products?action=sortdesc"/>">
                                        <i class="fa fa-sort-amount-desc" aria-hidden="true"></i>
                                    </a>
                                    Price
                                    <a href="<c:url value="/products?action=sortasc"/>">
                                        <i class="fa fa-sort-amount-asc" aria-hidden="true"></i>
                                    </a>

                                </th>
                                <th scope="col" style="text-align: center">Category</th>
                                <th scope="col" style="text-align: center;">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.products}" var="item">
                                <tr>
                                    <td style="text-align: center">${item.getId()}</td>
                                    <td><img width="100" height="100" src="${item.getImg()}"
                                             alt="Image ${item.getName()}"
                                             title="${item.getName()}"></td>
                                    <td>${item.getName()}</td>
                                    <td style="text-align: center">${item.getQuantity()}</td>
                                    <td style="text-align: end">
                                        <fmt:formatNumber type="number" pattern="###,###,###,### VNĐ"
                                                          value="${item.getPrice()}"/>
                                    </td>
                                    <td style="text-align: center">
                                        <c:forEach var="category" items="${applicationScope.categories}">
                                            <c:if test="${item.getCategoryId() == category.getCid()}">
                                                <c:out value="${category.getcName()}"/>
                                            </c:if>
                                        </c:forEach>

                                    </td>
                                    <td style="display: flex;justify-content: space-evenly; margin-top: 40px;">

                                        <a href="<c:url value="/products?action=edit&id=${item.getId()}"/>"><i
                                                class="dripicons-pencil"></i></a>
                                        <a href="<c:url value="/products?action=delete&id=${item.getId()}"/>"><i
                                                class="dripicons-cross"></i></a>

                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">

                                <c:if test="${requestScope.currentPage != 1}">
                                    <li class="page-item"><a class="page-link"
                                                             href="<c:url value="/products"/>">back to list</a>
                                    </li>
                                </c:if>
                                <c:forEach begin="1" end="${noOfPages}" var="i">
                                    <c:choose>
                                        <c:when test="${requestScope.currentPage eq i}">
                                            <li class="page-item"><a class="page-link"
                                                                     href="<c:url value="/products?page=${i}&searchproduct=${requestScope.searchproduct}"/>">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link"
                                                                     href="<c:url value="/products?page=${i}&searchproduct=${requestScope.searchproduct}"/>">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                                    <li class="page-item"><a class="page-link"
                                                             href="<c:url value="/products?page=${requestScope.currentPage + 1}&searchproduct=${requestScope.searchproduct}"/>">Next</a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>


    </div> <!-- container-fluid -->
</div>

<jsp:include page="/layout/footer.jsp"></jsp:include>
<!-- end row -->
<jsp:include page="/layout/footer_js.jsp">
    <jsp:param name="page" value="list"/>
</jsp:include>

<c:if test="${requestScope.message !=null}">
    <script>

        window.onload = function (e) {
            toastr["success"]("Product has been deleted!");
        }
    </script>

</c:if>

<jsp:include page="/layout/footer_js.jsp">
    <jsp:param name="page" value="create"/>
    <jsp:param name="toast" value="create"/>
</jsp:include>

<script>

    toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": false,
        "progressBar": true,
        "positionClass": "toast-top-right",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }

</script>
</body>
</html>

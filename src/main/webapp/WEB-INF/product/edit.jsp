<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Form</title>
    <jsp:include page="/layout/css_head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/layout/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="card-box">
                <c:if test="${requestScope.errors !=null}">
                    <div class="alert alert-icon alert-danger alert-dismissible fade show mb-0" role="alert" style="margin-top: 80px">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true"></span>
                        </button>
                        <strong>Errors!</strong> <br>
                        <c:forEach items="${requestScope.errors}" var="item">
                            <li>${item}</li>
                        </c:forEach>
                    </div>
                </c:if>
                <form class="form-horizontal" method="post" action="/products?action=edit" style="margin-top: 80px">
                    <c:if test="${requestScope.product != null}">
                        <input type="hidden" name="id" value="${requestScope.product.getId()}"/>
                        <h4>Edit product: <c:out value="${requestScope.product.getName()}"/></h4>
                    </c:if>
                    <div class="form-group row">

                        <label for="image" class="col-md-2 control-label"
                               style="text-align: center; padding: 10px">Image</label>
                        <div class="col-md-8">
                            <input type="text" id="image" class="form-control" name="img"
                                   value="${requestScope.product.getImg()}" placeholder="Enter URL image">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="name" class="col-md-2 control-label"
                               style="text-align: center; padding: 10px">Name</label>
                        <div class="col-md-8">
                            <input type="text" id="name" class="form-control" name="name"
                                   value="${requestScope.product.getName()}" placeholder="Enter product's name">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="quantity" class="col-md-2 control-label" style="text-align: center; padding: 10px">Quantity</label>
                        <div class="col-md-8">
                            <input type="number" id="quantity" name="quantity" class="form-control" placeholder="quantity"
                                   value="${requestScope.product.getQuantity()}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="price" class="col-md-2 control-label"
                               style="text-align: center; padding: 10px">Price</label>
                        <div class="col-md-8">
                            <input type="number" id="price" name="price" class="form-control"
                                   placeholder="<fmt:formatNumber type="number" pattern="###,###,###,### VNĐ" value="${requestScope.product.getPrice()}" />"
                                   value="${requestScope.product.getPrice()}"
                            >
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="category" class="col-md-2 control-label" style="text-align: center">Category</label>
                        <div class="col-md-10">
                            <select name="category" id="category">
                                <c:forEach var="category" items="${applicationScope.categories}">
                                    <c:choose>
                                        <c:when test="${category.getCid() == requestScope.product.getCategoryId()}">
                                            <option value="${category.getCid()}" selected>
                                                <c:out value="${category.getcName() }"/>
                                            </option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${category.getCid()}">
                                                <c:out value="${category.getcName() }"/>
                                            </option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row" style="display: flex;justify-content: flex-end;padding-right: 250px;">
                        <button type="submit" style="margin-right: 10px" class="btn-outline-success">Update</button>
                        <button class="btn-secondary">
                            <a style="color: white" href="<c:url value="/products"/>">
                                Cancel
                            </a>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/layout/footer_js.jsp">
    <jsp:param name="page" value="create"/>
    <jsp:param name="toast" value="create"/>
</jsp:include>

<c:if test="${requestScope.message !=null}">
    <script>

        window.onload = function (e) {
            toastr["success"]("Product information was updated!");
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

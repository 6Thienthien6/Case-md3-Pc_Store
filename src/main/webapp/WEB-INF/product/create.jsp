<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create Form</title>
    <jsp:include page="/layout/css_head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/layout/header.jsp"></jsp:include>


<div class="row">
    <div class="col-sm-12">
        <div class="card-box">
            <h4 class="header-title"style="margin-top: 90px; padding-left: 60px;">Input Types</h4>
            <c:if test="${requestScope.errors!=null}">
                <div class="alert alert-icon alert-danger alert-dismissible fade show mb-0" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <strong>Errors!</strong> <br>
                    <c:forEach items="${requestScope.errors}" var="item">
                        <li>${item}</li>
                    </c:forEach>
                </div>
            </c:if>
            <form class="form-horizontal" method="post" action="<c:url value="/products?action=create"/>">
                <div class="form-group row">
                    <label for="image" class="col-md-2 control-label"
                           style="text-align: center; padding: 10px">Image</label>
                    <div class="col-md-8">
                        <input type="text" id="image" class="form-control" name="image"
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
                        <input type="number" id="quantity" name="quantity" class="form-control" placeholder="Quantity"
                               value="${requestScope.product.getQuantity()}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="price" class="col-md-2 control-label"
                           style="text-align: center; padding: 10px">Price</label>
                    <div class="col-md-8">
                        <input type="number" id="price" name="price" class="form-control" placeholder="Price"
                               value="${requestScope.product.getPrice()}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="category" class="col-md-2 control-label" style="text-align: center">Category</label>
                    <div class="col-md-10">
                        <select name="category" id="category">
                            <c:forEach var="category" items="${applicationScope.categories}">
                                <option value="${category.getCid()}">
                                    <c:out value="${category.getcName() }"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group row" style="display: flex;justify-content: flex-end;padding-right: 250px;">
                    <button type="submit" style="margin-right: 10px" class="btn-outline-primary">Create</button>
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

<jsp:include page="/layout/footer_js.jsp">
    <jsp:param name="page" value="create"/>
    <jsp:param name="toast" value="create"/>
</jsp:include>

<c:if test="${requestScope.message !=null}">
    <script>

        window.onload = function (e) {
            toastr["success"]("New Product was created!");
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
<%--<jsp:include page="/layout/footer.jsp"></jsp:include>--%>
<!-- end row -->



</html>

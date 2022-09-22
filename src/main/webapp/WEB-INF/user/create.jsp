<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Form</title>
    <jsp:include page="/layout/css_head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/layout/header.jsp"></jsp:include>

<div class="container">
    <div class="row" style="justify-content: space-around; margin-top: 80px">
        <div class="col-sm-8">
            <div class="card-box">
                <h3 class="header-title" style="text-align: center">Add user</h3>
                <c:if test="${requestScope.errors!=null}">
                    <div class="alert alert-icon alert-danger alert-dismissible fade show mb-0" role="alert"
                         style="position: fixed;bottom: 8px;right: 10px;">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <strong>Errors!</strong> <br>
                        <c:forEach items="${requestScope.errors}" var="item">
                            <li>${item}</li>
                        </c:forEach>
                    </div>
                </c:if>
                <form method="post" data-parsley-validate="" novalidate="">
                    <div class="form-group">

                        <label for="username">User Name<span class="text-danger">*</span></label>
                        <input type="text" name="username" value="${requestScope.user.getUsername()}" required=""
                               placeholder="Enter user name" class="form-control" id="userName">
                    </div>

                    <div class="form-group">
                        <label for="pass">Password<span class="text-danger">*</span></label>
                        <input id="pass" type="password" value="${requestScope.user.getPassword()}" name="password"
                               placeholder="Password" required="" class="form-control">
                    </div>

                    <div class="form-group">
                        <label for="fullname">Full Name<span class="text-danger">*</span></label>
                        <input type="text" name="fullname" value="${requestScope.user.getFullName()}" required=""
                               placeholder="Enter Fullname" class="form-control" id="fullname">
                    </div>

                    <div class="form-group">
                        <label for="phone">Phone<span class="text-danger">*</span></label>
                        <input type="text" name="phone" value="${requestScope.user.getPhone()}" required=""
                               placeholder="Enter Phone" class="form-control" id="phone">
                    </div>

                    <div class="form-group">
                        <label for="email">Email<span class="text-danger">*</span></label>
                        <input type="email" name="email" value="${requestScope.user.getEmail()}" required=""
                               placeholder="Enter email" class="form-control" id="email">
                    </div>

                    <div class="form-group">
                        <label for="address">Address<span class="text-danger">*</span></label>
                        <input type="text" name="address" value="${requestScope.user.getAddress()}" required=""
                               placeholder="Enter address" class="form-control" id="address">
                    </div>

                    <div class="form-group">
                    </div>

                    <div class="form-group text-center mb-0">
                        <button class="btn btn-outline-primary waves-effect waves-light" type="submit">
                            Create
                        </button>
                        <button type="reset" class="btn btn-secondary waves-effect ml-1">
                            <a href="<c:url value="/users"/>" style="color:#fff;">Cancel</a>
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
            toastr["success"]("User was created!");
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

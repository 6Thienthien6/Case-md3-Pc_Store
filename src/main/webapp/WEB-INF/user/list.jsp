<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List User</title>
    <jsp:include page="/layout/css_head.jsp"></jsp:include>

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
                    <h4 class="mb-0 font-size-18">User List</h4>
                </div>
            </div>
        </div>
        <!-- end page title -->
        <div class="row" style="justify-content: flex-end;">
            <div class="col-10">
                <div class="page-title-box d-flex align-items-center justify-content-between">
                    <div class="page-title-right">
                        <ol class="breadcrumb m-0">
                            <li class="breadcrumb-item"><a href="javascript: void(0);">User</a></li>
                            <li class="breadcrumb-item active">User List</li>
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
                                border-radius: 5px; height: 35px;">
                            <a href="<c:url value="/users?action=create"/>" style="color: white">
                                Create User
                            </a>
                        </button>
                        <form method="get" action="/users?action=search" class="app-search">
                            <div class="app-search-box" style="margin-left: 50px;">
                                <div class="input-group">
                                    <input oninput="search(event)" style="width: 500px;border-radius: 20px" type="text" name="searchuser" class="form-control" placeholder="Search..." value="${requestScope.searchuser}">
                                    <div class="input-group-append">
                                        <button class="btn " type="submit">
                                            <i class="fas fa-search text-dark"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                        </div>

                        <table class="table project-list-table table-hover table-nowrap table-centered table-borderless">
                            <thead class="bg-success text-dark">
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>Phone</th>
                            <th>Email</th>
                            <th>Address</th>
                            <th>Action</th>
                            </thead>
                            <tbody class="bg-light text-secondary">
                            <c:forEach var="user" items="${requestScope.listUser}">
                                <tr>
                                    <td>${user.getId()}</td>
                                    <td>${user.getFullName()}</td>
                                    <td>${user.getPhone()}</td>
                                    <td>${user.getEmail()}</td>
                                    <td>${user.getAddress()}</td>
                                    <td>
                                        <a href="<c:url value="/users?action=edit&id=${user.getId()}"/>"
                                           class="btn btn-outline-secondary"><i
                                                class="fas fa-edit "></i>
                                        </a>

                                        <a
                                                href="<c:url value="/users?action=delete&id=${user.getId()}"/>"

                                           class="btn btn-outline-danger"
                                           onclick="return confirm('Are you sure you want to delete this user?');"
<%--                                           onclick="confirmDelete(${user.getFullName()});"--%>
                                        >
                                            <i
                                                    class="fas fa-trash ">
                                            </i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">

                                <c:if test="${requestScope.currentPage != 1}">
                                    <li class="page-item"><a class="page-link"
                                                             href="/users?">Back to list</a>
                                    </li>
                                </c:if>
                                <c:forEach begin="1" end="${noOfPages}" var="i">
                                    <c:choose>
                                        <c:when test="${requestScope.currentPage eq i}">
                                            <li class="page-item"><a class="page-link"
                                                                     href="/users?page=${i}&searchuser=${requestScope.searchuser}">${i}</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link"
                                                                     href="/users?page=${i}&searchuser=${requestScope.searchuser}">${i}</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                                    <li class="page-item"><a class="page-link"
                                                             href="/users?page=${requestScope.currentPage + 1}&searchuser=${requestScope.searchuser}">Next</a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <!-- end row -->

        <%--        <div class="row">--%>
        <%--            <div class="col-12">--%>
        <%--                <div class="text-center my-3">--%>
        <%--                    <a href="javascript:void(0);" class="text-success"><i class="bx bx-loader bx-spin font-size-18 align-middle mr-2"></i> Load more </a>--%>
        <%--                </div>--%>
        <%--            </div> <!-- end col-->--%>
        <%--        </div>--%>

    </div> <!-- container-fluid -->
</div>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<jsp:include page="/layout/footer.jsp"></jsp:include>
<!-- end row -->
<jsp:include page="/layout/footer_js.jsp">
    <jsp:param name="page" value="list"/>
</jsp:include>

<c:if test="${requestScope.message !=null}">
    <script>

        window.onload = function (e) {
            toastr["success"]("User has been deleted!");
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
    };

    function confirmDelete(fullname) {
        alert("aaa");
        Swal.fire({
            title: 'Do you want delete ' + fullname + '?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire(
                    'Deleted!',
                    'Your file has been deleted.',
                    'success'
                )
            }
        })
    }
</script>
</body>
</html>

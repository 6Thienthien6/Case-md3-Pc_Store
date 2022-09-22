<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="/assets/libs/jquery/jquery.min.js"></script>
<script src="/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/assets/libs/metismenu/metisMenu.min.js"></script>
<script src="/assets/libs/simplebar/simplebar.min.js"></script>
<script src="/assets/libs/node-waves/waves.min.js"></script>

<!-- Chart JS -->
<script src="/assets/libs/chart.js/Chart.bundle.min.js"></script>
<script src="/assets/js/pages/chartjs.init.js"></script>

<c:if test="${param.toast =='create'}">
    <script src="/assets/libs/toastr/build/toastr.min.js"></script>
    <script src="/assets/js/pages/toastr.init.js"></script>
</c:if>
<!-- App js -->
<script src="/assets/js/app.js"></script>

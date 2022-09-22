<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="vertical-menu">

    <div data-simplebar="init" class="h-100">
        <div class="simplebar-wrapper" style="margin: 0px;background: #343a40">
            <div class="simplebar-height-auto-observer-wrapper">
                <div class="simplebar-height-auto-observer"></div>
            </div>
            <div class="simplebar-mask">
                <div class="simplebar-offset" style="right: 0px; bottom: 0px;">
                    <div class="simplebar-content-wrapper" style="height: 100%; overflow: hidden;">
                        <div class="simplebar-content" style="padding: 0px;">

                            <!--- Sidemenu -->
                            <div id="sidebar-menu" class="mm-active" style="">
                                <!-- Left Menu Start -->
                                <ul class="metismenu list-unstyled mm-show" id="side-menu">
                                    <li class="menu-title">Menu</li>

                                    <li class="">
                                        <a href="javascript: void(0);" class="waves-effect" aria-expanded="false">
                                            <i class="bx bx-home-circle"></i>
                                            <span>HomePage</span>
                                        </a>
                                        <ul class="sub-menu mm-collapse" aria-expanded="false">
                                            <li><a href="<c:url value="/products"/>">List Product</a></li>
                                            <li><a href="<c:url value="/users"/>">List User</a></li>
                                            <li></li>
                                        </ul>
                                    </li>

                                    <li class="mm-active">
                                        <a href="javascript: void(0);" class="has-arrow waves-effect"
                                           aria-expanded="true">
                                            <i class="bx bx-layout"></i>
                                            <span>Category</span>
                                            <div class="waves-ripple waves-rippling" data-hold="1663255085441"
                                                 data-x="212" data-y="22.899993896484375" data-scale="scale(7.5)"
                                                 data-translate="translate(0,0)"
                                                 style="top:22.899993896484375px;left:212px;-webkit-transform:scale(7.5) translate(0,0);-moz-transform:scale(7.5) translate(0,0);-ms-transform:scale(7.5) translate(0,0);-o-transform:scale(7.5) translate(0,0);transform:scale(7.5) translate(0,0);opacity:1;-webkit-transition-duration:750ms;-moz-transition-duration:750ms;-o-transition-duration:750ms;transition-duration:750ms;"></div>
                                        </a>
                                        <ul class="sub-menu mm-collapse mm-show" aria-expanded="false" style="">
                                            <%--                                            <c:forEach var="category" items="${applicationScope.categories}">--%>
                                            <%--                                                <li>--%>
                                            <%--                                                    <a href="layouts-horizontal.html" id="${category.getCid()}"><c:out value="${category.getcName()}"/></a>--%>
                                            <%--                                                </li>--%>
                                            <%--                                            </c:forEach>--%>
                                            <li>
                                                <a href="<c:url value="/products?action=category&id=1"/>">Mouse</a>
                                            </li>
                                            <li>
                                                <a href="<c:url value="/products?action=category&id=2"/>">Keyboard</a>
                                            </li>
                                            <li>
                                                <a href="<c:url value="/products?action=category&id=3"/>">Screen</a>
                                            </li>
                                            <li>
                                                <a href="<c:url value="/products?action=category&id=4"/>">Ram</a>
                                            </li>
                                            <li>
                                                <a href="<c:url value="/products?action=category&id=5"/>">Hard Drive</a>
                                            </li>
                                            <li>
                                                <a href="<c:url value="/products?action=category&id=6"/>">Graphics Card</a>
                                            </li>
                                            <li>
                                                <a href="<c:url value="/products"/>">All</a>
                                            </li>

                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <!-- Sidebar -->
                        </div>
                    </div>
                </div>
            </div>
            <div class="simplebar-placeholder" style="width: auto; height: 364px;"></div>
        </div>
        <div class="simplebar-track simplebar-horizontal" style="visibility: hidden;">
            <div class="simplebar-scrollbar" style="transform: translate3d(0px, 0px, 0px); display: none;"></div>
        </div>
        <div class="simplebar-track simplebar-vertical" style="visibility: hidden;">
            <div class="simplebar-scrollbar"
                 style="height: 391px; transform: translate3d(0px, 0px, 0px); display: none;"></div>
        </div>
    </div>
</div>

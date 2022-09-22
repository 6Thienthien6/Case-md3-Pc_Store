<style>

    /*body[data-sidebar=dark] */
    .navbar-brand-box {
        background: #343a40;
    }
</style>
}
<header id="page-topbar">
    <div class="navbar-header">
        <div class="d-flex" style="heights:100%">
            <!-- LOGO -->
            <div class="navbar-brand-box" style="
    height: 70px;
    align-items: center;
    padding-top: 25px;
">
                <a href="index.html" class="logo logo-dark">
                                <span class="logo-sm">
                                    <img src="assets\images\logo.svg" alt="" height="22">
                                </span>
                    <span class="logo-lg">
                                    <img src="assets\images\logo-dark.png" alt="" height="17">
                                </span>
                </a>

                <a href="index.html" class="logo logo-light">
                                <span class="logo-sm">
                                    <img src="assets\images\logo-light.svg" alt="" height="22">
                                </span>
                    <span class="logo-lg">
                                    <img src="assets\images\logo-light.png" alt="" height="19">
                                </span>
                </a>
            </div>


            <!-- App Search-->

        </div>

        <div class="d-flex" style="margin-left: 960px;">
            <div class="dropdown d-inline-block d-lg-none ml-2">
                <button type="button" class="btn header-item noti-icon waves-effect" id="page-header-search-dropdown"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="mdi mdi-magnify"></i>
                </button>
                <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right p-0"
                     aria-labelledby="page-header-search-dropdown">

                    <form class="p-3">
                        <div class="form-group m-0">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search ..."
                                       aria-label="Recipient's username">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="submit"><i class="mdi mdi-magnify"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="dropdown d-inline-block">
                <button type="button" class="btn header-item waves-effect" id="page-header-user-dropdown"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <img class="rounded-circle header-profile-user" src="assets\images\users\avatar-1.jpg"
                         alt="Header Avatar">
                    <span class="d-none d-xl-inline-block ml-1">Henry</span>
                    <i class="mdi mdi-chevron-down d-none d-xl-inline-block"></i>
                </button>
                <div class="dropdown-menu dropdown-menu-right">
                    <!-- item-->
                    <a class="dropdown-item" href="#"><i class="bx bx-user font-size-16 align-middle mr-1"></i> Profile</a>
                    <a class="dropdown-item" href="#"><i class="bx bx-wallet font-size-16 align-middle mr-1"></i> My
                        Wallet</a>
                    <a class="dropdown-item d-block" href="#"><span class="badge badge-success float-right">11</span><i
                            class="bx bx-wrench font-size-16 align-middle mr-1"></i> Settings</a>
                    <a class="dropdown-item" href="#"><i class="bx bx-lock-open font-size-16 align-middle mr-1"></i>
                        Lock screen</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item text-danger" href="#"><i
                            class="bx bx-power-off font-size-16 align-middle mr-1 text-danger"></i> Logout</a>
                </div>
            </div>
        </div>
        <div class="logout">
            <a href="/products?action=logout">
                <i class="fa fa-sign-out" aria-hidden="true" style=" font-size: 20px;"></i>
            </a>
        </div>
    </div>
</header>

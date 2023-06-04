
var admin="";
var teacherDateAjex="";
Checklogin()
  function Checklogin() {
    var ulElement = document.querySelector('.list-unstyled');
ulElement.innerHTML = '';

    $.ajax({
      type: 'GET',
      url: "/administrators/session",
      contentType: 'application/json',
      success:function(responseData) {
        console.log(responseData);
        admin = responseData;
        var role = (admin.permissionId === 1) ? "管理員" : "老師";
        var newSubMenu;
        var newBar;
        newBar=`
        <header class="p-2 mb-2 mr-0 border-bottom">
        <div class="container m-0 pr-0">
          <div
            class="d-flex nowrap align-items-center justify-content-center justify-content-lg-start"
          >
            <div>
              <button
                type="button"
                id="collapse"
                class="collapse-btn text-start"
              >
                <i class="fa-solid fa-bars"></i>
              </button>
            </div>
            <div class="bartitle">
              <span> </span>
            </div>
            <div class="dropdown text-end ms-auto mb-lg-0 me-lg-0 p-0 m-0">
              <a
                href="#"
                class="d-block link-dark text-decoration-none dropdown-toggle"
                id="dropdownUser1"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <span class="barname">${admin.adminName} ${role}</span>
                <img
                  id="barPhoto"
                  src="../img/logobgg.png"
                  alt="mdo"
                  width="32"
                  height="32"
                  class="rounded-circle"
                />
              </a>
              <ul
                class="dropdown-menu text-small dropdown-menu-end"
                aria-labelledby="dropdownUser1"
              >
                <li>
                  <a
                    class="dropdown-item text-center"
                    href="../administrator/backbarsetting.html"
                    >帳號設定</a
                  >
                </li>
                <li>
                  <a
                    id="barprofile"
                    class="dropdown-item text-center"
                    href="../administrator/backbarprofile.html"
                    >個人檔案</a
                  >
                </li>
                <li><hr class="dropdown-divider" /></li>
                <li>
                  <a
                    class="dropdown-item text-center"
                    id="logout"
                    onclick="logout()"
                    href="#"
                    >登出</a
                  >
                </li>
              </ul>
            </div>
          </div>
        </div>
      </header>
        `
        $(".container-right").prepend(newBar);
        if(admin.permissionId==1){
          $("#barprofile").attr("hidden", true);
            newSubMenu = `
            <li>
              <!-- 收和展開href="#sublist" data-bs-toggle="collapse"  -->
              <a href="#sublist1" data-bs-toggle="collapse"
                >老師管理 <i class="fa-solid fa-gears"></i
              ></a>
              <!-- 子連結 -->
              <ul
                id="sublist1"
                class="list-unstyled collapse"
                data-bs-parent="#accordion"
              >
                <li>
                  <a href="../administrator/backuserLIST.html">老師列表</a>
                </li>
                <li>
                  <a href="../administrator/backuserADD.html">新增老師</a>
                </li>
              </ul>
            </li>
            <li>
              <a href="#sublist6" data-bs-toggle="collapse"
                >公告管理<i class="fa-solid fa-bell"></i
              ></a>
              <ul
                id="sublist6"
                class="list-unstyled collapse"
                data-bs-parent="#accordion"
              >
                <li><a href="../announcement/backannLIST.html">公告列表</a></li>
                <li><a href="../announcement/backannADD.html">新增公告</a></li>
              </ul>
            </li>
            <li>
              <a href="#sublist7" data-bs-toggle="collapse"
                >優惠券管理<i class="fa-solid fa-gift"></i
              ></a>
              <ul
                id="sublist7"
                class="list-unstyled collapse"
                data-bs-parent="#accordion"
              >
                <li><a href="../coupon/backcouponLIST.html">優惠券列表</a></li>
                <li><a href="../coupon/backcouponADD.html">新增優惠券</a></li>
              </ul>
            </li>
                                   <li>
              <a href="#sublist5" data-bs-toggle="collapse"
                >訂單管理<i class="fa-solid fa-cart-shopping"></i
              ></a>
              <ul
                id="sublist5"
                class="list-unstyled collapse"
                data-bs-parent="#accordion"
              >
                <li><a href="../administrator/backCourseOrder.html">課程訂單</a></li>
                <li><a href="../administrator/backActiveOrder.html">活動訂單</a></li>
                <li><a href="../administrator/backShopOrder.html">商城訂單</a></li>
              </ul>
            </li>
            <li>
              <a href="#sublist2" data-bs-toggle="collapse"
                >課程管理<i class="fa-solid fa-graduation-cap"></i
              ></a>
              <ul
                id="sublist2"
                class="list-unstyled collapse"
                data-bs-parent="#accordion"
              >
                <li><a href="../courseteacher/BackCourses.html">課程列表</a></li>
                <li><a href="../courseteacher/AddCourses.html">上傳課程</a></li>
              </ul>
            </li>
            <li>
              <a href="#sublist3" data-bs-toggle="collapse"
                >活動管理<i class="fa-solid fa-calendar-days"></i
              ></a>
              <ul
                id="sublist3"
                class="list-unstyled collapse"
                data-bs-parent="#accordion"
              >
                <li><a href="../active/activeBack.html">活動列表</a></li>
                <li><a href="../active/activeBackAdd.html">上傳活動</a></li>
              </ul>
            </li>
            <li>
              <a href="#sublist4" data-bs-toggle="collapse"
                >商城管理<i class="fa-sharp fa-solid fa-shop"></i
              ></a>
              <ul
                id="sublist4"
                class="list-unstyled collapse"
                data-bs-parent="#accordion"
              >
                <li><a href="../baseVer3/commodityLIST.html">商品列表</a></li>
                <li><a href="../baseVer3/backuserLIST.html">新增商品</a></li>
              </ul>
            </li>
                        <li>
              <a href="#">站內信管理 <i class="fa-solid fa-envelope"></i></a>
            </li>

                        <hr class="m-2" style="width: 50%">
<!--             <li>-->
<!--              <a href="backmemberLIST.html"-->
<!--                >會員管理<i class="fa-solid fa-user"></i-->
<!--              ></a>-->
<!--            </li>-->
<!--            <li>-->
<!--              <a href="#">許願管理<i class="fa-solid fa-face-smile"></i></a>-->
<!--            </li>-->
<!--            <li>-->
<!--              <a href="#">論壇管理<i class="fa-solid fa-comments"></i></a>-->
<!--            </li>-->
<!--            <li>-->
<!--              <a href="#">客服信管理<i class="fa-solid fa-paper-plane"></i></a>-->
<!--            </li>-->
          `;
          $(".list-unstyled").append(newSubMenu);
        }else{
            // $("#sublist6").parent("li").hide();
            newSubMenu=`

            <li>
              <a href="#sublist2" data-bs-toggle="collapse"
                >課程管理<i class="fa-solid fa-graduation-cap"></i
              ></a>
              <ul
                id="sublist2"
                class="list-unstyled collapse"
                data-bs-parent="#accordion"
              >
                <li><a href="../courseteacher/BackCourses.html">課程列表</a></li>
                <li><a href="../courseteacher/AddCourses.html">上傳課程</a></li>
              </ul>
            </li>
            <li>
              <a href="#sublist3" data-bs-toggle="collapse"
                >活動管理<i class="fa-solid fa-calendar-days"></i
              ></a>
              <ul
                id="sublist3"
                class="list-unstyled collapse"
                data-bs-parent="#accordion"
              >
                <li><a href="../active/activeBack.html">活動列表</a></li>
                <li><a href="../active/activeBackAdd.html">上傳活動</a></li>
              </ul>
            </li>
            <li>
              <a href="#">站內信管理 <i class="fa-solid fa-envelope"></i></a>
            </li>
            `
            $(".list-unstyled").append(newSubMenu);



            getTeaPhoto()
        }
        var role0 = (admin.permissionId === 1) ? `<span style="color:red;">管理員</span>` : "老師";
        $("#nameid").append(admin.adminName);
        $("#role").html(role0);
      },
      error:function (){
        alert("沒登入");
        window.location.href ="../administrator/backlogin.html";
        return; // 錯誤發生時立即結束函式，不執行其他的JavaScript代碼
      }
    });
  }




  function getTeaPhoto(){
    $.ajax({
      type: 'GET',
      url: '/teachers/'+admin.adminId,
      contentType: 'application/json',
      success: function(data) {
        teacherDateAjex = data;
        var teaPhoto = teacherDateAjex.teaPhoto;
        if (teaPhoto !== null) {
          $("#barPhoto").attr("src", "data:image/png;base64," + teaPhoto);
      }
    
      },
      error: function(jqXHR, textStatus, errorThrown) {
        console.log("error")
      }
    });
  }



  function logout() {
    console.log("想登出");
    $.ajax({
      url: "/administrators/logout", // 修正URL
      type: "GET",
      success: function(response) {
        window.location.href = "../administrator/backlogin.html";
      },
      error: function(xhr, status, error) {
        console.log("登出失敗"); // 添加引号
      }
    });
  }
  


  //回到首頁的方法
  function goToIndexPage() {
    window.location.href = "../administrator/backIndex.html";
  }

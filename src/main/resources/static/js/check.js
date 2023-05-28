
var admin="";
// $(document).ready(function(){
    Checklogin()
// }
  function Checklogin() {
    $.ajax({
      type: 'GET',
      url: "/administrators/session",
      contentType: 'application/json',
      success:function(responseData) {
        console.log(responseData);
        admin = responseData;
        var role;
        var newSubMenu;
        if(admin.permissionId==1){
            role="管理員"
            newSubMenu = `
            <li>
              <!-- 收和展開href="#sublist" data-bs-toggle="collapse"  -->
              <a href="#sublist1" data-bs-toggle="collapse"
                >使用者管理 <i class="fa-solid fa-gears"></i
              ></a>
              <!-- 子連結 -->
              <ul
                id="sublist1"
                class="list-unstyled collapse"
                data-bs-parent="#accordion"
              >
                <li>
                  <a href="../administrator/backuserLIST.html">使用者列表</a>
                </li>
                <li>
                  <a href="../administrator/backuserADD.html">新增使用者</a>
                </li>
              </ul>
            </li>
            <li>
              <a href="backmemberLIST.html"
                >會員管理<i class="fa-solid fa-user"></i
              ></a>
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
                <li><a href="#">課程列表</a></li>
                <li><a href="#">上傳課程</a></li>
                <li><a href="#">審核課程</a></li>
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
                <li><a href="#">活動列表</a></li>
                <li><a href="#">上傳活動</a></li>
                <li><a href="#">審核活動</a></li>
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
                <li><a href="#">商品列表</a></li>
                <li><a href="#">新增商品</a></li>
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
                <li><a href="#">課程訂單</a></li>
                <li><a href="#">活動訂單</a></li>
                <li><a href="#">商城訂單</a></li>
              </ul>
            </li>
            <li>
              <a href="#">許願管理<i class="fa-solid fa-face-smile"></i></a>
            </li>
            <li>
              <a href="#">論壇管理<i class="fa-solid fa-comments"></i></a>
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
                >優惠碼管理<i class="fa-solid fa-gift"></i
              ></a>
              <ul
                id="sublist7"
                class="list-unstyled collapse"
                data-bs-parent="#accordion"
              >
                <li><a href="../coupon/backcouponLIST.html">優惠碼列表</a></li>
                <li><a href="../coupon/backcouponADD.html">新增優惠碼</a></li>
              </ul>
            </li>
            <li>
              <a href="#">站內信管理 <i class="fa-solid fa-envelope"></i></a>
            </li>
            <li>
              <a href="#">客服信管理<i class="fa-solid fa-paper-plane"></i></a>
            </li>
          `;
          $(".list-unstyled").append(newSubMenu);
        }else{
            role="老師"
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
                <li><a href="#">課程列表</a></li>
                <li><a href="#">上傳課程</a></li>
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
                <li><a href="#">活動列表</a></li>
                <li><a href="#">上傳活動</a></li>
              </ul>
            </li>
            <li>
              <a href="#">站內信管理 <i class="fa-solid fa-envelope"></i></a>
            </li>
            `
            $(".list-unstyled").append(newSubMenu);
        }
        var name=admin.adminName+" "+role;
        $(".barname").text(name);
      },
      error:function (){
        alert("沒登入");
        window.location.href ="../administrator/backlogin.html";
        return; // 錯誤發生時立即結束函式，不執行其他的JavaScript代碼
      }
    });
  }
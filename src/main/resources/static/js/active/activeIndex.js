const urlParams = new URLSearchParams(window.location.search);
const searchText = urlParams.get("searchText");

const app = Vue.createApp({
  data() {
    return {
      activityIndexList: [],
    };
  },
  mounted() {
    axiosGetActive(searchText)
      .then((data) => {
        // console.log("mounted:" + data);
        this.activityIndexList = data;
      })
      .catch((error) => {
        console.error(error);
      });
    // 呼叫預先執行的函式
  },
  methods: {
    // 推薦課程 時間轉換 簡單格式 Vue方法裡 開始
    convertDateTimeEasy(originalDateTime) {
      var originalDate = new Date(originalDateTime);

      var year = originalDate.getFullYear() - 1911; // 西元年轉換為民國年
      var month = originalDate.getMonth() + 1; // 注意月份要加 1
      var day = originalDate.getDate();

      return (
        year +
        "/" +
        (month < 10 ? "0" + month : month) +
        "/" +
        (day < 10 ? "0" + day : day)
      );
    },
    // 跳轉頁面
    navigateToPage(parameter) {
      // 使用參數執行相應的操作
      //   window.location.href = "/active/active.html?activityId=" + parameter;
      window.open("/active/active.html?activityId=" + parameter);
      console.log("參數值:", parameter);
      // 其他相關的頁面導航操作
    },
    // 推薦課程 時間轉換 簡單格式 Vue方法裡 結束
  },
});

window.addEventListener("load", () => {
  app.mount("#app");
});

// 取得所有活動
function axiosGetActive() {
  return axios
    .get("/activeIndex", {
      params: {
        searchKeyword: searchText,
      },
    })
    .then((response) => {
      //   console.log(response.data);
      return response.data;
    })
    .catch((error) => {
      console.error(error);
    });
}

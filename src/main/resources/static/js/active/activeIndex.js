const urlParams = new URLSearchParams(window.location.search);
const searchText = urlParams.get("searchText");

const app = Vue.createApp({
  data() {
    return {
      activityIndexList: [],
      currentPage: 1,
      totalPages: 0,
      pageSize: 10, // 每页显示的记录数
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

    this.updateTotalPages();
    // 呼叫預先執行的函式
  },
  computed: {
    // 分頁 開始
    currentPageItems() {
      // 计算当前页显示的数据
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = startIndex + this.pageSize;
      return this.activityIndexList.slice(startIndex, endIndex);
    },
    // 分頁 結束
  },
  methods: {
    // 分頁 Vue方法裡 開始
    changePage(page) {
      this.currentPage = page;
    },
    updateTotalPages() {
      // 更新总页数
      this.totalPages = Math.ceil(
        this.activityIndexList.length / this.pageSize
      );
    },
    // 分頁 Vue方法裡 結束
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
    // 標籤查詢 Vue方法裡 開始
    searchByType() {
      axiosGetActive(searchText, this.activityIndexType)
        .then((data) => {
          // console.log("mounted:" + data);
          this.activityIndexList = data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 標籤查詢 Vue方法裡 結束
    // 清除搜索 Vue方法裡 開始
    clearSearch() {
      window.location.href = "/active/activeIndex.html";
    },
    // 清除搜索 Vue方法裡 結束
  },
  watch: {
    activityIndexList() {
      // 监听 items 数组的变化，更新总页数
      this.updateTotalPages();
    },
  },
});

window.addEventListener("load", () => {
  app.mount("#app");
});

// 取得所有活動
function axiosGetActive(searchText, searchType) {
  return axios
    .get("/activeIndex", {
      params: {
        searchKeyword: searchText,
        activityType: searchType,
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

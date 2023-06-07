const urlParams = new URLSearchParams(window.location.search);
const mailId = urlParams.get("mailId");

const app = Vue.createApp({
  data() {
    return {
      mailId: "",
      sender: "",
      mailCreateDate: "",
      mailContent: "",
      mailTitle: "",
      senderName:""
    };
  },
  mounted() {
    axiosGetUserMailOne().then((data) => {
      this.mailId = data.mailId;
      this.sender = data.sender;
      this.mailCreateDate = data.mailCreateDate;
      this.mailContent = data.mailContent;
      this.mailTitle = data.mailTitle;
      this.senderName=data.senderName;
    });
  },
  methods: {},
});

window.addEventListener("load", () => {
  app.mount("#app");
});

function axiosGetUserMailOne() {
  return axios
    .get("/inbox/inboxGetById", { params: { mailId: mailId } })
    .then((res) => {
      console.log(res.data);
      return res.data;
    })
    .catch((err) => {
      console.log(err.response.data);
    });
}

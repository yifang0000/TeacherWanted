const app = Vue.createApp({
  data() {
    return {
      mailList: [],
    };
  },
  mounted() {
    axiosGetUserMail().then((data) => {
      this.mailList = data;
    });
  },
  methods: {
    mailDelete(mailId) {
      axios
        .delete("/inbox/deleteMail", {
          params: {
            mailId: mailId,
          },
        })
        .then((res) => {
          location.reload();
        });
    },
  },
});

window.addEventListener("load", () => {
  app.mount("#app");
});

function axiosGetUserMail() {
  return axios
    .get("/inbox/getUserMails")
    .then((res) => {
      console.log(res.data);
      return res.data;
    })
    .catch((err) => {
      console.log(err.response.data);
    });
}

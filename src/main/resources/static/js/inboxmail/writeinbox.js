const app = Vue.createApp({
  data() {
    return {
      receiver: "",
      mailTitle: "",
      mailContent: "",
    };
  },
  mounted() {},
  methods: {
    submitMail() {
      console.log(this.mailContent);
      let data = {
        receiver: this.receiver,
        mailTitle: this.mailTitle,
        mailContent: this.mailContent,
      };
      axios
        .post("/inbox/inboxCreate", data)
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err.response.data);
        });location.reload();
    },
  },
});

window.addEventListener("load", () => {
  app.mount("#app");
});

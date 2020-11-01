console.log("============ ajax ============");
$.ajax({
    url: 'login?userName=jay',
    type: 'POST',
    crossDomain: true,
    xhrFields: {
        withCredentials: true
    },
    success: function (result) {
        console.log(result)
    },
    error: function (jqXHR) {
        alert("发生错误：" + jqXHR.status);
    }
});
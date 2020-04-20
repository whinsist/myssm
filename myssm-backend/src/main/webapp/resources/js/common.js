function frontLogout() {
    $.ajax({
        url: "/dec/logout.shtml",
        type: 'GET',
        cache: false,
        contentType: 'application/json; charset=UTF-8',
        dataType: "json",
        success: function (data) {
            var result = data.result;
            if (result == "0000") {
                location.reload();
            } else {
                layer.alert(message, {title: '提示'});
            }
        },
        error: function (result) {
            layer.alert('请检测你的网络环境是否正常', {title: '提示'});
        }
    });
}

function frontLogin() {
    var userName = $("#userName").val();
    var passWord = $("#passWord").val();
    var content = {"userName": userName, "passWord": passWord};
    $.ajax({
        url: "/dec/login.shtml",
        type: 'POST',
        cache: false,
        contentType: 'application/json; charset=UTF-8',
        dataType: "json",
        data: JSON.stringify(content),
        success: function (data) {
            var result = data.result;
            var message = data.message;
            if (result == "0000") {
                location.href = "/dec/statistic/list.shtml";
            } else {
                layer.alert(message, {title: '提示'});
            }
        },
        error: function (result) {
            layer.alert('请检测你的网络环境是否正常', {title: '提示'});
        }
    });
}

function getSyncCurrentUser() {
    var userInfo;
    $.ajax({
        url: $("#baseUrl").val() + "/v1/common/getCurrentUser.do",
        type: 'GET',
        async: false,
        contentType: 'application/json; charset=UTF-8',
        dataType: "json",
        data: {},
        success: function (data) {
            userInfo = data.data;
        },
        error: function (result) {
            alert("error");
        }
    });
    return userInfo;
}

function setCurrentUserInfo() {
    $.ajax({
        //url: $("#baseUrl").val() + "/v1/common/current_user",
        url: $("#baseUrl").val() + "/v1/back/users/token",
        type: 'GET',
        contentType: 'application/json; charset=UTF-8',
        dataType: "json",
        data: {},
        success: function (data) {
            var userInfo = data.data;
            var headImage = $("#baseUrl").val() + "/v1/attach/down?relativeUrl="
                    + userInfo.headImage;
            $('#avatar').attr("src", headImage);
            $('#currentUserHeadImage').attr("src", headImage);
            $('#currentUserId').val(userInfo.userSid);
            $('#userName').html(userInfo.account);
            $('#currentUserName').html(userInfo.account);
            $('#currentOrgName').html(userInfo.currentOrg.orgName);
        },
        error: function (result) {
            alert("error");
        }
    });
}
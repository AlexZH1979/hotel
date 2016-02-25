function loadTableAjax(path, e_id, funcFill, begin, countSize) {
    var json = {
        firstResult: begin,
        selectCount: countSize
    };

    $.ajax({
        url: path,
        data: JSON.stringify(json),
        type: "POST",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (e_obj) {
            funcFill(e_id, e_obj);
        },
        error: function () {
            alert('failure');
        }
    });
}

//search hotels fot table by parameter
function loadTableByParamAjax(path, e_id, funcFill, parameter, value, begin, count) {

    var json = {
        parameter:parameter,
        value: value,
        begin:begin,
        count:count
    };

    $.ajax({
        url: path,
        data: JSON.stringify(json),
        type: "POST",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (e_obj) {
            funcFill(e_id, e_obj);
        },
        error: function () {
            console.log("error")
        }

    });
}
function loadSizeList(path, e_id, funcSize){
    $.ajax({
        url: path,
        type: "POST",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (size) {
            $(e_id).html("");
            $(e_id).append("<p>"+size+"<p>")
        },
        error: function () {
            alert('failure');
        }
    });
}

function searchByParam(path, parameter, value, e_id, f_success, button) {
    var json = {
        parameter: parameter,
        value: value
    };
    $.ajax({
        url: path,
        type: "POST",
            data: JSON.stringify(json),
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (find) {
                console.log("find " + find + " by parameter " + parameter);
                if (find > 0) {
                    f_success(e_id, parameter, value, find);
                };
                console.log("$.active="+$.active);
                if($.active==1){
                    $(button).button('reset');
                    //$(button).removeAttr('disabled');
                }
            }

        }
    );
}
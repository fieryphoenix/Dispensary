function post(path, params, method, formId) {
    method = method || "post";
    var form = null;
    if (formId) {
        form = document.getElementById(formId);
    } else {
        form = document.createElement("form");
    }
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for (var key in params) {
        if (params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
        }
    }

    document.body.appendChild(form);
    form.submit();
}

function disableReadOnlyFields() {
    var condition = $('#readOnlyForm').val();
    if (condition !== "" && condition === 'true') {
        $('input,select,textarea').prop("disabled", "true");
    }
}

window.onload = function () {
    disableReadOnlyFields();
};

/**
 * Created by user on 5/26/2017.
 */

const userAjaxUrl = "admin/users/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: userAjaxUrl
};

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "email"
                },
                {
                    "data": "roles"
                },
                {
                    "data": "enabled"
                },
                {
                    "data": "registered"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );

    $(".enabled").click(function () {
        setEnable($(this).closest('tr').attr("id"), $(this).is(":checked"));
    });
});

function setEnable(id, enabled) {
    $.ajax({
        type: "PATCH",
        url: ctx.ajaxUrl + id + "/" + enabled,
    }).done(function () {
        $("[data-enabledUser][id='" +id+ "']").attr("data-enabledUser", enabled)
        if (enabled) {
            successNoty("User state: enabled.");
        } else {
            successNoty("User state: disabled.");
        }
    });
}
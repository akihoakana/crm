$(document).ready(function() {
    $(".btn-delete").click(function () {
        var id=$(this).attr("roleid")
        var This=$(this)
        $.ajax({
            method: "post",
            url: "http://localhost:8080/crm/api/role-delete?id="+id,

        })
            .done(function( data ) {
                if (data.success) {
                    alert("Xoá thành công")
                    This.closest("tr").remove()
                }
                else
                    alert("Xoá thất bại")


            })
    })
})
$(document).ready(function() {
    $(".btn-update").click(function () {
        var id=$(this).attr("roleid")
        var This=$(this)
        $(".btn-send").click(function (e) {
            e.preventDefault()
            var name=$("#form3").val()
            var description=$("#form4").val()
            if (name =="" || description ==""){
                alert("Name must be filled out");
                return false;
            }
            $.ajax({
                method: "post",
                url: "http://localhost:8080/crm/api/role-update?name="+name+"&description="+description+"&id="+id,
            })
                .done(function( data ) {
                    if (data.success) {
                        This.closest("#row").find(".name").text(name)
                        This.closest("#row").find(".description").text(description)
                    }
                    else
                        alert("Update thất bại")
                })
            $('.btn-modal').modal('toggle')
        })
    })
})
$(document).ready(function() {
    $(".btn-delete").click(function () {
        var id=$(this).attr("usersid")
        var This=$(this)
        $.ajax({
            method: "post",
            url: "http://localhost:8080/crm/api/user-delete?id="+id,

        })
            .done(function( data ) {
                if (data.success) {
                    This.closest("tr").remove()
                }
                else
                    alert("Xoá user thất bại")


            })
    })
})
$(document).ready(function() {
    $(".btn-update").click(function () {
        var id=$(this).attr("usersid")
        var This=$(this)
        $(".btn-send").click(function (e) {
            e.preventDefault()
            var firstname=$("#form1").val()
            var lastname=$("#form2").val()
            var username=$("#form3").val()
            var role_id=$("#form4").find("option:selected").val()
            var rolename=$("#form4").find("option:selected").text()
            if (firstname =="" || lastname =="" || username =="" || role_id ==""){
                alert("Name must be filled out");
                return false;
            }
            $.ajax({
                method: "post",
                url: "http://localhost:8080/crm/api/user-update?firstname="+firstname+"&lastname="+lastname+"&username="
                    +username+"&role_id="+role_id+"&id="+id,
            })
                .done(function( data ) {
                    if (data.success) {
                        This.closest("#row").find(".firstname").text(firstname)
                        This.closest("#row").find(".lastname").text(lastname)
                        This.closest("#row").find(".username").text("@"+username)
                        This.closest("#row").find(".rolename").text(rolename)
                    }
                    else
                        alert("Update user thất bại")
                })
            $('.btn-modal').modal('toggle')
        })
    })
})

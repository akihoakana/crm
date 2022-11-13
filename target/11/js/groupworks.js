$(document).ready(function() {
    $(".btn-delete").click(function () {
        var id=$(this).attr("jobsid")
        var This=$(this)
        $.ajax({
            method: "post",
            url: "http://localhost:8080/crm/api/groupwork-delete?id="+id,

        })
            .done(function( data ) {
                if (data.success) {
                    This.closest("tr").remove()
                }
                else
                    alert("Xoá dự án thất bại")


            })
    })
})
$(document).ready(function() {
    $(".btn-update").click(function () {
        var id=$(this).attr("jobsid")
        var This=$(this)
        $(".btn-send").click(function (e) {
            e.preventDefault()
            var name=$("#form1").val()
            var start_date=$("#form2").val()
            var end_date=$("#form3").val()

            if (name =="" || start_date =="" || end_date ==""){
                alert("Name must be filled out");
                return false;
            }
            $.ajax({
                method: "post",
                url: "http://localhost:8080/crm/api/groupwork-update?name="+name+"&start_date="+start_date+"&end_date="+end_date+"&id="+id,
            })
                .done(function( data ) {
                    if (data.success) {
                        This.closest("#row").find(".name").text(name)
                        This.closest("#row").find(".startdate").text(start_date)
                        This.closest("#row").find(".enddate").text(end_date)

                    }
                    else
                        alert("Update dự án thất bại")
                })
            $('.btn-modal').modal('toggle')
        })
    })
})
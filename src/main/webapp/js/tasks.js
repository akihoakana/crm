$(document).ready(function() {
    $(".btn-delete").click(function () {
        var id=$(this).attr("tasksid")
        var This=$(this)
        $.ajax({
            method: "post",
            url: "http://localhost:8080/crm/api/task-delete?id="+id,

        })
            .done(function( data ) {
                if (data.success) {
                    This.closest("tr").remove()
                }
                else
                    alert("Xoá task thất bại")


            })
    })
})
$(document).ready(function() {
    $(".btn-update").click(function () {
        var id=$(this).attr("tasksid")
        var This=$(this)
        $(".btn-send").click(function (e) {
                e.preventDefault()
                var name=$("#form1").val()
                var start_date=$("#form2").val()
                var end_date=$("#form3").val()
                var usersfullname=$("#form4").find("option:selected").text()
                var jobsname=$("#form5").find("option:selected").text()
                var statusname=$("#form6").find("option:selected").text()
                var User_id=$("#form4").find("option:selected").val()
                var Job_id=$("#form5").find("option:selected").val()
                var Status_id=$("#form6").find("option:selected").val()
            if (name =="" || start_date =="" || end_date =="" || User_id =="" || Job_id =="" || Status_id ==""){
                alert("Name must be filled out");
                return false;
            }
            $.ajax({
                method: "post",
                url: "http://localhost:8080/crm/api/task-update?name="+name+"&start_date="+start_date+"&end_date="
                    +end_date+"&user_id="+User_id+"&job_id="+Job_id+"&status_id="+Status_id+"&id="+id,
            })
                .done(function( data ) {
                    if (data.success) {
                        This.closest("#row").find(".name").text(name)
                        This.closest("#row").find(".startdate").text(start_date)
                        This.closest("#row").find(".enddate").text(end_date)
                        This.closest("#row").find(".jobsname").text(jobsname)
                        This.closest("#row").find(".usersfullname").text(usersfullname)
                        This.closest("#row").find(".statusname").text(statusname)
                    }
                    else
                        alert("Update task thất bại")
                })
            $('.btn-modal').modal('toggle')
        })
    })
})

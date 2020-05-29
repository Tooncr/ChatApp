$(document).ready(function(){
    $("#user-signup").submit(function(){
        var form = $(this),
            formData = form.serialize(),
            url = "RegisterServlet",
            method = 'POST',
            responsemsg = $('#signupresponse');

    console.log(formData);
        $.ajax({
            url: url,
            type: method,
            data: formData,
            success:function(data){
                var responseData = JSON.parse(data);
                document.getElementById("signupresponse").innerHTML = responseData.message;

            }
        })

        return false;
    })
})
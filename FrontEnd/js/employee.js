getAllEmployees();
function saveEmployee(){
    let name=$('#exampleFormControlInput2').val();
    let address=$('#exampleFormControlInput3').val();
    let mobile=$('#exampleFormControlInput4').val();
    

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/employee/saveEmployee",
        async:true,
        data:JSON.stringify({
            "id":3,
            "name": name,
            "address": address,
            "mobile": mobile
        }),
        success: function(data){
            alert("Saved")
            getAllEmployees();
        },
        error: function(xhr, exception){
            alert("Error")
        }
    })
}

function updateEmployee(){
    let id=$('#exampleFormControlInput1').val();
    let name=$('#exampleFormControlInput2').val();
    let address=$('#exampleFormControlInput3').val();
    let mobile=$('#exampleFormControlInput4').val();

    $.ajax({
        method:"PUT",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/employee/updateEmployee",
        async:true,
        data:JSON.stringify({
            "id":id,
            "name": name,
            "address": address,
            "mobile": mobile
        }),
        success: function(data){
            alert("Updated")
            getAllEmployees();
        },
        error: function(xhr, exception){
            alert("Error")
        }
    })
}

function deleteEmployee(){
    let id=$('#exampleFormControlInput1').val();
    $.ajax({
        method:"DELETE",
        url:"http://localhost:8080/api/v1/employee/deleteEmployee/"+id,
        async:true,
        success: function(data){
            alert("Deleted")
            getAllEmployees();
        },
        error: function(xhr, exception){
            alert("Error")
        }
    })
}

function getAllEmployees(){

    $.ajax({
        method:"GET",
        url:"http://localhost:8080/api/v1/employee/getAllEmployee",
        async:true,
        success: function(data){
            if (data.code=="00"){
                $('#empTable').empty();
                for(let emp of data.content){
                    let id=emp.id;
                    let name=emp.name;
                    let address=emp.address;
                    let mobile=emp.mobile;
                    
                    var row= `<tr><td>${id}</td><td>${name}</td><td>${address}</td><td>${mobile}</td></tr>`
                    $('#empTable').append(row);
               }
            } 
        },
        error: function(xhr, exception){
            alert("Error")
        }
    })
}

$(document).ready(function(){
    $(document).on('click','#empTable tr', function(){
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();

        $('#exampleFormControlInput1').val(col0);
        $('#exampleFormControlInput2').val(col1);
        $('#exampleFormControlInput3').val(col2);
        $('#exampleFormControlInput4').val(col3);

    })
})

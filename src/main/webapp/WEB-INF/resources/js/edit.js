
var btn = document.getElementById("editBook");
btn.addEventListener("click",()=>{
	//get entered details
	var code = document.getElementById("bookCode").innerHTML;
	var name = document.getElementById("bookName").value;
	var authorId = document.getElementById("bookAuthor").value;
	var date = document.getElementById("dateAdded").innerHTML;
	var obj ={
			"bookName": name,
    	    "bookCode": code,
    	    "author":authorId,
    	    "dateAdded": date
		}
	
	$.ajax({
        type: "PUT",
        url: "http://localhost:9090/book",
        contentType: "application/json",
        data: JSON.stringify(obj),
        success: function (response) {
            alert("Book Updated Successfully!");
        },
        error: function(response){
        	alert("Something Went Wrong!");
        }
    });
	
})
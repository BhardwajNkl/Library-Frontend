function setDate(){
	var options = {
			weekday : 'long',
			year : 'numeric',
			month : 'long',
			day : 'numeric'
		};
		var today = new Date();
		var currentDate = today.toLocaleDateString("en-US", options);
		var element = document.getElementById("dateAdded");
		element.innerHTML = currentDate;
}

setDate();

var btn = document.getElementById("addNewBook");
btn.addEventListener("click",()=>{
	//get entered details
	var code = document.getElementById("bookCode").value;
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
        type: "POST",
        url: "http://localhost:9090/book",
        contentType: "application/json",
        data: JSON.stringify(obj),
        success: function (response) {
        	if(response==""){
        		alert("Failed! Book With Same Code Exists!");
        	}
        	else{
        		alert("Book Added Successfully!");
        	}
        },
        error: function(response){
        	alert("Something Went Wrong!");
        }
    });
	
})
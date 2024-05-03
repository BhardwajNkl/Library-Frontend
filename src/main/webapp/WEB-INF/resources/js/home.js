var btns = document.getElementsByClassName("deleteBook");
for(var i=0;i<btns.length;i++){
	btns[i].addEventListener("click",(event)=>{
		var a = event.target.id;
		deleteBook(a);
	})
}

function deleteBook(id){
	$.ajax({
    type: "DELETE",
    url: "http://localhost:9090/book/"+id,
    success: function (response) {
        alert("Book Deleted Successfully!");
        location.reload();
    },
    error: function(response){
    	alert("Something Went Wrong!");
    }
});
}
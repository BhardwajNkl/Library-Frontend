<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bhardwaj's Library</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
  	<style>
  		body{
  		overflow-x:hidden;
  		}
  	</style>
  </head>
  <body>   
    <div class="fluid-container header">
    <h2 style="text-align:center;">Login To Bhardwaj's Library</h2>
    </div>
    <div class="fluid-container content" style="margin-top:5%;">
    	<div class="row">
    		<div class="col-3 left-side-bar">
    		<!-- left side bar content -->
    		</div>
    		<div class="col-6 center-content bg-info bg-opacity-10">
	    		<div class="fluid-container form-container">
	    		<!-- THIS FORM WILL BE SUBMITTED USING AJAX CALL -->
		    		<form id="login-form" action="login" method="post">
		    		
		    			<div class="row form-header border border-primary mb-2">
		    		<div class="col-12">Login</div>
		    		</div>
		    		
		    		<div class="row form-fields">
		    		
		    	
		    		 <div class="fluid-container">
		    		 <div class="row mb-2">
		    		 <div class="col-4">Username</div>
		    		 <div class="col-5"><input type="text" name="username" required></div>
		    		 </div>
		    		  <div class="row mb-2">
		    		 <div class="col-4">Password</div>
		    		 <div class="col-5"><input type="text" name="password" required></div>
		    		 </div>
		    		 <div class="row">
		    		 <div class="col-4"></div>
		    		 <div class="col-5"><a href="#">Forgotten your password?</a></div>
		    		 </div>
		    		 </div>
		    		 
		    		 
		    		</div>
		    		
		    		<div class="row form-submit border border-primary">
		    		<div class="col-12" style="text-align:right; padding-right:0px;">
		    		<input type="submit" name="login-btn" value="Login>>">
		    		</div>
		    		</div>
		    		</form>
	    		</div>
    		</div>
    		<div class="col-3 right-side-bar">
    		<!-- right side bar content -->
    		</div>
    	</div>
    </div>
    <div class="fluid-container footer">
    <!-- footer content -->
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
  </body>
</html>
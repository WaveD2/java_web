<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<link rel="stylesheet"
	href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="main">
		<form method="POST" action="formUser" class="register-form"
			id="login-form">
			<div class="form-group">
				<label for="username"><i
					class="zmdi zmdi-account material-icons-name"></i></label> <input
					type="text" name="username" id="username" placeholder="Username"
					value="${username}" />
			</div>
			<div class="form-group">
				<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
					type="text" name="upassword" id="password" value="${upassword}"
					placeholder="Password" />
			</div>

			<div class="form-group">
				<label for="username"><i
					class="zmdi zmdi-account material-icons-name"></i></label> <input
					type="email" name="email" id="email" placeholder="Email"
					value="${email}" />
			</div>
			<div class="form-group">
				<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
				value="${phone}"	type="text" name="phone" id="phone" placeholder="Phone"
					 />
			</div>

			<div class="form-group form-button">
				<input type="submit" name="signin" id="signin" class="form-submit"
					value="Change" />
			</div>


		</form>

		<button class="btn form-button " id="delete">Delete</button>

	</div>
</body>
<script type="text/javascript">
  
  document.getElementById("delete").addEventListener("click", function() {
      var confirmation = confirm("Are you sure you want to delete this user?");
      if (confirmation) {
          var email = document.getElementById("email").value; 
          fetch('formUser?email=' + email, {
              method: 'DELETE'
          })
          .then(response => {
              if (response.ok) {
                  window.location.href = "login.jsp"; 
              } else {
                  console.error('Failed to delete user');
              }
          })
          .catch(error => {
              console.error('Error:', error);
          });
      }
  });
  </script>

</html>
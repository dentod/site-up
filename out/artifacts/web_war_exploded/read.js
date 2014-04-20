function read()
{
	var name = 'admin';
	var password = '1223192';
	var name1 = document.getElementById("login-1").value;
	var password1 = document.getElementById("password-1").value;
	if(name1 == name && password1==password)
		window.open("users.html");
}
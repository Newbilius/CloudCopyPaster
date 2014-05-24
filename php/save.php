<?php
include_once("db.php");
if (isset($_POST['login']) && isset($_POST['pass']) && isset($_POST['id'])
&& ($_POST['login']!="")
&& ($_POST['pass']!="")
&& ($_POST['id']!="")
){
	$result = IsUserExists($_POST['login'],$_POST['pass']);
	$result_exists=GetUserByLogin($_POST['login']);

	if (!$result){
		if (!$result_exists){
			CreateUser($_POST['login'],$_POST['pass']);
			echo "OK NEWUSER";
		}else{
			echo "ERROR USER";
		}
	}else{
		if (strlen($_POST['id'])<256){
			UpdateUserKey($_POST['login'],$_POST['id']);
			echo "OK";
		}else{
			echo "ERROR";
		}
	}
}else{
	echo "ERROR";
}
?>
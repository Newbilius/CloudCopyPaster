<?php
include_once("db.php");
if (isset($_POST['login']) && isset($_POST['pass'])
&& ($_POST['login']!="")
&& ($_POST['pass']!="")
){
	$result = IsUserExists($_POST['login'],$_POST['pass']);

	if (!$result){
		echo "WRONGLOGIN";
		WriteToLog($_POST['login']." ERROR USER");
	}else{
	echo "OK";
		WriteToLog($_POST['login']." OK");
	}
}else{
	echo "WRONGLOGIN";
}
?>
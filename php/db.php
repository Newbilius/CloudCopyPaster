<?php
header('Content-Type: text/html; charset=utf-8');
class MyDB extends SQLite3
{
    function __construct()
    {
        $this->open('data.db');
    }
}

$db = new MyDB();
$db->exec('CREATE TABLE if NOT EXISTS users (userLogin STRING,userPass STRING,userKey STRING)');

function GetHash($pass){
	return hash("sha256", $pass);
}

function GetUser($login,$pass){
	global $db;
	$getLogin = $db->prepare("SELECT * FROM users WHERE userLogin=:login AND userPass=:pass");
	$getLogin->bindValue(':login', $login, SQLITE3_TEXT);
	$getLogin->bindValue(':pass', GetHash($pass), SQLITE3_TEXT);
	$tmp=$getLogin->execute();	
	$result=$tmp->fetchArray(SQLITE3_ASSOC);
	return $result;
}

function GetUserByLogin($login){
	global $db;
	$getLogin = $db->prepare("SELECT * FROM users WHERE userLogin=:login");
	$getLogin->bindValue(':login', $login, SQLITE3_TEXT);
	$tmp=$getLogin->execute();	
	$result=$tmp->fetchArray(SQLITE3_ASSOC);
	if ($result===FALSE){
		return NULL;
	}
	return $result;
}

function GetUserKey($login,$pass){
	$result=GetUser($login,$pass);
	if ($result===FALSE){
		return NULL;
	}else{
		return $result['userKey'];
	}
}

function IsUserExists($login,$pass){
	$result=GetUser($login,$pass);
	if ($result===FALSE){
		return FALSE;
	}else{
		return TRUE;
	}
}

function CreateUser($login,$pass){
	global $db;
	$insertUser = $db->prepare("INSERT INTO users (userLogin,userPass,userKey) VALUES (:login,:pass,'')");
	$insertUser->bindValue(':login', $login, SQLITE3_TEXT);
	$insertUser->bindValue(':pass', GetHash($pass), SQLITE3_TEXT);
	$tmp=$insertUser->execute();	
}

function UpdateUserPass($login,$pass){
	global $db;
	$insertUser = $db->prepare("UPDATE users SET userPass=:pass WHERE userLogin=:login");
	$insertUser->bindValue(':login', $login, SQLITE3_TEXT);
	$insertUser->bindValue(':pass', GetHash($pass), SQLITE3_TEXT);
	$tmp=$insertUser->execute();
}

function UpdateUserKey($login,$key){
	global $db;
	$getLogin = $db->prepare("UPDATE users SET userKey=:key WHERE userLogin=:login");
	$getLogin->bindValue(':login', $login, SQLITE3_TEXT);
	$getLogin->bindValue(':key', $key, SQLITE3_TEXT);
	$tmp=$getLogin->execute();
}

function WriteToLog($text){
	//file_put_contents("log.txt",date("d.m.Y H:i:s")." ".filter_var($text, FILTER_SANITIZE_STRING)."\r\n",FILE_APPEND);
}

function ErrorBox($text){?><div class="alert alert-error">
    <?=$text?>
    </div><?}?><?function SuccesBox($text){
?>
    <div class="alert alert-success">
    <?=$text?>
    </div>
<?}?>
<?php
include("config.php");
include("db.php");

if (IsUserExists($_POST['login'],$_POST['pass'])){
	$result = GetUserKey($_POST['login'],$_POST['pass']);
	if ($result==null OR $result ==""){
		echo "Телефон не подключен к сайту.";
		WriteToLog($_POST['login']." Телефон не подключен к сайту.");
		die();
	}

	if (strlen($_POST['message'])>4000){
		echo "TOLONG";
		WriteToLog($_POST['login']." Слишком длинное сообшение.");
		die();
	}
    $url = 'https://android.googleapis.com/gcm/send';   
		$message = array("text" => $_POST['message']);
        $fields = array(
             'registration_ids' => array($result),
             'data' => $message,
         ); 
         $headers = array(
             'Authorization: key='.$googleKey,
             'Content-Type: application/json'
         );

         $ch = curl_init();
         curl_setopt($ch, CURLOPT_URL, $url);
         curl_setopt($ch, CURLOPT_POST, true);
         curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
         curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
         curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
  
         curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
  
         $result = curl_exec($ch);
         if ($result === FALSE) {
             die('Curl упал: ' . curl_error($ch));
         }
         curl_close($ch);
		 $resultArray=json_decode($result,true);
		 
		 if ($resultArray['success']==1){
			//echo "Текст успешно отправлен!";
			echo "OK!";
			WriteToLog($_POST['login']." Текст успешно отправлен!");
		 }else{
			WriteToLog($_POST['login']." ".$resultArray['results'][0]['error']);
			echo "Error: ".$resultArray['results'][0]['error'];
		 }
         //echo "<pre>".print_r($resultArray,true)."</pre>";
	
}else{
	echo "WRONGLOGIN";
}
?>
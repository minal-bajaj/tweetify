<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tweetify</title>
<style>
input[type=text], select, textarea {
    width: 100%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    margin-top: 6px;
    margin-bottom: 16px;
    resize: vertical;
}

input[type=submit] {
    background-color: #4CAF50;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #45a049;
}

.container {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
}
</style>
</head>
<body>
<div class="container">
  <form action="Result.jsp" method="POST">

    <label for="twitterId">Twitter Id</label>
    <input type="text" id="twitterId" name="twitterId" placeholder="Twitter Id..." required>

    <label for="tweetsToA">Tweets to Analyze</label>
    <input type="text" id="tweetsToA" name="tweetsToA" placeholder="Number of Tweets to analyze.." required>

    <label for="aggregationBy">Aggregation By</label>
    <select name="aggregationBy">
  			<option value="TWEET">Tweet</option>
		 	<option value="RETWEET">Retweet</option>
		 	<option value="FAV">Fav</option>
		</select>

	<label for="aggregationType">Aggregation Type</label>
    <select name="aggregationType">
  			<option value="HOURLY">Hourly</option>
		 	<option value="WEEKLY">weekly</option>
		</select>

    <input type="submit" value="Submit">

  </form>
</div>
</body>
</html>
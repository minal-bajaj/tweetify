# tweetify
This application analyze tweet, retweet and fav trends for any user. It displays tweets' trends in form of graph charts.

Pre-requisites:
        Docker
        Maven

Docker image is available publically @ minalbajaj/tweetify. Use following command to run docker image, docker instance will automatically download image from docker hub.

docker run -d -p 80:8080 -p 443:8443 minalbajaj/tweetify

If you want to create image locally with different oauth credentials. Please follow below steps.<br>
        1. Unzip "TwitterAnalyzer.zip" and provide oauth credentials in "src/main/resources/twitter4j.properties" file.<br>
        2. Run createImage.sh script to create docker image. This step will create docker image with tag "tweetify".<br>
        3. Run docker image with following command - "docker run -d -p 80:8080 -p 443:8443 minalbajaj/tweetify"<br>

If all the above steps are exeuted successfully then application is accessible at localhost:80

Access http://localhost/Tweetify.jsp and provide following inputs to see graph charts -
        Twitter Id -
                (Valid twitter handle) - twitter id of user whose tweets' we want to analyze
        Tweets to Analyze -
                (Any integer) - maximum tweets to be considered for analysis. If value is 100 then last 100 tweets is used for analysis.
        Aggregation By -
                (Tweet/Retweet/Fav)
        Aggregation type -
                (Hourly/weekly)

Note: This application does not accept time window to work upon. I may add this support in future, if required.

# knowledge-feeder-hackathon
knowledge-feeder-hackathon

# Prerequisites
** To run the document scraper service, the webdriver should be passed as vm arguments when starting the springboot application ( as per your OS)
-Dwebdriver.chrome.driver="E:\\chromedriver_win32\\chromedriver.exe"

** Send the SendGrid Credentails in application.properties

# Execution steps

# Backend 
Start the spring boot application by providing the vm arguments as mentioned above
* APIs created *
- POST http://localhost:8080/api/user/register
- POST http://localhost:8080/api/document/createDocument
- POST http://localhost:8080/api/interest/create
- GET http://localhost:8080/api/interest/getAllInterests
- Few other verification APIs are created to send email and start document scraping process

* Scheduler jobs *
- Document scraper
- Weekly email

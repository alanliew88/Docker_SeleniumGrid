# Running selenium java using Docker + SeleniumGrid

First, point the remotewebdriver to the localhost:4444 and the VNC view to localhost:7777

Then, start a docker using 

` docker run -d -p 4444:4444 -p 7900:7900 --shm-size="2g" --name mymamatest seleniarm/standalone-chromium`

Then run the test using `mvn test`


OR 2nd Method is 

` docker-compose -f docker-compose.yml up  ` to run the docker 
